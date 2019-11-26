package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class LinkageDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LinkageDTO.class);
        LinkageDTO linkageDTO1 = new LinkageDTO();
        linkageDTO1.setId(1L);
        LinkageDTO linkageDTO2 = new LinkageDTO();
        assertThat(linkageDTO1).isNotEqualTo(linkageDTO2);
        linkageDTO2.setId(linkageDTO1.getId());
        assertThat(linkageDTO1).isEqualTo(linkageDTO2);
        linkageDTO2.setId(2L);
        assertThat(linkageDTO1).isNotEqualTo(linkageDTO2);
        linkageDTO1.setId(null);
        assertThat(linkageDTO1).isNotEqualTo(linkageDTO2);
    }
}
