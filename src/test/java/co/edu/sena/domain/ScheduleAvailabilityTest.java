package co.edu.sena.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class ScheduleAvailabilityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ScheduleAvailability.class);
        ScheduleAvailability scheduleAvailability1 = new ScheduleAvailability();
        scheduleAvailability1.setId(1L);
        ScheduleAvailability scheduleAvailability2 = new ScheduleAvailability();
        scheduleAvailability2.setId(scheduleAvailability1.getId());
        assertThat(scheduleAvailability1).isEqualTo(scheduleAvailability2);
        scheduleAvailability2.setId(2L);
        assertThat(scheduleAvailability1).isNotEqualTo(scheduleAvailability2);
        scheduleAvailability1.setId(null);
        assertThat(scheduleAvailability1).isNotEqualTo(scheduleAvailability2);
    }
}
