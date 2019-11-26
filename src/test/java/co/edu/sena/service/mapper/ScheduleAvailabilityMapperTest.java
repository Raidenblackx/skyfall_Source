package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ScheduleAvailabilityMapperTest {

    private ScheduleAvailabilityMapper scheduleAvailabilityMapper;

    @BeforeEach
    public void setUp() {
        scheduleAvailabilityMapper = new ScheduleAvailabilityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(scheduleAvailabilityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(scheduleAvailabilityMapper.fromId(null)).isNull();
    }
}
