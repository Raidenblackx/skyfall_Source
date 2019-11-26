package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class ProyectActivityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProyectActivity.class);
        ProyectActivity proyectActivity1 = new ProyectActivity();
        proyectActivity1.setId(1L);
        ProyectActivity proyectActivity2 = new ProyectActivity();
        proyectActivity2.setId(proyectActivity1.getId());
        assertThat(proyectActivity1).isEqualTo(proyectActivity2);
        proyectActivity2.setId(2L);
        assertThat(proyectActivity1).isNotEqualTo(proyectActivity2);
        proyectActivity1.setId(null);
        assertThat(proyectActivity1).isNotEqualTo(proyectActivity2);
    }
}
