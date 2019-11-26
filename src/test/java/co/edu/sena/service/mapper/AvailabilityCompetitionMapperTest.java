package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AvailabilityCompetitionMapperTest {

    private AvailabilityCompetitionMapper availabilityCompetitionMapper;

    @BeforeEach
    public void setUp() {
        availabilityCompetitionMapper = new AvailabilityCompetitionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(availabilityCompetitionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(availabilityCompetitionMapper.fromId(null)).isNull();
    }
}
