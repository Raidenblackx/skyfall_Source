package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class WorkingDayMapperTest {

    private WorkingDayMapper workingDayMapper;

    @BeforeEach
    public void setUp() {
        workingDayMapper = new WorkingDayMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(workingDayMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(workingDayMapper.fromId(null)).isNull();
    }
}
