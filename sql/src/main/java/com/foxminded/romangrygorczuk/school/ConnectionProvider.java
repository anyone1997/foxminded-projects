package com.foxminded.romangrygorczuk.school;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {

    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.user";
    private static final String DB_PASSWORD = "db.password";

    private String url;
    private String username;
    private String password;

    public ConnectionProvider() {
        URL resource = getClass().getClassLoader().getResource("connection.properties");
        try {
            assert resource != null;
            try (FileInputStream fileInputStream = new FileInputStream(new File(resource.toURI()))) {
                Properties properties = new Properties();
                properties.load(fileInputStream);
                url = properties.getProperty(DB_URL);
                username = properties.getProperty(DB_USERNAME);
                password = properties.getProperty(DB_PASSWORD);
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("Reading file properties has failed!", e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
