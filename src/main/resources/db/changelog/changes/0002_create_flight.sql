-- liquibase formatted sql

-- changeset gita:2

CREATE TABLE flight
(
    id serial PRIMARY KEY,
    from_id VARCHAR(100) NOT NULL,
    to_id VARCHAR(100) NOT NULL,
    carrier VARCHAR(100) NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    CONSTRAINT flight_from_id_fkey foreign KEY(from_id) REFERENCES airport(airport),
    CONSTRAINT flight_to_id_fkey foreign KEY(to_id) REFERENCES airport(airport)
);