package com.foxminded.romangrygorczuk.school;

import com.foxminded.romangrygorczuk.dao.StudentDao;
import com.foxminded.romangrygorczuk.model.Course;
import com.foxminded.romangrygorczuk.model.Group;
import com.foxminded.romangrygorczuk.model.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StudentGenerator {

    private static final String[] FIRST_NAMES = {"Lynn", "Ricky", "Elyse", "Dick", "Hayleigh", "Unice",
        "Chad", "Bryana", "Egbert", "Evangeline", "Zander", "Ranulph", "Sharyn",
        "Hewie", "Ernest", "Collyn", "Kelli", "Eli", "York", "Tianna"};
    private static final String[] LAST_NAMES = {"Reyes", "Olson", "Turner", "Byrd", "Knight", "Daniel",
        "Erickson", "Ramos", "Ingram", "Smith", "Patel", "Bolton", "Ward", "Ryan",
        "Williams", "Guerrero", "Mejia", "Vaughan", "Harris", "Marsh"};

    private final StudentDao studentDao;

    public StudentGenerator(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    Random random = new Random();

    public List<Student> generate(List<Course> courses, List<Group> groups, int numberOfStudents, int minStudentsPerGroup, int maxStudentsPerGroup) throws SQLException {
        List<Student> students = Stream
            .generate(() -> {
                    Group randomGroup = groups.get(random.nextInt(groups.size()));
                    Student student = new Student(randomGroup.getId(), assignCourses(courses), FIRST_NAMES[random.nextInt(FIRST_NAMES.length)],
                        LAST_NAMES[random.nextInt(LAST_NAMES.length)]);
                    randomGroup.getStudents().add(student);
                    return student;
                }
            )
            .limit(numberOfStudents)
            .collect(toList());

        for (Group group : groups) {
            if (group.getStudents().size() < minStudentsPerGroup) {
                for (int i = 0; i < group.getStudents().size(); i++) {
                    group.getStudents().get(i).setGroupId(null);
                }
            } else if (group.getStudents().size() > maxStudentsPerGroup) {
                for (int i = maxStudentsPerGroup; i < group.getStudents().size(); i++) {
                    group.getStudents().get(i).setGroupId(null);
                }
            }
        }

        for (Student student : students) {
            studentDao.create(student);
        }

        return students;
    }

    private List<Course> assignCourses(List<Course> courses) {
        List<Course> assignedCourses = new ArrayList<>();
        int randomNumber = generateRandomNumberOfCourses();
        ThreadLocalRandom.current()
            .ints(1, 10)
            .distinct().limit(randomNumber)
            .forEach(e -> assignedCourses.add(courses.get(e)));

        return assignedCourses;
    }

    private Integer generateRandomNumberOfCourses() {
        int min = 1;
        int max = 3;

        return (int) (Math.random() * (max - min + 1) + min);
    }
}
