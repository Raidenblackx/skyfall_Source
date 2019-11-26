package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.Modality} entity.
 */
public class ModalityDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String nameModality;

    @NotNull
    @Size(max = 50)
    private String color;

    @NotNull
    private State stateModality;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameModality() {
        return nameModality;
    }

    public void setNameModality(String nameModality) {
        this.nameModality = nameModality;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public State getStateModality() {
        return stateModality;
    }

    public void setStateModality(State stateModality) {
        this.stateModality = stateModality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ModalityDTO modalityDTO = (ModalityDTO) o;
        if (modalityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), modalityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ModalityDTO{" +
            "id=" + getId() +
            ", nameModality='" + getNameModality() + "'" +
            ", color='" + getColor() + "'" +
            ", stateModality='" + getStateModality() + "'" +
            "}";
    }
}
