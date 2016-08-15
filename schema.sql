CREATE SCHEMA canyontrails;

CREATE TABLE users (
  uid char(36),
  password_hash char(60),
  name varchar(255),
  email varchar(255)
);