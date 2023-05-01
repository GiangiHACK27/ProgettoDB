DROP database if exists GameShop;

CREATE DATABASE GameShop;

USE GameShop;

CREATE TABLE Game (
	id int NOT NULL AUTO_INCREMENT,
    price int NOT NULL,
    name varchar(30) NOT NULL,
    description varchar(30) DEFAULT(""),
    shortDescription varchar(30) DEFAULT(""),
    realeseDate date DEFAULT("1999-09-09"),
    State ENUM("Realesed", "Beta", "Alpha", "Coming soon", "UnListed") NOT NULL DEFAULT("Coming soon"),
    pegi int NOT NULL DEFAULT(18),
    primary key(id)
);

CREATE TABLE Category (
	name varchar(30) NOT NULL,
    primary key(name)
);

CREATE TABLE Belongs (
	nameCategory varchar(30) NOT NULL,
    gameId int NOT NULL,
    CONSTRAINT gameIdConsBelongs 
		foreign key (gameId) references Game (id),
	CONSTRAINT nameCategoryConsBelongs
		foreign key (nameCategory) references Category (name)
);

CREATE TABLE SystemRequirement (
	name varchar(30) NOT NULL,
    os ENUM("Windows", "Linux", "Mac") NOT NULL DEFAULT("Linux"),
    gameId int NOT NULL,
    value varchar(30) NOT NULL,
    CONSTRAINT gameIdConsSystemRequirement
		foreign key (gameId) references Game (id),
    primary key(name, os, gameId)
);

CREATE TABLE Image (
	id varchar(30) NOT NULL,
	raw mediumblob NULL,
    alt varchar(30) NOT NULL DEFAULT("Image Not Found"),
    PRIMARY KEY(id)
);

CREATE TABLE Represented (
	gameId int NOT NULL,
    imageId varchar(30) NOT NULL,
	role varchar(30) NOT NULL,
    CONSTRAINT gameIdConsRepresented
		foreign key (gameId) references Game (id),
	CONSTRAINT imageIdConsRepresented
		foreign key (imageId) references Image (id),
	primary key (gameId, imageId)
);

CREATE TABLE User (
	username varchar(30) NOT NULL,
    password varchar(30) NOT NULL,
	email varchar(30) NOT NULL UNIQUE,
    profileImageId varchar(30) NULL,
    gamesOwned int NOT NULL DEFAULT(0),
    Role ENUM("client", "admin") NOT NULL DEFAULT("client"),
    primary key(username)
);

CREATE TABLE Interested (
	username varchar(30) NOT NULL,
    gameId int NOT NULL,
    category ENUM("chart", "whishlist") NOT NULL DEFAULT("char"),
    CONSTRAINT gameIdConsInterested
		foreign key (gameId) references Game (id),
	CONSTRAINT usernameConsInterested
		foreign key (username) references User (username),
	primary key(gameId, username)
);

CREATE TABLE Purchase (
	id int NOT NULL AUTO_INCREMENT,
    gameId int NOT NULL,
    username varchar(30) NOT NULL,
    price int NOT NULL DEFAULT(0),
    datePurchased date NOT NULL DEFAULT(CURDATE()),
    CONSTRAINT gameIdConsPurchase 
		foreign key (gameId) references Game (id),
	CONSTRAINT usernameConsPurchase
		foreign key (username) references User (username),
	UNIQUE (gameId, username), -- TEST: IF WORK
	primary key (id)
);

CREATE TABLE Review (
	id int NOT NULL AUTO_INCREMENT,
    gameId int NOT NULL,
    username varchar(30) NOT NULL,
    textField text NULL, 
    stars int NOT NULL DEFAULT(0),
    likes int NOT NULL DEFAULT(0),
    CONSTRAINT gameIdConsReview 
		foreign key (gameId) references Game (id),
	CONSTRAINT usernameConsReview 
		foreign key (username) references User (username),
	primary key (id)
);

CREATE TABLE Likes (
	reviewId int NOT NULL,
    username varchar(30) NOT NULL,
    CONSTRAINT reviewIdConsLikes
		foreign key (reviewId) references Review (id),
	CONSTRAINT usernameConsLikes
		foreign key (username) references User (username),
    primary key (reviewId, username)
);

-- Triggers to update like counter in review
CREATE TRIGGER increaseNumberOfLikes
AFTER INSERT ON Likes 
FOR EACH ROW
UPDATE Review as R SET R.likes = R.likes + 1 WHERE NEW.reviewId = R.id;

CREATE TRIGGER decreaseNumberOfLikes
AFTER DELETE ON Likes
FOR EACH ROW
UPDATE Review as R SET R.likes = R.likes - 1 WHERE OLD.reviewId = R.id;
-- Triggers to update like counter in review

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

-- Triggers for a valid like
DELIMITER //
CREATE TRIGGER likeOnDifferentUsers BEFORE INSERT ON Likes for each row 
begin 
IF EXISTS(SELECT * FROM Review as R WHERE R.username = NEW.username) THEN signal sqlstate '45000';
end if;
end // 
DELIMITER ; 
-- Triggers for a valid like