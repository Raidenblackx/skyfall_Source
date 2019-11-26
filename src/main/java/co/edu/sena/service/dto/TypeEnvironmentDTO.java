package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.TypeEnvironment} entity.
 */
public class TypeEnvironmentDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String type;

    @NotNull
    @Size(max = 100)
    private String description;

    @NotNull
    private State stateTypeEnvironment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getStateTypeEnvironment() {
        return stateTypeEnvironment;
    }

    public void setStateTypeEnvironment(State stateTypeEnvironment) {
        this.stateTypeEnvironment = stateTypeEnvironment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TypeEnvironmentDTO typeEnvironmentDTO = (TypeEnvironmentDTO) o;
        if (typeEnvironmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeEnvironmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeEnvironmentDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", description='" + getDescription() + "'" +
            ", stateTypeEnvironment='" + getStateTypeEnvironment() + "'" +
            "}";
    }
}
