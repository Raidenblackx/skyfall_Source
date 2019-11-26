package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TrimesterMapperTest {

    private TrimesterMapper trimesterMapper;

    @BeforeEach
    public void setUp() {
        trimesterMapper = new TrimesterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(trimesterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(trimesterMapper.fromId(null)).isNull();
    }
}
