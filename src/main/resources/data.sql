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

--Create Course
INSERT INTO COURSE (duration, description, name, nickname) VALUES (60,'API With Spring Boot','API REST - Spring Boot','Spring Boot REST');
INSERT INTO COURSE (duration, description, name, nickname) VALUES (60,'CSS Evolution with SASS','SASS - Super CSS','SASS');
INSERT INTO COURSE (duration, description, name, nickname) VALUES (60,'Front-end with React','SPA with React','React on Front');

--Course - Author

INSERT INTO COURSE_USER (course_id,user_id) VALUES (
	(SELECT id FROM COURSE WHERE name = 'API REST - Spring Boot'),
	(SELECT id FROM USER WHERE name='Joao')
);

INSERT INTO COURSE_USER (course_id,user_id) VALUES (
	(SELECT id FROM COURSE WHERE name = 'SASS - Super CSS'),
	(SELECT id FROM USER WHERE name='Joao')
);

INSERT INTO COURSE_USER (course_id,user_id) VALUES (
	(SELECT id FROM COURSE WHERE name = 'SPA with React'),
	(SELECT id FROM USER WHERE name='Joao')
);

--Create Subject

INSERT INTO SUBJECT (sequence, title, course_id) VALUES (1,'WEEK 1',1);
INSERT INTO SUBJECT (sequence, title, course_id) VALUES (2,'WEEK 2',1);
INSERT INTO SUBJECT (sequence, title, course_id) VALUES (3,'WEEK 3',1);
INSERT INTO SUBJECT (sequence, title, course_id) VALUES (4,'WEEK 4',1);

INSERT INTO SUBJECT (sequence, title, course_id) VALUES (1,'WEEK 1',2);
INSERT INTO SUBJECT (sequence, title, course_id) VALUES (2,'WEEK 2',2);
INSERT INTO SUBJECT (sequence, title, course_id) VALUES (3,'WEEK 3',2);
INSERT INTO SUBJECT (sequence, title, course_id) VALUES (4,'WEEK 4',2);

INSERT INTO SUBJECT (sequence, title, course_id) VALUES (1,'WEEK 1',3);
INSERT INTO SUBJECT (sequence, title, course_id) VALUES (2,'WEEK 2',3);
INSERT INTO SUBJECT (sequence, title, course_id) VALUES (3,'WEEK 3',3);
INSERT INTO SUBJECT (sequence, title, course_id) VALUES (4,'WEEK 4',3);

--Create Ativities
INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 1', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 1'));
INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 2', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 1'));
INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 3', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 1'));
INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 4', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 1'));

INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 1', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 2'));
INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 2', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 2'));
INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 3', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 2'));
INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 4', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 2'));

INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 1', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 3'));
INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 2', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 3'));
INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 3', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 3'));
INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 4', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 3'));

INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 1', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 4'));
INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 2', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 4'));
INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 3', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 4'));
INSERT INTO ACTIVITY (title,subject_id ) VALUES ('Activity 4', (SELECT id FROM SUBJECT WHERE course_id = 1 AND title ='WEEK 4'));
