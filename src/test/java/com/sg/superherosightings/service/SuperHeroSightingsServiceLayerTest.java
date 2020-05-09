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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author chris
 */
public class SuperHeroSightingsServiceLayerTest {
    
    private SuperHeroSightingsServiceLayer service;
    
    public SuperHeroSightingsServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("superHeroSightingsServiceLayer", SuperHeroSightingsServiceLayer.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addHero method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testAddHero() throws Exception {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerId(1);
        superpower.setName("Flight");
        
        Hero hero = new Hero();
        hero.setHeroId(1);
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);

        service.addHero(hero);
    }

    /**
     * Test of deleteHero method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testDeleteHero() throws Exception {
        service.deleteHero(3);
    }
    
    /**
     * Test of deleteHero method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testDeleteHeroOfOrganization() throws Exception {
        try {
            service.deleteHero(1);
            fail("Expected OrganizationHasHeroException was not thrown.");
        } catch (OrganizationHasHeroException e) {
            return;
        }
    }
    
    /**
     * Test of deleteHero method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testDeleteHeroOfSighting() throws Exception {
        try {
            service.deleteHero(2);
            fail("Expected SightingHasHeroException was not thrown.");
        } catch (SightingHasHeroException e) {
            return;
        }
    }

    /**
     * Test of updateHero method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testUpdateHero() throws Exception {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerId(1);
        superpower.setName("Flight");
        
        Hero hero = new Hero();
        hero.setHeroId(1);
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);

        service.updateHero(hero);
    }

    /**
     * Test of getHeroById method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testGetHeroById() {
        Hero hero = service.getHeroById(1);
        assertNotNull(hero);
        hero = service.getHeroById(3);
        assertNull(hero);
    }

    /**
     * Test of getAllHeroes method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testGetAllHeroes() {
        assertEquals(2, service.getAllHeroes().size());
    }

    /**
     * Test of addLocation method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testAddLocation() throws Exception {
        State state = new State();
        state.setName("New York");
        
        Location location = new Location();
        location.setName("Avengers Tower");
        location.setDescription("A high-rise building complex which is "
                + "the headquarters for the Avengers.");
        location.setStreetAddress("200 Park Avenue");
        location.setCity("New York");
        location.setState(state);
        location.setZipCode("10166");
        location.setLatitude(40.754188);
        location.setLongitude(-73.976210);

        service.addLocation(location);
    }

    /**
     * Test of deleteLocation method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testDeleteLocation() throws Exception {
        service.deleteLocation(3);
    }
    
    /**
     * Test of deleteHero method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testDeleteLocationOfOrganization() throws Exception {
        try {
            service.deleteLocation(1);
            fail("Expected OrganizationHasLocationException was not thrown.");
        } catch (OrganizationHasLocationException e) {
            return;
        }
    }
    
    /**
     * Test of deleteHero method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testDeleteLocationOfSighting() throws Exception {
        try {
            service.deleteLocation(2);
            fail("Expected SightingHasLocationException was not thrown.");
        } catch (SightingHasLocationException e) {
            return;
        }
    }

    /**
     * Test of updateLocation method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testUpdateLocation() throws Exception {
        State state = new State();
        state.setName("New York");
        
        Location location = new Location();
        location.setName("Avengers Tower");
        location.setDescription("A high-rise building complex which is "
                + "the headquarters for the Avengers.");
        location.setStreetAddress("200 Park Avenue");
        location.setCity("New York");
        location.setState(state);
        location.setZipCode("10166");
        location.setLatitude(40.754188);
        location.setLongitude(-73.976210);

        service.updateLocation(location);
    }

    /**
     * Test of getLocationById method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testGetLocationById() {
        Location location = service.getLocationById(1);
        assertNotNull(location);
        location = service.getLocationById(2);
        assertNull(location);
    }

    /**
     * Test of getAllLocations method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testGetAllLocations() {
        assertEquals(1, service.getAllLocations().size());
    }

    /**
     * Test of addOrganization method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testAddOrganization() throws Exception {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        Hero hero = new Hero();
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);
        
        State state = new State();
        state.setName("New York");

        Location location = new Location();
        location.setName("Avengers Tower");
        location.setDescription("A high-rise building complex which is "
                + "the headquarters for the Avengers.");
        location.setStreetAddress("200 Park Avenue");
        location.setCity("New York");
        location.setState(state);
        location.setZipCode("10166");
        location.setLatitude(40.754188);
        location.setLongitude(-73.976210);

        Organization organization = new Organization();
        organization.setName("The Avengers");
        organization.setDescription("Earth's Mightiest Heroes stand as the "
                + "planet's first line of defense against the most powerful "
                + "threats in the universe.");
        organization.setPhone("555-555-5555");
        organization.setEmail("info@avengers.com");
        organization.setLocation(location);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        organization.setHeroes(heroes);
        
        service.addOrganization(organization);
    }

    /**
     * Test of deleteOrganization method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testDeleteOrganization() {
        service.deleteOrganization(1);
    }

    /**
     * Test of updateOrganization method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testUpdateOrganization() throws Exception {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        Hero hero = new Hero();
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);
        
        State state = new State();
        state.setName("New York");

        Location location = new Location();
        location.setName("Avengers Tower");
        location.setDescription("A high-rise building complex which is "
                + "the headquarters for the Avengers.");
        location.setStreetAddress("200 Park Avenue");
        location.setCity("New York");
        location.setState(state);
        location.setZipCode("10166");
        location.setLatitude(40.754188);
        location.setLongitude(-73.976210);

        Organization organization = new Organization();
        organization.setName("The Avengers");
        organization.setDescription("Earth's Mightiest Heroes stand as the "
                + "planet's first line of defense against the most powerful "
                + "threats in the universe.");
        organization.setPhone("555-555-5555");
        organization.setEmail("info@avengers.com");
        organization.setLocation(location);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        organization.setHeroes(heroes);
        
        service.updateOrganization(organization);
    }

    /**
     * Test of getOrganizationById method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testGetOrganizationById() {
        Organization organization = service.getOrganizationById(1);
        assertNotNull(organization);
        organization = service.getOrganizationById(2);
        assertNull(organization);
    }

    /**
     * Test of getAllOrganizations method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testGetAllOrganizations() {
        assertEquals(1, service.getAllOrganizations().size());
    }

    /**
     * Test of addSighting method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testAddSighting() throws Exception {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        Hero hero = new Hero();
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);
        
        State state = new State();
        state.setName("New York");

        Location location = new Location();
        location.setName("Avengers Tower");
        location.setDescription("A high-rise building complex which is "
                + "the headquarters for the Avengers.");
        location.setStreetAddress("200 Park Avenue");
        location.setCity("New York");
        location.setState(state);
        location.setZipCode("10166");
        location.setLatitude(40.754188);
        location.setLongitude(-73.976210);
        
        Sighting sighting = new Sighting();
        String dateTimeString = "2020-02-24 12:15:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        sighting.setDateTime(dateTime);
        sighting.setLocation(location);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        sighting.setHeroes(heroes);
        
        service.addSighting(sighting);
    }

    /**
     * Test of deleteSighting method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testDeleteSighting() {
        service.deleteSighting(1);
    }

    /**
     * Test of updateSighting method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testUpdateSighting() throws Exception {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        Hero hero = new Hero();
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);
        
        State state = new State();
        state.setName("New York");

        Location location = new Location();
        location.setName("Avengers Tower");
        location.setDescription("A high-rise building complex which is "
                + "the headquarters for the Avengers.");
        location.setStreetAddress("200 Park Avenue");
        location.setCity("New York");
        location.setState(state);
        location.setZipCode("10166");
        location.setLatitude(40.754188);
        location.setLongitude(-73.976210);
        
        Sighting sighting = new Sighting();
        String dateTimeString = "2020-02-24 12:15:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        sighting.setDateTime(dateTime);
        sighting.setLocation(location);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        sighting.setHeroes(heroes);
        
        service.updateSighting(sighting);
    }

    /**
     * Test of getSightingById method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testGetSightingById() {
        Sighting sighting = service.getSightingById(1);
        assertNotNull(sighting);
        sighting = service.getSightingById(2);
        assertNull(sighting);
    }

    /**
     * Test of getLatestSightingsLimitByNum method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testGetLatestSightingsLimitByNum() {
        assertEquals(1, service.getLatestSightingsLimitByNum(1).size());
    }

    /**
     * Test of getAllSightings method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testGetAllSightings() {
        assertEquals(1, service.getAllSightings().size());
    }

    /**
     * Test of addState method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testAddState() {
        State state = new State();
        state.setName("New York");
        
        service.addState(state);
    }

    /**
     * Test of deleteState method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testDeleteState() {
        service.deleteState(1);
    }

    /**
     * Test of updateState method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testUpdateState() {
        State state = new State();
        state.setStateId(1);
        state.setName("New York");

        service.updateState(state);
    }

    /**
     * Test of getStateById method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testGetStateById() {
        State state = service.getStateById(1);
        assertNotNull(state);
        state = service.getStateById(2);
        assertNull(state);
    }

    /**
     * Test of getAllStates method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testGetAllStates() {
        assertEquals(1, service.getAllStates().size());
    }

    /**
     * Test of addSuperpower method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testAddSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        service.addSuperpower(superpower);
    }

    /**
     * Test of deleteSuperpower method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testDeleteSuperpower() throws Exception {
        service.deleteSuperpower(2);
    }
    
    /**
     * Test of deleteSuperpower method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testDeleteSuperpowerOfHero() throws Exception {
        try {
            service.deleteSuperpower(1);
            fail("Expected HeroHasSuperpowerException was not thrown.");
        } catch (HeroHasSuperpowerException e) {
            return;
        }
    }

    /**
     * Test of updateSuperpower method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testUpdateSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerId(1);
        superpower.setName("Flight");

        service.updateSuperpower(superpower);
    }

    /**
     * Test of getSuperpowerById method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testGetSuperpowerById() {
        Superpower superpower = service.getSuperpowerById(1);
        assertNotNull(superpower);
        superpower = service.getSuperpowerById(2);
        assertNull(superpower);
    }

    /**
     * Test of getAllSuperpowers method, of class SuperHeroSightingsServiceLayer.
     */
    @Test
    public void testGetAllSuperpowers() {
        assertEquals(1, service.getAllSuperpowers().size());
    }
}
