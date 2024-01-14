CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(30) NOT NULL,
    first_name VARCHAR(25),
    last_name  VARCHAR(25),
    email      VARCHAR(50) NOT NULL,
    phone      VARCHAR(25) NOT NULL,
    password   TEXT        NOT NULL,
    role_id    INT REFERENCES roles (id)
);

INSERT INTO users (username, first_name, last_name, email, phone, password, role_id)
VALUES ('superadmin', 'ADMIN_FN', 'ADMIN_SURNAME', 'super.admin@example.com', '123-456-7890',
        '$2a$12$dCWlj1W7ZH5pym3cEHXdx.5fvuFr8xaDkd4zcLuBvYnZkG1cn4DMu', 4),
       ('user1', 'John', 'Doe', 'john.doe@example.com', '123-456-7890', '$2a$12$/3E4ASQ5vkAHoh3X6hVOTelwpENRDFA8Q1QfbRYIn.9.fv3wobqZi
', 6),
       ('user2', 'Jane', 'Smith', 'jane.smith@example.com', '987-654-3210', '$2a$12$GE8vgNjrYOQaBI.ALayO0eRjyUW/Nn3cSjtgENkYYFKL5EF0lxfLO
', 6),
       ('user3', 'Bob', 'Johnson', 'bob.johnson@example.com', '555-123-4567', '$2a$12$DILn2xa1FV3Upy7o4uCFD.9lkkW0jpEqzZWBJaoE/gMpL6fqql4IS
', 6),
       ('user4', 'Alice', 'Williams', 'alice.williams@example.com', '111-222-3333', '$2a$12$dtB4TGzUPdMamxj5WEUH.ezNYo2B09iKFcV/kM0PU.d5NKBq07ws6
', 6),
       ('user5', 'Charlie', 'Brown', 'charlie.brown@example.com', '999-888-7777', '$2a$12$poPT49YGYKP8ZzmZfTn4hOootow4mBc1M6hREW9LQO7BJj/lX/N02
', 6),
       ('user6', 'Eva', 'Davis', 'eva.davis@example.com', '444-555-6666', '$2a$12$GJPoactVKyla10Rs2uD3ieuDU2UZhm0x0KrYu3xjL.QG4fQUFxLbi
', 6),
       ('user7', 'Frank', 'Miller', 'frank.miller@example.com', '777-666-5555', '$2a$12$RDQsN7ZGkPOR7i.ZpxXdpe6GCyWVU4g2RbWImnDskxpaFOH8IQViG
', 6),
       ('user8', 'Grace', 'Anderson', 'grace.anderson@example.com', '333-999-1111', '$2a$12$IDuBj1GKPw2XjvOZKsn9Gu6DwyqpwhoxwM109l6f/8YYTO5KPc6p.
', 6),
       ('user9', 'Harry', 'Taylor', 'harry.taylor@example.com', '222-444-8888', '$2a$12$D8qbyeAxCgn2.WtYSHhS5edxfYz4GYx2SwmB2Y0XfyFk5SARKp.8y
', 6),
       ('user10', 'Ivy', 'Clark', 'ivy.clark@example.com', '666-333-2222', '$2a$12$Y1wsJ70J79Pvn0hAWE7QLOsZ7MhwqpZUI2icBGws9G1lErmjGiPTa
', 6);