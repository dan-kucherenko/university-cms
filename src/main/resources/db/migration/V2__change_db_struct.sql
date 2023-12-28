ALTER TABLE IF EXISTS students
    RENAME COLUMN "group" TO group_id;

ALTER TABLE IF EXISTS teachers
    RENAME COLUMN firstName TO first_name;
ALTER TABLE IF EXISTS teachers
    RENAME COLUMN lastName TO last_name;
ALTER TABLE IF EXISTS courses
    ADD COLUMN department_id INT REFERENCES departments (department_id);