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
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author chris
 */
public class SuperHeroSightingsDaoTest {
    
    SuperHeroSightingsDao dao;
    
    public SuperHeroSightingsDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("superHeroSightingsDao", SuperHeroSightingsDao.class);
        
        // remove all of the sightings
        List<Sighting> sightings = dao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            dao.deleteSighting(currentSighting.getSightingId());
        }
        
        // remove all of the organizations
        List<Organization> organizations = dao.getAllOrganizations();
        for (Organization currentOrganization : organizations) {
            dao.deleteOrganization(currentOrganization.getOrganizationId());
        }
        
        // remove all of the locations
        List<Location> locations = dao.getAllLocations();
        for (Location currentLocation : locations) {
            dao.deleteLocation(currentLocation.getLocationId());
        }
        
        // remove all of the states
        List<State> states = dao.getAllStates();
        for (State currentState : states) {
            dao.deleteState(currentState.getStateId());
        }
        
        // remove all of the heroes
        List<Hero> heroes = dao.getAllHeroes();
        for (Hero currentHero : heroes) {
            dao.deleteHero(currentHero.getHeroId());
        }
        
        // remove all of the superpowers
        List<Superpower> superpowers = dao.getAllSuperpowers();
        for (Superpower currentSuperpower : superpowers) {
            dao.deleteSuperpower(currentSuperpower.getSuperpowerId());
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetHero() {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        dao.addSuperpower(superpower);
        
        Hero hero = new Hero();
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);

        dao.addHero(hero);

        Hero fromDao = dao.getHeroById(hero.getHeroId());
        assertEquals(hero, fromDao);
    }

    @Test
    public void testDeleteHero() {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        dao.addSuperpower(superpower);
        
        Hero hero = new Hero();
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);

        dao.addHero(hero);

        Hero fromDao = dao.getHeroById(hero.getHeroId());
        assertEquals(hero, fromDao);
        dao.deleteHero(hero.getHeroId());
        assertNull(dao.getHeroById(hero.getHeroId()));
    }

    @Test
    public void testUpdateHero() {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        dao.addSuperpower(superpower);
        
        Hero hero = new Hero();
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);

        dao.addHero(hero);

        hero.setName("Tony Stark");

        dao.updateHero(hero);

        Hero fromDao = dao.getHeroById(hero.getHeroId());
        assertEquals(hero, fromDao);
    }

    @Test
    public void testGetHeroesByLocationId() {
        Superpower superpower1 = new Superpower();
        superpower1.setName("Flight");
        
        dao.addSuperpower(superpower1);
        
        Hero hero1 = new Hero();
        hero1.setName("Iron Man");
        hero1.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero1.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers1 = new ArrayList<>();
        superpowers1.add(superpower1);
        hero1.setSuperpowers(superpowers1);

        dao.addHero(hero1);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Strength");
        
        dao.addSuperpower(superpower2);
        
        Hero hero2 = new Hero();
        hero2.setName("Captain America");
        hero2.setDescription("America's World War II Super-Soldier continues "
                + "his fight in the present as an Avenger and "
                + "untiring sentinel of liberty.");
        hero2.setPictureFilename("captain-america.jpg");
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        hero2.setSuperpowers(superpowers2);

        dao.addHero(hero2);
        
        State state = new State();
        state.setName("New York");

        dao.addState(state);

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

        dao.addLocation(location);
        
        Sighting sighting = new Sighting();
        String dateTimeString = "2020-02-24 12:15:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        sighting.setDateTime(dateTime);
        sighting.setLocation(location);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero1);
        heroes.add(hero2);
        sighting.setHeroes(heroes);
        
        dao.addSighting(sighting);
        
        assertEquals(2, dao.getHeroesByLocationId(location.getLocationId()).size());
    }

    @Test
    public void testGetHeroesByOrganizationId() {
        Superpower superpower1 = new Superpower();
        superpower1.setName("Flight");
        
        dao.addSuperpower(superpower1);
        
        Hero hero1 = new Hero();
        hero1.setName("Iron Man");
        hero1.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero1.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers1 = new ArrayList<>();
        superpowers1.add(superpower1);
        hero1.setSuperpowers(superpowers1);

        dao.addHero(hero1);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Strength");
        
        dao.addSuperpower(superpower2);
        
        Hero hero2 = new Hero();
        hero2.setName("Captain America");
        hero2.setDescription("America's World War II Super-Soldier continues "
                + "his fight in the present as an Avenger and "
                + "untiring sentinel of liberty.");
        hero2.setPictureFilename("captain-america.jpg");
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        hero2.setSuperpowers(superpowers2);

        dao.addHero(hero2);
        
        State state = new State();
        state.setName("New York");

        dao.addState(state);

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

        dao.addLocation(location);

        Organization organization = new Organization();
        organization.setName("The Avengers");
        organization.setDescription("Earth's Mightiest Heroes stand as the "
                + "planet's first line of defense against the most powerful "
                + "threats in the universe.");
        organization.setPhone("555-555-5555");
        organization.setEmail("info@avengers.com");
        organization.setLocation(location);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero1);
        heroes.add(hero2);
        organization.setHeroes(heroes);
        
        dao.addOrganization(organization);
        
        assertEquals(2, dao.getHeroesByOrganizationId(organization.getOrganizationId()).size());
    }
    
    @Test
    public void testGetHeroesBySuperpowerId() {
        Superpower superpower1 = new Superpower();
        superpower1.setName("Flight");
        
        dao.addSuperpower(superpower1);
        
        Hero hero1 = new Hero();
        hero1.setName("Iron Man");
        hero1.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero1.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers1 = new ArrayList<>();
        superpowers1.add(superpower1);
        hero1.setSuperpowers(superpowers1);

        dao.addHero(hero1);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Strength");
        
        dao.addSuperpower(superpower2);
        
        Hero hero2 = new Hero();
        hero2.setName("Captain America");
        hero2.setDescription("America's World War II Super-Soldier continues "
                + "his fight in the present as an Avenger and "
                + "untiring sentinel of liberty.");
        hero2.setPictureFilename("captain-america.jpg");
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        hero2.setSuperpowers(superpowers2);

        dao.addHero(hero2);
        
        assertEquals(1, dao.getHeroesBySuperpowerId(superpower1.getSuperpowerId()).size());
    }

    @Test
    public void testGetAllHeroes() {
        Superpower superpower1 = new Superpower();
        superpower1.setName("Flight");
        
        dao.addSuperpower(superpower1);
        
        Hero hero1 = new Hero();
        hero1.setName("Iron Man");
        hero1.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero1.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers1 = new ArrayList<>();
        superpowers1.add(superpower1);
        hero1.setSuperpowers(superpowers1);

        dao.addHero(hero1);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Strength");
        
        dao.addSuperpower(superpower2);
        
        Hero hero2 = new Hero();
        hero2.setName("Captain America");
        hero2.setDescription("America's World War II Super-Soldier continues "
                + "his fight in the present as an Avenger and "
                + "untiring sentinel of liberty.");
        hero2.setPictureFilename("captain-america.jpg");
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        hero2.setSuperpowers(superpowers2);

        dao.addHero(hero2);
        
        assertEquals(2, dao.getAllHeroes().size());
    }

    @Test
    public void testAddGetLocation() {
        State state = new State();
        state.setName("New York");

        dao.addState(state);

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

        dao.addLocation(location);

        Location fromDao = dao.getLocationById(location.getLocationId());
        assertEquals(location, fromDao);
    }

    @Test
    public void testDeleteLocation() {
        State state = new State();
        state.setName("New York");

        dao.addState(state);

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

        dao.addLocation(location);

        Location fromDao = dao.getLocationById(location.getLocationId());
        assertEquals(location, fromDao);
        dao.deleteLocation(location.getLocationId());
        assertNull(dao.getLocationById(location.getLocationId()));
    }

    @Test
    public void testUpdateLocation() {
        State state = new State();
        state.setName("New York");

        dao.addState(state);

        Location location = new Location();
        location.setName("Stark Tower");
        location.setDescription("A high-rise building complex which is "
                + "the headquarters for the Avengers.");
        location.setStreetAddress("200 Park Avenue");
        location.setCity("New York");
        location.setState(state);
        location.setZipCode("10166");
        location.setLatitude(40.754188);
        location.setLongitude(-73.976210);

        dao.addLocation(location);

        location.setName("Avengers Tower");

        dao.updateLocation(location);

        Location fromDao = dao.getLocationById(location.getLocationId());
        assertEquals(location, fromDao);
    }

    @Test
    public void testGetLocationsByHeroId() {
        State state = new State();
        state.setName("New York");

        dao.addState(state);
        
        Superpower superpower1 = new Superpower();
        superpower1.setName("Flight");
        
        dao.addSuperpower(superpower1);
        
        Hero hero1 = new Hero();
        hero1.setName("Iron Man");
        hero1.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero1.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers1 = new ArrayList<>();
        superpowers1.add(superpower1);
        hero1.setSuperpowers(superpowers1);
        
        dao.addHero(hero1);
        
        Location location1 = new Location();
        location1.setName("Avengers Tower");
        location1.setDescription("A high-rise building complex which is "
                + "the headquarters for the Avengers.");
        location1.setStreetAddress("200 Park Avenue");
        location1.setCity("New York");
        location1.setState(state);
        location1.setZipCode("10166");
        location1.setLatitude(40.754188);
        location1.setLongitude(-73.976210);

        dao.addLocation(location1);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Sighting sighting1 = new Sighting();
        String dateTimeString1 = "2020-02-24 12:15:00";
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeString1, formatter);
        sighting1.setDateTime(dateTime1);
        sighting1.setLocation(location1);
        List<Hero> heroes1 = new ArrayList<>();
        heroes1.add(hero1);
        sighting1.setHeroes(heroes1);
        
        dao.addSighting(sighting1);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Healing");
        
        dao.addSuperpower(superpower2);
        
        Hero hero2 = new Hero();
        hero2.setName("Wolverine");
        hero2.setDescription("A long-lived mutant with the rage of a beast and "
                + "the soul of a Samurai, James \"Logan\" Howlett's once "
                + "mysterious past is filled with blood, war and betrayal. "
                + "Possessing an accelerated healing factor, keenly enhanced "
                + "senses and bone claws in each hand (along with his "
                + "skeleton) that are coated in adamantium; Wolverine is, "
                + "without question, the ultimate weapon.");
        hero2.setPictureFilename("wolverine.jpg");
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        hero2.setSuperpowers(superpowers2);
        
        dao.addHero(hero2);
        
        Location location2 = new Location();
        location2.setName("X-Mansion");
        location2.setDescription("It serves as the base of operations and "
                + "training site of the X-Men.");
        location2.setStreetAddress("1407 Graymalkin Lane");
        location2.setCity("North Salem");
        location2.setState(state);
        location2.setZipCode("10560");
        location2.setLatitude(41.335036);
        location2.setLongitude(-73.571905);
        
        dao.addLocation(location2);

        Sighting sighting2 = new Sighting();
        String dateTimeString2 = "2020-03-02 16:30:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(dateTimeString2, formatter);
        sighting2.setDateTime(dateTime2);
        sighting2.setLocation(location2);
        List<Hero> heroes2 = new ArrayList<>();
        heroes1.add(hero2);
        sighting2.setHeroes(heroes2);
        
        dao.addSighting(sighting2);
        
        assertEquals(1, dao.getLocationsByHeroId(hero1.getHeroId()).size());
    }

    @Test
    public void testGetAllLocations() {
        State state = new State();
        state.setName("New York");

        dao.addState(state);

        Location location1 = new Location();
        location1.setName("Avengers Tower");
        location1.setDescription("A high-rise building complex which is "
                + "the headquarters for the Avengers.");
        location1.setStreetAddress("200 Park Avenue");
        location1.setCity("New York");
        location1.setState(state);
        location1.setZipCode("10166");
        location1.setLatitude(40.754188);
        location1.setLongitude(-73.976210);
        
        dao.addLocation(location1);
        
        Location location2 = new Location();
        location2.setName("X-Mansion");
        location2.setDescription("It serves as the base of operations and "
                + "training site of the X-Men.");
        location2.setStreetAddress("1407 Graymalkin Lane");
        location2.setCity("North Salem");
        location2.setState(state);
        location2.setZipCode("10560");
        location2.setLatitude(41.335036);
        location2.setLongitude(-73.571905);
        
        dao.addLocation(location2);
        
        assertEquals(2, dao.getAllLocations().size());
    }

    @Test
    public void testAddGetOrganization() {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        dao.addSuperpower(superpower);
        
        Hero hero = new Hero();
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);
        
        dao.addHero(hero);
        
        State state = new State();
        state.setName("New York");

        dao.addState(state);

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

        dao.addLocation(location);

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
        
        dao.addOrganization(organization);
        
        Organization fromDao = dao.getOrganizationById(organization.getOrganizationId());
        assertEquals(organization, fromDao);
    }

    @Test
    public void testDeleteOrganization() {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        dao.addSuperpower(superpower);
        
        Hero hero = new Hero();
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);
        
        dao.addHero(hero);
        
        State state = new State();
        state.setName("New York");

        dao.addState(state);

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

        dao.addLocation(location);

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
        
        dao.addOrganization(organization);

        Organization fromDao = dao.getOrganizationById(organization.getOrganizationId());
        assertEquals(organization, fromDao);
        dao.deleteOrganization(organization.getOrganizationId());
        assertNull(dao.getOrganizationById(organization.getOrganizationId()));
    }

    @Test
    public void testUpdateOrganization() {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        dao.addSuperpower(superpower);
        
        Hero hero = new Hero();
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);
        
        dao.addHero(hero);
        
        State state = new State();
        state.setName("New York");

        dao.addState(state);

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

        dao.addLocation(location);

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
        
        dao.addOrganization(organization);

        organization.setName("Avengers");

        dao.updateOrganization(organization);

        Organization fromDao = dao.getOrganizationById(organization.getOrganizationId());
        assertEquals(organization, fromDao);
    }

    @Test
    public void testGetOrganizationsByHeroId() {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        dao.addSuperpower(superpower);
        
        Hero hero = new Hero();
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);
        
        dao.addHero(hero);
        
        State state = new State();
        state.setName("New York");

        dao.addState(state);

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

        dao.addLocation(location);

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
        
        dao.addOrganization(organization);
        
        assertEquals(1, dao.getOrganizationsByHeroId(hero.getHeroId()).size());
    }
    
    @Test
    public void testGetOrganizationsByLocationId() {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        dao.addSuperpower(superpower);
        
        Hero hero = new Hero();
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);
        
        dao.addHero(hero);
        
        State state = new State();
        state.setName("New York");

        dao.addState(state);

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

        dao.addLocation(location);

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
        
        dao.addOrganization(organization);
        
        assertEquals(1, dao.getOrganizationsByLocationId(location.getLocationId()).size());
    }

    @Test
    public void testGetAllOrganizations() {
        State state = new State();
        state.setName("New York");

        dao.addState(state);
        
        Superpower superpower1 = new Superpower();
        superpower1.setName("Flight");
        
        dao.addSuperpower(superpower1);
        
        Hero hero1 = new Hero();
        hero1.setName("Iron Man");
        hero1.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero1.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers1 = new ArrayList<>();
        superpowers1.add(superpower1);
        hero1.setSuperpowers(superpowers1);
        
        dao.addHero(hero1);
        
        Location location1 = new Location();
        location1.setName("Avengers Tower");
        location1.setDescription("A high-rise building complex which is "
                + "the headquarters for the Avengers.");
        location1.setStreetAddress("200 Park Avenue");
        location1.setCity("New York");
        location1.setState(state);
        location1.setZipCode("10166");
        location1.setLatitude(40.754188);
        location1.setLongitude(-73.976210);

        dao.addLocation(location1);

        Organization organization1 = new Organization();
        organization1.setName("The Avengers");
        organization1.setDescription("Earth's Mightiest Heroes stand as the "
                + "planet's first line of defense against the most powerful "
                + "threats in the universe.");
        organization1.setPhone("555-555-5555");
        organization1.setEmail("info@avengers.com");
        organization1.setLocation(location1);
        List<Hero> heroes1 = new ArrayList<>();
        heroes1.add(hero1);
        organization1.setHeroes(heroes1);
        
        dao.addOrganization(organization1);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Healing");
        
        dao.addSuperpower(superpower2);
        
        Hero hero2 = new Hero();
        hero2.setName("Wolverine");
        hero2.setDescription("A long-lived mutant with the rage of a beast and "
                + "the soul of a Samurai, James \"Logan\" Howlett's once "
                + "mysterious past is filled with blood, war and betrayal. "
                + "Possessing an accelerated healing factor, keenly enhanced "
                + "senses and bone claws in each hand (along with his "
                + "skeleton) that are coated in adamantium; Wolverine is, "
                + "without question, the ultimate weapon.");
        hero2.setPictureFilename("wolverine.jpg");
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        hero2.setSuperpowers(superpowers2);
        
        dao.addHero(hero2);
        
        Location location2 = new Location();
        location2.setName("X-Mansion");
        location2.setDescription("It serves as the base of operations and "
                + "training site of the X-Men.");
        location2.setStreetAddress("1407 Graymalkin Lane");
        location2.setCity("North Salem");
        location2.setState(state);
        location2.setZipCode("10560");
        location2.setLatitude(41.335036);
        location2.setLongitude(-73.571905);
        
        dao.addLocation(location2);

        Organization organization2 = new Organization();
        organization2.setName("X-Men");
        organization2.setDescription("Charles Xavier's team of merry mutants is "
                + "charged with the mission of protecting a world that hates "
                + "and fears them for their unusual appearances and abilities.");
        organization2.setPhone("555-555-0000");
        organization2.setEmail("email@x-men.com");
        organization2.setLocation(location2);
        List<Hero> heroes2 = new ArrayList<>();
        heroes2.add(hero2);
        organization2.setHeroes(heroes2);
        
        dao.addOrganization(organization2);
        
        assertEquals(2, dao.getAllOrganizations().size());
    }

    @Test
    public void testAddGetSighting() {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        dao.addSuperpower(superpower);
        
        Hero hero = new Hero();
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);
        
        dao.addHero(hero);
        
        State state = new State();
        state.setName("New York");

        dao.addState(state);

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

        dao.addLocation(location);
        
        Sighting sighting = new Sighting();
        String dateTimeString = "2020-02-24 12:15:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        sighting.setDateTime(dateTime);
        sighting.setLocation(location);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        sighting.setHeroes(heroes);
        
        dao.addSighting(sighting);
        
        Sighting fromDao = dao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);
    }

    @Test
    public void testDeleteSighting() {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        dao.addSuperpower(superpower);
        
        Hero hero = new Hero();
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);
        
        dao.addHero(hero);
        
        State state = new State();
        state.setName("New York");

        dao.addState(state);

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

        dao.addLocation(location);
        
        Sighting sighting = new Sighting();
        String dateTimeString = "2020-02-24 12:15:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        sighting.setDateTime(dateTime);
        sighting.setLocation(location);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        sighting.setHeroes(heroes);
        
        dao.addSighting(sighting);

        Sighting fromDao = dao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);
        dao.deleteSighting(sighting.getSightingId());
        assertNull(dao.getSightingById(sighting.getSightingId()));
    }

    @Test
    public void testUpdateSighting() {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");
        
        dao.addSuperpower(superpower);
        
        Hero hero = new Hero();
        hero.setName("Iron Man");
        hero.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        hero.setSuperpowers(superpowers);
        
        dao.addHero(hero);
        
        State state = new State();
        state.setName("New York");

        dao.addState(state);

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

        dao.addLocation(location);
        
        Sighting sighting = new Sighting();
        String dateTimeString = "2020-02-24 12:15:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        sighting.setDateTime(dateTime);
        sighting.setLocation(location);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        sighting.setHeroes(heroes);
        
        dao.addSighting(sighting);
        
        String dateTimeStringUpdated = "2020-02-25 12:15:00";
        LocalDateTime dateTimeUpdated = LocalDateTime.parse(dateTimeStringUpdated, formatter);

        sighting.setDateTime(dateTimeUpdated);

        dao.updateSighting(sighting);

        Sighting fromDao = dao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);
    }
    
    @Test
    public void testGetSightingsByDate() {
        State state = new State();
        state.setName("New York");

        dao.addState(state);
        
        Superpower superpower1 = new Superpower();
        superpower1.setName("Flight");
        
        dao.addSuperpower(superpower1);
        
        Hero hero1 = new Hero();
        hero1.setName("Iron Man");
        hero1.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero1.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers1 = new ArrayList<>();
        superpowers1.add(superpower1);
        hero1.setSuperpowers(superpowers1);
        
        dao.addHero(hero1);
        
        Location location1 = new Location();
        location1.setName("Avengers Tower");
        location1.setDescription("A high-rise building complex which is "
                + "the headquarters for the Avengers.");
        location1.setStreetAddress("200 Park Avenue");
        location1.setCity("New York");
        location1.setState(state);
        location1.setZipCode("10166");
        location1.setLatitude(40.754188);
        location1.setLongitude(-73.976210);

        dao.addLocation(location1);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Sighting sighting1 = new Sighting();
        String dateTimeString1 = "2020-02-24 12:15:00";
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeString1, formatter);
        sighting1.setDateTime(dateTime1);
        sighting1.setLocation(location1);
        List<Hero> heroes1 = new ArrayList<>();
        heroes1.add(hero1);
        sighting1.setHeroes(heroes1);
        
        dao.addSighting(sighting1);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Healing");
        
        dao.addSuperpower(superpower2);
        
        Hero hero2 = new Hero();
        hero2.setName("Wolverine");
        hero2.setDescription("A long-lived mutant with the rage of a beast and "
                + "the soul of a Samurai, James \"Logan\" Howlett's once "
                + "mysterious past is filled with blood, war and betrayal. "
                + "Possessing an accelerated healing factor, keenly enhanced "
                + "senses and bone claws in each hand (along with his "
                + "skeleton) that are coated in adamantium; Wolverine is, "
                + "without question, the ultimate weapon.");
        hero2.setPictureFilename("wolverine.jpg");
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        hero2.setSuperpowers(superpowers2);
        
        dao.addHero(hero2);
        
        Location location2 = new Location();
        location2.setName("X-Mansion");
        location2.setDescription("It serves as the base of operations and "
                + "training site of the X-Men.");
        location2.setStreetAddress("1407 Graymalkin Lane");
        location2.setCity("North Salem");
        location2.setState(state);
        location2.setZipCode("10560");
        location2.setLatitude(41.335036);
        location2.setLongitude(-73.571905);
        
        dao.addLocation(location2);

        Sighting sighting2 = new Sighting();
        String dateTimeString2 = "2020-03-02 16:30:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(dateTimeString2, formatter);
        sighting2.setDateTime(dateTime2);
        sighting2.setLocation(location2);
        List<Hero> heroes2 = new ArrayList<>();
        heroes1.add(hero2);
        sighting2.setHeroes(heroes2);
        
        dao.addSighting(sighting2);
        
        LocalDate date = LocalDate.of(2020, Month.MARCH, 2);
        
        assertEquals(1, dao.getSightingsByDate(date).size());
    }

    @Test
    public void testGetSightingsByLocationId() {
        State state = new State();
        state.setName("New York");

        dao.addState(state);
        
        Superpower superpower1 = new Superpower();
        superpower1.setName("Flight");
        
        dao.addSuperpower(superpower1);
        
        Hero hero1 = new Hero();
        hero1.setName("Iron Man");
        hero1.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero1.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers1 = new ArrayList<>();
        superpowers1.add(superpower1);
        hero1.setSuperpowers(superpowers1);
        
        dao.addHero(hero1);
        
        Location location1 = new Location();
        location1.setName("Avengers Tower");
        location1.setDescription("A high-rise building complex which is "
                + "the headquarters for the Avengers.");
        location1.setStreetAddress("200 Park Avenue");
        location1.setCity("New York");
        location1.setState(state);
        location1.setZipCode("10166");
        location1.setLatitude(40.754188);
        location1.setLongitude(-73.976210);

        dao.addLocation(location1);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Sighting sighting1 = new Sighting();
        String dateTimeString1 = "2020-02-24 12:15:00";
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeString1, formatter);
        sighting1.setDateTime(dateTime1);
        sighting1.setLocation(location1);
        List<Hero> heroes1 = new ArrayList<>();
        heroes1.add(hero1);
        sighting1.setHeroes(heroes1);
        
        dao.addSighting(sighting1);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Healing");
        
        dao.addSuperpower(superpower2);
        
        Hero hero2 = new Hero();
        hero2.setName("Wolverine");
        hero2.setDescription("A long-lived mutant with the rage of a beast and "
                + "the soul of a Samurai, James \"Logan\" Howlett's once "
                + "mysterious past is filled with blood, war and betrayal. "
                + "Possessing an accelerated healing factor, keenly enhanced "
                + "senses and bone claws in each hand (along with his "
                + "skeleton) that are coated in adamantium; Wolverine is, "
                + "without question, the ultimate weapon.");
        hero2.setPictureFilename("wolverine.jpg");
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        hero2.setSuperpowers(superpowers2);
        
        dao.addHero(hero2);
        
        Location location2 = new Location();
        location2.setName("X-Mansion");
        location2.setDescription("It serves as the base of operations and "
                + "training site of the X-Men.");
        location2.setStreetAddress("1407 Graymalkin Lane");
        location2.setCity("North Salem");
        location2.setState(state);
        location2.setZipCode("10560");
        location2.setLatitude(41.335036);
        location2.setLongitude(-73.571905);
        
        dao.addLocation(location2);

        Sighting sighting2 = new Sighting();
        String dateTimeString2 = "2020-03-02 16:30:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(dateTimeString2, formatter);
        sighting2.setDateTime(dateTime2);
        sighting2.setLocation(location2);
        List<Hero> heroes2 = new ArrayList<>();
        heroes1.add(hero2);
        sighting2.setHeroes(heroes2);
        
        dao.addSighting(sighting2);
        
        LocalDate date = LocalDate.of(2020, Month.MARCH, 2);
        
        assertEquals(1, dao.getSightingsByLocationId(location1.getLocationId()).size());
    }

    @Test
    public void testGetLatestSightingsLimitByNum() {
        State state = new State();
        state.setName("New York");

        dao.addState(state);
        
        Superpower superpower1 = new Superpower();
        superpower1.setName("Flight");
        
        dao.addSuperpower(superpower1);
        
        Hero hero1 = new Hero();
        hero1.setName("Iron Man");
        hero1.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero1.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers1 = new ArrayList<>();
        superpowers1.add(superpower1);
        hero1.setSuperpowers(superpowers1);
        
        dao.addHero(hero1);
        
        Location location1 = new Location();
        location1.setName("Avengers Tower");
        location1.setDescription("A high-rise building complex which is "
                + "the headquarters for the Avengers.");
        location1.setStreetAddress("200 Park Avenue");
        location1.setCity("New York");
        location1.setState(state);
        location1.setZipCode("10166");
        location1.setLatitude(40.754188);
        location1.setLongitude(-73.976210);

        dao.addLocation(location1);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Sighting sighting1 = new Sighting();
        String dateTimeString1 = "2020-02-24 12:15:00";
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeString1, formatter);
        sighting1.setDateTime(dateTime1);
        sighting1.setLocation(location1);
        List<Hero> heroes1 = new ArrayList<>();
        heroes1.add(hero1);
        sighting1.setHeroes(heroes1);
        
        dao.addSighting(sighting1);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Healing");
        
        dao.addSuperpower(superpower2);
        
        Hero hero2 = new Hero();
        hero2.setName("Wolverine");
        hero2.setDescription("A long-lived mutant with the rage of a beast and "
                + "the soul of a Samurai, James \"Logan\" Howlett's once "
                + "mysterious past is filled with blood, war and betrayal. "
                + "Possessing an accelerated healing factor, keenly enhanced "
                + "senses and bone claws in each hand (along with his "
                + "skeleton) that are coated in adamantium; Wolverine is, "
                + "without question, the ultimate weapon.");
        hero2.setPictureFilename("wolverine.jpg");
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        hero2.setSuperpowers(superpowers2);
        
        dao.addHero(hero2);
        
        Location location2 = new Location();
        location2.setName("X-Mansion");
        location2.setDescription("It serves as the base of operations and "
                + "training site of the X-Men.");
        location2.setStreetAddress("1407 Graymalkin Lane");
        location2.setCity("North Salem");
        location2.setState(state);
        location2.setZipCode("10560");
        location2.setLatitude(41.335036);
        location2.setLongitude(-73.571905);
        
        dao.addLocation(location2);

        Sighting sighting2 = new Sighting();
        String dateTimeString2 = "2020-03-02 16:30:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(dateTimeString2, formatter);
        sighting2.setDateTime(dateTime2);
        sighting2.setLocation(location2);
        List<Hero> heroes2 = new ArrayList<>();
        heroes1.add(hero2);
        sighting2.setHeroes(heroes2);
        
        dao.addSighting(sighting2);
        
        assertEquals(1, dao.getLatestSightingsLimitByNum(1).size());
    }

    @Test
    public void testGetAllSightings() {
        State state = new State();
        state.setName("New York");

        dao.addState(state);
        
        Superpower superpower1 = new Superpower();
        superpower1.setName("Flight");
        
        dao.addSuperpower(superpower1);
        
        Hero hero1 = new Hero();
        hero1.setName("Iron Man");
        hero1.setDescription("Inventor Tony Stark applies his genius for "
                + "high-tech solutions to problems as Iron Man, "
                + "the armored Avenger.");
        hero1.setPictureFilename("iron-man.jpg");
        List<Superpower> superpowers1 = new ArrayList<>();
        superpowers1.add(superpower1);
        hero1.setSuperpowers(superpowers1);
        
        dao.addHero(hero1);
        
        Location location1 = new Location();
        location1.setName("Avengers Tower");
        location1.setDescription("A high-rise building complex which is "
                + "the headquarters for the Avengers.");
        location1.setStreetAddress("200 Park Avenue");
        location1.setCity("New York");
        location1.setState(state);
        location1.setZipCode("10166");
        location1.setLatitude(40.754188);
        location1.setLongitude(-73.976210);

        dao.addLocation(location1);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Sighting sighting1 = new Sighting();
        String dateTimeString1 = "2020-02-24 12:15:00";
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeString1, formatter);
        sighting1.setDateTime(dateTime1);
        sighting1.setLocation(location1);
        List<Hero> heroes1 = new ArrayList<>();
        heroes1.add(hero1);
        sighting1.setHeroes(heroes1);
        
        dao.addSighting(sighting1);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Healing");
        
        dao.addSuperpower(superpower2);
        
        Hero hero2 = new Hero();
        hero2.setName("Wolverine");
        hero2.setDescription("A long-lived mutant with the rage of a beast and "
                + "the soul of a Samurai, James \"Logan\" Howlett's once "
                + "mysterious past is filled with blood, war and betrayal. "
                + "Possessing an accelerated healing factor, keenly enhanced "
                + "senses and bone claws in each hand (along with his "
                + "skeleton) that are coated in adamantium; Wolverine is, "
                + "without question, the ultimate weapon.");
        hero2.setPictureFilename("wolverine.jpg");
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        hero2.setSuperpowers(superpowers2);
        
        dao.addHero(hero2);
        
        Location location2 = new Location();
        location2.setName("X-Mansion");
        location2.setDescription("It serves as the base of operations and "
                + "training site of the X-Men.");
        location2.setStreetAddress("1407 Graymalkin Lane");
        location2.setCity("North Salem");
        location2.setState(state);
        location2.setZipCode("10560");
        location2.setLatitude(41.335036);
        location2.setLongitude(-73.571905);
        
        dao.addLocation(location2);

        Sighting sighting2 = new Sighting();
        String dateTimeString2 = "2020-03-02 16:30:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(dateTimeString2, formatter);
        sighting2.setDateTime(dateTime2);
        sighting2.setLocation(location2);
        List<Hero> heroes2 = new ArrayList<>();
        heroes1.add(hero2);
        sighting2.setHeroes(heroes2);
        
        dao.addSighting(sighting2);
        
        assertEquals(2, dao.getAllSightings().size());
    }

    @Test
    public void testAddGetState() {
        State state = new State();
        state.setName("New York");

        dao.addState(state);

        State fromDao = dao.getStateById(state.getStateId());
        assertEquals(state, fromDao);
    }

    @Test
    public void testDeleteState() {
        State state = new State();
        state.setName("New York");

        dao.addState(state);

        State fromDao = dao.getStateById(state.getStateId());
        assertEquals(state, fromDao);
        dao.deleteState(state.getStateId());
        assertNull(dao.getStateById(state.getStateId()));
    }

    @Test
    public void testUpdateState() {
        State state = new State();
        state.setName("New York");

        dao.addState(state);

        state.setName("New Jersey");

        dao.updateState(state);

        State fromDao = dao.getStateById(state.getStateId());
        assertEquals(state, fromDao);
    }

    @Test
    public void testGetAllStates() {
        State state1 = new State();
        state1.setName("New York");
        
        dao.addState(state1);
        
        State state2 = new State();
        state2.setName("California");
        
        dao.addState(state2);
        
        assertEquals(2, dao.getAllStates().size());
    }

    @Test
    public void testAddGetSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");

        dao.addSuperpower(superpower);

        Superpower fromDao = dao.getSuperpowerById(superpower.getSuperpowerId());
        assertEquals(superpower, fromDao); 
    }

    @Test
    public void testDeleteSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");

        dao.addSuperpower(superpower);

        Superpower fromDao = dao.getSuperpowerById(superpower.getSuperpowerId());
        assertEquals(superpower, fromDao);
        dao.deleteSuperpower(superpower.getSuperpowerId());
        assertNull(dao.getStateById(superpower.getSuperpowerId()));
    }

    @Test
    public void testUpdateSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setName("Flight");

        dao.addSuperpower(superpower);

        superpower.setName("Levitation");

        dao.updateSuperpower(superpower);

        Superpower fromDao = dao.getSuperpowerById(superpower.getSuperpowerId());
        assertEquals(superpower, fromDao);
    }

    @Test
    public void testGetAllSuperpowers() {
        Superpower superpower1 = new Superpower();
        superpower1.setName("Flight");
        
        dao.addSuperpower(superpower1);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("Strength");
        
        dao.addSuperpower(superpower2);
        
        assertEquals(2, dao.getAllSuperpowers().size());
    }
}
