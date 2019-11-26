package co.edu.sena.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class LinkageMapperTest {

    private LinkageMapper linkageMapper;

    @BeforeEach
    public void setUp() {
        linkageMapper = new LinkageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(linkageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(linkageMapper.fromId(null)).isNull();
    }
}
