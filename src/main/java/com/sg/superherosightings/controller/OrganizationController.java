/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.service.OrganizationDataValidationException;
import com.sg.superherosightings.service.SuperHeroSightingsServiceLayer;
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
public class OrganizationController {
    
    private SuperHeroSightingsServiceLayer service;
    
    @Inject
    public OrganizationController(SuperHeroSightingsServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/displayOrganizationsPage", method = RequestMethod.GET)
    public String displayOrganizationsPage(Model model) {
        List<Organization> organizationList = service.getAllOrganizations();

        model.addAttribute("organizationList", organizationList);

        return "organizations";
    }
    
    @RequestMapping(value = "/displayCreateOrganizationForm", method = RequestMethod.GET)
    public String displayCreateOrganizationForm(HttpServletRequest request, Model model) {
        List<Location> locationList = service.getAllLocations();
        List<Hero> heroList = service.getAllHeroes();
        
        model.addAttribute("locationList", locationList);
        model.addAttribute("heroList", heroList);
        
        return "createOrganizationForm";
    }
    
    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrganization(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Organization organization = new Organization();
        organization.setName(request.getParameter("name"));
        organization.setDescription(request.getParameter("description"));
        organization.setPhone(request.getParameter("phone"));
        organization.setEmail(request.getParameter("email"));
        // Get and set location
        String locationIdParameter = request.getParameter("location");
        if (!locationIdParameter.isEmpty()) {
            int locationId = Integer.parseInt(locationIdParameter);
            Location location = service.getLocationById(locationId);
            organization.setLocation(location);
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
        organization.setHeroes(heroList);

        try {
            service.addOrganization(organization);
        } catch (OrganizationDataValidationException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:displayCreateOrganizationForm";
        }

        return "redirect:displayOrganizationsPage";
    }
    
    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);

        Organization organization = service.getOrganizationById(organizationId);

        model.addAttribute("organization", organization);

        return "organizationDetails";
    }
    
    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        
        service.deleteOrganization(organizationId);
        
        return "redirect:displayOrganizationsPage";
    }
    
    @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        Organization organization = service.getOrganizationById(organizationId);
        List<Location> locationList = service.getAllLocations();
        List<Hero> heroList = service.getAllHeroes();
        
        model.addAttribute("organization", organization);
        model.addAttribute("locationList", locationList);
        model.addAttribute("heroList", heroList); 
        
        return "editOrganizationForm";
    }
    
    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Organization organization = new Organization();
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        organization.setOrganizationId(organizationId);
        organization.setName(request.getParameter("name"));
        organization.setDescription(request.getParameter("description"));
        organization.setPhone(request.getParameter("phone"));
        organization.setEmail(request.getParameter("email"));
        // Get and set location
        String locationIdParameter = request.getParameter("location");
        if (!locationIdParameter.isEmpty()) {
            int locationId = Integer.parseInt(locationIdParameter);
            Location location = service.getLocationById(locationId);
            organization.setLocation(location);
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
        organization.setHeroes(heroList);
        
        try {
            service.updateOrganization(organization);
        } catch (OrganizationDataValidationException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:displayEditOrganizationForm?organizationId=" + organizationIdParameter;
        }

        return "redirect:displayOrganizationsPage";
    }
}
