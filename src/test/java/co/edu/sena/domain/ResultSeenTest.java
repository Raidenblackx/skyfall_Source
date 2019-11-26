package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class ResultSeenTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResultSeen.class);
        ResultSeen resultSeen1 = new ResultSeen();
        resultSeen1.setId(1L);
        ResultSeen resultSeen2 = new ResultSeen();
        resultSeen2.setId(resultSeen1.getId());
        assertThat(resultSeen1).isEqualTo(resultSeen2);
        resultSeen2.setId(2L);
        assertThat(resultSeen1).isNotEqualTo(resultSeen2);
        resultSeen1.setId(null);
        assertThat(resultSeen1).isNotEqualTo(resultSeen2);
    }
}
