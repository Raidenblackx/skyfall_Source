package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.domain.LimitationEnvironment} entity.
 */
public class LimitationEnvironmentDTO implements Serializable {

    private Long id;


    private Long learningResultId;

    private String learningResultCodeResult;

    private Long ambientId;

    private String ambientNumberRoom;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LimitationEnvironmentDTO limitationEnvironmentDTO = (LimitationEnvironmentDTO) o;
        if (limitationEnvironmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), limitationEnvironmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LimitationEnvironmentDTO{" +
            "id=" + getId() +
            ", learningResult=" + getLearningResultId() +
            ", learningResult='" + getLearningResultCodeResult() + "'" +
            ", ambient=" + getAmbientId() +
            ", ambient='" + getAmbientNumberRoom() + "'" +
            "}";
    }
}
