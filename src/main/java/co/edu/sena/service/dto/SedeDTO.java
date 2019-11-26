package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.Sede} entity.
 */
public class SedeDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String nameSede;

    @NotNull
    @Size(max = 400)
    private String description;

    @NotNull
    private State stateSede;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSede() {
        return nameSede;
    }

    public void setNameSede(String nameSede) {
        this.nameSede = nameSede;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getStateSede() {
        return stateSede;
    }

    public void setStateSede(State stateSede) {
        this.stateSede = stateSede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SedeDTO sedeDTO = (SedeDTO) o;
        if (sedeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sedeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SedeDTO{" +
            "id=" + getId() +
            ", nameSede='" + getNameSede() + "'" +
            ", description='" + getDescription() + "'" +
            ", stateSede='" + getStateSede() + "'" +
            "}";
    }
}
