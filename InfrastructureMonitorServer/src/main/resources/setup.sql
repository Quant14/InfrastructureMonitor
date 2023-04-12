DROP DATABASE IF EXISTS monitorDB;
CREATE DATABASE monitorDB;

USE monitorDB;

CREATE TABLE machine (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    cpu_usage float,
    ram_usage float,
    disk_usage float
);