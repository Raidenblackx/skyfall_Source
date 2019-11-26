package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.Instructor} entity.
 */
public class InstructorDTO implements Serializable {

    private Long id;

    @NotNull
    private State stateInstructor;


    private Long clientId;

    private String clientDocumentNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getStateInstructor() {
        return stateInstructor;
    }

    public void setStateInstructor(State stateInstructor) {
        this.stateInstructor = stateInstructor;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientDocumentNumber() {
        return clientDocumentNumber;
    }

    public void setClientDocumentNumber(String clientDocumentNumber) {
        this.clientDocumentNumber = clientDocumentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InstructorDTO instructorDTO = (InstructorDTO) o;
        if (instructorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), instructorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InstructorDTO{" +
            "id=" + getId() +
            ", stateInstructor='" + getStateInstructor() + "'" +
            ", client=" + getClientId() +
            ", client='" + getClientDocumentNumber() + "'" +
            "}";
    }
}
