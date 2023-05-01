USE GameShop;

INSERT INTO User (username, password, email) values ("tonaion", "tonaion", "tonaion@email.com");
INSERT INTO User (username, password, email) values ("AleCoppola02", "asdad", "alala");

INSERT INTO Category values ("categoria1");
INSERT INTO Category values ("categoria2");

INSERT INTO Game (price, name) values (10, "Titolo");

INSERT INTO Belongs values ("categoria1", 1);

INSERT INTO Purchase (gameId, username) values (1, "tonaion");

INSERT INTO Review (gameId, username) values (1, "tonaion");
INSERT INTO Likes values (1, "AleCoppola02"); 