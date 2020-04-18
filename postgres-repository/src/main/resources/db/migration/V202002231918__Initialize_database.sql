CREATE TABLE users
(

    id       BIGSERIAL NOT NULL PRIMARY KEY,
    email    TEXT UNIQUE DEFAULT NULL,
    password TEXT        DEFAULT NULL

);

CREATE TABLE communities
(

    id   BIGSERIAL NOT NULL PRIMARY KEY,
    name TEXT      NOT NULL

)