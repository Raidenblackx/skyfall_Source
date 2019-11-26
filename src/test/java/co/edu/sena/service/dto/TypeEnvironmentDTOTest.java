package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class TypeEnvironmentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeEnvironmentDTO.class);
        TypeEnvironmentDTO typeEnvironmentDTO1 = new TypeEnvironmentDTO();
        typeEnvironmentDTO1.setId(1L);
        TypeEnvironmentDTO typeEnvironmentDTO2 = new TypeEnvironmentDTO();
        assertThat(typeEnvironmentDTO1).isNotEqualTo(typeEnvironmentDTO2);
        typeEnvironmentDTO2.setId(typeEnvironmentDTO1.getId());
        assertThat(typeEnvironmentDTO1).isEqualTo(typeEnvironmentDTO2);
        typeEnvironmentDTO2.setId(2L);
        assertThat(typeEnvironmentDTO1).isNotEqualTo(typeEnvironmentDTO2);
        typeEnvironmentDTO1.setId(null);
        assertThat(typeEnvironmentDTO1).isNotEqualTo(typeEnvironmentDTO2);
    }
}
