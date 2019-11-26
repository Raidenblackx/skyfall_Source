package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SedeMapperTest {

    private SedeMapper sedeMapper;

    @BeforeEach
    public void setUp() {
        sedeMapper = new SedeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(sedeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sedeMapper.fromId(null)).isNull();
    }
}
