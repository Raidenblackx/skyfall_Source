package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ResultSeenMapperTest {

    private ResultSeenMapper resultSeenMapper;

    @BeforeEach
    public void setUp() {
        resultSeenMapper = new ResultSeenMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(resultSeenMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(resultSeenMapper.fromId(null)).isNull();
    }
}
