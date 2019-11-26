package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CourseStateMapperTest {

    private CourseStateMapper courseStateMapper;

    @BeforeEach
    public void setUp() {
        courseStateMapper = new CourseStateMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(courseStateMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(courseStateMapper.fromId(null)).isNull();
    }
}
