--DROP TABLE users IF EXISTS;

CREATE TABLE researchFields (
  id INTEGER,
  field VARCHAR(50)
);

CREATE TABLE users (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1) PRIMARY KEY,
  name VARCHAR(30),
  email VARCHAR(50),
  password VARCHAR(20),
  number VARCHAR(20),
  field VARCHAR(500),
  COUNTRY VARCHAR(10)
);

CREATE TABLE conferences (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1000, INCREMENT BY 1) PRIMARY KEY,
  name VARCHAR(200),
  venue VARCHAR(200),
  conferenceTime VARCHAR(50),
  organizer VARCHAR(100),
  website VARCHAR(200),
  emails VARCHAR(1000)
);

CREATE TABLE presentations (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 10000, INCREMENT BY 1) PRIMARY KEY,
  userId INTEGER,
  conferenceId INTEGER,
  title VARCHAR(300),
  authors VARCHAR(200),
  fieldId INTEGER,
  abs VARCHAR(2000)
);