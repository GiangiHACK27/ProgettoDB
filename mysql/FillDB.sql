USE GameShop;

INSERT INTO User (username, password, email, role) values ("tonaion", "bb2600aab23d189369ea4b0a665d9433d99241b66809c4e5162c0d933fb29cd5eb16fa38f348b02816570b9778c07c5828caf18fdda5c64f6f33a3d08c7e823d", "tonaion@email.com", "admin"); #password: tonaion
INSERT INTO User (username, password, email) values ("AleCoppola02", "8061fd1e0fe7b78dfc90e35c740873a59413da216ee12cfd67694b13626d3978056909b84961b8411dff6466831964166a40b3e65eda3e718cdf478efda64076", "alala"); #password: asdad

INSERT INTO Category values ("categoria1");
INSERT INTO Category values ("categoria2");

INSERT INTO Game (price, name, description, shortDescription) values (10, "Titolo", 
"L'ultimo ruggito di un dio morente rompe il confine tra i mondi, aprendo un portale verso il Regno del Caos. Da questo Maelstrom, emergono Khorne, Nurgle, Tzeentch e Slaanesh, i quattro Poteri Perniciosi che seminano oscuritÃ  e disperazione. Gli austeri guerrieri di Kislev e il vasto impero del Grande Catai attendono sulla soglia, mentre il vendicativo Principe Demone giura di distruggere coloro che lo hanno corrotto. L'imminente conflitto divorerÃ  tutto. Sconfiggerai i tuoi demoni? Oppure li comanderai? L'ultimo ruggito di un dio morente rompe il confine tra i mondi, aprendo un portale verso il Regno del Caos. Da questo Maelstrom, emergono Khorne, Nurgle, Tzeentch e Slaanesh, i quattro Poteri Perniciosi che seminano oscuritÃ  e disperazione. Gli austeri guerrieri di Kislev e il vasto impero del Grande Catai attendono sulla soglia, mentre il vendicativo Principe Demone giura di distruggere coloro che lo hanno corrotto. L'imminente conflitto divorerÃ  tutto. Sconfiggerai i tuoi demoni? Oppure li comanderai?",
"sadasdasdadasasasasasasasasasasasasasasasasasasda sadasdasdas sdasdddddddad sdasdwafsfgsdg fdgfgdfgggggdfggggggggdfg fdgfdgrewerewr wer werewrer"
);
INSERT INTO Game (price, name, description, shortDescription) values (15, "Titolo2", "", "");

INSERT INTO Belongs values ("categoria1", 1);

INSERT INTO Purchase (gameId, username) values (1, "tonaion");

INSERT INTO Review (gameId, username) values (1, "tonaion");
INSERT INTO Likes values (1, "AleCoppola02"); 