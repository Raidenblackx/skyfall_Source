package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.Trimester} entity.
 */
public class TrimesterDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer nameTrimester;

    @NotNull
    private State stateTrimester;


    private Long workingDayId;

    private String workingDayNameWorkingDay;

    private Long levelFormationId;

    private String levelFormationLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNameTrimester() {
        return nameTrimester;
    }

    public void setNameTrimester(Integer nameTrimester) {
        this.nameTrimester = nameTrimester;
    }

    public State getStateTrimester() {
        return stateTrimester;
    }

    public void setStateTrimester(State stateTrimester) {
        this.stateTrimester = stateTrimester;
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

    public Long getLevelFormationId() {
        return levelFormationId;
    }

    public void setLevelFormationId(Long levelFormationId) {
        this.levelFormationId = levelFormationId;
    }

    public String getLevelFormationLevel() {
        return levelFormationLevel;
    }

    public void setLevelFormationLevel(String levelFormationLevel) {
        this.levelFormationLevel = levelFormationLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TrimesterDTO trimesterDTO = (TrimesterDTO) o;
        if (trimesterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trimesterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrimesterDTO{" +
            "id=" + getId() +
            ", nameTrimester=" + getNameTrimester() +
            ", stateTrimester='" + getStateTrimester() + "'" +
            ", workingDay=" + getWorkingDayId() +
            ", workingDay='" + getWorkingDayNameWorkingDay() + "'" +
            ", levelFormation=" + getLevelFormationId() +
            ", levelFormation='" + getLevelFormationLevel() + "'" +
            "}";
    }
}
