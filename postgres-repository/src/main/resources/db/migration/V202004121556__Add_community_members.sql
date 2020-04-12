CREATE TABLE communities_member
(

    application_user_id BIGINT NOT NULL,
    community_id        BIGINT NOT NULL,
    type                TEXT CHECK ( type IN ('OWNER', 'MEMBER') ),

    PRIMARY KEY (application_user_id, community_id),
    CONSTRAINT application_user_id_fk FOREIGN KEY (application_user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT community_id_fk FOREIGN KEY (community_id)
        REFERENCES communities (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE

);