package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class LimitationEnvironmentMapperTest {

    private LimitationEnvironmentMapper limitationEnvironmentMapper;

    @BeforeEach
    public void setUp() {
        limitationEnvironmentMapper = new LimitationEnvironmentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(limitationEnvironmentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(limitationEnvironmentMapper.fromId(null)).isNull();
    }
}
