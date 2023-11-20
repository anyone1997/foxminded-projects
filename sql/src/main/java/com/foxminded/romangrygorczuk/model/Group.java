package com.foxminded.romangrygorczuk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group {

    private Integer id;
    private String name;
    private List<Student> students = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Group name: " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(name, group.name) && Objects.equals(students, group.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, students);
    }
}
