package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class TypeEnvironmentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeEnvironment.class);
        TypeEnvironment typeEnvironment1 = new TypeEnvironment();
        typeEnvironment1.setId(1L);
        TypeEnvironment typeEnvironment2 = new TypeEnvironment();
        typeEnvironment2.setId(typeEnvironment1.getId());
        assertThat(typeEnvironment1).isEqualTo(typeEnvironment2);
        typeEnvironment2.setId(2L);
        assertThat(typeEnvironment1).isNotEqualTo(typeEnvironment2);
        typeEnvironment1.setId(null);
        assertThat(typeEnvironment1).isNotEqualTo(typeEnvironment2);
    }
}
