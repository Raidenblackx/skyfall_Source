package co.edu.sena.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.domain.Course} entity.
 */
public class CourseDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String courseNumber;

    @NotNull
    private ZonedDateTime startDate;

    @NotNull
    private ZonedDateTime endDate;

    @NotNull
    @Size(max = 40)
    private String route;


    private Long courseStateId;

    private String courseStateNameCourseState;

    private Long workingDayId;

    private String workingDayNameWorkingDay;

    private Long programId;

    private String programNameProgram;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Long getCourseStateId() {
        return courseStateId;
    }

    public void setCourseStateId(Long courseStateId) {
        this.courseStateId = courseStateId;
    }

    public String getCourseStateNameCourseState() {
        return courseStateNameCourseState;
    }

    public void setCourseStateNameCourseState(String courseStateNameCourseState) {
        this.courseStateNameCourseState = courseStateNameCourseState;
    }

    public Long getWorkingDayId() {
        return workingDayId;
    }

    public void setWorkingDayId(Long workingDayId) {
        this.workingDayId = workingDayId;
    }

    public String getWorkingDayNameWorkingDay() {
        return workingDayNameWorkingDay;
    }

    public void setWorkingDayNameWorkingDay(String workingDayNameWorkingDay) {
        this.workingDayNameWorkingDay = workingDayNameWorkingDay;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getProgramNameProgram() {
        return programNameProgram;
    }

    public void setProgramNameProgram(String programNameProgram) {
        this.programNameProgram = programNameProgram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourseDTO courseDTO = (CourseDTO) o;
        if (courseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
            "id=" + getId() +
            ", courseNumber='" + getCourseNumber() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", route='" + getRoute() + "'" +
            ", courseState=" + getCourseStateId() +
            ", courseState='" + getCourseStateNameCourseState() + "'" +
            ", workingDay=" + getWorkingDayId() +
            ", workingDay='" + getWorkingDayNameWorkingDay() + "'" +
            ", program=" + getProgramId() +
            ", program='" + getProgramNameProgram() + "'" +
            "}";
    }
}
