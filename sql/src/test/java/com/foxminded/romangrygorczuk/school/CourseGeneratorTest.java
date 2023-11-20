package com.foxminded.romangrygorczuk.school;

import com.foxminded.romangrygorczuk.dao.CourseDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CourseGeneratorTest {

    @Mock
    CourseDao courseDao;

    @InjectMocks
    CourseGenerator courseGenerator;

    @Test
    void generate_shouldReturnTenCourses_whenMethodExecuted() throws URISyntaxException, IOException {
        assertEquals(10, courseGenerator.generate().size());
    }
}