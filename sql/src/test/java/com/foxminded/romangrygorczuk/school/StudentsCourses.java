package com.foxminded.romangrygorczuk.school;

import java.util.Objects;

public class StudentsCourses {
    Integer studentId;
    Integer courseId;

    public StudentsCourses(Integer studentId, Integer courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentsCourses that = (StudentsCourses) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
    }
}
