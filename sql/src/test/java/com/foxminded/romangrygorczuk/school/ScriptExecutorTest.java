package com.foxminded.romangrygorczuk.school;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScriptExecutorTest {

    @Mock
    ConnectionProvider connectionProvider;

    @Mock
    Connection connection;

    @Mock
    Statement statement;

    @InjectMocks
    ScriptExecutor scriptExecutor;

    @Test
    void run_shouldNotThrowAnything_whenFileExists() throws SQLException {
        String tableStructure = "schema.sql";
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.getUpdateCount()).thenReturn(-1);

        assertDoesNotThrow(() -> scriptExecutor.run(tableStructure));
    }

    @Test
    void run_shouldThrowFileNotFoundException_whenFileIsMissing() {
        String tableStructure = "fileDoesNotExist.sql";

        assertThrows(FileNotFoundException.class, () -> scriptExecutor.run(tableStructure));
    }
}