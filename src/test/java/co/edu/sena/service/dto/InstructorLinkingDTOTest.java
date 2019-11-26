package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class InstructorLinkingDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstructorLinkingDTO.class);
        InstructorLinkingDTO instructorLinkingDTO1 = new InstructorLinkingDTO();
        instructorLinkingDTO1.setId(1L);
        InstructorLinkingDTO instructorLinkingDTO2 = new InstructorLinkingDTO();
        assertThat(instructorLinkingDTO1).isNotEqualTo(instructorLinkingDTO2);
        instructorLinkingDTO2.setId(instructorLinkingDTO1.getId());
        assertThat(instructorLinkingDTO1).isEqualTo(instructorLinkingDTO2);
        instructorLinkingDTO2.setId(2L);
        assertThat(instructorLinkingDTO1).isNotEqualTo(instructorLinkingDTO2);
        instructorLinkingDTO1.setId(null);
        assertThat(instructorLinkingDTO1).isNotEqualTo(instructorLinkingDTO2);
    }
}
