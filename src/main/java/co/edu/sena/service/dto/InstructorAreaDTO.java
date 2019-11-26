package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.InstructorArea} entity.
 */
public class InstructorAreaDTO implements Serializable {

    private Long id;

    @NotNull
    private State stateInstructorArea;


    private Long areaId;

    private String areaNameArea;

    private Long instructorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getStateInstructorArea() {
        return stateInstructorArea;
    }

    public void setStateInstructorArea(State stateInstructorArea) {
        this.stateInstructorArea = stateInstructorArea;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaNameArea() {
        return areaNameArea;
    }

    public void setAreaNameArea(String areaNameArea) {
        this.areaNameArea = areaNameArea;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InstructorAreaDTO instructorAreaDTO = (InstructorAreaDTO) o;
        if (instructorAreaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), instructorAreaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InstructorAreaDTO{" +
            "id=" + getId() +
            ", stateInstructorArea='" + getStateInstructorArea() + "'" +
            ", area=" + getAreaId() +
            ", area='" + getAreaNameArea() + "'" +
            ", instructor=" + getInstructorId() +
            "}";
    }
}
