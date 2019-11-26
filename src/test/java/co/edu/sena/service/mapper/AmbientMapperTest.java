package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AmbientMapperTest {

    private AmbientMapper ambientMapper;

    @BeforeEach
    public void setUp() {
        ambientMapper = new AmbientMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(ambientMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ambientMapper.fromId(null)).isNull();
    }
}
