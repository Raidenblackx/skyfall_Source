package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.StateProgram;

/**
 * A DTO for the {@link co.edu.sena.domain.Program} entity.
 */
public class ProgramDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String codeProgram;

    @NotNull
    @Size(max = 40)
    private String version;

    @NotNull
    @Size(max = 500)
    private String nameProgram;

    @NotNull
    @Size(max = 40)
    private String initial;

    @NotNull
    private StateProgram stateProgram;


    private Long levelFormationId;

    private String levelFormationLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeProgram() {
        return codeProgram;
    }

    public void setCodeProgram(String codeProgram) {
        this.codeProgram = codeProgram;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNameProgram() {
        return nameProgram;
    }

    public void setNameProgram(String nameProgram) {
        this.nameProgram = nameProgram;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public StateProgram getStateProgram() {
        return stateProgram;
    }

    public void setStateProgram(StateProgram stateProgram) {
        this.stateProgram = stateProgram;
    }

    public Long getLevelFormationId() {
        return levelFormationId;
    }

    public void setLevelFormationId(Long levelFormationId) {
        this.levelFormationId = levelFormationId;
    }

    public String getLevelFormationLevel() {
        return levelFormationLevel;
    }

    public void setLevelFormationLevel(String levelFormationLevel) {
        this.levelFormationLevel = levelFormationLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProgramDTO programDTO = (ProgramDTO) o;
        if (programDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), programDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProgramDTO{" +
            "id=" + getId() +
            ", codeProgram='" + getCodeProgram() + "'" +
            ", version='" + getVersion() + "'" +
            ", nameProgram='" + getNameProgram() + "'" +
            ", initial='" + getInitial() + "'" +
            ", stateProgram='" + getStateProgram() + "'" +
            ", levelFormation=" + getLevelFormationId() +
            ", levelFormation='" + getLevelFormationLevel() + "'" +
            "}";
    }
}
