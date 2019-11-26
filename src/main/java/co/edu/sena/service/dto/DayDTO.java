package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.Day} entity.
 */
public class DayDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String nameDay;

    @NotNull
    private State stateDay;


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

    public State getStateDay() {
        return stateDay;
    }

    public void setStateDay(State stateDay) {
        this.stateDay = stateDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DayDTO dayDTO = (DayDTO) o;
        if (dayDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dayDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DayDTO{" +
            "id=" + getId() +
            ", nameDay='" + getNameDay() + "'" +
            ", stateDay='" + getStateDay() + "'" +
            "}";
    }
}
