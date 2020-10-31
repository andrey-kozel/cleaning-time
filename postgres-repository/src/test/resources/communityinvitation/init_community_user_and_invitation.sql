INSERT INTO users (id, email, password)
VALUES (1, 'existing_email@email.email', 'password');

INSERT INTO communities (id, name)
VALUES (1, 'community_name1');

insert into community_user_invitations (id, community_id, application_user_id, status)
VALUES (1, 1, 1, 'SUBMITTED');