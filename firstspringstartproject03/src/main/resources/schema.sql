DROP TABLE IF EXISTS PERSONNE;

CREATE TABLE PERSONNE (
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  NOM VARCHAR(250) NOT NULL,
  PRENOM VARCHAR(250) NOT NULL,
  DATE_ANNIVERSAIRE DATETIME,
  RUE VARCHAR(250),
  CODEPOSTAL VARCHAR(250),
  VILLE VARCHAR(250),
  TELEPHONE VARCHAR(250)
  );