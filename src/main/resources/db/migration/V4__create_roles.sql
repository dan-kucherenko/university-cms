CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO roles (name)
VALUES ('STAFF'),
       ('TEACHER'),
       ('ADMIN'),
       ('SUPERADMIN'),
       ('STUDENT'),
       ('USER');
