package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.ProyectActivity} entity.
 */
public class ProyectActivityDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer numberProyectActivity;

    @NotNull
    @Size(max = 400)
    private String descriptionActivity;

    @NotNull
    private State stateProyectActivity;


    private Long phaseId;

    private String phaseNamePhase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberProyectActivity() {
        return numberProyectActivity;
    }

    public void setNumberProyectActivity(Integer numberProyectActivity) {
        this.numberProyectActivity = numberProyectActivity;
    }

    public String getDescriptionActivity() {
        return descriptionActivity;
    }

    public void setDescriptionActivity(String descriptionActivity) {
        this.descriptionActivity = descriptionActivity;
    }

    public State getStateProyectActivity() {
        return stateProyectActivity;
    }

    public void setStateProyectActivity(State stateProyectActivity) {
        this.stateProyectActivity = stateProyectActivity;
    }

    public Long getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Long phaseId) {
        this.phaseId = phaseId;
    }

    public String getPhaseNamePhase() {
        return phaseNamePhase;
    }

    public void setPhaseNamePhase(String phaseNamePhase) {
        this.phaseNamePhase = phaseNamePhase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProyectActivityDTO proyectActivityDTO = (ProyectActivityDTO) o;
        if (proyectActivityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proyectActivityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProyectActivityDTO{" +
            "id=" + getId() +
            ", numberProyectActivity=" + getNumberProyectActivity() +
            ", descriptionActivity='" + getDescriptionActivity() + "'" +
            ", stateProyectActivity='" + getStateProyectActivity() + "'" +
            ", phase=" + getPhaseId() +
            ", phase='" + getPhaseNamePhase() + "'" +
            "}";
    }
}
