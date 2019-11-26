package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AreaMapperTest {

    private AreaMapper areaMapper;

    @BeforeEach
    public void setUp() {
        areaMapper = new AreaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(areaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(areaMapper.fromId(null)).isNull();
    }
}
