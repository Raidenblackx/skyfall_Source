package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.LevelFormation} entity.
 */
public class LevelFormationDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String level;

    @NotNull
    private State stateLevelFormation;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public State getStateLevelFormation() {
        return stateLevelFormation;
    }

    public void setStateLevelFormation(State stateLevelFormation) {
        this.stateLevelFormation = stateLevelFormation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LevelFormationDTO levelFormationDTO = (LevelFormationDTO) o;
        if (levelFormationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), levelFormationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LevelFormationDTO{" +
            "id=" + getId() +
            ", level='" + getLevel() + "'" +
            ", stateLevelFormation='" + getStateLevelFormation() + "'" +
            "}";
    }
}
