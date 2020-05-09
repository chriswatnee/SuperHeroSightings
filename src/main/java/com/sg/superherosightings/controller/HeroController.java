/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Superpower;
import com.sg.superherosightings.service.HeroDataValidationException;
import com.sg.superherosightings.service.SightingHasHeroException;
import com.sg.superherosightings.service.OrganizationHasHeroException;
import com.sg.superherosightings.service.SuperHeroSightingsServiceLayer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author chris
 */
@Controller
public class HeroController {
    
    public static final String pictureFolder = "images/";
    private SuperHeroSightingsServiceLayer service;
    
    @Inject
    public HeroController(SuperHeroSightingsServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value="/displayHeroesPage", method = RequestMethod.GET)
    public String displayHeroesPage(Model model) {
        List<Hero> heroList = service.getAllHeroes();

        model.addAttribute("heroList", heroList);

        return "heroes";
    }
    
    @RequestMapping(value = "/displayCreateHeroForm", method = RequestMethod.GET)
    public String displayCreateHeroForm(HttpServletRequest request, Model model) {
        List<Superpower> superpowerList = service.getAllSuperpowers();

        model.addAttribute("superpowerList", superpowerList);
        
        return "createHeroForm";
    }
    
    @RequestMapping(value = "/createHero", method = RequestMethod.POST)
    public String createHero(HttpServletRequest request, 
            @RequestParam("picture") MultipartFile pictureFile, 
            RedirectAttributes redirectAttributes) {
        Hero hero = new Hero();
        hero.setName(request.getParameter("name"));
        hero.setDescription(request.getParameter("description"));
        // Picture
        if (!pictureFile.isEmpty()) {
            try {
                String savePath = request
                        .getSession()
                        .getServletContext()
                        .getRealPath("/") + pictureFolder;
                File dir = new File(savePath);
                // if pictureFolder directory is not there create it
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String filename = pictureFile.getOriginalFilename();

                pictureFile.transferTo(new File(savePath + filename));

                hero.setPictureFilename(pictureFolder + filename);
            } catch (Exception e) {
                // Add flash atttribute
                redirectAttributes.addFlashAttribute("message", "File upload failed: " + 
                        e.getMessage());
                return "redirect:displayCreateHeroForm";
            }
        }
        // Convert list of Superpower IDs to list of Superpowers
        String[] superpowersParameter = request.getParameterValues("superpowers");
        List<Superpower> superpowerList = new ArrayList<>();
        if (superpowersParameter != null && superpowersParameter.length > 0) {
            for (String currentSuperpower : superpowersParameter) {
                int superpowerId = Integer.parseInt(currentSuperpower);
                superpowerList.add(service.getSuperpowerById(superpowerId));
            }
        }
        hero.setSuperpowers(superpowerList);

        try {
            service.addHero(hero);
        } catch (HeroDataValidationException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:displayCreateHeroForm";
        }
        
        return "redirect:displayHeroesPage";
    }
    
    @RequestMapping(value = "/displayHeroDetails", method = RequestMethod.GET)
    public String displayHeroDetails(HttpServletRequest request, Model model) {
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);
        
        Hero hero = service.getHeroById(heroId);
        
        model.addAttribute("hero", hero);
        
        return "heroDetails";
    }
    
    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET)
    public String deleteHero(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);
        
        try {
            service.deleteHero(heroId);
        } catch (OrganizationHasHeroException | SightingHasHeroException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        
        return "redirect:displayHeroesPage";
    }
    
    @RequestMapping(value = "/displayEditHeroForm", method = RequestMethod.GET)
    public String displayEditHeroForm(HttpServletRequest request, Model model) {
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);
        Hero hero = service.getHeroById(heroId);
        List<Superpower> superpowerList = service.getAllSuperpowers();
        
        model.addAttribute("hero", hero);
        model.addAttribute("superpowerList", superpowerList);
        
        return "editHeroForm";
    }
    
    @RequestMapping(value = "/editHero", method = RequestMethod.POST)
    public String editHero(HttpServletRequest request, 
            @RequestParam("picture") MultipartFile pictureFile, 
            RedirectAttributes redirectAttributes) {
        Hero hero = new Hero();
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);
        hero.setHeroId(heroId);
        hero.setName(request.getParameter("name"));
        hero.setDescription(request.getParameter("description"));
        // Picture
        if (!pictureFile.isEmpty()) {
            try {
                String savePath = request
                        .getSession()
                        .getServletContext()
                        .getRealPath("/") + pictureFolder;
                File dir = new File(savePath);
                // if pictureFolder directory is not there create it
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String filename = pictureFile.getOriginalFilename();

                pictureFile.transferTo(new File(savePath + filename));

                hero.setPictureFilename(pictureFolder + filename);
            } catch (Exception e) {
                // Add flash atttribute
                redirectAttributes.addFlashAttribute("message", "File upload failed: " + 
                        e.getMessage());
                return "redirect:displayEditHeroForm";
            }
        }
        // Convert list of Superpower IDs to list of Superpowers
        String[] superpowersParameter = request.getParameterValues("superpowers");
        List<Superpower> superpowerList = new ArrayList<>();
        if (superpowersParameter != null && superpowersParameter.length > 0) {
            for (String currentSuperpower : superpowersParameter) {
                int superpowerId = Integer.parseInt(currentSuperpower);
                superpowerList.add(service.getSuperpowerById(superpowerId));
            }
        }
        hero.setSuperpowers(superpowerList);

        try {
            service.updateHero(hero);
        } catch (HeroDataValidationException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:displayEditHeroForm?heroId=" + heroIdParameter;
        }
        
        return "redirect:displayHeroesPage";
    }
}
