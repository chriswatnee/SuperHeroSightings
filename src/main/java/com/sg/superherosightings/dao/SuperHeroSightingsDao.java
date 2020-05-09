/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.State;
import com.sg.superherosightings.model.Superpower;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author chris
 */
public interface SuperHeroSightingsDao {
    
    public void addHero(Hero hero);
    
    public void deleteHero(int heroId);
    
    public void updateHero(Hero hero);
    
    public Hero getHeroById(int id);
    
    public List<Hero> getHeroesByLocationId(int locationId);
    
    public List<Hero> getHeroesByOrganizationId(int organizationId);
    
    public List<Hero> getHeroesBySuperpowerId(int superpowerId);
    
    public List<Hero> getAllHeroes();
    
    public void addLocation(Location location);
    
    public void deleteLocation(int locationId);
    
    public void updateLocation(Location location);
    
    public Location getLocationById(int id);
    
    public List<Location> getLocationsByHeroId(int heroId);
    
    public List<Location> getAllLocations();
    
    public void addOrganization(Organization organization);
    
    public void deleteOrganization(int organizationId);
    
    public void updateOrganization(Organization organization);
    
    public Organization getOrganizationById(int id);
    
    public List<Organization> getOrganizationsByHeroId(int heroId);
    
    public List<Organization> getOrganizationsByLocationId(int locationId);
    
    public List<Organization> getAllOrganizations();
    
    public void addSighting(Sighting sighting);
    
    public void deleteSighting(int sightingId);
    
    public void updateSighting(Sighting sighting);
    
    public Sighting getSightingById(int id);
    
    public List<Sighting> getSightingsByLocationId(int locationId);
    
    public List<Sighting> getSightingsByDate(LocalDate date);
    
    public List<Sighting> getLatestSightingsLimitByNum(int num);
    
    public List<Sighting> getAllSightings();
    
    public void addState(State state);
    
    public void deleteState(int stateId);
    
    public void updateState(State state);
    
    public State getStateById(int id);
    
    public List<State> getAllStates();
    
    public void addSuperpower(Superpower superpower);
    
    public void deleteSuperpower(int superpowerId);
    
    public void updateSuperpower(Superpower superpower);
    
    public Superpower getSuperpowerById(int id);
    
    public List<Superpower> getAllSuperpowers();
}
