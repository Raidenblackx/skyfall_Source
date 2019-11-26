package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.domain.TrimesterPlanning} entity.
 */
public class TrimesterPlanningDTO implements Serializable {

    private Long id;


    private Long learningResultId;

    private String learningResultCodeResult;

    private Long trimesterId;

    private String trimesterNameTrimester;

    private Long planningId;

    private String planningCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLearningResultId() {
        return learningResultId;
    }

    public void setLearningResultId(Long learningResultId) {
        this.learningResultId = learningResultId;
    }

    public String getLearningResultCodeResult() {
        return learningResultCodeResult;
    }

    public void setLearningResultCodeResult(String learningResultCodeResult) {
        this.learningResultCodeResult = learningResultCodeResult;
    }

    public Long getTrimesterId() {
        return trimesterId;
    }

    public void setTrimesterId(Long trimesterId) {
        this.trimesterId = trimesterId;
    }

    public String getTrimesterNameTrimester() {
        return trimesterNameTrimester;
    }

    public void setTrimesterNameTrimester(String trimesterNameTrimester) {
        this.trimesterNameTrimester = trimesterNameTrimester;
    }

    public Long getPlanningId() {
        return planningId;
    }

    public void setPlanningId(Long planningId) {
        this.planningId = planningId;
    }

    public String getPlanningCode() {
        return planningCode;
    }

    public void setPlanningCode(String planningCode) {
        this.planningCode = planningCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TrimesterPlanningDTO trimesterPlanningDTO = (TrimesterPlanningDTO) o;
        if (trimesterPlanningDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trimesterPlanningDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrimesterPlanningDTO{" +
            "id=" + getId() +
            ", learningResult=" + getLearningResultId() +
            ", learningResult='" + getLearningResultCodeResult() + "'" +
            ", trimester=" + getTrimesterId() +
            ", trimester='" + getTrimesterNameTrimester() + "'" +
            ", planning=" + getPlanningId() +
            ", planning='" + getPlanningCode() + "'" +
            "}";
    }
}
