package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class AvailabilityCompetitionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvailabilityCompetitionDTO.class);
        AvailabilityCompetitionDTO availabilityCompetitionDTO1 = new AvailabilityCompetitionDTO();
        availabilityCompetitionDTO1.setId(1L);
        AvailabilityCompetitionDTO availabilityCompetitionDTO2 = new AvailabilityCompetitionDTO();
        assertThat(availabilityCompetitionDTO1).isNotEqualTo(availabilityCompetitionDTO2);
        availabilityCompetitionDTO2.setId(availabilityCompetitionDTO1.getId());
        assertThat(availabilityCompetitionDTO1).isEqualTo(availabilityCompetitionDTO2);
        availabilityCompetitionDTO2.setId(2L);
        assertThat(availabilityCompetitionDTO1).isNotEqualTo(availabilityCompetitionDTO2);
        availabilityCompetitionDTO1.setId(null);
        assertThat(availabilityCompetitionDTO1).isNotEqualTo(availabilityCompetitionDTO2);
    }
}
