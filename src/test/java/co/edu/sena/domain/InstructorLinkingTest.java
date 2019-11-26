package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class InstructorLinkingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstructorLinking.class);
        InstructorLinking instructorLinking1 = new InstructorLinking();
        instructorLinking1.setId(1L);
        InstructorLinking instructorLinking2 = new InstructorLinking();
        instructorLinking2.setId(instructorLinking1.getId());
        assertThat(instructorLinking1).isEqualTo(instructorLinking2);
        instructorLinking2.setId(2L);
        assertThat(instructorLinking1).isNotEqualTo(instructorLinking2);
        instructorLinking1.setId(null);
        assertThat(instructorLinking1).isNotEqualTo(instructorLinking2);
    }
}
