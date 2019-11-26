package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class LevelFormationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LevelFormationDTO.class);
        LevelFormationDTO levelFormationDTO1 = new LevelFormationDTO();
        levelFormationDTO1.setId(1L);
        LevelFormationDTO levelFormationDTO2 = new LevelFormationDTO();
        assertThat(levelFormationDTO1).isNotEqualTo(levelFormationDTO2);
        levelFormationDTO2.setId(levelFormationDTO1.getId());
        assertThat(levelFormationDTO1).isEqualTo(levelFormationDTO2);
        levelFormationDTO2.setId(2L);
        assertThat(levelFormationDTO1).isNotEqualTo(levelFormationDTO2);
        levelFormationDTO1.setId(null);
        assertThat(levelFormationDTO1).isNotEqualTo(levelFormationDTO2);
    }
}
