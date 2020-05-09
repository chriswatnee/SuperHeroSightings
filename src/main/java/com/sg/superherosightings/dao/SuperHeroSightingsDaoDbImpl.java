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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chris
 */
public class SuperHeroSightingsDaoDbImpl implements SuperHeroSightingsDao {
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Hero and HeroSuperpower
    private static final String SQL_INSERT_HERO
            = "INSERT INTO Hero (`Name`, `Description`, PictureFilename) "
            + "VALUES (?, ?, ?)";
    
    private static final String SQL_INSERT_HERO_SUPERPOWER
            = "INSERT INTO HeroSuperpower (HeroID, SuperpowerID) VALUES (?, ?)";

    private static final String SQL_DELETE_HERO
            = "DELETE FROM Hero WHERE HeroID = ?";
    
    private static final String SQL_DELETE_HERO_SUPERPOWER
            = "DELETE FROM HeroSuperpower WHERE HeroID = ?";

    private static final String SQL_UPDATE_HERO
            = "UPDATE Hero SET `Name` = ?, `Description` = ?, PictureFilename = ? "
            + "WHERE HeroID = ?";

    private static final String SQL_SELECT_HERO
            = "SELECT * FROM Hero WHERE HeroID = ?";
    
    private static final String SQL_SELECT_HEROES_BY_LOCATION_ID
            = "SELECT h.* "
            + "FROM Hero h "
            + "INNER JOIN SightingHero sh ON h.HeroID = sh.HeroID "
            + "INNER JOIN Sighting s ON sh.SightingID = s.SightingID "
            + "WHERE LocationID = ? "
            + "ORDER BY Name";
    
    private static final String SQL_SELECT_HEROES_BY_ORGANIZATION_ID
            = "SELECT h.* "
            + "FROM Hero h "
            + "INNER JOIN OrganizationHero oh ON h.HeroID = oh.HeroID "
            + "WHERE oh.OrganizationID = ? "
            + "ORDER BY Name";
    
    private static final String SQL_SELECT_HEROES_BY_SIGHTING_ID
            = "SELECT h.* "
            + "FROM Hero h "
            + "INNER JOIN SightingHero sh ON h.HeroID = sh.HeroID "
            + "WHERE sh.SightingID = ? "
            + "ORDER BY Name";
    
    private static final String SQL_SELECT_HEROES_BY_SUPERPOWER_ID
            = "SELECT h.* "
            + "FROM Hero h "
            + "INNER JOIN HeroSuperpower hs ON h.HeroID = hs.HeroID "
            + "WHERE hs.SuperpowerID = ? "
            + "ORDER BY Name";

    private static final String SQL_SELECT_ALL_HEROES
            = "SELECT * FROM Hero ORDER BY Name";
    
    // Location
    private static final String SQL_INSERT_LOCATION
            = "INSERT INTO Location (`Name`, `Description`, StreetAddress, "
            + "City, StateID, ZipCode, Latitude, Longitude) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "DELETE FROM Location WHERE LocationID = ?";

    private static final String SQL_UPDATE_LOCATION
            = "UPDATE Location SET `Name` = ?, `Description` = ?, "
            + "StreetAddress = ?, City = ?, StateID = ?, ZipCode = ?, "
            + "Latitude = ?, Longitude = ? "
            + "WHERE LocationID = ?";

    private static final String SQL_SELECT_LOCATION
            = "SELECT * FROM Location WHERE LocationID = ?";
    
    private static final String SQL_SELECT_LOCATION_BY_ORGANIZATION_ID
            = "SELECT l.* "
            + "FROM Location l "
            + "INNER JOIN Organization o ON l.LocationID = o.LocationID "
            + "WHERE OrganizationID = ?";
    
    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
            = "SELECT l.* "
            + "FROM Location l "
            + "INNER JOIN Sighting s ON l.LocationID = s.LocationID "
            + "WHERE SightingID = ?";
    
    private static final String SQL_SELECT_LOCATIONS_BY_HERO_ID
            = "SELECT l.* "
            + "FROM Sighting s "
            + "INNER JOIN Location l ON s.LocationID = l.LocationID "
            + "INNER JOIN SightingHero sh ON s.SightingID = sh.SightingID "
            + "WHERE HeroId = ? "
            + "ORDER BY Name";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "SELECT * FROM Location ORDER BY Name";
    
