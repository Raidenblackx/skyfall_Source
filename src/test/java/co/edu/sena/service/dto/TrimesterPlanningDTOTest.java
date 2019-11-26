package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class TrimesterPlanningDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrimesterPlanningDTO.class);
        TrimesterPlanningDTO trimesterPlanningDTO1 = new TrimesterPlanningDTO();
        trimesterPlanningDTO1.setId(1L);
        TrimesterPlanningDTO trimesterPlanningDTO2 = new TrimesterPlanningDTO();
        assertThat(trimesterPlanningDTO1).isNotEqualTo(trimesterPlanningDTO2);
        trimesterPlanningDTO2.setId(trimesterPlanningDTO1.getId());
        assertThat(trimesterPlanningDTO1).isEqualTo(trimesterPlanningDTO2);
        trimesterPlanningDTO2.setId(2L);
        assertThat(trimesterPlanningDTO1).isNotEqualTo(trimesterPlanningDTO2);
        trimesterPlanningDTO1.setId(null);
        assertThat(trimesterPlanningDTO1).isNotEqualTo(trimesterPlanningDTO2);
    }
}
