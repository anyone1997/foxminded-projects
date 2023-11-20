package com.foxminded.romangrygorczuk.dao;

import com.foxminded.romangrygorczuk.model.Student;
import com.foxminded.romangrygorczuk.school.ConnectionProvider;
import com.foxminded.romangrygorczuk.school.ScriptExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JdbcStudentDaoTest {

    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM students";

    StudentDao studentDao;
    ConnectionProvider connectionProvider;
    ScriptExecutor scriptRunner;

    @BeforeEach
    void initialize() throws SQLException, IOException {
        connectionProvider = new ConnectionProvider();
        studentDao = new JdbcStudentDao(connectionProvider);
        scriptRunner = new ScriptExecutor(connectionProvider);
        scriptRunner.run("schema.sql");
        scriptRunner.run("data.sql");
    }

    @Test
    void getByCourseName_shouldReturnAllStudentsRelatedToCourse_whenCourseNameIsProvided() {
        List<Student> actual = studentDao.getByCourseName("biology");
        List<Student> expected = new ArrayList<>();
        expected.add(new Student("Hayleigh", "Byrd"));
        expected.add(new Student("Ranulph", "Harris"));

        assertEquals(expected, actual);
    }

    @Test
    void create_shouldCreateStudentInDB_whenStudentIsProvided() throws SQLException {
        Student student = new Student("Many", "Paquao");
        studentDao.create(student);

        List<Student> actual = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                actual.add(new Student(resultSet.getString(3), resultSet.getString(4)));
            }
        }

        List<Student> expected = new ArrayList<>();
        expected.add(new Student("Ricky", "Guerrero"));
        expected.add(new Student("Ricky", "Bolton"));
        expected.add(new Student("Ranulph", "Bolton"));
        expected.add(new Student("Hayleigh", "Byrd"));
        expected.add(new Student("Ranulph", "Harris"));
        expected.add(new Student("Many", "Paquao"));

        assertEquals(expected, actual);
    }

    @Test
    void remove_shouldRemoveStudentFromDatabase_whenStudentDeleted() throws SQLException {
        studentDao.remove(3);
        List<Student> actual = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                actual.add(new Student(resultSet.getString(3), resultSet.getString(4)));
            }
        }

        List<Student> expected = new ArrayList<>();
        expected.add(new Student("Ricky", "Guerrero"));
        expected.add(new Student("Ricky", "Bolton"));
        expected.add(new Student("Hayleigh", "Byrd"));
        expected.add(new Student("Ranulph", "Harris"));

        assertEquals(expected, actual);
    }

    @Test
    void getStudents_shouldReturnAllStudents_whenMethodIsRunning() throws SQLException {
        studentDao.getStudents();
        List<Student> actual = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                actual.add(new Student(resultSet.getString(3), resultSet.getString(4)));
            }
        }

        List<Student> expected = new ArrayList<>();
        expected.add(new Student("Ricky", "Guerrero"));
        expected.add(new Student("Ricky", "Bolton"));
        expected.add(new Student("Ranulph", "Bolton"));
        expected.add(new Student("Hayleigh", "Byrd"));
        expected.add(new Student("Ranulph", "Harris"));

        assertEquals(expected, actual);
    }
}