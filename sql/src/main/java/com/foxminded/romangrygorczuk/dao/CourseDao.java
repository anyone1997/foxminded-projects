package com.foxminded.romangrygorczuk.dao;

import com.foxminded.romangrygorczuk.model.Course;

import java.util.List;

public interface CourseDao {

    void create(Course course);

    void addStudentToCourse(int studentId, int courseId);

    void removeStudentFromCourse(int studentId, int courseId);

    List<Course> getAll();

    List<Course> getByStudentId(int studentId);
}
