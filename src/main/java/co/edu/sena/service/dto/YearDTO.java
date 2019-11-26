package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.Year} entity.
 */
public class YearDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer numberYear;

    @NotNull
    private State stateYear;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberYear() {
        return numberYear;
    }

    public void setNumberYear(Integer numberYear) {
        this.numberYear = numberYear;
    }

    public State getStateYear() {
        return stateYear;
    }

    public void setStateYear(State stateYear) {
        this.stateYear = stateYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        YearDTO yearDTO = (YearDTO) o;
        if (yearDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), yearDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "YearDTO{" +
            "id=" + getId() +
            ", numberYear=" + getNumberYear() +
            ", stateYear='" + getStateYear() + "'" +
            "}";
    }
}
