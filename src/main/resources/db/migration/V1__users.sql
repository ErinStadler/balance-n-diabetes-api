DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id   BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
   username  VARCHAR(255) NOT NULL,
   email  VARCHAR(255) NOT NULL UNIQUE,
   password  VARCHAR(255) NOT NULL
);