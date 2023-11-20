package com.foxminded.romangrygorczuk.school;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class ScriptExecutor {

    private final ConnectionProvider connectionProvider;

    public ScriptExecutor(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public void run(String scriptFilename) throws SQLException, IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(scriptFilename)) {

            if (inputStream == null) {
                throw new FileNotFoundException(scriptFilename);
            }
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            Reader reader = new BufferedReader(bufferedReader);
            ScriptRunner scriptRunner = new ScriptRunner(connectionProvider.getConnection());
            scriptRunner.runScript(reader);
        }
    }
}