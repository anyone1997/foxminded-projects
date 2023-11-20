package com.foxminded.romangrygorczuk.dao;

import com.foxminded.romangrygorczuk.model.Course;
import com.foxminded.romangrygorczuk.school.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCourseDao implements CourseDao {

    private static final String INSERT_SQL = "INSERT INTO courses (course_name, course_description) VALUES (?,?)";
    private static final String ADD_STUDENT_TO_COURSE = "INSERT INTO students_courses (student_id, course_id) VALUES (?,?)";
    private static final String REMOVE_STUDENT_FROM_COURSE = "DELETE FROM students_courses WHERE student_id=? AND course_id=?";
    private static final String ALL_COURSES = "SELECT courses.course_id, courses.course_name, courses.course_description FROM courses;";
    private static final String ALL_ASSIGNED_COURSES = "SELECT courses.course_id, courses.course_name, courses.course_description FROM courses INNER JOIN students_courses sc on courses.course_id = sc.course_id WHERE student_id=?;";

    private ConnectionProvider connectionProvider;

    public JdbcCourseDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public void create(Course course) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                course.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Course creation failed");
        }
    }

    public void addStudentToCourse(int studentId, int courseId) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_STUDENT_TO_COURSE)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Adding student to the course has failed!", e);
        }
    }

    public void removeStudentFromCourse(int studentId, int courseId) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_STUDENT_FROM_COURSE)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Removing the student from courses has failed!", e);
        }
    }

    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ALL_COURSES)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                courses.add(new Course(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Getting courses has failed!", e);
        }

        return courses;
    }

    public List<Course> getByStudentId(int studentId) {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ALL_ASSIGNED_COURSES)) {
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                courses.add(new Course(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Getting courses has failed!", e);
        }
        return courses;
    }
}