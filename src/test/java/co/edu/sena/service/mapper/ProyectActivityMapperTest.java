package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProyectActivityMapperTest {

    private ProyectActivityMapper proyectActivityMapper;

    @BeforeEach
    public void setUp() {
        proyectActivityMapper = new ProyectActivityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proyectActivityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proyectActivityMapper.fromId(null)).isNull();
    }
}
