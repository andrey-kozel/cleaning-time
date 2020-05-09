CREATE TABLE users
(

    id       BIGSERIAL   NOT NULL PRIMARY KEY,
    email    TEXT UNIQUE NOT NULL,
    password TEXT        NOT NULL

);

CREATE TABLE communities
(

    id   BIGSERIAL NOT NULL PRIMARY KEY,
    name TEXT      NOT NULL

);

CREATE TABLE community_members
(

    id                  BIGSERIAL NOT NULL PRIMARY KEY,
    application_user_id BIGINT    NOT NULL,
    community_id        BIGINT    NOT NULL,
    type                TEXT CHECK ( type IN ('OWNER', 'MEMBER') ),

    UNIQUE (application_user_id, community_id),

    CONSTRAINT application_user_id_fk FOREIGN KEY (application_user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE,

    CONSTRAINT community_id_fk FOREIGN KEY (community_id)
        REFERENCES communities (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE

);