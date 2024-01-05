ALTER TABLE students
    RENAME COLUMN student_id to id;
ALTER TABLE students
    ADD COLUMN role_id INT REFERENCES roles (id);

ALTER TABLE teachers
    RENAME COLUMN teacher_id to id;
ALTER TABLE teachers
    ADD COLUMN role_id INT REFERENCES roles (id);

ALTER TABLE administrators
    ADD COLUMN role_id INT REFERENCES roles (id);