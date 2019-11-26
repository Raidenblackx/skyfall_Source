package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class ResultSeenDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResultSeenDTO.class);
        ResultSeenDTO resultSeenDTO1 = new ResultSeenDTO();
        resultSeenDTO1.setId(1L);
        ResultSeenDTO resultSeenDTO2 = new ResultSeenDTO();
        assertThat(resultSeenDTO1).isNotEqualTo(resultSeenDTO2);
        resultSeenDTO2.setId(resultSeenDTO1.getId());
        assertThat(resultSeenDTO1).isEqualTo(resultSeenDTO2);
        resultSeenDTO2.setId(2L);
        assertThat(resultSeenDTO1).isNotEqualTo(resultSeenDTO2);
        resultSeenDTO1.setId(null);
        assertThat(resultSeenDTO1).isNotEqualTo(resultSeenDTO2);
    }
}
