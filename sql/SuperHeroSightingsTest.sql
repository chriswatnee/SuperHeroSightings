DROP DATABASE IF EXISTS SuperHeroSightingsTest;

CREATE DATABASE SuperHeroSightingsTest;

USE SuperHeroSightingsTest;

-- Hero/Supervillain Information

CREATE TABLE Hero
(
	HeroID INT NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR(50) NOT NULL,
	`Description` VARCHAR(500) NULL,
	PictureFilename VARCHAR(50) NULL,
	PRIMARY KEY (HeroID)
);

CREATE TABLE Superpower
(
	SuperpowerID INT NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR(50) NOT NULL,
	PRIMARY KEY (SuperpowerID)
);

CREATE TABLE HeroSuperpower
(
	HeroID INT NOT NULL,
	SuperpowerID INT NOT NULL,
	PRIMARY KEY (HeroID, SuperpowerID)
);

ALTER TABLE HeroSuperpower
ADD CONSTRAINT fk_HeroSuperpower_Hero
FOREIGN KEY (HeroID) REFERENCES Hero(HeroID) ON DELETE NO ACTION;

ALTER TABLE HeroSuperpower
ADD CONSTRAINT fk_HeroSuperpower_Superpower
FOREIGN KEY (SuperpowerID) REFERENCES Superpower(SuperpowerID) ON DELETE NO ACTION;

-- Location Information

CREATE TABLE Location
(
	LocationID INT NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR(50) NOT NULL,
	`Description` VARCHAR(100) NULL,
	StreetAddress VARCHAR(50) NOT NULL,
    City VARCHAR(50) NOT NULL,
	StateID INT NOT NULL,
	ZipCode CHAR(5) NOT NULL,
    Latitude DECIMAL(9,6) NOT NULL,
    Longitude DECIMAL(9,6) NOT NULL, 
	PRIMARY KEY (LocationID)
);

CREATE TABLE State
(
	StateID INT NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR(50) NOT NULL,
	PRIMARY KEY (StateID)
);

ALTER TABLE Location
ADD CONSTRAINT fk_Location_State
FOREIGN KEY (StateID) REFERENCES State(StateID) ON DELETE NO ACTION;

-- Hero/Supervillain Organization Information

CREATE TABLE `Organization`
(
	OrganizationID INT NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR(50) NOT NULL,
	`Description` VARCHAR(200) NULL,
	Phone VARCHAR(20) NOT NULL,
	Email VARCHAR(30) NOT NULL,
    LocationID INT NOT NULL,
	PRIMARY KEY (OrganizationID)
);

CREATE TABLE OrganizationHero
(
	OrganizationID INT NOT NULL,
	HeroID INT NOT NULL,
	PRIMARY KEY (OrganizationID, HeroID)
);

ALTER TABLE OrganizationHero
ADD CONSTRAINT fk_OrganizationHero_Organization
FOREIGN KEY (OrganizationID) REFERENCES `Organization`(OrganizationID) ON DELETE NO ACTION;

ALTER TABLE OrganizationHero
ADD CONSTRAINT fk_OrganizationHero_Hero
FOREIGN KEY (HeroID) REFERENCES Hero(HeroID) ON DELETE NO ACTION;

-- Sighting Information

CREATE TABLE Sighting
(
	SightingID INT NOT NULL AUTO_INCREMENT,
	`DateTime` DATETIME NOT NULL,
    LocationID INT NOT NULL,
	PRIMARY KEY (SightingID)
);

CREATE TABLE SightingHero
(
	SightingID INT NOT NULL,
	HeroID INT NOT NULL,
	PRIMARY KEY (SightingID, HeroID)
);

ALTER TABLE Sighting
ADD CONSTRAINT fk_Sighting_Location
FOREIGN KEY (LocationID) REFERENCES Location(LocationID) ON DELETE NO ACTION;

ALTER TABLE SightingHero
ADD CONSTRAINT fk_SightingHero_Sighting
FOREIGN KEY (SightingID) REFERENCES Sighting(SightingID) ON DELETE NO ACTION;

ALTER TABLE SightingHero
ADD CONSTRAINT fk_SightingHero_Hero
FOREIGN KEY (HeroID) REFERENCES Hero(HeroID) ON DELETE NO ACTION;