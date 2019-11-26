package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.Phase} entity.
 */
public class PhaseDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String namePhase;

    @NotNull
    private State statePhase;


    private Long proyectId;

    private String proyectCodeProyect;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamePhase() {
        return namePhase;
    }

    public void setNamePhase(String namePhase) {
        this.namePhase = namePhase;
    }

    public State getStatePhase() {
        return statePhase;
    }

    public void setStatePhase(State statePhase) {
        this.statePhase = statePhase;
    }

    public Long getProyectId() {
        return proyectId;
    }

    public void setProyectId(Long proyectId) {
        this.proyectId = proyectId;
    }

    public String getProyectCodeProyect() {
        return proyectCodeProyect;
    }

    public void setProyectCodeProyect(String proyectCodeProyect) {
        this.proyectCodeProyect = proyectCodeProyect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PhaseDTO phaseDTO = (PhaseDTO) o;
        if (phaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), phaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PhaseDTO{" +
            "id=" + getId() +
            ", namePhase='" + getNamePhase() + "'" +
            ", statePhase='" + getStatePhase() + "'" +
            ", proyect=" + getProyectId() +
            ", proyect='" + getProyectCodeProyect() + "'" +
            "}";
    }
}
