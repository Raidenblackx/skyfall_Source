package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class WorkingDayDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkingDayDTO.class);
        WorkingDayDTO workingDayDTO1 = new WorkingDayDTO();
        workingDayDTO1.setId(1L);
        WorkingDayDTO workingDayDTO2 = new WorkingDayDTO();
        assertThat(workingDayDTO1).isNotEqualTo(workingDayDTO2);
        workingDayDTO2.setId(workingDayDTO1.getId());
        assertThat(workingDayDTO1).isEqualTo(workingDayDTO2);
        workingDayDTO2.setId(2L);
        assertThat(workingDayDTO1).isNotEqualTo(workingDayDTO2);
        workingDayDTO1.setId(null);
        assertThat(workingDayDTO1).isNotEqualTo(workingDayDTO2);
    }
}
