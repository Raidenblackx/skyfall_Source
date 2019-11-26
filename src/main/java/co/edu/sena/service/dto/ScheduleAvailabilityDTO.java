package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.domain.ScheduleAvailability} entity.
 */
public class ScheduleAvailabilityDTO implements Serializable {

    private Long id;


    private Long instructorLinkingId;

    private Long journeyInstructorId;

    private String journeyInstructorNameDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstructorLinkingId() {
        return instructorLinkingId;
    }

    public void setInstructorLinkingId(Long instructorLinkingId) {
        this.instructorLinkingId = instructorLinkingId;
    }

    public Long getJourneyInstructorId() {
        return journeyInstructorId;
    }

    public void setJourneyInstructorId(Long journeyInstructorId) {
        this.journeyInstructorId = journeyInstructorId;
    }

    public String getJourneyInstructorNameDay() {
        return journeyInstructorNameDay;
    }

    public void setJourneyInstructorNameDay(String journeyInstructorNameDay) {
        this.journeyInstructorNameDay = journeyInstructorNameDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ScheduleAvailabilityDTO scheduleAvailabilityDTO = (ScheduleAvailabilityDTO) o;
        if (scheduleAvailabilityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), scheduleAvailabilityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ScheduleAvailabilityDTO{" +
            "id=" + getId() +
            ", instructorLinking=" + getInstructorLinkingId() +
            ", journeyInstructor=" + getJourneyInstructorId() +
            ", journeyInstructor='" + getJourneyInstructorNameDay() + "'" +
            "}";
    }
}
