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

CREATE TABLE community_user_invitations
(
    id                  BIGSERIAL NOT NULL PRIMARY KEY,
    community_id        BIGINT    NOT NULL,
    application_user_id BIGINT    NOT NULL,
    status              TEXT      NOT NULL CHECK ( status IN ('SUBMITTED', 'CONFIRMED', 'REJECTED', 'RESUBMITTED')),

    UNIQUE (community_id, application_user_id),

    CONSTRAINT application_user_id_fk FOREIGN KEY (application_user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE,

    CONSTRAINT community_id_fk FOREIGN KEY (community_id)
        REFERENCES communities (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE

);

CREATE TABLE community_user_invitations_log
(
    id            BIGSERIAL      NOT NULL PRIMARY KEY,
    invitation_id BIGINT         NOT NULL,
    status        TEXT           NOT NULL CHECK ( status IN ('SUBMITTED', 'CONFIRMED', 'REJECTED', 'RESUBMITTED')),
    submitted_at  TIMESTAMPTZ(3) NOT NULL,

    CONSTRAINT invitation_id_id_fk FOREIGN KEY (invitation_id)
        REFERENCES community_user_invitations (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE FUNCTION log_community_invitation() RETURNS trigger AS
$$
BEGIN
    INSERT INTO community_user_invitations_log (invitation_id, status, submitted_at)
    VALUES (new.id, new.status, CURRENT_TIMESTAMP(3));
    RETURN new;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER log_community_invitation
    AFTER UPDATE OR INSERT
    ON community_user_invitations
    FOR ROW
EXECUTE PROCEDURE log_community_invitation();