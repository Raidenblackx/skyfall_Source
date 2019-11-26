package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.CourseState} entity.
 */
public class CourseStateDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 20)
    private String nameCourseState;

    @NotNull
    private State stateCourseState;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCourseState() {
        return nameCourseState;
    }

    public void setNameCourseState(String nameCourseState) {
        this.nameCourseState = nameCourseState;
    }

    public State getStateCourseState() {
        return stateCourseState;
    }

    public void setStateCourseState(State stateCourseState) {
        this.stateCourseState = stateCourseState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourseStateDTO courseStateDTO = (CourseStateDTO) o;
        if (courseStateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseStateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseStateDTO{" +
            "id=" + getId() +
            ", nameCourseState='" + getNameCourseState() + "'" +
            ", stateCourseState='" + getStateCourseState() + "'" +
            "}";
    }
}
