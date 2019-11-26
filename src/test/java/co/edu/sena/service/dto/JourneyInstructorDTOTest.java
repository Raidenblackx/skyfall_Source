package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class JourneyInstructorDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JourneyInstructorDTO.class);
        JourneyInstructorDTO journeyInstructorDTO1 = new JourneyInstructorDTO();
        journeyInstructorDTO1.setId(1L);
        JourneyInstructorDTO journeyInstructorDTO2 = new JourneyInstructorDTO();
        assertThat(journeyInstructorDTO1).isNotEqualTo(journeyInstructorDTO2);
        journeyInstructorDTO2.setId(journeyInstructorDTO1.getId());
        assertThat(journeyInstructorDTO1).isEqualTo(journeyInstructorDTO2);
        journeyInstructorDTO2.setId(2L);
        assertThat(journeyInstructorDTO1).isNotEqualTo(journeyInstructorDTO2);
        journeyInstructorDTO1.setId(null);
        assertThat(journeyInstructorDTO1).isNotEqualTo(journeyInstructorDTO2);
    }
}
