INSERT INTO researchFields (id, field) VALUES (1, 'Physics');
INSERT INTO researchFields (id, field) VALUES (2, 'Chemistry');
INSERT INTO researchFields (id, field) VALUES (3, 'Biology');
INSERT INTO researchFields (id, field) VALUES (4, 'Material Science and Engineering');
INSERT INTO researchFields (id, field) VALUES (5, 'Chemical Engineering');
INSERT INTO researchFields (id, field) VALUES (6, 'Electrical Engineering');

INSERT INTO users (name, email, field) VALUES ('Jianyong Ouyang', 'ouyang58@gmail.com', 'Chemistry, Material Science and Engineering');
INSERT INTO users (name, email, field) VALUES ('Xuezhong Jiang', 'jiangxzs@yahoo.com', 'Chemistry, Chemical Engineering');
INSERT INTO users (name, email, field) VALUES ('Nanjing Zhang', 'njzhang@yahoo.com', 'Chemistry, Biology');

INSERT INTO conferences (name, venue, conferenceTime, organizer) VALUES ('Spring 2016 National Meeting', 'San Diego', 'March 13-17, 2016', 'ACS');
INSERT INTO conferences (name, venue, conferenceTime, organizer) VALUES ('INNOVATION FROM DISCOVERY TO APPLICATION', 'Boston', 'August 16-20, 2015', 'ACS');

INSERT INTO presentations (userId, conferenceId, title, authors, fieldId, abstract) VALUES (100, 1001, 'my presentation', 'AA, BB, CC', 2, 'this is the abstract');
INSERT INTO presentations (userId, conferenceId, title, authors, fieldId, abstract) VALUES (101, 1000, 'my presentation2', '面包机', 2, 'this is the abstract2‘);


