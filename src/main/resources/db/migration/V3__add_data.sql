INSERT INTO groups (group_name, group_faculty, group_speciality)
VALUES ('Group D', 'Business', 'Marketing'),
       ('Group E', 'Engineering', 'Electrical Engineering'),
       ('Group F', 'Science', 'Biology');

INSERT INTO departments (department_name, department_sphere)
VALUES ('Bus', 'Business and Economics'),
       ('EE', 'Engineering'),
       ('Bio', 'Life Sciences');

INSERT INTO courses (course_name, course_description, department_id)
VALUES ('Business Ethics', 'Principles of ethical business practices', null),
       ('Electromagnetism', 'Study of electromagnetic fields and forces', 2),
       ('Cell Biology', 'Fundamentals of cellular structure and function', null);
