package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.domain.AvailabilityCompetition} entity.
 */
public class AvailabilityCompetitionDTO implements Serializable {

    private Long id;


    private Long competitionId;

    private String competitionCodeCompetition;

    private Long instructorLinkingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getInstructorLinkingId() {
        return instructorLinkingId;
    }

    public void setInstructorLinkingId(Long instructorLinkingId) {
        this.instructorLinkingId = instructorLinkingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AvailabilityCompetitionDTO availabilityCompetitionDTO = (AvailabilityCompetitionDTO) o;
        if (availabilityCompetitionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), availabilityCompetitionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AvailabilityCompetitionDTO{" +
            "id=" + getId() +
            ", competition=" + getCompetitionId() +
            ", competition='" + getCompetitionCodeCompetition() + "'" +
            ", instructorLinking=" + getInstructorLinkingId() +
            "}";
    }
}
