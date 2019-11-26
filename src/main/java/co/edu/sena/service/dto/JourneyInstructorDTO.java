package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.JourneyInstructor} entity.
 */
public class JourneyInstructorDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String nameDay;

    @NotNull
    @Size(max = 200)
    private String description;

    @NotNull
    private State stateJourneyInstructor;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDay() {
        return nameDay;
    }

    public void setNameDay(String nameDay) {
        this.nameDay = nameDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getStateJourneyInstructor() {
        return stateJourneyInstructor;
    }

    public void setStateJourneyInstructor(State stateJourneyInstructor) {
        this.stateJourneyInstructor = stateJourneyInstructor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JourneyInstructorDTO journeyInstructorDTO = (JourneyInstructorDTO) o;
        if (journeyInstructorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), journeyInstructorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JourneyInstructorDTO{" +
            "id=" + getId() +
            ", nameDay='" + getNameDay() + "'" +
            ", description='" + getDescription() + "'" +
            ", stateJourneyInstructor='" + getStateJourneyInstructor() + "'" +
            "}";
    }
}
