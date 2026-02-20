CREATE DATABASE bankdb;

USE bankdb;

CREATE TABLE account (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    contact BIGINT UNIQUE,
    pin INT,
    balance DOUBLE
);

CREATE TABLE transactions (
    tid INT PRIMARY KEY AUTO_INCREMENT,
    contact BIGINT,
    type VARCHAR(20),
    amount DOUBLE,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
