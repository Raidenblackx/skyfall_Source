package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class InstructorAreaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstructorArea.class);
        InstructorArea instructorArea1 = new InstructorArea();
        instructorArea1.setId(1L);
        InstructorArea instructorArea2 = new InstructorArea();
        instructorArea2.setId(instructorArea1.getId());
        assertThat(instructorArea1).isEqualTo(instructorArea2);
        instructorArea2.setId(2L);
        assertThat(instructorArea1).isNotEqualTo(instructorArea2);
        instructorArea1.setId(null);
        assertThat(instructorArea1).isNotEqualTo(instructorArea2);
    }
}
