/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.service.SuperHeroSightingsServiceLayer;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author chris
 */
@Controller
public class HomeController {
    
    private SuperHeroSightingsServiceLayer service;
    
    @Inject
    public HomeController(SuperHeroSightingsServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displaySightingsPage(Model model) {
        List<Sighting> sightingList = service.getLatestSightingsLimitByNum(10);

        model.addAttribute("sightingList", sightingList);
        model.addAttribute("localDateTimeFormat", DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a"));

        return "index";
    }
    
    @RequestMapping(value = "/sightings", method = RequestMethod.GET)
    @ResponseBody
    public List<Sighting> getAllContacts() {
        return service.getLatestSightingsLimitByNum(10);
    }
}
