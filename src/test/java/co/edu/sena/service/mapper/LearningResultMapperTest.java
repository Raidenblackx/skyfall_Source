package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class LearningResultMapperTest {

    private LearningResultMapper learningResultMapper;

    @BeforeEach
    public void setUp() {
        learningResultMapper = new LearningResultMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(learningResultMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(learningResultMapper.fromId(null)).isNull();
    }
}
