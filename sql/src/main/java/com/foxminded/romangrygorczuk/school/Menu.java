package com.foxminded.romangrygorczuk.school;

import com.foxminded.romangrygorczuk.dao.CourseDao;
import com.foxminded.romangrygorczuk.dao.GroupDao;
import com.foxminded.romangrygorczuk.dao.StudentDao;
import com.foxminded.romangrygorczuk.model.Course;
import com.foxminded.romangrygorczuk.model.Student;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Menu {

    private final StudentDao studentDao;
    private final GroupDao groupDao;
    private final CourseDao courseDao;

    public Menu(StudentDao studentDao, GroupDao groupDao, CourseDao courseDao) {
        this.studentDao = studentDao;
        this.groupDao = groupDao;
        this.courseDao = courseDao;
    }

    public void generate() {
        String[] options = {
            "1. Find all groups with less or equals student count",
            "2. Find all students related to course with given name",
            "3. Add new student",
            "4. Delete student by STUDENT_ID",
            "5. Add a student to the course (from a list)",
            "6. Remove the student from one of his or her courses",
            "7. Exit"
        };

        Scanner scanner = new Scanner(System.in);
        int option = 1;
        int studentId;
        int courseId;
        while (option != 7) {
            printMenu(options);
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Provide a student count: ");
                        int studentCount = scanner.nextInt();
                        groupDao.getByStudentCount(studentCount).forEach(System.out::println);
                        break;
                    case 2:
                        System.out.println("Provide a course name: ");
                        String courseName = scanner.next();
                        studentDao.getByCourseName(courseName).forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println("Provide a first name: ");
                        String name = scanner.next();
                        System.out.println("Provide a surname: ");
                        String surname = scanner.next();
                        studentDao.create(new Student(name, surname));
                        System.out.println("Student have been added." + System.lineSeparator());
                        break;
                    case 4:
                        System.out.println("Provide a studentId to delete a student: ");
                        studentId = scanner.nextInt();
                        studentDao.remove(studentId);
                        break;
                    case 5:
                        studentDao.getStudents().forEach(System.out::println);
                        System.out.println("Choose studentId from the list above: ");
                        studentId = scanner.nextInt();
                        System.out.println("Already assigned courses: ");
                        List<Course> courses = courseDao.getByStudentId(studentId);
                        courseDao.getAll().stream()
                            .filter(course -> !courses.contains(course))
                            .forEach(System.out::println);
                        System.out.println("Choose courseId from the list: ");
                        courseId = scanner.nextInt();
                        courseDao.addStudentToCourse(studentId, courseId);
                        break;
                    case 6:
                        studentDao.getStudents().forEach(System.out::println);
                        System.out.println("Choose studentId from the list above: ");
                        studentId = scanner.nextInt();
                        courseDao.getByStudentId(studentId).forEach(System.out::println);
                        System.out.println("Choose courseId that you want to delete from the list above: ");
                        courseId = scanner.nextInt();
                        courseDao.removeStudentFromCourse(studentId, courseId);
                        break;
                    case 7:
                        exit(0);
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex + System.lineSeparator() + "Try again!");
            }
        }
    }

    private static void printMenu(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.print("Choose your option: ");
    }
}