    // Organization and OrganizationHero
    private static final String SQL_INSERT_ORGANIZATION
            = "INSERT INTO `Organization` (`Name`, `Description`, Phone, "
            + "Email, LocationID) "
            + "VALUES (?, ?, ?, ?, ?)";
    
    private static final String SQL_INSERT_ORGANIZATION_HERO
            = "INSERT INTO OrganizationHero "
            + "(OrganizationID, HeroID) VALUES (?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "DELETE FROM `Organization` WHERE OrganizationID = ?";
    
    private static final String SQL_DELETE_ORGANIZATION_HERO
            = "DELETE FROM OrganizationHero WHERE OrganizationID = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "UPDATE `Organization` SET `Name` = ?, `Description` = ?, "
            + "Phone = ?, Email = ?, LocationID = ? "
            + "WHERE OrganizationID = ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "SELECT * FROM `Organization` WHERE OrganizationID = ?";
    
    private static final String SQL_SELECT_ORGANIZATIONS_BY_HERO_ID
            = "SELECT o.* "
            + "FROM `Organization` o "
            + "INNER JOIN OrganizationHero oh "
            + "ON o.OrganizationID = oh.OrganizationID "
            + "WHERE oh.HeroID = ? "
            + "ORDER BY Name";
    
    private static final String SQL_SELECT_ORGANIZATIONS_BY_LOCATION_ID
            = "SELECT * "
            + "FROM `Organization` "
            + "WHERE LocationID = ? "
            + "ORDER BY Name";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "SELECT * FROM `Organization` ORDER BY Name";
    
    // Sighting and SightingHero
    private static final String SQL_INSERT_SIGHTING
            = "INSERT INTO Sighting (`DateTime`, LocationID) "
            + "VALUES (?, ?)";
    
    private static final String SQL_INSERT_SIGHTING_HERO
            = "INSERT INTO SightingHero (SightingID, HeroID) VALUES (?, ?)";

    private static final String SQL_DELETE_SIGHTING
            = "DELETE FROM Sighting WHERE SightingID = ?";
    
    private static final String SQL_DELETE_SIGHTING_HERO
            = "DELETE FROM SightingHero WHERE SightingID = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "UPDATE Sighting SET `DateTime` = ?, LocationID = ? "
            + "WHERE SightingID = ?";

    private static final String SQL_SELECT_SIGHTING
            = "SELECT * FROM Sighting WHERE SightingID = ?";
    
    private static final String SQL_SELECT_SIGHTINGS_BY_LOCATION_ID
            = "SELECT * "
            + "FROM Sighting "
            + "WHERE LocationID = ? "
            + "ORDER BY `DateTime`";
    
    private static final String SQL_SELECT_SIGHTINGS_BY_DATE
            = "SELECT * FROM Sighting "
            + "WHERE `DateTime` BETWEEN ? AND ? "
            + "ORDER BY `DateTime`";

    private static final String SQL_SELECT_LATEST_SIGHTINGS_LIMIT_BY_NUM
            = "SELECT * FROM Sighting "
            + "ORDER BY `DateTime` DESC "
            + "LIMIT ?";
    
    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "SELECT * FROM Sighting ORDER BY `DateTime`";
    
    // State
    private static final String SQL_INSERT_STATE
            = "INSERT INTO State (`Name`) "
            + "VALUES (?)";

    private static final String SQL_DELETE_STATE
            = "DELETE FROM State WHERE StateID = ?";

    private static final String SQL_UPDATE_STATE
            = "UPDATE State SET `Name` = ? "
            + "WHERE StateID = ?";

    private static final String SQL_SELECT_STATE
            = "SELECT * FROM State WHERE StateID = ?";
    
    private static final String SQL_SELECT_STATE_BY_LOCATION_ID
            = "SELECT s.* "
            + "FROM State s "
            + "INNER JOIN Location l ON s.StateID = l.StateID "
            + "WHERE LocationID = ?";

    private static final String SQL_SELECT_ALL_STATES
            = "SELECT * FROM State ORDER BY Name";
    
    // Superpower
    private static final String SQL_INSERT_SUPERPOWER
            = "INSERT INTO Superpower (`Name`) "
            + "VALUES (?)";

    private static final String SQL_DELETE_SUPERPOWER
            = "DELETE FROM Superpower WHERE SuperpowerID = ?";

    private static final String SQL_UPDATE_SUPERPOWER
            = "UPDATE Superpower SET `Name` = ? "
            + "WHERE SuperpowerID = ?";

