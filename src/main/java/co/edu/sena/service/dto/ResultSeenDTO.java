package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.domain.ResultSeen} entity.
 */
public class ResultSeenDTO implements Serializable {

    private Long id;


    private Long learningResultId;

    private String learningResultCodeResult;

    private Long courseHasTrimesterId;

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

    public Long getCourseHasTrimesterId() {
        return courseHasTrimesterId;
    }

    public void setCourseHasTrimesterId(Long courseHasTrimesterId) {
        this.courseHasTrimesterId = courseHasTrimesterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResultSeenDTO resultSeenDTO = (ResultSeenDTO) o;
        if (resultSeenDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resultSeenDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResultSeenDTO{" +
            "id=" + getId() +
            ", learningResult=" + getLearningResultId() +
            ", learningResult='" + getLearningResultCodeResult() + "'" +
            ", courseHasTrimester=" + getCourseHasTrimesterId() +
            "}";
    }
}
