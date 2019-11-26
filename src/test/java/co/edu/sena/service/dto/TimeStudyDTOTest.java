package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class TimeStudyDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TimeStudyDTO.class);
        TimeStudyDTO timeStudyDTO1 = new TimeStudyDTO();
        timeStudyDTO1.setId(1L);
        TimeStudyDTO timeStudyDTO2 = new TimeStudyDTO();
        assertThat(timeStudyDTO1).isNotEqualTo(timeStudyDTO2);
        timeStudyDTO2.setId(timeStudyDTO1.getId());
        assertThat(timeStudyDTO1).isEqualTo(timeStudyDTO2);
        timeStudyDTO2.setId(2L);
        assertThat(timeStudyDTO1).isNotEqualTo(timeStudyDTO2);
        timeStudyDTO1.setId(null);
        assertThat(timeStudyDTO1).isNotEqualTo(timeStudyDTO2);
    }
}
