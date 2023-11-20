package com.foxminded.romangrygorczuk.dao;

import com.foxminded.romangrygorczuk.model.Course;
import com.foxminded.romangrygorczuk.model.Student;
import com.foxminded.romangrygorczuk.school.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentDao implements StudentDao {

    private static final String INSERT_SQL = "INSERT INTO students (group_id, first_name, last_name) VALUES (?,?,?)";
    private static final String ADD_STUDENT_COURSE_QUERY = "INSERT INTO students_courses (student_id, course_id) VALUES (?,?)";
    private static final String FIND_ALL_STUDENTS = "SELECT students.student_id, students.first_name, students.last_name FROM students INNER JOIN students_courses on students.student_id = students_courses.student_id INNER JOIN courses on students_courses.course_id = courses.course_id WHERE courses.course_name=? ORDER BY students.student_id ASC";
    private static final String REMOVE_STUDENT = "DELETE FROM students WHERE student_id=?";
    private static final String ALL_STUDENTS = "SELECT student_id, first_name, last_name FROM students";

    private ConnectionProvider connectionProvider;

    public JdbcStudentDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public void create(Student student) throws SQLException {
        Connection connection = connectionProvider.getConnection();
        try (connection;
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedStatementForManyToMany = connection.prepareStatement(ADD_STUDENT_COURSE_QUERY)
        ) {
            connection.setAutoCommit(false);
            preparedStatement.setObject(1, student.getGroupId(), java.sql.Types.INTEGER);
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                student.setId(resultSet.getInt(1));
            }

            if (student.getCourses() != null) {
                for (Course course : student.getCourses()) {
                    preparedStatementForManyToMany.setInt(1, student.getId());
                    preparedStatementForManyToMany.setInt(2, course.getId());
                    preparedStatementForManyToMany.executeUpdate();
                }
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException("Student creation has failed!", e);
        }
    }

    public List<Student> getByCourseName(String courseName) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_STUDENTS)) {
            preparedStatement.setString(1, courseName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                students.add(new Student(resultSet.getInt(1) ,resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Finding all students has failed!", e);
        }
        return students;
    }

    public void remove(int studentId) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_STUDENT)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Student deletion has failed!", e);
        }
    }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ALL_STUDENTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                students.add(new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Getting students has failed", e);
        }
        return students;
    }
}
