package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class LevelFormationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LevelFormation.class);
        LevelFormation levelFormation1 = new LevelFormation();
        levelFormation1.setId(1L);
        LevelFormation levelFormation2 = new LevelFormation();
        levelFormation2.setId(levelFormation1.getId());
        assertThat(levelFormation1).isEqualTo(levelFormation2);
        levelFormation2.setId(2L);
        assertThat(levelFormation1).isNotEqualTo(levelFormation2);
        levelFormation1.setId(null);
        assertThat(levelFormation1).isNotEqualTo(levelFormation2);
    }
}
