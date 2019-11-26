package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class InstructorDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstructorDTO.class);
        InstructorDTO instructorDTO1 = new InstructorDTO();
        instructorDTO1.setId(1L);
        InstructorDTO instructorDTO2 = new InstructorDTO();
        assertThat(instructorDTO1).isNotEqualTo(instructorDTO2);
        instructorDTO2.setId(instructorDTO1.getId());
        assertThat(instructorDTO1).isEqualTo(instructorDTO2);
        instructorDTO2.setId(2L);
        assertThat(instructorDTO1).isNotEqualTo(instructorDTO2);
        instructorDTO1.setId(null);
        assertThat(instructorDTO1).isNotEqualTo(instructorDTO2);
    }
}
