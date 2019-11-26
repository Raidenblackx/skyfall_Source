package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.domain.Competition} entity.
 */
public class CompetitionDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String codeCompetition;

    @NotNull
    @Size(max = 1000)
    private String denomination;


    private Long programId;

    private String programNameProgram;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeCompetition() {
        return codeCompetition;
    }

    public void setCodeCompetition(String codeCompetition) {
        this.codeCompetition = codeCompetition;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
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

        CompetitionDTO competitionDTO = (CompetitionDTO) o;
        if (competitionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), competitionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompetitionDTO{" +
            "id=" + getId() +
            ", codeCompetition='" + getCodeCompetition() + "'" +
            ", denomination='" + getDenomination() + "'" +
            ", program=" + getProgramId() +
            ", program='" + getProgramNameProgram() + "'" +
            "}";
    }
}
