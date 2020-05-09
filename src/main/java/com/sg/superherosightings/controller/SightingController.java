/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.service.SightingDataValidationException;
import com.sg.superherosightings.service.SuperHeroSightingsServiceLayer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author chris
 */
@Controller
public class SightingController {
    
    private SuperHeroSightingsServiceLayer service;
    
    @Inject
    public SightingController(SuperHeroSightingsServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/displaySightingsPage", method = RequestMethod.GET)
    public String displaySightingsPage(Model model) {
        List<Sighting> sightingList = service.getAllSightings();

        model.addAttribute("sightingList", sightingList);
        model.addAttribute("localDateTimeFormat", DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a"));

        return "sightings";
    }
    
    @RequestMapping(value = "/displayCreateSightingForm", method = RequestMethod.GET)
    public String displayCreateSightingForm(HttpServletRequest request, Model model) {
        List<Location> locationList = service.getAllLocations();
        List<Hero> heroList = service.getAllHeroes();
        
        model.addAttribute("locationList", locationList);
        model.addAttribute("heroList", heroList);
        
        return "createSightingForm";
    }
    
    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Sighting sighting = new Sighting();
        // Get and set dateTime
        String dateTimeParameter = request.getParameter("dateTime");
        if (!dateTimeParameter.isEmpty()) {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeParameter);
            sighting.setDateTime(dateTime);
        }
        // Get and set location
        String locationIdParameter = request.getParameter("location");
        if (!locationIdParameter.isEmpty()) {
            int locationId = Integer.parseInt(locationIdParameter);
            Location location = service.getLocationById(locationId);
            sighting.setLocation(location);
        }
        String[] heroesParameter = request.getParameterValues("heroes");
        // Convert list of Hero IDs to list of Heroes
        List<Hero> heroList = new ArrayList<>();
        if (heroesParameter != null && heroesParameter.length > 0) {
            for (String currentHero : heroesParameter) {
                int heroId = Integer.parseInt(currentHero);
                heroList.add(service.getHeroById(heroId));
            }
        }
        sighting.setHeroes(heroList);

        try {
            service.addSighting(sighting);
        } catch (SightingDataValidationException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:displayCreateSightingForm";
        }

        return "redirect:displaySightingsPage";
    }
    
    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);

        Sighting sighting = service.getSightingById(sightingId);

        model.addAttribute("sighting", sighting);
        model.addAttribute("localDateTimeFormat", DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a"));

        return "sightingDetails";
    }
    
    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        
        service.deleteSighting(sightingId);
        
        return "redirect:displaySightingsPage";
    }
    
    @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        Sighting sighting = service.getSightingById(sightingId);
        List<Location> locationList = service.getAllLocations();
        List<Hero> heroList = service.getAllHeroes();
        
        model.addAttribute("sighting", sighting);
        model.addAttribute("locationList", locationList);
        model.addAttribute("heroList", heroList); 
        
        return "editSightingForm";
    }
    
    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Sighting sighting = new Sighting();
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        sighting.setSightingId(sightingId);
        // Get and set dateTime
        String dateTimeParameter = request.getParameter("dateTime");
        if (!dateTimeParameter.isEmpty()) {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeParameter);
            sighting.setDateTime(dateTime);
        }
        // Get and set location
        String locationIdParameter = request.getParameter("location");
        if (!locationIdParameter.isEmpty()) {
            int locationId = Integer.parseInt(locationIdParameter);
            Location location = service.getLocationById(locationId);
            sighting.setLocation(location);
        }
        String[] heroesParameter = request.getParameterValues("heroes");
        // Convert list of Hero IDs to list of Heroes
        List<Hero> heroList = new ArrayList<>();
        if (heroesParameter != null && heroesParameter.length > 0) {
            for (String currentHero : heroesParameter) {
                int heroId = Integer.parseInt(currentHero);
                heroList.add(service.getHeroById(heroId));
            }
        }
        sighting.setHeroes(heroList);
        
        try {
            service.updateSighting(sighting);
        } catch (SightingDataValidationException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:displayEditSightingForm?sightingId=" + sightingIdParameter;
        }

        return "redirect:displaySightingsPage";
    }
}
