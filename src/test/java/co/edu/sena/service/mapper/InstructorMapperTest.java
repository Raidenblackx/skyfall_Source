package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class InstructorMapperTest {

    private InstructorMapper instructorMapper;

    @BeforeEach
    public void setUp() {
        instructorMapper = new InstructorMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(instructorMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(instructorMapper.fromId(null)).isNull();
    }
}
