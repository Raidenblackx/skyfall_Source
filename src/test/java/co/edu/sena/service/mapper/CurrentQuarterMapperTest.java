package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CurrentQuarterMapperTest {

    private CurrentQuarterMapper currentQuarterMapper;

    @BeforeEach
    public void setUp() {
        currentQuarterMapper = new CurrentQuarterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(currentQuarterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(currentQuarterMapper.fromId(null)).isNull();
    }
}
