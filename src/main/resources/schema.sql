DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS ANIMAL;
CREATE TABLE USER (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  NAME VARCHAR(250) NOT NULL,
  MAIL VARCHAR(250) NOT NULL,
  PASSWORD VARCHAR(250) NOT NULL
);
CREATE TABLE ANIMAL (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  TYPE VARCHAR(250) NOT NULL,
  FAMILY VARCHAR(250) NOT NULL,
  GENUS VARCHAR(250) NOT NULL,
  SPECIES VARCHAR(250) NOT NULL
);

