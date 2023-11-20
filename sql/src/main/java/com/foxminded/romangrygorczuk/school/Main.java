package com.foxminded.romangrygorczuk.school;

import com.foxminded.romangrygorczuk.dao.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException, SQLException {
        ConnectionProvider connectionProvider = new ConnectionProvider();
        ScriptExecutor scriptExecutor = new ScriptExecutor(connectionProvider);
        GroupDao groupDao = new JdbcGroupDao(connectionProvider);
        CourseDao courseDao = new JdbcCourseDao(connectionProvider);
        StudentDao studentDao = new JdbcStudentDao(connectionProvider);
        GroupGenerator groupGenerator = new GroupGenerator(groupDao);
        CourseGenerator courseGenerator = new CourseGenerator(courseDao);
        StudentGenerator studentGenerator = new StudentGenerator(studentDao);
        Menu menu = new Menu(studentDao, groupDao, courseDao);

        scriptExecutor.run("schema.sql");
        studentGenerator.generate(courseGenerator.generate(), groupGenerator.generate(10), 200, 10, 30);
        menu.generate();
    }
}