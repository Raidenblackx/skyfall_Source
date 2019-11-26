package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.Proyect} entity.
 */
public class ProyectDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String codeProyect;

    @NotNull
    @Size(max = 500)
    private String nameProyect;

    @NotNull
    private State stateProyect;


    private Long programId;

    private String programNameProgram;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeProyect() {
        return codeProyect;
    }

    public void setCodeProyect(String codeProyect) {
        this.codeProyect = codeProyect;
    }

    public String getNameProyect() {
        return nameProyect;
    }

    public void setNameProyect(String nameProyect) {
        this.nameProyect = nameProyect;
    }

    public State getStateProyect() {
        return stateProyect;
    }

    public void setStateProyect(State stateProyect) {
        this.stateProyect = stateProyect;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getProgramNameProgram() {
        return programNameProgram;
    }

    public void setProgramNameProgram(String programNameProgram) {
        this.programNameProgram = programNameProgram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProyectDTO proyectDTO = (ProyectDTO) o;
        if (proyectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proyectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProyectDTO{" +
            "id=" + getId() +
            ", codeProyect='" + getCodeProyect() + "'" +
            ", nameProyect='" + getNameProyect() + "'" +
            ", stateProyect='" + getStateProyect() + "'" +
            ", program=" + getProgramId() +
            ", program='" + getProgramNameProgram() + "'" +
            "}";
    }
}
