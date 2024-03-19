DROP TABLE IF EXISTS rider_type;
DROP TABLE IF EXISTS type;
DROP TABLE IF EXISTS rider;
DROP TABLE IF EXISTS team;

CREATE TABLE team (
team_id int NOT NULL AUTO_INCREMENT,
team_name varchar(256) NOT NULL,
team_nationality varchar(256),
PRIMARY KEY (team_id)
);

CREATE TABLE rider (
rider_id int NOT NULL AUTO_INCREMENT,
first_name varchar(50) NOT NULL,
last_name varchar(50) NOT NULL,
rider_nationality varchar(50),
team_id int NULL,
PRIMARY KEY (rider_id),
FOREIGN KEY (team_id) REFERENCES team (team_id)
);

CREATE TABLE type (
type_id int NOT NULL AUTO_INCREMENT,
type_name varchar(50) NOT NULL,
PRIMARY KEY (type_id)
);

CREATE TABLE rider_type(
rider_id int NOT NULL,
type_id int NOT NULL,
FOREIGN KEY (rider_id) REFERENCES rider (rider_id) ON DELETE CASCADE,
FOREIGN KEY (type_id) REFERENCES type (type_id) ON DELETE CASCADE
);