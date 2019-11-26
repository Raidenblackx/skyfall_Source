package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.ScheduleVersion} entity.
 */
public class ScheduleVersionDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String numberVersion;

    @NotNull
    private State stateScheduleVersion;


    private Long currentQuarterId;

    private String currentQuarterScheduledQuarter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberVersion() {
        return numberVersion;
    }

    public void setNumberVersion(String numberVersion) {
        this.numberVersion = numberVersion;
    }

    public State getStateScheduleVersion() {
        return stateScheduleVersion;
    }

    public void setStateScheduleVersion(State stateScheduleVersion) {
        this.stateScheduleVersion = stateScheduleVersion;
    }

    public Long getCurrentQuarterId() {
        return currentQuarterId;
    }

    public void setCurrentQuarterId(Long currentQuarterId) {
        this.currentQuarterId = currentQuarterId;
    }

    public String getCurrentQuarterScheduledQuarter() {
        return currentQuarterScheduledQuarter;
    }

    public void setCurrentQuarterScheduledQuarter(String currentQuarterScheduledQuarter) {
        this.currentQuarterScheduledQuarter = currentQuarterScheduledQuarter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ScheduleVersionDTO scheduleVersionDTO = (ScheduleVersionDTO) o;
        if (scheduleVersionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), scheduleVersionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ScheduleVersionDTO{" +
            "id=" + getId() +
            ", numberVersion='" + getNumberVersion() + "'" +
            ", stateScheduleVersion='" + getStateScheduleVersion() + "'" +
            ", currentQuarter=" + getCurrentQuarterId() +
            ", currentQuarter='" + getCurrentQuarterScheduledQuarter() + "'" +
            "}";
    }
}
