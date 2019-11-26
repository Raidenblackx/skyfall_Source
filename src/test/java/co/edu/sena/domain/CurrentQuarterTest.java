package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class CurrentQuarterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CurrentQuarter.class);
        CurrentQuarter currentQuarter1 = new CurrentQuarter();
        currentQuarter1.setId(1L);
        CurrentQuarter currentQuarter2 = new CurrentQuarter();
        currentQuarter2.setId(currentQuarter1.getId());
        assertThat(currentQuarter1).isEqualTo(currentQuarter2);
        currentQuarter2.setId(2L);
        assertThat(currentQuarter1).isNotEqualTo(currentQuarter2);
        currentQuarter1.setId(null);
        assertThat(currentQuarter1).isNotEqualTo(currentQuarter2);
    }
}
