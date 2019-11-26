package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.domain.LearningResult} entity.
 */
public class LearningResultDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String codeResult;

    @NotNull
    @Size(max = 1000)
    private String denomination;


    private Long competitionId;

    private String competitionCodeCompetition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeResult() {
        return codeResult;
    }

    public void setCodeResult(String codeResult) {
        this.codeResult = codeResult;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionCodeCompetition() {
        return competitionCodeCompetition;
    }

    public void setCompetitionCodeCompetition(String competitionCodeCompetition) {
        this.competitionCodeCompetition = competitionCodeCompetition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LearningResultDTO learningResultDTO = (LearningResultDTO) o;
        if (learningResultDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), learningResultDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LearningResultDTO{" +
            "id=" + getId() +
            ", codeResult='" + getCodeResult() + "'" +
            ", denomination='" + getDenomination() + "'" +
            ", competition=" + getCompetitionId() +
            ", competition='" + getCompetitionCodeCompetition() + "'" +
            "}";
    }
}
