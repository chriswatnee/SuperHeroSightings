/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.State;
import com.sg.superherosightings.service.LocationDataValidationException;
import com.sg.superherosightings.service.OrganizationHasLocationException;
import com.sg.superherosightings.service.SightingHasLocationException;
import com.sg.superherosightings.service.SuperHeroSightingsServiceLayer;
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
public class LocationController {
    
    private SuperHeroSightingsServiceLayer service;
    
    @Inject
    public LocationController(SuperHeroSightingsServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/displayLocationsPage", method = RequestMethod.GET)
    public String displayLocationsPage(Model model) {
        List<Location> locationList = service.getAllLocations();

        model.addAttribute("locationList", locationList);

        return "locations";
    }
    
    @RequestMapping(value = "/displayCreateLocationForm", method = RequestMethod.GET)
    public String displayCreateLocationForm(HttpServletRequest request, Model model) {
        List<State> stateList = service.getAllStates();
        
        model.addAttribute("stateList", stateList);
        
        return "createLocationForm";
    }
    
    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Location location = new Location();
        location.setName(request.getParameter("name"));
        location.setDescription(request.getParameter("description"));
        location.setStreetAddress(request.getParameter("streetAddress"));
        location.setCity(request.getParameter("city"));
        // Get and set state
        String stateIdParameter = request.getParameter("state");
        if (!stateIdParameter.isEmpty()) {
            int stateId = Integer.parseInt(stateIdParameter);
            State state = service.getStateById(stateId);
            location.setState(state);
        }
        location.setZipCode(request.getParameter("zipCode"));
        // Get and set latitude
        String latitudeParameter = request.getParameter("latitude");
        if (!latitudeParameter.isEmpty()) {
            double latitude = Double.parseDouble(latitudeParameter);
            location.setLatitude(latitude);
        }
        // Get and set longitude
        String longitudeParameter = request.getParameter("longitude");
        if (!longitudeParameter.isEmpty()) {
            double longitude = Double.parseDouble(longitudeParameter);
            location.setLongitude(longitude);
        }
        
        try {
            service.addLocation(location);
        } catch (LocationDataValidationException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:displayCreateLocationForm";
        }

        return "redirect:displayLocationsPage";
    }
    
    @RequestMapping(value = "/displayLocationDetails", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);

        Location location = service.getLocationById(locationId);

        model.addAttribute("location", location);

        return "locationDetails";
    }
    
    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        
        try {
            service.deleteLocation(locationId);
        } catch (OrganizationHasLocationException | SightingHasLocationException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        
        return "redirect:displayLocationsPage";
    }
    
    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        Location location = service.getLocationById(locationId);
        List<State> stateList = service.getAllStates();
        
        model.addAttribute("location", location);
        model.addAttribute("stateList", stateList);
        
        return "editLocationForm";
    }
    
    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Location location = new Location();
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        location.setLocationId(locationId);
        location.setName(request.getParameter("name"));
        location.setDescription(request.getParameter("description"));
        location.setStreetAddress(request.getParameter("streetAddress"));
        location.setCity(request.getParameter("city"));
        // Get and set state
        String stateIdParameter = request.getParameter("state");
        if (!stateIdParameter.isEmpty()) {
            int stateId = Integer.parseInt(stateIdParameter);
            State state = service.getStateById(stateId);
            location.setState(state);
        }
        location.setZipCode(request.getParameter("zipCode"));
        // Get and set latitude
        String latitudeParameter = request.getParameter("latitude");
        if (!latitudeParameter.isEmpty()) {
            double latitude = Double.parseDouble(latitudeParameter);
            location.setLatitude(latitude);
        }
        // Get and set longitude
        String longitudeParameter = request.getParameter("longitude");
        if (!longitudeParameter.isEmpty()) {
            double longitude = Double.parseDouble(longitudeParameter);
            location.setLongitude(longitude);
        }
        
        try {
            service.updateLocation(location);
        } catch (LocationDataValidationException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:displayEditLocationForm?locationId=" + locationIdParameter;
        }

        return "redirect:displayLocationsPage";
    }
}
