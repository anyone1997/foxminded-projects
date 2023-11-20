package com.foxminded.romangrygorczuk.dao;

import com.foxminded.romangrygorczuk.model.Group;
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

class JdbcGroupDaoTest {

    private static final String SELECT_ALL_GROUPS = "SELECT * FROM groups";

    GroupDao groupDao;
    ConnectionProvider connectionProvider;
    ScriptExecutor scriptRunner;

    @BeforeEach
    void initialization() throws SQLException, IOException {
        connectionProvider = new ConnectionProvider();
        groupDao = new JdbcGroupDao(connectionProvider);
        scriptRunner = new ScriptExecutor(connectionProvider);
        scriptRunner.run("schema.sql");
        scriptRunner.run("data.sql");
    }

    @Test
    void findAllGroups_shouldReturnAllGroups_whenLessOrEqualsStudentCountProvided() {
        List<Group> actual = groupDao.getByStudentCount(2);
        List<Group> expected = new ArrayList<>();
        expected.add(new Group(2, "PM-34"));

        assertEquals(expected, actual);
    }

    @Test
    void create_shouldCreateGroup_whenGroupIsProvided() throws SQLException {
        Group group = new Group("KP-27");
        groupDao.create(group);
        List<Group> actual = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GROUPS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                actual.add(new Group(resultSet.getString(2)));
            }
        }

        List<Group> expected = new ArrayList<>();
        expected.add(new Group("CY-37"));
        expected.add(new Group("PM-34"));
        expected.add(new Group("KP-27"));

        assertEquals(expected, actual);
    }
}