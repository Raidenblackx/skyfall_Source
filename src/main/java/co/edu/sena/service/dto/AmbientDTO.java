package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.Limitation;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.Ambient} entity.
 */
public class AmbientDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String numberRoom;

    @NotNull
    @Size(max = 1000)
    private String description;

    @NotNull
    private Limitation limitation;

    @NotNull
    private State stateAmbient;


    private Long typeEnvironmentId;

    private String typeEnvironmentType;

    private Long sedeId;

    private String sedeNameSede;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Limitation getLimitation() {
        return limitation;
    }

    public void setLimitation(Limitation limitation) {
        this.limitation = limitation;
    }

    public State getStateAmbient() {
        return stateAmbient;
    }

    public void setStateAmbient(State stateAmbient) {
        this.stateAmbient = stateAmbient;
    }

    public Long getTypeEnvironmentId() {
        return typeEnvironmentId;
    }

    public void setTypeEnvironmentId(Long typeEnvironmentId) {
        this.typeEnvironmentId = typeEnvironmentId;
    }

    public String getTypeEnvironmentType() {
        return typeEnvironmentType;
    }

    public void setTypeEnvironmentType(String typeEnvironmentType) {
        this.typeEnvironmentType = typeEnvironmentType;
    }

    public Long getSedeId() {
        return sedeId;
    }

    public void setSedeId(Long sedeId) {
        this.sedeId = sedeId;
    }

    public String getSedeNameSede() {
        return sedeNameSede;
    }

    public void setSedeNameSede(String sedeNameSede) {
        this.sedeNameSede = sedeNameSede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AmbientDTO ambientDTO = (AmbientDTO) o;
        if (ambientDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ambientDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AmbientDTO{" +
            "id=" + getId() +
            ", numberRoom='" + getNumberRoom() + "'" +
            ", description='" + getDescription() + "'" +
            ", limitation='" + getLimitation() + "'" +
            ", stateAmbient='" + getStateAmbient() + "'" +
            ", typeEnvironment=" + getTypeEnvironmentId() +
            ", typeEnvironment='" + getTypeEnvironmentType() + "'" +
            ", sede=" + getSedeId() +
            ", sede='" + getSedeNameSede() + "'" +
            "}";
    }
}
