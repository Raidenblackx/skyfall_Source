package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class JourneyInstructorMapperTest {

    private JourneyInstructorMapper journeyInstructorMapper;

    @BeforeEach
    public void setUp() {
        journeyInstructorMapper = new JourneyInstructorMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(journeyInstructorMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(journeyInstructorMapper.fromId(null)).isNull();
    }
}
