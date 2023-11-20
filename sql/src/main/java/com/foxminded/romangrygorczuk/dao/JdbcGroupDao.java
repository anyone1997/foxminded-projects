package com.foxminded.romangrygorczuk.dao;

import com.foxminded.romangrygorczuk.model.Group;
import com.foxminded.romangrygorczuk.school.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcGroupDao implements GroupDao {

    private static final String INSERT_SQL = "INSERT INTO groups (group_name) VALUES (?)";
    private static final String FIND_ALL_GROUPS = "SELECT groups.group_id, groups.group_name FROM groups INNER JOIN students ON students.group_id = groups.group_id GROUP BY groups.group_id HAVING COUNT(students.student_id)<=?";

    private ConnectionProvider connectionProvider;

    public JdbcGroupDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public void create(Group group) {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                group.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Group creation failed", e);
        }
    }

    public List<Group> getByStudentCount(int studentCount) {
        List<Group> groups = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_GROUPS)) {
            preparedStatement.setInt(1, studentCount);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                groups.add(new Group(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Finding All Groups has failed!", e);
        }
        return groups;
    }
}
