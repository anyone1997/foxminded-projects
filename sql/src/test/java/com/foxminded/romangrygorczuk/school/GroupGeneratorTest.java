package com.foxminded.romangrygorczuk.school;

import com.foxminded.romangrygorczuk.dao.GroupDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GroupGeneratorTest {

    @Mock
    GroupDao groupDao;

    @InjectMocks
    GroupGenerator groupGenerator;

    @Test
    void generate_shouldReturnTenGroups_whenMethodExecuted() {
        assertEquals(10, groupGenerator.generate(10).size());
    }
}