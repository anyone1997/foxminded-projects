package com.foxminded.romangrygorczuk.school;

import com.foxminded.romangrygorczuk.dao.GroupDao;
import com.foxminded.romangrygorczuk.model.Group;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class GroupGenerator {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final GroupDao groupDao;

    public GroupGenerator(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public List<Group> generate(int numberOfGroups) {
        return IntStream.range(0, numberOfGroups)
            .mapToObj(iterator -> {
                Group group = new Group(generateName());
                groupDao.create(group);
                return group;
            })
            .collect(toList());
    }

    private String generateName() {
        return String.format("%s%c%d", generateRandomString(), '-', generateRandomInt());
    }

    private String generateRandomString() {
        StringBuilder groupName = new StringBuilder(2);

        for (int i = 0; i < 2; i++) {
            int index
                = (int) (ALPHA_NUMERIC_STRING.length() * Math.random());
            groupName.append(ALPHA_NUMERIC_STRING
                .charAt(index));
        }
        return groupName.toString();
    }

    private Integer generateRandomInt() {
        int min = 10;
        int max = 99;
        return (int) (Math.random() * (max - min + 1) + min);
    }
}