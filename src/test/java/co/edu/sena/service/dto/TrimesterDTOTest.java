package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class TrimesterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrimesterDTO.class);
        TrimesterDTO trimesterDTO1 = new TrimesterDTO();
        trimesterDTO1.setId(1L);
        TrimesterDTO trimesterDTO2 = new TrimesterDTO();
        assertThat(trimesterDTO1).isNotEqualTo(trimesterDTO2);
        trimesterDTO2.setId(trimesterDTO1.getId());
        assertThat(trimesterDTO1).isEqualTo(trimesterDTO2);
        trimesterDTO2.setId(2L);
        assertThat(trimesterDTO1).isNotEqualTo(trimesterDTO2);
        trimesterDTO1.setId(null);
        assertThat(trimesterDTO1).isNotEqualTo(trimesterDTO2);
    }
}
