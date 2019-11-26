package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TypeEnvironmentMapperTest {

    private TypeEnvironmentMapper typeEnvironmentMapper;

    @BeforeEach
    public void setUp() {
        typeEnvironmentMapper = new TypeEnvironmentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(typeEnvironmentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeEnvironmentMapper.fromId(null)).isNull();
    }
}
