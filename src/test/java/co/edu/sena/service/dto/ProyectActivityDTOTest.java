package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class ProyectActivityDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProyectActivityDTO.class);
        ProyectActivityDTO proyectActivityDTO1 = new ProyectActivityDTO();
        proyectActivityDTO1.setId(1L);
        ProyectActivityDTO proyectActivityDTO2 = new ProyectActivityDTO();
        assertThat(proyectActivityDTO1).isNotEqualTo(proyectActivityDTO2);
        proyectActivityDTO2.setId(proyectActivityDTO1.getId());
        assertThat(proyectActivityDTO1).isEqualTo(proyectActivityDTO2);
        proyectActivityDTO2.setId(2L);
        assertThat(proyectActivityDTO1).isNotEqualTo(proyectActivityDTO2);
        proyectActivityDTO1.setId(null);
        assertThat(proyectActivityDTO1).isNotEqualTo(proyectActivityDTO2);
    }
}
