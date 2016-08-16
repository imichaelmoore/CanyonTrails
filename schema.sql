DROP TABLE users;
DROP TABLE trails;
DROP TABLE images;
DROP TABLE images_to_trails;
DROP TABLE timeline;


CREATE TABLE users (
  uid           CHAR(36) PRIMARY KEY,
  password_hash CHAR(60),
  name          VARCHAR(255),
  email         VARCHAR(255)
);
CREATE UNIQUE INDEX users_email
  ON users (email);

CREATE TABLE trails (
  id          CHAR(36) PRIMARY KEY,
  owner_uid   CHAR(36),
  name        VARCHAR(255),
  description LONG VARCHAR,
  gpx         CLOB
);
CREATE INDEX trails_owner_uid
  ON trails (owner_uid);

DROP TABLE images;
CREATE TABLE images (
  id        CHAR(36) PRIMARY KEY,
  mimetype  VARCHAR(255),
  trail_id  CHAR(36),
  owner_uid CHAR(36),
  uploaded  TIMESTAMP,
  data      BINARY LARGE OBJECT
);
CREATE INDEX images_owner_uid
  ON images (owner_uid);
CREATE INDEX images_trail_id
  ON images (trail_id);

CREATE TABLE timeline (
  owner_uid   CHAR(36),
  mtime       TIMESTAMP DEFAULT current_timestamp,
  update_text LONG VARCHAR,
  trail_id    CHAR(36)  DEFAULT NULL
);
CREATE INDEX timeline_owner_uid
  ON timeline (owner_uid);
CREATE INDEX timeline_trail_id
  ON timeline (trail_id);

INSERT INTO users (UID, PASSWORD_HASH, NAME, EMAIL) VALUES
  ('18b9b4d6-a0ef-45f7-89eb-69232f97f676', '$2a$10$RAEX3PIR.bsI6f6O5CHm9.VNG8l3XNFBV5vq3FL96P8RmlWWwbatC',
   'Michael Moore', 'michaelmoore@gmail.com');
