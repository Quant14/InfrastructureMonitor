DROP DATABASE IF EXISTS monitorDB;
CREATE DATABASE monitorDB;

USE monitorDB;

CREATE TABLE machine (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(20) NOT NULL,
    cpu_usage int,
    ram_usage int,
    disk_usage int
);