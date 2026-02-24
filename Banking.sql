CREATE DATABASE bankdb;

use bankdb;

CREATE TABLE account (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    contact VARCHAR(10) UNIQUE,
    pin INT,
    balance DOUBLE,
    CHECK (contact REGEXP '^[0-9]{10}$')
);

CREATE TABLE transactions (
    tid INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50),
    contact VARCHAR(10) UNIQUE,
    type VARCHAR(20),
    amount DOUBLE,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (contact) REFERENCES account(contact)
);

drop database bankdb; 
