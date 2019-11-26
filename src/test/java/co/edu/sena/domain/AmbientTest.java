package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class AmbientTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ambient.class);
        Ambient ambient1 = new Ambient();
        ambient1.setId(1L);
        Ambient ambient2 = new Ambient();
        ambient2.setId(ambient1.getId());
        assertThat(ambient1).isEqualTo(ambient2);
        ambient2.setId(2L);
        assertThat(ambient1).isNotEqualTo(ambient2);
        ambient1.setId(null);
        assertThat(ambient1).isNotEqualTo(ambient2);
    }
}
