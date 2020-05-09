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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author chris
 */
public class SuperHeroSightingsDaoStubImpl implements SuperHeroSightingsDao {
    
    Hero hero1;
    Hero hero2;
    List<Hero> heroList = new ArrayList<>();
    List<Hero> organizationHeroList = new ArrayList<>();
    List<Hero> locationHeroList = new ArrayList<>();
    Location location1;
    Location location2;
    List<Location> locationList = new ArrayList<>();
    Organization onlyOrganization;
    List<Organization> organizationList = new ArrayList<>();
    Sighting onlySighting;
    List<Sighting> sightingList = new ArrayList<>();
    State onlyState;
    List<State> stateList = new ArrayList<>();
    Superpower onlySuperpower;
    List<Superpower> superpowerList = new ArrayList<>();
    
    public SuperHeroSightingsDaoStubImpl() {
        onlySuperpower = new Superpower();
        onlySuperpower.setSuperpowerId(1);
        onlySuperpower.setName("Flight");
        
        superpowerList.add(onlySuperpower);
        
        hero1 = new Hero();
        hero1.setHeroId(1);
        hero1.setName("Iron Man");
        hero1.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero1.setSuperpowers(superpowerList);
        
        heroList.add(hero1);
        organizationHeroList.add(hero1);
        
        hero2 = new Hero();
        hero2.setHeroId(2);
        hero2.setName("Captain America");
        hero2.setDescription("America's World War II Super-Soldier continues "
                + "his fight in the present as an Avenger and untiring "
                + "sentinel of liberty.");
        hero2.setSuperpowers(superpowerList);
        
        heroList.add(hero2);
        locationHeroList.add(hero2);
        
        onlyState = new State();
        onlyState.setStateId(1);
        onlyState.setName("New York");
        
        stateList.add(onlyState);
        
        location1 = new Location();
        location1.setLocationId(1);
        location1.setName("Avengers Tower");
        location1.setDescription("A high-rise building complex which is "
                + "the headquarters for the Avengers.");
        location1.setStreetAddress("200 Park Avenue");
        location1.setCity("New York");
        location1.setState(onlyState);
        location1.setZipCode("10166");
        location1.setLatitude(40.754188);
        location1.setLongitude(-73.976210);
        
        location2 = new Location();
        location2.setLocationId(2);
        location2.setName("X-Mansion");
        location2.setDescription("It serves as the base of operations and "
                + "training site of the X-Men.");
        location2.setStreetAddress("1407 Graymalkin Lane");
        location2.setCity("North Salem");
        location2.setState(onlyState);
        location2.setZipCode("10560");
        location2.setLatitude(41.335036);
        location2.setLongitude(-73.571905);
        
        locationList.add(location1);
        
        onlyOrganization = new Organization();
        onlyOrganization.setOrganizationId(1);
        onlyOrganization.setName("The Avengers");
        onlyOrganization.setDescription("Earth's Mightiest Heroes stand as the "
                + "planet's first line of defense against the most powerful "
                + "threats in the universe.");
        onlyOrganization.setPhone("555-555-5555");
        onlyOrganization.setEmail("info@avengers.com");
        onlyOrganization.setLocation(location1);
        onlyOrganization.setHeroes(organizationHeroList);
        
        organizationList.add(onlyOrganization);
        
        onlySighting = new Sighting();
        onlySighting.setSightingId(1);
        String dateTimeString = "2020-02-24 12:15:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        onlySighting.setDateTime(dateTime);
        onlySighting.setLocation(location2);
        onlySighting.setHeroes(locationHeroList);
        
        sightingList.add(onlySighting);
    }

    @Override
    public void addHero(Hero hero) {
        // do nothing...
    }

    @Override
    public void deleteHero(int heroId) {
        // do nothing...
    }

    @Override
    public void updateHero(Hero hero) {
        // do nothing...
    }

    @Override
    public Hero getHeroById(int id) {
        if (id == hero1.getHeroId()) {
            return hero1;
        } else {
            return null;
        }
    }

    @Override
    public List<Hero> getHeroesByLocationId(int locationId) {
        if (locationId == location1.getLocationId()) {
            return heroList;
        } else {
            return null;
        }
    }

    @Override
    public List<Hero> getHeroesByOrganizationId(int organizationId) {
        if (organizationId == onlyOrganization.getOrganizationId()) {
            return heroList;
        } else {
            return null;
        }
    }

    @Override
    public List<Hero> getHeroesBySuperpowerId(int superpowerId) {
        if (superpowerId == onlySuperpower.getSuperpowerId()) {
            return heroList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Hero> getAllHeroes() {
        return heroList;
    }

    @Override
    public void addLocation(Location location) {
        // do nothing...
    }

    @Override
    public void deleteLocation(int locationId) {
        // do nothing...
    }

    @Override
    public void updateLocation(Location location) {
        // do nothing...
    }

    @Override
    public Location getLocationById(int id) {
        if (id == location1.getLocationId()) {
            return location1;
        } else {
            return null;
        }
    }

    @Override
    public List<Location> getLocationsByHeroId(int heroId) {
        if (heroId == hero2.getHeroId()) {
            return locationList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Location> getAllLocations() {
        return locationList;
    }

    @Override
    public void addOrganization(Organization organization) {
        // do nothing...
    }

    @Override
    public void deleteOrganization(int organizationId) {
        // do nothing...
    }

    @Override
    public void updateOrganization(Organization organization) {
        // do nothing...
    }

    @Override
    public Organization getOrganizationById(int id) {
        if (id == onlyOrganization.getOrganizationId()) {
            return onlyOrganization;
        } else {
            return null;
        }
    }

    @Override
    public List<Organization> getOrganizationsByHeroId(int heroId) {
        if (heroId == hero1.getHeroId()) {
            return organizationList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Organization> getOrganizationsByLocationId(int locationId) {
        if (locationId == location1.getLocationId()) {
            return organizationList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationList;
    }

    @Override
    public void addSighting(Sighting sighting) {
        // do nothing...
    }

    @Override
    public void deleteSighting(int sightingId) {
        // do nothing...
    }

    @Override
    public void updateSighting(Sighting sighting) {
        // do nothing...
    }

    @Override
    public Sighting getSightingById(int id) {
        if (id == onlySighting.getSightingId()) {
            return onlySighting;
        } else {
            return null;
        }
    }

    @Override
    public List<Sighting> getSightingsByLocationId(int locationId) {
        if (locationId == location2.getLocationId()) {
            return sightingList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        if (date == onlySighting.getDateTime().toLocalDate()) {
            return sightingList;
        } else {
            return null;
        }
    }

    @Override
    public List<Sighting> getLatestSightingsLimitByNum(int num) {
            return sightingList;
    }

    @Override
    public List<Sighting> getAllSightings() {
        return sightingList;
    }

    @Override
    public void addState(State state) {
        // do nothing...
    }

    @Override
    public void deleteState(int stateId) {
        // do nothing...
    }

    @Override
    public void updateState(State state) {
        // do nothing...
    }

    @Override
    public State getStateById(int id) {
        if (id == onlyState.getStateId()) {
            return onlyState;
        } else {
            return null;
        }
    }

    @Override
    public List<State> getAllStates() {
        return stateList;
    }

    @Override
    public void addSuperpower(Superpower superpower) {
        // do nothing...
    }

    @Override
    public void deleteSuperpower(int superpowerId) {
        // do nothing...
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        // do nothing...
    }

    @Override
    public Superpower getSuperpowerById(int id) {
        if (id == onlySuperpower.getSuperpowerId()) {
            return onlySuperpower;
        } else {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        return superpowerList;
    }
    
}
