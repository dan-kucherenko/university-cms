CREATE SCHEMA IF NOT EXISTS university;
CREATE TABlE IF NOT EXISTS groups
(
    group_id   SERIAL PRIMARY KEY,
    group_name VARCHAR(25) NOT NULL
    );
CREATE TABLE IF NOT EXISTS courses
(
    course_id          SERIAL PRIMARY KEY,
    course_name        VARCHAR(50)  NOT NULL,
    course_description VARCHAR(200) NOT NULL
    );

CREATE TABLE IF NOT EXISTS students
(
    student_id SERIAL PRIMARY KEY,
    group_id   INT,
    first_name VARCHAR(25) NOT NULL,
    last_name  VARCHAR(25) NOT NULL
    );

CREATE TABLE IF NOT EXISTS teachers
(
    teacher_id SERIAL PRIMARY KEY,
    department  VARCHAR(25) NOT NULL,
    salary  INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS departments
(
    department_id SERIAL PRIMARY KEY,
    department_name  VARCHAR(25) NOT NULL,
    department_sphere VARCHAR(25) NOT NULL
    );

CREATE TABLE IF NOT EXISTS lessons
(
    lesson_id SERIAL PRIMARY KEY,
    group_id  INT REFERENCES groups (group_id),
    day_of_week VARCHAR(25) NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    location VARCHAR(25) NOT NULL,
    course INT REFERENCES courses (course_id)
    );


CREATE TABLE IF NOT EXISTS student_courses
(
    student_id INT REFERENCES students (student_id) ON DELETE CASCADE,
    course_id  INT REFERENCES courses (course_id),
    PRIMARY KEY (student_id, course_id)
    );


