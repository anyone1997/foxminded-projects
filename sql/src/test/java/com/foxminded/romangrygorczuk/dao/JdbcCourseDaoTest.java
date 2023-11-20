package com.foxminded.romangrygorczuk.dao;

import com.foxminded.romangrygorczuk.model.Course;
import com.foxminded.romangrygorczuk.school.ConnectionProvider;
import com.foxminded.romangrygorczuk.school.ScriptExecutor;
import com.foxminded.romangrygorczuk.school.StudentsCourses;
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

class JdbcCourseDaoTest {

    private static final String SELECT_ALL_COURSES = "SELECT * FROM courses";
    private static final String SELECT_ALL_STUDENTS_COURSES = "SELECT * FROM students_courses";

    CourseDao courseDao;
    ConnectionProvider connectionProvider;
    ScriptExecutor scriptRunner;

    @BeforeEach
    void initialize() throws SQLException, IOException {
        connectionProvider = new ConnectionProvider();
        courseDao = new JdbcCourseDao(connectionProvider);
        scriptRunner = new ScriptExecutor(connectionProvider);
        scriptRunner.run("schema.sql");
        scriptRunner.run("data.sql");
    }

    @Test
    void create_shouldCreateCourse_whenCourseIsProvided() throws SQLException {
        Course course = new Course("gym", "get big");
        courseDao.create(course);

        List<Course> actual = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COURSES);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                actual.add(new Course(resultSet.getString(2), resultSet.getString(3)));
            }
        }

        List<Course> expected = new ArrayList<>();
        expected.add(new Course("math", "learn calculations"));
        expected.add(new Course("biology", "human being"));
        expected.add(new Course("gym", "get big"));

        assertEquals(expected, actual);
    }

    @Test
    void addStudentToTheCourse_shouldAddStudentToCourse_whenStudentWasAdded() throws SQLException {
        courseDao.addStudentToCourse(1, 2);
        List<StudentsCourses> actual = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS_COURSES)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                actual.add(new StudentsCourses(resultSet.getInt(1), resultSet.getInt(2)));
            }
        }

        List<StudentsCourses> expected = new ArrayList<>();
        expected.add(new StudentsCourses(1, 1));
        expected.add(new StudentsCourses(2, 1));
        expected.add(new StudentsCourses(3, 1));
        expected.add(new StudentsCourses(4, 2));
        expected.add(new StudentsCourses(5, 2));
        expected.add(new StudentsCourses(1, 2));

        assertEquals(expected, actual);
    }

    @Test
    void removeStudentFromCourses_shouldRemoveStudentFromCourse_whenStudentRemoved() throws SQLException {
        courseDao.removeStudentFromCourse(1, 1);

        List<StudentsCourses> actual = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS_COURSES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                actual.add(new StudentsCourses(resultSet.getInt(1), resultSet.getInt(2)));
            }
        }

        List<StudentsCourses> expected = new ArrayList<>();
        expected.add(new StudentsCourses(2, 1));
        expected.add(new StudentsCourses(3, 1));
        expected.add(new StudentsCourses(4, 2));
        expected.add(new StudentsCourses(5, 2));

        assertEquals(expected, actual);
    }

    @Test
    void getAll_shouldReturnUnassignedCourses_whenStudentIdProvided() throws SQLException {
        courseDao.getAll();
        List<Course> actual = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COURSES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                actual.add(new Course(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
        }

        List<Course> expected = new ArrayList<>();
        expected.add(new Course(2, "biology", "human being"));
    }

    @Test
    void getByStudentId_shouldReturnAssignedCourses_whenStudentIdProvided() throws SQLException {
        courseDao.getByStudentId(2);

        List<Course> actual = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COURSES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                actual.add(new Course(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
        }

        List<Course> expected = new ArrayList<>();
        expected.add(new Course(1, "math", "learn calculations"));
    }
}