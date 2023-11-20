package com.foxminded.romangrygorczuk.dao;

import com.foxminded.romangrygorczuk.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {

    void create(Student student) throws SQLException;

    List<Student> getByCourseName(String courseName);

    void remove(int studentId) throws SQLException;

    List<Student> getStudents();
}
