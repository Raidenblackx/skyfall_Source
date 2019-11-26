package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class PlanningActivityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanningActivity.class);
        PlanningActivity planningActivity1 = new PlanningActivity();
        planningActivity1.setId(1L);
        PlanningActivity planningActivity2 = new PlanningActivity();
        planningActivity2.setId(planningActivity1.getId());
        assertThat(planningActivity1).isEqualTo(planningActivity2);
        planningActivity2.setId(2L);
        assertThat(planningActivity1).isNotEqualTo(planningActivity2);
        planningActivity1.setId(null);
        assertThat(planningActivity1).isNotEqualTo(planningActivity2);
    }
}
