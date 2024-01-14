ALTER TABLE students
    ADD COLUMN username VARCHAR(30) NOT NULL,
    ADD COLUMN password TEXT        NOT NULL;

ALTER TABLE teachers
    ADD COLUMN username VARCHAR(30) NOT NULL,
    ADD COLUMN password TEXT        NOT NULL;

ALTER TABLE administrators
    ADD COLUMN username VARCHAR(30) NOT NULL,
    ADD COLUMN password TEXT        NOT NULL;