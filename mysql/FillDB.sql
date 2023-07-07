USE GameShop;

INSERT INTO User (username, password, email, role) values ("tonaion", "bb2600aab23d189369ea4b0a665d9433d99241b66809c4e5162c0d933fb29cd5eb16fa38f348b02816570b9778c07c5828caf18fdda5c64f6f33a3d08c7e823d", "tonaion@email.com", "admin"); #password: tonaion
INSERT INTO User (username, password, email) values ("AleCoppola02", "8061fd1e0fe7b78dfc90e35c740873a59413da216ee12cfd67694b13626d3978056909b84961b8411dff6466831964166a40b3e65eda3e718cdf478efda64076", "alala"); #password: asdad

INSERT INTO Category values ("categoria1");
INSERT INTO Category values ("categoria2");
INSERT INTO Category values ("categoria3");

INSERT INTO Game (price, name, description, shortDescription) values (10, "Titolo", 
"L'ultimo ruggito di un dio morente rompe il confine tra i mondi, aprendo un portale verso il Regno del Caos. Da questo Maelstrom, emergono Khorne, Nurgle, Tzeentch e Slaanesh, i quattro Poteri Perniciosi che seminano oscuritÃ  e disperazione. Gli austeri guerrieri di Kislev e il vasto impero del Grande Catai attendono sulla soglia, mentre il vendicativo Principe Demone giura di distruggere coloro che lo hanno corrotto. L'imminente conflitto divorerÃ  tutto. Sconfiggerai i tuoi demoni? Oppure li comanderai? L'ultimo ruggito di un dio morente rompe il confine tra i mondi, aprendo un portale verso il Regno del Caos. Da questo Maelstrom, emergono Khorne, Nurgle, Tzeentch e Slaanesh, i quattro Poteri Perniciosi che seminano oscuritÃ  e disperazione. Gli austeri guerrieri di Kislev e il vasto impero del Grande Catai attendono sulla soglia, mentre il vendicativo Principe Demone giura di distruggere coloro che lo hanno corrotto. L'imminente conflitto divorerÃ  tutto. Sconfiggerai i tuoi demoni? Oppure li comanderai?",
"sadasdasdadasasasasasasasasasasasasasasasasasasda sadasdasdas sdasdddddddad sdasdwafsfgsdg fdgfgdfgggggdfggggggggdfg fdgfdgrewerewr wer werewrer"
);
INSERT INTO Game (price, name, description, shortDescription) values (15, "Titolo2", "Ciao bella", "bella");
INSERT INTO Game (price, name, description, shortDescription) values (17, "Titolo3", "Ciao blelo", "bello");
INSERT INTO Game (price, name, description, shortDescription) values (15, "Titolo4", "Ciao bella", "bella");
INSERT INTO Game (price, name, description, shortDescription) values (15, "Titolo6", "Ciao bella", "bella");
INSERT INTO Game (price, name, description, shortDescription) values (15, "Titolo5", "Ciao bella", "bella");
INSERT INTO Game (price, name, description, shortDescription) values (15, "Titolo8", "Ciao bella", "bella");
INSERT INTO Game (price, name, description, shortDescription) values (15, "Titolo9", "Ciao bella", "bella");
INSERT INTO Game (price, name, description, shortDescription) values (15, "Titolo7", "Ciao bella", "bella");
INSERT INTO Game (price, name, description, shortDescription) values (15, "Titolo10", "Ciao bella", "bella");
INSERT INTO Game (price, name, description, shortDescription) values (15, "Titolo11", "Ciao bella", "bella");
INSERT INTO Game (price, name, description, shortDescription) values (15, "Titolo12", "Ciao bella", "bella");
INSERT INTO Game (price, name, description, shortDescription) values (15, "Titolo13", "Ciao bella", "bella");
INSERT INTO Game (price, name, description, shortDescription) values (15, "Titolo14", "Ciao bella", "bella");
INSERT INTO Game (price, name, description, shortDescription) values (15, "Titolo15", "Ciao bella", "bella");
INSERT INTO Game (price, name, description, shortDescription, state) values (30, "hey", "Ciao bella", "bella", "unlisted");


INSERT INTO SystemRequirement (name, os, gameId) values ("minimum-qualcosa", "Windows", 1);
INSERT INTO SystemRequirement (name, os, gameId) values ("minimum-qualcosa", "Linux", 1);

INSERT INTO Belongs values ("categoria1", 1);
INSERT INTO Belongs values ("categoria2", 1);
INSERT INTO Belongs values ("categoria1", 2);
INSERT INTO Belongs values ("categoria1", 3);
INSERT INTO Belongs values ("categoria1", 4);
INSERT INTO Belongs values ("categoria1", 5);
INSERT INTO Belongs values ("categoria1", 6);
INSERT INTO Belongs values ("categoria1", 7);
INSERT INTO Belongs values ("categoria1", 8);
INSERT INTO Belongs values ("categoria1", 9);
INSERT INTO Belongs values ("categoria1", 10);
INSERT INTO Belongs values ("categoria1", 11);
INSERT INTO Belongs values ("categoria1", 12);
INSERT INTO Belongs values ("categoria1", 13);
INSERT INTO Belongs values ("categoria1", 14);
INSERT INTO Belongs values ("categoria1", 15);
INSERT INTO Belongs values ("categoria1", 16);

INSERT INTO Purchase (gameId, username, price, datePurchased) values (2, "tonaion", 15, "2000-01-01");
INSERT INTO Purchase (gameId, username, price, datePurchased) values (3, "AleCoppola02", 10, "2024-01-01");

INSERT INTO Review (gameId, username) values (1, "tonaion");
INSERT INTO Likes values (1, "AleCoppola02"); 