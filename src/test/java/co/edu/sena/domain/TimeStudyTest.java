package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class TimeStudyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TimeStudy.class);
        TimeStudy timeStudy1 = new TimeStudy();
        timeStudy1.setId(1L);
        TimeStudy timeStudy2 = new TimeStudy();
        timeStudy2.setId(timeStudy1.getId());
        assertThat(timeStudy1).isEqualTo(timeStudy2);
        timeStudy2.setId(2L);
        assertThat(timeStudy1).isNotEqualTo(timeStudy2);
        timeStudy1.setId(null);
        assertThat(timeStudy1).isNotEqualTo(timeStudy2);
    }
}
