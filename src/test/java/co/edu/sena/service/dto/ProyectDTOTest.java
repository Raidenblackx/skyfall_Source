package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class ProyectDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProyectDTO.class);
        ProyectDTO proyectDTO1 = new ProyectDTO();
        proyectDTO1.setId(1L);
        ProyectDTO proyectDTO2 = new ProyectDTO();
        assertThat(proyectDTO1).isNotEqualTo(proyectDTO2);
        proyectDTO2.setId(proyectDTO1.getId());
        assertThat(proyectDTO1).isEqualTo(proyectDTO2);
        proyectDTO2.setId(2L);
        assertThat(proyectDTO1).isNotEqualTo(proyectDTO2);
        proyectDTO1.setId(null);
        assertThat(proyectDTO1).isNotEqualTo(proyectDTO2);
    }
}
