package co.edu.sena.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.domain.Schedule} entity.
 */
public class ScheduleDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime startTime;

    @NotNull
    private ZonedDateTime endTime;


    private Long courseHasTrimesterId;

    private Long ambientId;

    private String ambientNumberRoom;

    private Long instructorId;

    private Long dayId;

    private String dayNameDay;

    private Long scheduleVersionId;

    private String scheduleVersionNumberVersion;

    private Long modalityId;

    private String modalityNameModality;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getCourseHasTrimesterId() {
        return courseHasTrimesterId;
    }

    public void setCourseHasTrimesterId(Long courseHasTrimesterId) {
        this.courseHasTrimesterId = courseHasTrimesterId;
    }

    public Long getAmbientId() {
        return ambientId;
    }

    public void setAmbientId(Long ambientId) {
        this.ambientId = ambientId;
    }

    public String getAmbientNumberRoom() {
        return ambientNumberRoom;
    }

    public void setAmbientNumberRoom(String ambientNumberRoom) {
        this.ambientNumberRoom = ambientNumberRoom;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
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

    public Long getScheduleVersionId() {
        return scheduleVersionId;
    }

    public void setScheduleVersionId(Long scheduleVersionId) {
        this.scheduleVersionId = scheduleVersionId;
    }

    public String getScheduleVersionNumberVersion() {
        return scheduleVersionNumberVersion;
    }

    public void setScheduleVersionNumberVersion(String scheduleVersionNumberVersion) {
        this.scheduleVersionNumberVersion = scheduleVersionNumberVersion;
    }

    public Long getModalityId() {
        return modalityId;
    }

    public void setModalityId(Long modalityId) {
        this.modalityId = modalityId;
    }

    public String getModalityNameModality() {
        return modalityNameModality;
    }

    public void setModalityNameModality(String modalityNameModality) {
        this.modalityNameModality = modalityNameModality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ScheduleDTO scheduleDTO = (ScheduleDTO) o;
        if (scheduleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), scheduleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
            "id=" + getId() +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", courseHasTrimester=" + getCourseHasTrimesterId() +
            ", ambient=" + getAmbientId() +
            ", ambient='" + getAmbientNumberRoom() + "'" +
            ", instructor=" + getInstructorId() +
            ", day=" + getDayId() +
            ", day='" + getDayNameDay() + "'" +
            ", scheduleVersion=" + getScheduleVersionId() +
            ", scheduleVersion='" + getScheduleVersionNumberVersion() + "'" +
            ", modality=" + getModalityId() +
            ", modality='" + getModalityNameModality() + "'" +
            "}";
    }
}
