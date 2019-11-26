package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class LevelFormationMapperTest {

    private LevelFormationMapper levelFormationMapper;

    @BeforeEach
    public void setUp() {
        levelFormationMapper = new LevelFormationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(levelFormationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(levelFormationMapper.fromId(null)).isNull();
    }
}
