package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class PlanningActivityDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanningActivityDTO.class);
        PlanningActivityDTO planningActivityDTO1 = new PlanningActivityDTO();
        planningActivityDTO1.setId(1L);
        PlanningActivityDTO planningActivityDTO2 = new PlanningActivityDTO();
        assertThat(planningActivityDTO1).isNotEqualTo(planningActivityDTO2);
        planningActivityDTO2.setId(planningActivityDTO1.getId());
        assertThat(planningActivityDTO1).isEqualTo(planningActivityDTO2);
        planningActivityDTO2.setId(2L);
        assertThat(planningActivityDTO1).isNotEqualTo(planningActivityDTO2);
        planningActivityDTO1.setId(null);
        assertThat(planningActivityDTO1).isNotEqualTo(planningActivityDTO2);
    }
}
