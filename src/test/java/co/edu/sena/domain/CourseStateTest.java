package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class CourseStateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseState.class);
        CourseState courseState1 = new CourseState();
        courseState1.setId(1L);
        CourseState courseState2 = new CourseState();
        courseState2.setId(courseState1.getId());
        assertThat(courseState1).isEqualTo(courseState2);
        courseState2.setId(2L);
        assertThat(courseState1).isNotEqualTo(courseState2);
        courseState1.setId(null);
        assertThat(courseState1).isNotEqualTo(courseState2);
    }
}
