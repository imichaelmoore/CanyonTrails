CREATE SCHEMA canyontrails;

CREATE TABLE users (
  uid char(36) PRIMARY KEY,
  password_hash char(60),
  name varchar(255),
  email varchar(255)
);
CREATE UNIQUE INDEX users_email ON users(email);

DROP TABLE trails;
CREATE TABLE trails (
  id char(36) PRIMARY KEY,
  owner_uid char(36),
  name VARCHAR(255),
  description LONG VARCHAR,
  gpx CLOB);
CREATE INDEX trails_owner_uid ON trails(owner_uid);

CREATE TABLE images (
  id char(36) PRIMARY KEY,
  owner_uid char(36),
  uploaded TIMESTAMP,
  data BINARY LARGE OBJECT
);
CREATE INDEX images_owner_uid ON images(owner_uid);

CREATE TABLE images_to_trails (
  image_id CHAR(36) PRIMARY KEY,
  trail_id CHAR(36)
);
CREATE INDEX i2t_trail_id ON images_to_trails(trail_id);


CREATE TABLE timeline (
  owner_uid char(36),
  update_text LONG VARCHAR,
  trail_id CHAR(36)  DEFAULT NULL
);
CREATE INDEX timeline_owner_uid ON timeline(owner_uid);
CREATE INDEX timeline_trail_id ON timeline(trail_id);
