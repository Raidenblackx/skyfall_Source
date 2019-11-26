package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.Linkage} entity.
 */
public class LinkageDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String typeLink;

    @NotNull
    private Integer hours;

    @NotNull
    private State stateLink;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeLink() {
        return typeLink;
    }

    public void setTypeLink(String typeLink) {
        this.typeLink = typeLink;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public State getStateLink() {
        return stateLink;
    }

    public void setStateLink(State stateLink) {
        this.stateLink = stateLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LinkageDTO linkageDTO = (LinkageDTO) o;
        if (linkageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), linkageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LinkageDTO{" +
            "id=" + getId() +
            ", typeLink='" + getTypeLink() + "'" +
            ", hours=" + getHours() +
            ", stateLink='" + getStateLink() + "'" +
            "}";
    }
}
