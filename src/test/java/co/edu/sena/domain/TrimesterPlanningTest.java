package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class TrimesterPlanningTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrimesterPlanning.class);
        TrimesterPlanning trimesterPlanning1 = new TrimesterPlanning();
        trimesterPlanning1.setId(1L);
        TrimesterPlanning trimesterPlanning2 = new TrimesterPlanning();
        trimesterPlanning2.setId(trimesterPlanning1.getId());
        assertThat(trimesterPlanning1).isEqualTo(trimesterPlanning2);
        trimesterPlanning2.setId(2L);
        assertThat(trimesterPlanning1).isNotEqualTo(trimesterPlanning2);
        trimesterPlanning1.setId(null);
        assertThat(trimesterPlanning1).isNotEqualTo(trimesterPlanning2);
    }
}
