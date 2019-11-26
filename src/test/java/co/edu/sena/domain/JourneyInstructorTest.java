package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class JourneyInstructorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JourneyInstructor.class);
        JourneyInstructor journeyInstructor1 = new JourneyInstructor();
        journeyInstructor1.setId(1L);
        JourneyInstructor journeyInstructor2 = new JourneyInstructor();
        journeyInstructor2.setId(journeyInstructor1.getId());
        assertThat(journeyInstructor1).isEqualTo(journeyInstructor2);
        journeyInstructor2.setId(2L);
        assertThat(journeyInstructor1).isNotEqualTo(journeyInstructor2);
        journeyInstructor1.setId(null);
        assertThat(journeyInstructor1).isNotEqualTo(journeyInstructor2);
    }
}
