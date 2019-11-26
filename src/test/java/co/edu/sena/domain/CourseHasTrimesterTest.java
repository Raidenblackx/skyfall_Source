package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class CourseHasTrimesterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseHasTrimester.class);
        CourseHasTrimester courseHasTrimester1 = new CourseHasTrimester();
        courseHasTrimester1.setId(1L);
        CourseHasTrimester courseHasTrimester2 = new CourseHasTrimester();
        courseHasTrimester2.setId(courseHasTrimester1.getId());
        assertThat(courseHasTrimester1).isEqualTo(courseHasTrimester2);
        courseHasTrimester2.setId(2L);
        assertThat(courseHasTrimester1).isNotEqualTo(courseHasTrimester2);
        courseHasTrimester1.setId(null);
        assertThat(courseHasTrimester1).isNotEqualTo(courseHasTrimester2);
    }
}
