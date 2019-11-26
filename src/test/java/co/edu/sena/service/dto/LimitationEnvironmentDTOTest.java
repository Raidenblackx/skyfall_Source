package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class LimitationEnvironmentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LimitationEnvironmentDTO.class);
        LimitationEnvironmentDTO limitationEnvironmentDTO1 = new LimitationEnvironmentDTO();
        limitationEnvironmentDTO1.setId(1L);
        LimitationEnvironmentDTO limitationEnvironmentDTO2 = new LimitationEnvironmentDTO();
        assertThat(limitationEnvironmentDTO1).isNotEqualTo(limitationEnvironmentDTO2);
        limitationEnvironmentDTO2.setId(limitationEnvironmentDTO1.getId());
        assertThat(limitationEnvironmentDTO1).isEqualTo(limitationEnvironmentDTO2);
        limitationEnvironmentDTO2.setId(2L);
        assertThat(limitationEnvironmentDTO1).isNotEqualTo(limitationEnvironmentDTO2);
        limitationEnvironmentDTO1.setId(null);
        assertThat(limitationEnvironmentDTO1).isNotEqualTo(limitationEnvironmentDTO2);
    }
}
