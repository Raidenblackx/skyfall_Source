package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class LinkageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Linkage.class);
        Linkage linkage1 = new Linkage();
        linkage1.setId(1L);
        Linkage linkage2 = new Linkage();
        linkage2.setId(linkage1.getId());
        assertThat(linkage1).isEqualTo(linkage2);
        linkage2.setId(2L);
        assertThat(linkage1).isNotEqualTo(linkage2);
        linkage1.setId(null);
        assertThat(linkage1).isNotEqualTo(linkage2);
    }
}
