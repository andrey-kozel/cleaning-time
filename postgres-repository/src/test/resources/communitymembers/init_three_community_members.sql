INSERT INTO communities (id, name)
VALUES (1, 'community_name1');

INSERT INTO users (id, email, password)
VALUES (1, 'existing_email@email.email', 'password');
INSERT INTO community_members (application_user_id, community_id, type)
VALUES (1, 1, 'OWNER');


INSERT INTO users (id, email, password)
VALUES (2, 'existing_email2@email.email', 'password');
INSERT INTO community_members (application_user_id, community_id, type)
VALUES (2, 1, 'MEMBER');

INSERT INTO users (id, email, password)
VALUES (3, 'existing_email3@email.email', 'password');
INSERT INTO community_members (application_user_id, community_id, type)
VALUES (3, 1, 'MEMBER');