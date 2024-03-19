INSERT INTO team (
team_name,
team_nationality)
VALUES (
'Alpecin- Deceuninck',
'Belgium'
);

INSERT INTO team (
team_name,
team_nationality)
VALUES (
'Arkea- B&B Hotels',
'France'
);

INSERT INTO rider (
first_name,
last_name,
rider_nationality,
team_id)
VALUES (
'Mathieu',
'Van Der Poel',
'Netherlands',
'1'
);

INSERT INTO rider (
first_name,
last_name,
rider_nationality,
team_id)
VALUES (
'Kaden',
'Groves',
'Australia',
'1'
);

INSERT INTO rider (
first_name,
last_name,
rider_nationality,
team_id)
VALUES (
'Arnaud',
'Demare',
'France',
'2'
);

INSERT INTO type (type_name) VALUES ('Classics Specialist');
INSERT INTO type (type_name) VALUES ('Stage Hunter');
INSERT INTO type (type_name) VALUES ('Puncheur');
INSERT INTO type (type_name) VALUES ('Sprinter');

INSERT INTO rider_type (
rider_id,
type_id)
VALUES 
('1', '1'),
('1', '2'),
('1', '3'),
('2', '4'),
('3', '4');

