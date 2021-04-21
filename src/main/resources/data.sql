--Create User Profiles

INSERT INTO USER_PROFILE (name) VALUES ('Teacher');
INSERT INTO USER_PROFILE (name) VALUES ('Tutor');
INSERT INTO USER_PROFILE (name) VALUES ('Student');

--Create User - PassWord '12345'

INSERT INTO USER (name,email,password,expired,locked, enabled) VALUES('Joao','teacher@email.com', '$2a$10$5OjdPg5nbY8Ct3oLCBXP5.7dRAPWLteY4.RfLeERu1paBKn3JNkLG',0,0,1);
INSERT INTO USER (name,email,password,expired,locked, enabled) VALUES('Maria','tutor@email.com', '$2a$10$5OjdPg5nbY8Ct3oLCBXP5.7dRAPWLteY4.RfLeERu1paBKn3JNkLG',0,0,1);
INSERT INTO USER (name,email,password,expired,locked, enabled) VALUES('Dario','student@email.com', '$2a$10$5OjdPg5nbY8Ct3oLCBXP5.7dRAPWLteY4.RfLeERu1paBKn3JNkLG',0,0,1);

--Set UserProfiles for Users

INSERT INTO USER_PROFILES (user_id,profiles_id) VALUES (
	SELECT id FROM USER WHERE name ='Joao',
	SELECT id FROM USER_PROFILE WHERE name='Teacher'
);
INSERT INTO USER_PROFILES (user_id,profiles_id) VALUES (
	SELECT id FROM USER WHERE name ='Maria',
	SELECT id FROM USER_PROFILE WHERE name='Tutor'
);
INSERT INTO USER_PROFILES (user_id,profiles_id) VALUES (
	SELECT id FROM USER WHERE name ='Dario',
	SELECT id FROM USER_PROFILE WHERE name='Student'
);