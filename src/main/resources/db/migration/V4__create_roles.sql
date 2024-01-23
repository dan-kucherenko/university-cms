CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO roles (name)
VALUES ('TEACHER'),
       ('ADMIN'),
       ('SUPERADMIN'),
       ('STUDENT'),
       ('USER');
