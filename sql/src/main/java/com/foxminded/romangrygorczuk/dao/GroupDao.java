package com.foxminded.romangrygorczuk.dao;

import com.foxminded.romangrygorczuk.model.Group;

import java.util.List;

public interface GroupDao {

    void create(Group group);

    List<Group> getByStudentCount(int studentCount);
}
