DROP database if exists GameShop;

CREATE DATABASE GameShop;
CREATE USER IF NOT EXISTS 'client'@'localhost' IDENTIFIED BY 'client';
GRANT ALL ON gameshop.* TO 'client'@'localhost';
USE GameShop;

CREATE TABLE Game (
	id int NOT NULL AUTO_INCREMENT,
    price int NOT NULL,
    name varchar(30) NOT NULL,
    description text DEFAULT(""),
    shortDescription text DEFAULT(""),
    releaseDate date DEFAULT("1999-09-09"),
    state ENUM("Released", "Beta", "Alpha", "Unlisted") NOT NULL DEFAULT("Alpha"),
    pegi int NOT NULL DEFAULT(18),
    publisher varchar(30) NOT NULL DEFAULT("Microsoft"),
    primary key(id)
);

CREATE TABLE Category (
	name varchar(30) NOT NULL,
    primary key(name)
);

CREATE TABLE Belongs (
	categoryName varchar(30) NOT NULL,
    gameId int NOT NULL,
    CONSTRAINT gameIdConsBelongs 
		foreign key (gameId) references Game (id)
        on delete cascade,
	CONSTRAINT nameCategoryConsBelongs
		foreign key (categoryName) references Category (name)
);

CREATE TABLE SystemRequirement (
	name varchar(30) NOT NULL,
    os ENUM("Windows", "Linux", "Mac") NOT NULL DEFAULT("Windows"),
    gameId int NOT NULL,
    value varchar(30) NOT NULL DEFAULT(""),
    CONSTRAINT gameIdConsSystemRequirement
		foreign key (gameId) references Game (id),
    primary key(name, os, gameId)
);

CREATE TABLE Image (
	id INT auto_increment NOT NULL,
	raw mediumblob NULL,
    alt varchar(30) NOT NULL DEFAULT("Image Not Found"),
    PRIMARY KEY(id)
);

CREATE TABLE Represented (
	gameId int NOT NULL,
    imageId INT NOT NULL,
	role varchar(30) NOT NULL,
    CONSTRAINT gameIdConsRepresented
		foreign key (gameId) references Game (id)
        on delete cascade,
	CONSTRAINT imageIdConsRepresented
		foreign key (imageId) references Image (id),
	primary key (gameId, imageId)
);

CREATE TABLE User (
	username varchar(30) NOT NULL,
    password varchar(300) NOT NULL,
	email varchar(30) NOT NULL UNIQUE,
    profileImageId varchar(30) NULL,
    gamesOwned int NOT NULL DEFAULT(0),
    role ENUM("client", "admin") NOT NULL DEFAULT("client"),
    primary key(username)
);

CREATE TABLE Interested (
	username varchar(30) NOT NULL,
    gameId int NOT NULL,
    category ENUM("cart", "wishlist") NOT NULL DEFAULT("cart"),
    CONSTRAINT gameIdConsInterested
		foreign key (gameId) references Game (id),
	CONSTRAINT usernameConsInterested
		foreign key (username) references User (username),
	primary key(gameId, username, category)
);

CREATE TABLE Purchase (
	id int NOT NULL AUTO_INCREMENT,
    gameId int NOT NULL,
    username varchar(30) NOT NULL,
    price int NOT NULL DEFAULT(0),
    datePurchased date NOT NULL DEFAULT(CURDATE()),
    CONSTRAINT gameIdConsPurchase 
		foreign key (gameId) references Game (id)
        on delete cascade,
	CONSTRAINT usernameConsPurchase
		foreign key (username) references User (username),
	UNIQUE (gameId, username), -- TEST: IF WORK
	primary key (id)
);


-- Triggers to update the number of games of user's library
CREATE TRIGGER increaseGamesOwned
AFTER INSERT ON Purchase 
FOR EACH ROW
UPDATE User as U SET U.gamesOwned = U.gamesOwned + 1 WHERE NEW.username = U.username;

CREATE TRIGGER decreaseGamesOwned
AFTER DELETE ON Purchase
FOR EACH ROW
UPDATE User as U SET U.gamesOwned = U.gamesOwned - 1 WHERE OLD.username = U.username;
-- Triggers to update the number of games of user's library

-- Triggers for a valid purchase
DELIMITER //
CREATE TRIGGER buyIfNotPossesed BEFORE INSERT ON Purchase for each row 
begin 
IF EXISTS(SELECT * FROM Purchase as P WHERE P.gameId = NEW.gameId AND NEW.username = P.username) THEN signal sqlstate '45000';
end if;
end // 
DELIMITER ; 
-- Triggers for a valid purchase

-- Triggers for a valid game
DELIMITER //
CREATE TRIGGER validGame BEFORE INSERT ON Game for each row 
begin 
IF NEW.price < 0 AND NEW.price > 100000 THEN signal sqlstate '45000';
end if;
end // 
DELIMITER ; 
-- Triggers for a valid game