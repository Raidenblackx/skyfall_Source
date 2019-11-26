package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.domain.TimeStudy} entity.
 */
public class TimeStudyDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer startTime;

    @NotNull
    private Integer endTime;


    private Long journeyInstructorId;

    private String journeyInstructorNameDay;

    private Long dayId;

    private String dayNameDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
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

    public Long getDayId() {
        return dayId;
    }

    public void setDayId(Long dayId) {
        this.dayId = dayId;
    }

    public String getDayNameDay() {
        return dayNameDay;
    }

    public void setDayNameDay(String dayNameDay) {
        this.dayNameDay = dayNameDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TimeStudyDTO timeStudyDTO = (TimeStudyDTO) o;
        if (timeStudyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), timeStudyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TimeStudyDTO{" +
            "id=" + getId() +
            ", startTime=" + getStartTime() +
            ", endTime=" + getEndTime() +
            ", journeyInstructor=" + getJourneyInstructorId() +
            ", journeyInstructor='" + getJourneyInstructorNameDay() + "'" +
            ", day=" + getDayId() +
            ", day='" + getDayNameDay() + "'" +
            "}";
    }
}
