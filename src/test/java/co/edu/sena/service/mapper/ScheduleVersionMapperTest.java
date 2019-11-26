package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ScheduleVersionMapperTest {

    private ScheduleVersionMapper scheduleVersionMapper;

    @BeforeEach
    public void setUp() {
        scheduleVersionMapper = new ScheduleVersionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(scheduleVersionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(scheduleVersionMapper.fromId(null)).isNull();
    }
}
