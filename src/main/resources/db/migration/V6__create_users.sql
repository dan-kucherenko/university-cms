CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(30) NOT NULL,
    first_name VARCHAR(25),
    last_name  VARCHAR(25),
    email      VARCHAR(50) NOT NULL,
    phone      VARCHAR(25) NOT NULL,
    password   TEXT NOT NULL,
    role_id    INT REFERENCES roles (id)
);

INSERT INTO users (username, first_name, last_name, email, phone, password)
VALUES ('user_admin', 'ADMIN_FN', 'ADMIN_SURNAME', 'john.doe@example.com', '123-456-7890', 'admin'),
       ('user1', 'John', 'Doe', 'john.doe@example.com', '123-456-7890', 'password1'),
       ('user2', 'Jane', 'Smith', 'jane.smith@example.com', '987-654-3210', 'password2'),
       ('user3', 'Bob', 'Johnson', 'bob.johnson@example.com', '555-123-4567', 'password3'),
       ('user4', 'Alice', 'Williams', 'alice.williams@example.com', '111-222-3333', 'password4'),
       ('user5', 'Charlie', 'Brown', 'charlie.brown@example.com', '999-888-7777', 'password5'),
       ('user6', 'Eva', 'Davis', 'eva.davis@example.com', '444-555-6666', 'password6'),
       ('user7', 'Frank', 'Miller', 'frank.miller@example.com', '777-666-5555', 'password7'),
       ('user8', 'Grace', 'Anderson', 'grace.anderson@example.com', '333-999-1111', 'password8'),
       ('user9', 'Harry', 'Taylor', 'harry.taylor@example.com', '222-444-8888', 'password9'),
       ('user10', 'Ivy', 'Clark', 'ivy.clark@example.com', '666-333-2222', 'password10');