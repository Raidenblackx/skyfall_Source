package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class CourseHasTrimesterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseHasTrimesterDTO.class);
        CourseHasTrimesterDTO courseHasTrimesterDTO1 = new CourseHasTrimesterDTO();
        courseHasTrimesterDTO1.setId(1L);
        CourseHasTrimesterDTO courseHasTrimesterDTO2 = new CourseHasTrimesterDTO();
        assertThat(courseHasTrimesterDTO1).isNotEqualTo(courseHasTrimesterDTO2);
        courseHasTrimesterDTO2.setId(courseHasTrimesterDTO1.getId());
        assertThat(courseHasTrimesterDTO1).isEqualTo(courseHasTrimesterDTO2);
        courseHasTrimesterDTO2.setId(2L);
        assertThat(courseHasTrimesterDTO1).isNotEqualTo(courseHasTrimesterDTO2);
        courseHasTrimesterDTO1.setId(null);
        assertThat(courseHasTrimesterDTO1).isNotEqualTo(courseHasTrimesterDTO2);
    }
}
