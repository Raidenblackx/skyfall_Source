package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TimeStudyMapperTest {

    private TimeStudyMapper timeStudyMapper;

    @BeforeEach
    public void setUp() {
        timeStudyMapper = new TimeStudyMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(timeStudyMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(timeStudyMapper.fromId(null)).isNull();
    }
}
