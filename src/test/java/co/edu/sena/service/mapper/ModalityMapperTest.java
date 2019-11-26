package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ModalityMapperTest {

    private ModalityMapper modalityMapper;

    @BeforeEach
    public void setUp() {
        modalityMapper = new ModalityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(modalityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(modalityMapper.fromId(null)).isNull();
    }
}
