package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TrimesterPlanningMapperTest {

    private TrimesterPlanningMapper trimesterPlanningMapper;

    @BeforeEach
    public void setUp() {
        trimesterPlanningMapper = new TrimesterPlanningMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(trimesterPlanningMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(trimesterPlanningMapper.fromId(null)).isNull();
    }
}
