package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class AmbientDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AmbientDTO.class);
        AmbientDTO ambientDTO1 = new AmbientDTO();
        ambientDTO1.setId(1L);
        AmbientDTO ambientDTO2 = new AmbientDTO();
        assertThat(ambientDTO1).isNotEqualTo(ambientDTO2);
        ambientDTO2.setId(ambientDTO1.getId());
        assertThat(ambientDTO1).isEqualTo(ambientDTO2);
        ambientDTO2.setId(2L);
        assertThat(ambientDTO1).isNotEqualTo(ambientDTO2);
        ambientDTO1.setId(null);
        assertThat(ambientDTO1).isNotEqualTo(ambientDTO2);
    }
}
