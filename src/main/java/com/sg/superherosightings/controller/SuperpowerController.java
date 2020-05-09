/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Superpower;
import com.sg.superherosightings.service.HeroHasSuperpowerException;
import com.sg.superherosightings.service.SuperHeroSightingsServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author chris
 */
@Controller
public class SuperpowerController {
    
    private SuperHeroSightingsServiceLayer service;
    
    @Inject
    public SuperpowerController(SuperHeroSightingsServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/displaySuperpowersPage", method = RequestMethod.GET)
    public String displaySuperpowersPage(HttpServletRequest request, Model model) {
        List<Superpower> superpowerList = service.getAllSuperpowers();

        model.addAttribute("superpowerList", superpowerList);
        model.addAttribute("superpower", new Superpower());

        return "superpowers";
    }
    
    @RequestMapping(value = "/createSuperpower", method = RequestMethod.POST)
    public String createSuperpower(@Valid @ModelAttribute("superpower") Superpower superpower, BindingResult result, Model model) {
  
        if (result.hasErrors()) {
            List<Superpower> superpowerList = service.getAllSuperpowers();

            model.addAttribute("superpowerList", superpowerList);

            return "superpowers";
        }

        service.addSuperpower(superpower);

        return "redirect:displaySuperpowersPage";
    }
    
    @RequestMapping(value = "/deleteSuperpower", method = RequestMethod.GET)
    public String deleteSuperpower(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String superpowerIdParameter = request.getParameter("superpowerId");
        int superpowerId = Integer.parseInt(superpowerIdParameter);
        
        try {
            service.deleteSuperpower(superpowerId);
        } catch (HeroHasSuperpowerException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        
        return "redirect:displaySuperpowersPage";
    }
    
    @RequestMapping(value = "/displayEditSuperpowerForm", method = RequestMethod.GET)
    public String displayEditSuperpowerForm(HttpServletRequest request, Model model) {
        String superpowerIdParameter = request.getParameter("superpowerId");
        int superpowerId = Integer.parseInt(superpowerIdParameter);
        Superpower superpower = service.getSuperpowerById(superpowerId);
        
        model.addAttribute("superpower", superpower);
        
        return "editSuperpowerForm";
    }
    
    @RequestMapping(value = "/editSuperpower", method = RequestMethod.POST)
    public String editSuperpower(@Valid @ModelAttribute("superpower") Superpower superpower, BindingResult result) {
        
        if (result.hasErrors()) {
            return "editSuperpowerForm";
        }

        service.updateSuperpower(superpower);

        return "redirect:displaySuperpowersPage";
    }
}
