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
    course_name        VARCHAR(50)  NOT NULL,
    course_description VARCHAR(200) NOT NULL,
    department_id INT REFERENCES departments (department_id)
);

CREATE TABLE IF NOT EXISTS students
(
    id INT PRIMARY KEY,
    group_id  INT REFERENCES groups (group_id),
    date_of_birth DATE,
    year_of_study INT,
    first_name VARCHAR(25),
    last_name  VARCHAR(25),
    email VARCHAR(50) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    role_id INT REFERENCES roles (id),
    username VARCHAR(30) NOT NULL,
    password TEXT        NOT NULL
);

CREATE TABLE IF NOT EXISTS teachers
(
    id INT PRIMARY KEY,
    date_of_birth DATE,
    department_id INT REFERENCES departments (department_id),
    salary FLOAT,
    first_name VARCHAR(25),
    last_name VARCHAR(25),
    email VARCHAR(50) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    role_id INT REFERENCES roles (id),
    username VARCHAR(30) NOT NULL,
    password TEXT        NOT NULL
);

CREATE TABLE IF NOT EXISTS lessons
(
    lesson_id SERIAL PRIMARY KEY,
    teacher_id INT REFERENCES teachers (id),
    course_id INT REFERENCES courses (course_id),
    group_id INT REFERENCES groups (group_id),
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    location VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS administrators
(
    id INT PRIMARY KEY,
    first_name VARCHAR(25),
    last_name VARCHAR(25),
    email VARCHAR(50) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    role_id INT REFERENCES roles (id),
    username VARCHAR(30) NOT NULL,
    password TEXT        NOT NULL
);

CREATE TABLE IF NOT EXISTS student_courses
(
    student_id INT REFERENCES students (id) ON DELETE CASCADE,
    course_id  INT REFERENCES courses (course_id),
    PRIMARY KEY (student_id, course_id)
);

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