    private static final String SQL_SELECT_SUPERPOWER
            = "SELECT * FROM Superpower WHERE SuperpowerID = ?";
    
    private static final String SQL_SELECT_SUPERPOWERS_BY_HERO_ID
            = "SELECT s.* "
            + "FROM Superpower s "
            + "INNER JOIN HeroSuperpower hs "
            + "ON s.SuperpowerID = hs.SuperpowerID "
            + "WHERE hs.HeroID = ? "
            + "ORDER BY Name";

    private static final String SQL_SELECT_ALL_SUPERPOWERS
            = "SELECT * FROM Superpower ORDER BY Name";
    
    // Hero Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHero(Hero hero) {
        jdbcTemplate.update(SQL_INSERT_HERO,
                            hero.getName(),
                            hero.getDescription(),
                            hero.getPictureFilename());

        int heroId = 
                jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                                             Integer.class);

        hero.setHeroId(heroId);
        // now update the HeroSuperpower table
        insertHeroSuperpower(hero);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteHero(int heroId) {
        // delete HeroSuperpower relationships for this hero
        jdbcTemplate.update(SQL_DELETE_HERO_SUPERPOWER, heroId);
        // delete hero
        jdbcTemplate.update(SQL_DELETE_HERO, heroId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateHero(Hero hero) {
        // update hero table
        jdbcTemplate.update(SQL_UPDATE_HERO,
                            hero.getName(),
                            hero.getDescription(),
                            hero.getPictureFilename(),
                            hero.getHeroId());
        // delete HeroSuperpower relationships and then reset them
        jdbcTemplate.update(SQL_DELETE_HERO_SUPERPOWER, hero.getHeroId());
        insertHeroSuperpower(hero);
    }

    @Override
    public Hero getHeroById(int id) {
        try {
            // get the properties from the hero table
            Hero hero = 
                    jdbcTemplate.queryForObject(SQL_SELECT_HERO, 
                                                new HeroMapper(), 
                                                id);
            // get the superpowers for this hero and set list on the hero
            hero.setSuperpowers(findSuperpowersForHero(hero));
            return hero;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hero> getHeroesByLocationId(int locationId) {
        List<Hero> heroList = 
                jdbcTemplate.query(SQL_SELECT_HEROES_BY_LOCATION_ID, 
                                   new HeroMapper(),
                                   locationId);
        // set the superpowers for each hero
        return associateSuperpowersWithHeroes(heroList);
    }

    @Override
    public List<Hero> getHeroesByOrganizationId(int organizationId) {
        List<Hero> heroList = 
                jdbcTemplate.query(SQL_SELECT_HEROES_BY_ORGANIZATION_ID, 
                                   new HeroMapper(),
                                   organizationId);
        // set the superpowers for each hero
        return associateSuperpowersWithHeroes(heroList);
    }
    
    @Override
    public List<Hero> getHeroesBySuperpowerId(int superpowerId) {
        List<Hero> heroList = 
                jdbcTemplate.query(SQL_SELECT_HEROES_BY_SUPERPOWER_ID, 
                                   new HeroMapper(),
                                   superpowerId);
        // set the superpowers for each hero
        return associateSuperpowersWithHeroes(heroList);
    }

    @Override
    public List<Hero> getAllHeroes() {
        // get all the heroes
        List<Hero> heroList = 
                jdbcTemplate.query(SQL_SELECT_ALL_HEROES, 
                                   new HeroMapper());
        // set the superpowers for each hero
        return associateSuperpowersWithHeroes(heroList);      
    }

    // Location Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                            location.getName(),
                            location.getDescription(),
                            location.getStreetAddress(),
                            location.getCity(),
                            location.getState().getStateId(),
                            location.getZipCode(),
                            location.getLatitude(),
                            location.getLongitude());

        int locationId = 
                jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                                             Integer.class);

        location.setLocationId(locationId);
    }

    @Override
    public void deleteLocation(int locationId) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                            location.getName(),
                            location.getDescription(),
                            location.getStreetAddress(),
                            location.getCity(),
                            location.getState().getStateId(),
                            location.getZipCode(),
                            location.getLatitude(),
                            location.getLongitude(),
                            location.getLocationId());
    }

    @Override
    public Location getLocationById(int id) {
        try {
            // get the properties from the location table
            Location location = 
                    jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, 
                                                new LocationMapper(), 
                                                id);
            // get the state for this location
            location.setState(findStateForLocation(location));
            return location;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getLocationsByHeroId(int heroId) {
        // get all the locations
        List<Location> locationList = 
                jdbcTemplate.query(SQL_SELECT_LOCATIONS_BY_HERO_ID, 
                                   new LocationMapper(),
                                   heroId);
        // set the state for each location
        return associateStateWithLocations(locationList);
    }

    @Override
    public List<Location> getAllLocations() {
        // get all the locations
        List<Location> locationList = 
                jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, 
                                   new LocationMapper());
        // set the state for each location
        return associateStateWithLocations(locationList);
    }

    // Organization Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                            organization.getName(),
                            organization.getDescription(),
                            organization.getPhone(),
                            organization.getEmail(),
                            organization.getLocation().getLocationId());

        int organizationId = 
                jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                                             Integer.class);

        organization.setOrganizationId(organizationId);
        // now update the OrganizationHero table
        insertOrganizationHero(organization);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganization(int organizationId) {
        // delete OrganizationHero relationships for this organization
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_HERO, organizationId);
        // delete organization
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationId);
    }

    @Override
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                            organization.getName(),
                            organization.getDescription(),
                            organization.getPhone(),
                            organization.getEmail(),
                            organization.getLocation().getLocationId(),
                            organization.getOrganizationId());
        // delete OrganizationHero relationships and then reset them
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_HERO, organization.getOrganizationId());
        insertOrganizationHero(organization);
    }

    @Override
    public Organization getOrganizationById(int id) {
        try {
            // get the properties from the organization table
            Organization organization = 
                    jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, 
                                                new OrganizationMapper(), 
                                                id);
            // get the location for this organization
            organization.setLocation(findLocationForOrganization(organization));
            // get the heroes for this organization and set list on the organization
            organization.setHeroes(findHeroesForOrganization(organization));
            return organization;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getOrganizationsByHeroId(int heroId) {
        // get all the organizations
        List<Organization> organizationList = 
                jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_HERO_ID, 
                                   new OrganizationMapper(), 
                                   heroId);
        
        // set the location and list of heroes for each organization
        return associateLocationAndHeroesWithOrganizations(organizationList);
    }
    
    @Override
    public List<Organization> getOrganizationsByLocationId(int locationId) {
        // get all the organizations
        List<Organization> organizationList = 
                jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_LOCATION_ID, 
                                   new OrganizationMapper(), 
                                   locationId);
        
        // set the location and list of heroes for each organization
        return associateLocationAndHeroesWithOrganizations(organizationList);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        // get all the organizations
        List<Organization> organizationList = 
            jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, 
                               new OrganizationMapper());
        // set the location and list of heroes for each organization
        return associateLocationAndHeroesWithOrganizations(organizationList);
    }

    // Sighting Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                            sighting.getDateTime().toString(),
                            sighting.getLocation().getLocationId());

        int sightingId = 
                jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                                             Integer.class);

        sighting.setSightingId(sightingId);
        // now update the SightingHero table
        insertSightingHero(sighting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSighting(int sightingId) {
        // delete SightingHero relationships for this sighting
        jdbcTemplate.update(SQL_DELETE_SIGHTING_HERO, sightingId);
        // delete sighting
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                            sighting.getDateTime().toString(),
                            sighting.getLocation().getLocationId(),
                            sighting.getSightingId());
        // delete OrganizationHero relationships and then reset them
        jdbcTemplate.update(SQL_DELETE_SIGHTING_HERO, sighting.getSightingId());
        insertSightingHero(sighting);
    }

    @Override
    public Sighting getSightingById(int id) {
        try {
            // get the properties from the sighting table
            Sighting sighting = 
                    jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING, 
                                                new SightingMapper(), 
                                                id);
            // get the location for this sighting
            sighting.setLocation(findLocationForSighting(sighting));
            // get the heroes for this sighting and set list on the sighting
            sighting.setHeroes(findHeroesForSighting(sighting));
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public List<Sighting> getSightingsByLocationId(int locationId) {
        // get all the sightings
        List<Sighting> sightingList = 
                jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOCATION_ID, 
                                   new SightingMapper(),
                                   locationId);
        // set the location and list of heroes for each sighting
        return associateLocationAndHeroesWithSightings(sightingList);
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        // get all the sightings
        List<Sighting> sightingList = 
                jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE, 
                                   new SightingMapper(),
                                   date,
                                   date + " 23:59:59");
        // set the location and list of heroes for each sighting
        return associateLocationAndHeroesWithSightings(sightingList);
    }

    @Override
    public List<Sighting> getLatestSightingsLimitByNum(int num) {
        // get all the sightings
        List<Sighting> sightingList = 
                jdbcTemplate.query(SQL_SELECT_LATEST_SIGHTINGS_LIMIT_BY_NUM, 
                                   new SightingMapper(),
                                   num);
        // set the location and list of heroes for each sighting
        return associateLocationAndHeroesWithSightings(sightingList);
    }

    @Override
    public List<Sighting> getAllSightings() {
        // get all the sightings
        List<Sighting> sightingList = 
                jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, 
                                   new SightingMapper());
        // set the location and list of heroes for each sighting
        return associateLocationAndHeroesWithSightings(sightingList);
    }

    // State Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addState(State state) {
        jdbcTemplate.update(SQL_INSERT_STATE, state.getName());

        int stateId = 
                jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                                             Integer.class);

        state.setStateId(stateId);
    }

    @Override
    public void deleteState(int stateId) {
        jdbcTemplate.update(SQL_DELETE_STATE, stateId);
    }

    @Override
    public void updateState(State state) {
        jdbcTemplate.update(SQL_UPDATE_STATE,
                            state.getName(),
                            state.getStateId());
    }

    @Override
    public State getStateById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_STATE, 
                                               new StateMapper(), 
                                               id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<State> getAllStates() {
        return jdbcTemplate.query(SQL_SELECT_ALL_STATES, new StateMapper());
    }

    // Superpower Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperpower(Superpower superpower) {
        jdbcTemplate.update(SQL_INSERT_SUPERPOWER, superpower.getName());

        int stateId = 
                jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                                             Integer.class);

        superpower.setSuperpowerId(stateId);
    }

    @Override
    public void deleteSuperpower(int superpowerId) {
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER, superpowerId);
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        jdbcTemplate.update(SQL_UPDATE_SUPERPOWER,
                            superpower.getName(),
                            superpower.getSuperpowerId());
    }

    @Override
    public Superpower getSuperpowerById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPOWER, 
                                               new SuperpowerMapper(), 
                                               id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPERPOWERS, 
                                  new SuperpowerMapper());
    }
    
    // Hero Helper Methods
    private void insertHeroSuperpower(Hero hero) {
        final int heroId = hero.getHeroId();
        final List<Superpower> superpowers = hero.getSuperpowers();

        // Update the HeroSuperPower bridge table with an entry for 
        // each superpower of this hero
        for (Superpower currentSuperpower : superpowers) {
            jdbcTemplate.update(SQL_INSERT_HERO_SUPERPOWER, 
                                heroId, 
                                currentSuperpower.getSuperpowerId());
        }
    }
    
    private List<Superpower> findSuperpowersForHero(Hero hero) {
        return jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_HERO_ID, 
                                  new SuperpowerMapper(), 
                                  hero.getHeroId());
    }
    
    private List<Hero> associateSuperpowersWithHeroes(List<Hero> heroList) {
        for (Hero currentHero : heroList) {
            currentHero.setSuperpowers(findSuperpowersForHero(currentHero));
        }
        return heroList;
    }
    
    // Location Helper Methods
    private State findStateForLocation(Location location) {
        return jdbcTemplate.queryForObject(SQL_SELECT_STATE_BY_LOCATION_ID, 
                                           new StateMapper(), 
                                           location.getLocationId());
    }
    
    private List<Location> associateStateWithLocations(List<Location> locationList) {
        for (Location currentLocation : locationList) {
            currentLocation.setState(findStateForLocation(currentLocation));
        }
        return locationList;
    }
    
    // Organization Helper Methods
    private void insertOrganizationHero(Organization organization) {
        final int organizationId = organization.getOrganizationId();
        final List<Hero> heroes = organization.getHeroes();

        // Update the OrganizationHero bridge table with an entry for 
        // each hero of this organization
        for (Hero currentHero : heroes) {
            jdbcTemplate.update(SQL_INSERT_ORGANIZATION_HERO, 
                                organizationId, 
                                currentHero.getHeroId());
        }
    }
    
    private Location findLocationForOrganization(Organization organization) {
        // get the properties from the location table
        Location location = 
                jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_ORGANIZATION_ID, 
                                            new LocationMapper(), 
                                            organization.getOrganizationId());
        // get the state for this location
        location.setState(findStateForLocation(location));
        return location;
    }
    
    private List<Hero> findHeroesForOrganization(Organization organization) {
        // get all the heroes for organization
        List<Hero> heroList = 
                jdbcTemplate.query(SQL_SELECT_HEROES_BY_ORGANIZATION_ID, 
                                   new HeroMapper(),
                                   organization.getOrganizationId());
        // set the superpowers for each hero
        return associateSuperpowersWithHeroes(heroList);
    }
    
    private List<Organization> associateLocationAndHeroesWithOrganizations(List<Organization> organizationList) {
        for (Organization currentOrganization : organizationList) {
            // add the location to current organization
            currentOrganization.setLocation(findLocationForOrganization(currentOrganization));
            // add heroes to current organization
            currentOrganization.setHeroes(findHeroesForOrganization(currentOrganization));
        }
        return organizationList;
    }
    
    // Sighting Helper Methods
    private void insertSightingHero(Sighting sighting) {
        final int sightingId = sighting.getSightingId();
        final List<Hero> heroes = sighting.getHeroes();

        // Update the SightingHero bridge table with an entry for 
        // each hero of this sighting
        for (Hero currentHero : heroes) {
            jdbcTemplate.update(SQL_INSERT_SIGHTING_HERO, 
                                sightingId, 
                                currentHero.getHeroId());
        }
    }
    
    private Location findLocationForSighting(Sighting sighting) {
        // get the properties from the location table
        Location location = 
                jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID, 
                                            new LocationMapper(), 
                                            sighting.getSightingId());
        // get the state for this location
        location.setState(findStateForLocation(location));
        return location;
    }
    
    private List<Hero> findHeroesForSighting(Sighting sighting) {
        // get all the heroes for sighting
        List<Hero> heroList = 
                jdbcTemplate.query(SQL_SELECT_HEROES_BY_SIGHTING_ID, 
                                   new HeroMapper(),
                                   sighting.getSightingId());
        // set the superpowers for each hero
        return associateSuperpowersWithHeroes(heroList);
    }
    
    private List<Sighting> associateLocationAndHeroesWithSightings(List<Sighting> sightingList) {
        for (Sighting currentSighting : sightingList) {
            // add the location to current sighting
            currentSighting.setLocation(findLocationForSighting(currentSighting));
            // add heroes to current sighting
            currentSighting.setHeroes(findHeroesForSighting(currentSighting));
        }
        return sightingList;
    }
    
    // Mappers
    private static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {
            Hero hero = new Hero();
            hero.setHeroId(rs.getInt("HeroID"));
            hero.setName(rs.getString("Name"));
            hero.setDescription(rs.getString("Description"));
            hero.setPictureFilename(rs.getString("PictureFilename"));
            return hero;
        }
    }
    
    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            location.setLocationId(rs.getInt("LocationID"));
            location.setName(rs.getString("Name"));
            location.setDescription(rs.getString("Description"));
            location.setStreetAddress(rs.getString("StreetAddress"));
            location.setCity(rs.getString("City"));
            location.setZipCode(rs.getString("ZipCode"));
            location.setLatitude(rs.getDouble("Latitude"));
            location.setLongitude(rs.getDouble("Longitude"));
            return location;
        }
    }
    
    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationId(rs.getInt("OrganizationID"));
            organization.setName(rs.getString("Name"));
            organization.setDescription(rs.getString("Description"));
            organization.setPhone(rs.getString("Phone"));
            organization.setEmail(rs.getString("Email"));
            return organization;
        }
    }
    
    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("SightingID"));
            sighting.setDateTime(rs.getTimestamp("DateTime").toLocalDateTime());
            return sighting;
        }
    }
    
    private static final class StateMapper implements RowMapper<State> {

        @Override
        public State mapRow(ResultSet rs, int i) throws SQLException {
            State state = new State();
            state.setStateId(rs.getInt("StateID"));
            state.setName(rs.getString("Name"));
            return state;
        }
    }
    
    private static final class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int i) throws SQLException {
            Superpower superpower = new Superpower();
            superpower.setSuperpowerId(rs.getInt("SuperpowerID"));
            superpower.setName(rs.getString("Name"));
            return superpower;
        }
    }
}
