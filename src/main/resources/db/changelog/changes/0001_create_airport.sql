-- liquibase formatted sql

-- changeset gita:1

CREATE TABLE airport
(
    airport VARCHAR(250) PRIMARY KEY,
    country VARCHAR(250) NOT NULL,
    city    VARCHAR(250) NOT NULL
);