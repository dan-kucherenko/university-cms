ALTER TABLE students
    ADD COLUMN username VARCHAR(30) NOT NULL,
    ADD COLUMN password TEXT        NOT NULL;

ALTER TABLE teachers
    ADD COLUMN username VARCHAR(30) NOT NULL,
    ADD COLUMN password TEXT        NOT NULL;

ALTER TABLE administrators
    ADD COLUMN username VARCHAR(30) NOT NULL,
    ADD COLUMN password TEXT        NOT NULL;


INSERT INTO students (id, username, first_name, last_name, email, phone, password, role_id)
VALUES (2, 'user1', 'John', 'Doe', 'john.doe@example.com', '123-456-7890',
        '$2a$12$/3E4ASQ5vkAHoh3X6hVOTelwpENRDFA8Q1QfbRYIn.9.fv3wobqZi', 4),
       (3, 'user2', 'Jane', 'Smith', 'jane.smith@example.com', '987-654-3210',
        '$2a$12$GE8vgNjrYOQaBI.ALayO0eRjyUW/Nn3cSjtgENkYYFKL5EF0lxfLO', 4),
       (4, 'user3', 'Bob', 'Johnson', 'bob.johnson@example.com', '555-123-4567',
        '$2a$12$DILn2xa1FV3Upy7o4uCFD.9lkkW0jpEqzZWBJaoE/gMpL6fqql4IS', 4);

INSERT INTO administrators (id, username, first_name, last_name, email, phone, password, role_id)
VALUES (5, 'user3', 'Bob', 'Johnson', 'bob.johnson@example.com', '555-123-4567',
        '$2a$12$DILn2xa1FV3Upy7o4uCFD.9lkkW0jpEqzZWBJaoE/gMpL6fqql4IS', 2),
       (6, 'user4', 'Alice', 'Williams', 'alice.williams@example.com', '111-222-3333',
        '$2a$12$dtB4TGzUPdMamxj5WEUH.ezNYo2B09iKFcV/kM0PU.d5NKBq07ws6', 2),
       (7, 'user5', 'Charlie', 'Brown', 'charlie.brown@example.com', '999-888-7777',
        '$2a$12$poPT49YGYKP8ZzmZfTn4hOootow4mBc1M6hREW9LQO7BJj/lX/N02', 2);

INSERT INTO teachers (id, username, first_name, last_name, email, phone, password, role_id)
VALUES (8, 'user6', 'Eva', 'Davis', 'eva.davis@example.com', '444-555-6666',
        '$2a$12$GJPoactVKyla10Rs2uD3ieuDU2UZhm0x0KrYu3xjL.QG4fQUFxLbi', 1),
       (9, 'user7', 'Frank', 'Miller', 'frank.miller@example.com', '777-666-5555',
        '$2a$12$RDQsN7ZGkPOR7i.ZpxXdpe6GCyWVU4g2RbWImnDskxpaFOH8IQViG', 1)