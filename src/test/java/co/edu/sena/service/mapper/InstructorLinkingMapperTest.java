package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class InstructorLinkingMapperTest {

    private InstructorLinkingMapper instructorLinkingMapper;

    @BeforeEach
    public void setUp() {
        instructorLinkingMapper = new InstructorLinkingMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(instructorLinkingMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(instructorLinkingMapper.fromId(null)).isNull();
    }
}
