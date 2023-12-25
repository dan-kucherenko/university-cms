CREATE SCHEMA IF NOT EXISTS university;
CREATE TABlE IF NOT EXISTS groups
(
    group_id   SERIAL PRIMARY KEY,
    group_name VARCHAR(25) NOT NULL,
    group_faculty VARCHAR (25) NOT NULL,
    group_speciality VARCHAR (25) NOT NULL
    );


CREATE TABLE IF NOT EXISTS departments
(
    department_id SERIAL PRIMARY KEY,
    department_name  VARCHAR(25) NOT NULL,
    department_sphere VARCHAR(25) NOT NULL
    );

CREATE TABLE IF NOT EXISTS courses
(
    course_id          SERIAL PRIMARY KEY,
    department_id      INT REFERENCES departments (department_id),
    course_name        VARCHAR(50)  NOT NULL,
    course_description VARCHAR(200) NOT NULL
    );

CREATE TABLE IF NOT EXISTS students
(
    student_id SERIAL PRIMARY KEY,
    group_id  INT REFERENCES groups (group_id),
    first_name VARCHAR(25) NOT NULL,
    last_name  VARCHAR(25) NOT NULL,
    date_of_birth DATE,
    email VARCHAR(25),
    phone VARCHAR(25),
    year_of_study INT
    );

CREATE TABLE IF NOT EXISTS teachers
(
    teacher_id SERIAL PRIMARY KEY,
    first_name VARCHAR(25) NOT NULL,
    last_name VARCHAR(25) NOT NULL,
    date_of_birth DATE,
    email VARCHAR(25) NOT NULL,
    phone VARCHAR(25) NOT NULL,
    department_id INT REFERENCES departments (department_id),
    salary FLOAT
    );

CREATE TABLE IF NOT EXISTS lessons
(
    lesson_id SERIAL PRIMARY KEY,
    teacher_id INT REFERENCES teachers (teacher_id),
    course_id INT REFERENCES courses (course_id),
    group_id INT REFERENCES groups (group_id),
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    location VARCHAR(25) NOT NULL
    );

CREATE TABLE IF NOT EXISTS administrators
(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(25) NOT NULL,
    last_name VARCHAR(25) NOT NULL,
    email VARCHAR(25) NOT NULL,
    phone VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS student_courses
(
    student_id INT REFERENCES students (student_id) ON DELETE CASCADE,
    course_id  INT REFERENCES courses (course_id),
    PRIMARY KEY (student_id, course_id)
    );


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


INSERT INTO students (group_id, first_name, last_name, date_of_birth, email, phone, year_of_study)
VALUES (1, 'Eva', 'Williams', '1996-03-25', 'eva.williams@example.com', '555-111-2222', 4),
       (2, 'Charlie', 'Brown', '1999-09-05', 'charlie.brown@example.com', '555-333-4444', 2),
       (3, 'Sophie', 'Miller', '1997-06-12', 'sophie.miller@example.com', '555-555-6666', 3);

INSERT INTO teachers (first_name, last_name, date_of_birth, email, phone, department_id, salary)
VALUES ('Prof.', 'White', '1965-07-18', 'prof.white@example.com', '555-777-8888', 1, 75000.00),
       ('Dr.', 'Clark', '1982-12-03', 'dr.clark@example.com', '555-999-0000', 2, 80000.00),
       ('Mr.', 'Davis', '1978-04-30', 'mr.davis@example.com', '555-123-9876', 3, 70000.00);

INSERT INTO lessons (teacher_id, course_id, group_id, start_time, end_time, location)
VALUES (1, 1, 1, '10:00:00', '11:30:00', 'Room 401'),
       (2, 2, 2, '14:00:00', '15:30:00', 'Room 502'),
       (3, 3, 3, '16:00:00', '17:30:00', 'Room 603');

INSERT INTO administrators (first_name, last_name, email, phone)
VALUES ('Super', 'Admin', 'super.admin@example.com', '999-888-7777');

INSERT INTO student_courses (student_id, course_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);


