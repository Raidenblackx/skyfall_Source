package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PlanningActivityMapperTest {

    private PlanningActivityMapper planningActivityMapper;

    @BeforeEach
    public void setUp() {
        planningActivityMapper = new PlanningActivityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(planningActivityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(planningActivityMapper.fromId(null)).isNull();
    }
}
