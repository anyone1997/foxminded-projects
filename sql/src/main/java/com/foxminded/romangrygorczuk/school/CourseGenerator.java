package com.foxminded.romangrygorczuk.school;

import com.foxminded.romangrygorczuk.dao.CourseDao;
import com.foxminded.romangrygorczuk.model.Course;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CourseGenerator {

    private final CourseDao courseDao;

    public CourseGenerator(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public List<Course> generate() throws URISyntaxException, IOException {
        URL resourceURL = getClass().getClassLoader().getResource("courses.txt");
        assert resourceURL != null;
        try (Stream<String> lines = Files.lines(Paths.get(resourceURL.toURI()))) {
            return lines
                .filter(line -> line.contains(","))
                .map(line -> {
                    String[] keyValuePair = line.split(",", 2);
                    String courseName = keyValuePair[0];
                    String courseDescription = keyValuePair[1];
                    Course course = new Course(courseName, courseDescription);
                    courseDao.create(course);
                    return course;
                })
                .collect(toList());
        }
    }
}
