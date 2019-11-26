package co.edu.sena.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.CurrentQuarter} entity.
 */
public class CurrentQuarterDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer scheduledQuarter;

    @NotNull
    private ZonedDateTime startDate;

    @NotNull
    private ZonedDateTime endDate;

    @NotNull
    private State stateCurrentQuarter;


    private Long yearId;

    private String yearNumberYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScheduledQuarter() {
        return scheduledQuarter;
    }

    public void setScheduledQuarter(Integer scheduledQuarter) {
        this.scheduledQuarter = scheduledQuarter;
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

    public State getStateCurrentQuarter() {
        return stateCurrentQuarter;
    }

    public void setStateCurrentQuarter(State stateCurrentQuarter) {
        this.stateCurrentQuarter = stateCurrentQuarter;
    }

    public Long getYearId() {
        return yearId;
    }

    public void setYearId(Long yearId) {
        this.yearId = yearId;
    }

    public String getYearNumberYear() {
        return yearNumberYear;
    }

    public void setYearNumberYear(String yearNumberYear) {
        this.yearNumberYear = yearNumberYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CurrentQuarterDTO currentQuarterDTO = (CurrentQuarterDTO) o;
        if (currentQuarterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), currentQuarterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CurrentQuarterDTO{" +
            "id=" + getId() +
            ", scheduledQuarter=" + getScheduledQuarter() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", stateCurrentQuarter='" + getStateCurrentQuarter() + "'" +
            ", year=" + getYearId() +
            ", year='" + getYearNumberYear() + "'" +
            "}";
    }
}
