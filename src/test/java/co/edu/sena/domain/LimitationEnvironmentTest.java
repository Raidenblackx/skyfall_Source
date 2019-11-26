package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class LimitationEnvironmentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LimitationEnvironment.class);
        LimitationEnvironment limitationEnvironment1 = new LimitationEnvironment();
        limitationEnvironment1.setId(1L);
        LimitationEnvironment limitationEnvironment2 = new LimitationEnvironment();
        limitationEnvironment2.setId(limitationEnvironment1.getId());
        assertThat(limitationEnvironment1).isEqualTo(limitationEnvironment2);
        limitationEnvironment2.setId(2L);
        assertThat(limitationEnvironment1).isNotEqualTo(limitationEnvironment2);
        limitationEnvironment1.setId(null);
        assertThat(limitationEnvironment1).isNotEqualTo(limitationEnvironment2);
    }
}
