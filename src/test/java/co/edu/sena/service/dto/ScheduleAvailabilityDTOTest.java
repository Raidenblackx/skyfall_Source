package co.edu.sena.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.sena.web.rest.TestUtil;

public class ScheduleAvailabilityDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ScheduleAvailabilityDTO.class);
        ScheduleAvailabilityDTO scheduleAvailabilityDTO1 = new ScheduleAvailabilityDTO();
        scheduleAvailabilityDTO1.setId(1L);
        ScheduleAvailabilityDTO scheduleAvailabilityDTO2 = new ScheduleAvailabilityDTO();
        assertThat(scheduleAvailabilityDTO1).isNotEqualTo(scheduleAvailabilityDTO2);
        scheduleAvailabilityDTO2.setId(scheduleAvailabilityDTO1.getId());
        assertThat(scheduleAvailabilityDTO1).isEqualTo(scheduleAvailabilityDTO2);
        scheduleAvailabilityDTO2.setId(2L);
        assertThat(scheduleAvailabilityDTO1).isNotEqualTo(scheduleAvailabilityDTO2);
        scheduleAvailabilityDTO1.setId(null);
        assertThat(scheduleAvailabilityDTO1).isNotEqualTo(scheduleAvailabilityDTO2);
    }
}
