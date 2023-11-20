package com.foxminded.romangrygorczuk.school;

import com.foxminded.romangrygorczuk.dao.StudentDao;
import com.foxminded.romangrygorczuk.model.Course;
import com.foxminded.romangrygorczuk.model.Group;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class StudentGeneratorTest {

    @Mock
    StudentDao studentDao;

    @InjectMocks
    StudentGenerator studentGenerator;

    @Test
    void generate_shouldCreateTwoHundredStudent_WhenMethodIsExecuted() throws SQLException {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("test", "test"));
        courses.add(new Course("test", "test"));
        courses.add(new Course("test", "test"));
        courses.add(new Course("test", "test"));
        courses.add(new Course("test", "test"));
        courses.add(new Course("test", "test"));
        courses.add(new Course("test", "test"));
        courses.add(new Course("test", "test"));
        courses.add(new Course("test", "test"));
        courses.add(new Course("test", "test"));
        List<Group> groups = new ArrayList<>();
        groups.add(new Group("test"));
        groups.add(new Group("test"));
        groups.add(new Group("test"));
        groups.add(new Group("test"));
        groups.add(new Group("test"));
        groups.add(new Group("test"));
        groups.add(new Group("test"));
        groups.add(new Group("test"));
        groups.add(new Group("test"));
        groups.add(new Group("test"));

        studentDao.create(any());

        assertEquals(20, studentGenerator.generate(courses, groups, 20, 1, 30).size());
    }
}