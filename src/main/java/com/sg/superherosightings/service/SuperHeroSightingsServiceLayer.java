/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.State;
import com.sg.superherosightings.model.Superpower;
import java.util.List;

/**
 *
 * @author chris
 */
public interface SuperHeroSightingsServiceLayer {
    
    public void addHero(Hero hero) throws HeroDataValidationException;
    
    public void deleteHero(int heroId) throws OrganizationHasHeroException, SightingHasHeroException;
    
    public void updateHero(Hero hero) throws HeroDataValidationException;
    
    public Hero getHeroById(int id);
    
    public List<Hero> getAllHeroes();
    
    public void addLocation(Location location) throws LocationDataValidationException;
    
    public void deleteLocation(int locationId) throws OrganizationHasLocationException, SightingHasLocationException;
    
    public void updateLocation(Location location) throws LocationDataValidationException;
    
    public Location getLocationById(int id);
    
    public List<Location> getAllLocations();
    
    public void addOrganization(Organization organization) throws OrganizationDataValidationException;
    
    public void deleteOrganization(int organizationId);
    
    public void updateOrganization(Organization organization) throws OrganizationDataValidationException;
    
    public Organization getOrganizationById(int id);
    
    public List<Organization> getAllOrganizations();
    
    public void addSighting(Sighting sighting) throws SightingDataValidationException;
    
    public void deleteSighting(int sightingId);
    
    public void updateSighting(Sighting sighting) throws SightingDataValidationException;
    
    public Sighting getSightingById(int id);
    
    public List<Sighting> getLatestSightingsLimitByNum(int num); 
    
    public List<Sighting> getAllSightings();
    
    public void addState(State state);
    
    public void deleteState(int stateId);
    
    public void updateState(State state);
    
    public State getStateById(int id);
    
    public List<State> getAllStates();
    
    public void addSuperpower(Superpower superpower);
    
    public void deleteSuperpower(int superpowerId) throws HeroHasSuperpowerException;
    
    public void updateSuperpower(Superpower superpower);
    
    public Superpower getSuperpowerById(int id);
    
    public List<Superpower> getAllSuperpowers();
}
