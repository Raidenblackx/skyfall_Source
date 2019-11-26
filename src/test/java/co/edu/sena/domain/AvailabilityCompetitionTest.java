package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class AvailabilityCompetitionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvailabilityCompetition.class);
        AvailabilityCompetition availabilityCompetition1 = new AvailabilityCompetition();
        availabilityCompetition1.setId(1L);
        AvailabilityCompetition availabilityCompetition2 = new AvailabilityCompetition();
        availabilityCompetition2.setId(availabilityCompetition1.getId());
        assertThat(availabilityCompetition1).isEqualTo(availabilityCompetition2);
        availabilityCompetition2.setId(2L);
        assertThat(availabilityCompetition1).isNotEqualTo(availabilityCompetition2);
        availabilityCompetition1.setId(null);
        assertThat(availabilityCompetition1).isNotEqualTo(availabilityCompetition2);
    }
}
