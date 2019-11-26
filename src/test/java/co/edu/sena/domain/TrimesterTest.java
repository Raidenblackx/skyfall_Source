package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class TrimesterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Trimester.class);
        Trimester trimester1 = new Trimester();
        trimester1.setId(1L);
        Trimester trimester2 = new Trimester();
        trimester2.setId(trimester1.getId());
        assertThat(trimester1).isEqualTo(trimester2);
        trimester2.setId(2L);
        assertThat(trimester1).isNotEqualTo(trimester2);
        trimester1.setId(null);
        assertThat(trimester1).isNotEqualTo(trimester2);
    }
}
