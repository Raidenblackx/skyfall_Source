package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CourseHasTrimesterMapperTest {

    private CourseHasTrimesterMapper courseHasTrimesterMapper;

    @BeforeEach
    public void setUp() {
        courseHasTrimesterMapper = new CourseHasTrimesterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(courseHasTrimesterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(courseHasTrimesterMapper.fromId(null)).isNull();
    }
}
