package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class LearningResultDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LearningResultDTO.class);
        LearningResultDTO learningResultDTO1 = new LearningResultDTO();
        learningResultDTO1.setId(1L);
        LearningResultDTO learningResultDTO2 = new LearningResultDTO();
        assertThat(learningResultDTO1).isNotEqualTo(learningResultDTO2);
        learningResultDTO2.setId(learningResultDTO1.getId());
        assertThat(learningResultDTO1).isEqualTo(learningResultDTO2);
        learningResultDTO2.setId(2L);
        assertThat(learningResultDTO1).isNotEqualTo(learningResultDTO2);
        learningResultDTO1.setId(null);
        assertThat(learningResultDTO1).isNotEqualTo(learningResultDTO2);
    }
}
