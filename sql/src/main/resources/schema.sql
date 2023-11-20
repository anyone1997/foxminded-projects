DROP TABLE IF EXISTS courses, groups, students, students_courses;

CREATE TABLE groups
(
    group_id   SERIAL PRIMARY KEY,
    group_name TEXT
);
CREATE TABLE students
(
    student_id SERIAL PRIMARY KEY,
    group_id   INTEGER,
    first_name TEXT,
    last_name  TEXT,

    CONSTRAINT fk_group
        FOREIGN KEY (group_id)
            REFERENCES groups (group_id) ON DELETE CASCADE
);

CREATE TABLE courses
(
    course_id          SERIAL PRIMARY KEY,
    course_name        TEXT,
    course_description TEXT
);
CREATE TABLE students_courses
(
    student_id INT,
    course_id  INT,
    UNIQUE (student_id, course_id),
    CONSTRAINT FK_student
        FOREIGN KEY (student_id) REFERENCES students (student_id) ON DELETE CASCADE,
    CONSTRAINT FK_course
        FOREIGN KEY (course_id) REFERENCES courses (course_id)
);