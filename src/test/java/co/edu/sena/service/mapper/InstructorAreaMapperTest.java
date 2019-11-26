package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class InstructorAreaMapperTest {

    private InstructorAreaMapper instructorAreaMapper;

    @BeforeEach
    public void setUp() {
        instructorAreaMapper = new InstructorAreaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(instructorAreaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(instructorAreaMapper.fromId(null)).isNull();
    }
}
