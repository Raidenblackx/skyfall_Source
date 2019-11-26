package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProyectMapperTest {

    private ProyectMapper proyectMapper;

    @BeforeEach
    public void setUp() {
        proyectMapper = new ProyectMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proyectMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proyectMapper.fromId(null)).isNull();
    }
}
