/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.SuperHeroSightingsDao;
import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.State;
import com.sg.superherosightings.model.Superpower;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author chris
 */
public class SuperHeroSightingsServiceLayerImpl implements SuperHeroSightingsServiceLayer  {

    private SuperHeroSightingsDao dao;
    
    @Inject
    public SuperHeroSightingsServiceLayerImpl(SuperHeroSightingsDao dao) {
        this.dao = dao;
    }
    
    @Override
    public void addHero(Hero hero) throws HeroDataValidationException {

        validateHeroData(hero);
        
        dao.addHero(hero);
    }

    @Override
    public void deleteHero(int heroId) throws OrganizationHasHeroException, SightingHasHeroException {
        List<Organization> organizationList = dao.getOrganizationsByHeroId(heroId);
        List<Location> locationList = dao.getLocationsByHeroId(heroId);
        
        if (!organizationList.isEmpty()) {
            throw new OrganizationHasHeroException("Unable to delete because a organization has this hero.");
        }
        
        if (!locationList.isEmpty()) {
            throw new SightingHasHeroException("Unable to delete because a sighting has this hero.");
        }
        
        dao.deleteHero(heroId);
    }

    @Override
    public void updateHero(Hero hero) throws HeroDataValidationException {

        validateHeroData(hero);
        
        dao.updateHero(hero);
    }

    @Override
    public Hero getHeroById(int id) {
        return dao.getHeroById(id);
    }

    @Override
    public List<Hero> getAllHeroes() {
        return dao.getAllHeroes();
    }

    @Override
    public void addLocation(Location location) throws LocationDataValidationException {

        validateLocationData(location);
        
        dao.addLocation(location);
    }

    @Override
    public void deleteLocation(int locationId) throws OrganizationHasLocationException, SightingHasLocationException {
        List<Organization> organizationList = dao.getOrganizationsByLocationId(locationId);
        List<Sighting> sightingList = dao.getSightingsByLocationId(locationId);
        
        if (!organizationList.isEmpty()) {
            throw new OrganizationHasLocationException("Unable to delete because a organization has this location.");
        }
        
        if (!sightingList.isEmpty()) {
            throw new SightingHasLocationException("Unable to delete because a sighting has this location.");
        }
        
        dao.deleteLocation(locationId);
    }

    @Override
    public void updateLocation(Location location) throws LocationDataValidationException {

        validateLocationData(location);
        
        dao.updateLocation(location);
    }

    @Override
    public Location getLocationById(int id) {
        return dao.getLocationById(id);
    }

    @Override
    public List<Location> getAllLocations() {
        return dao.getAllLocations();
    }

    @Override
    public void addOrganization(Organization organization) throws OrganizationDataValidationException {

        validateOrganizationData(organization);
        
        dao.addOrganization(organization);
    }

    @Override
    public void deleteOrganization(int organizationId) {
        dao.deleteOrganization(organizationId);
    }

    @Override
    public void updateOrganization(Organization organization) throws OrganizationDataValidationException {

        validateOrganizationData(organization);
        
        dao.updateOrganization(organization);
    }

    @Override
    public Organization getOrganizationById(int id) {
        return dao.getOrganizationById(id);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return dao.getAllOrganizations();
    }

    @Override
    public void addSighting(Sighting sighting) throws SightingDataValidationException {

        validateSightingData(sighting);
        
        dao.addSighting(sighting);
    }

    @Override
    public void deleteSighting(int sightingId) {
        dao.deleteSighting(sightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) throws SightingDataValidationException {

        validateSightingData(sighting);
        
        dao.updateSighting(sighting);
    }

    @Override
    public Sighting getSightingById(int id) {
        return dao.getSightingById(id);
    }

    @Override
    public List<Sighting> getLatestSightingsLimitByNum(int num) {
        return dao.getLatestSightingsLimitByNum(num);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return dao.getAllSightings();
    }

    @Override
    public void addState(State state) {
        dao.addState(state);
    }

    @Override
    public void deleteState(int stateId) {
        dao.deleteState(stateId);
    }

    @Override
    public void updateState(State state) {
        dao.updateState(state);
    }

    @Override
    public State getStateById(int id) {
        return dao.getStateById(id);
    }

    @Override
    public List<State> getAllStates() {
        return dao.getAllStates();
    }

    @Override
    public void addSuperpower(Superpower superpower) {
        dao.addSuperpower(superpower);
    }

    @Override
    public void deleteSuperpower(int superpowerId) throws HeroHasSuperpowerException {
        List<Hero> heroList = dao.getHeroesBySuperpowerId(superpowerId);
        if (heroList.size() > 0) {
            throw new HeroHasSuperpowerException("Unable to delete because a hero/villain has this superpower.");
        }
        
        dao.deleteSuperpower(superpowerId);
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        dao.updateSuperpower(superpower);
    }

    @Override
    public Superpower getSuperpowerById(int id) {
        return dao.getSuperpowerById(id);
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        return dao.getAllSuperpowers();
    }
    
    private void validateHeroData(Hero hero) throws HeroDataValidationException {
        if (hero.getName() == null
                || hero.getName().trim().length() == 0
                || hero.getName().trim().length() > 50
                || hero.getDescription().trim().length() > 500
                || hero.getSuperpowers().isEmpty()) {
            
            throw new HeroDataValidationException("Error: Hero/Villain data not valid.");
        }
    }
    
    private void validateLocationData(Location location) throws LocationDataValidationException {
        if (location.getName() == null
                || location.getName().trim().length() == 0
                || location.getName().trim().length() > 50
                || location.getDescription().trim().length() > 100
                || location.getStreetAddress() == null
                || location.getStreetAddress().trim().length() == 0
                || location.getStreetAddress().trim().length() > 50
                || location.getCity() == null
                || location.getCity().trim().length() == 0
                || location.getCity().trim().length() > 50
                || location.getState() == null
                || location.getZipCode() == null
                || location.getZipCode().trim().length() == 0
                || location.getZipCode().trim().length() > 5
                || location.getLatitude() == 0.0d
                || location.getLongitude() == 0.0d) {
            
            throw new LocationDataValidationException("Error: Location data not valid.");
        }
    }
    
    private void validateOrganizationData(Organization organization) throws OrganizationDataValidationException {
        if (organization.getName() == null
                || organization.getName().trim().length() == 0
                || organization.getName().trim().length() > 50
                || organization.getDescription().trim().length() > 200
                || organization.getPhone() == null
                || organization.getPhone().trim().length() == 0
                || organization.getPhone().trim().length() > 20
                || organization.getEmail() == null
                || organization.getEmail().trim().length() == 0
                || organization.getEmail().trim().length() > 30
                || organization.getLocation() == null
                || organization.getHeroes().isEmpty()) {
            
            throw new OrganizationDataValidationException("Error: Organization data not valid.");
        }
    }
    
    private void validateSightingData(Sighting sighting) throws SightingDataValidationException {
        if (sighting.getDateTime() == null
                || sighting.getLocation() == null
                || sighting.getHeroes().isEmpty()) {
            
            throw new SightingDataValidationException("Error: Sighting data not valid.");
        }
    }
}
