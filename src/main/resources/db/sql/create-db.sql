--DROP TABLE users IF EXISTS;

--CREATE TABLE researchFields (
--  id INTEGER,
--  field VARCHAR(50)
--);

DROP TABLE users IF EXISTS;
CREATE TABLE users (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1) PRIMARY KEY,
  name VARCHAR(30),
  email VARCHAR(50),
  password VARCHAR(20),
  phoneNumber VARCHAR(20),
  fields VARCHAR(500),
  address VARCHAR(200),
  sex VARCHAR(1),
  COUNTRY VARCHAR(10)
);

DROP TABLE conferences IF EXISTS;
CREATE TABLE conferences (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1000, INCREMENT BY 1) PRIMARY KEY,
  name VARCHAR(200),
  venue VARCHAR(200),
  conferenceTime VARCHAR(50),
  organizer VARCHAR(100),
  website VARCHAR(200),
  emails VARCHAR(1000)
);

DROP TABLE presentations IF EXISTS;
CREATE TABLE presentations (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 10000, INCREMENT BY 1) PRIMARY KEY,
  userId INTEGER,
  conferenceId INTEGER,
  title VARCHAR(300),
  authors VARCHAR(200),
  fileName VARCHAR(100),
  fieldId INTEGER,
  abs VARCHAR(2000)
);