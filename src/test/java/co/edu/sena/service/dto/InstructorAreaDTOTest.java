package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class InstructorAreaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstructorAreaDTO.class);
        InstructorAreaDTO instructorAreaDTO1 = new InstructorAreaDTO();
        instructorAreaDTO1.setId(1L);
        InstructorAreaDTO instructorAreaDTO2 = new InstructorAreaDTO();
        assertThat(instructorAreaDTO1).isNotEqualTo(instructorAreaDTO2);
        instructorAreaDTO2.setId(instructorAreaDTO1.getId());
        assertThat(instructorAreaDTO1).isEqualTo(instructorAreaDTO2);
        instructorAreaDTO2.setId(2L);
        assertThat(instructorAreaDTO1).isNotEqualTo(instructorAreaDTO2);
        instructorAreaDTO1.setId(null);
        assertThat(instructorAreaDTO1).isNotEqualTo(instructorAreaDTO2);
    }
}
