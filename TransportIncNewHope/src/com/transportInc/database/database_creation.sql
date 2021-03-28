CREATE DATABASE transport_inc_new_hope;

CREATE TABLE vehicles (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` ENUM('BUS','AIRPLANE','TRAIN','CAR') NOT NULL,
    `type` ENUM('LAND','AIR','SEA') NOT NULL,
    price_per_kilometer DOUBLE NOT NULL,
    passenger_capacity INT NOT NULL
);
CREATE TABLE trips (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    starting_point VARCHAR(30) NOT NULL,
    destination VARCHAR(30) NOT NULL,
    distance INT NOT NULL,
	vehicle_name VARCHAR(30) NOT NULL
);
CREATE TABLE tickets (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    trip_destination VARCHAR(30) NOT NULL,
    price DOUBLE NOT NULL
);