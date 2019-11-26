package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class LearningResultTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LearningResult.class);
        LearningResult learningResult1 = new LearningResult();
        learningResult1.setId(1L);
        LearningResult learningResult2 = new LearningResult();
        learningResult2.setId(learningResult1.getId());
        assertThat(learningResult1).isEqualTo(learningResult2);
        learningResult2.setId(2L);
        assertThat(learningResult1).isNotEqualTo(learningResult2);
        learningResult1.setId(null);
        assertThat(learningResult1).isNotEqualTo(learningResult2);
    }
}
