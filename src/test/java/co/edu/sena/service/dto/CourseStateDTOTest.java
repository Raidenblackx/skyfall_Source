package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class CourseStateDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseStateDTO.class);
        CourseStateDTO courseStateDTO1 = new CourseStateDTO();
        courseStateDTO1.setId(1L);
        CourseStateDTO courseStateDTO2 = new CourseStateDTO();
        assertThat(courseStateDTO1).isNotEqualTo(courseStateDTO2);
        courseStateDTO2.setId(courseStateDTO1.getId());
        assertThat(courseStateDTO1).isEqualTo(courseStateDTO2);
        courseStateDTO2.setId(2L);
        assertThat(courseStateDTO1).isNotEqualTo(courseStateDTO2);
        courseStateDTO1.setId(null);
        assertThat(courseStateDTO1).isNotEqualTo(courseStateDTO2);
    }
}
