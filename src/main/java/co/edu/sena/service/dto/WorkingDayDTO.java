package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.WorkingDay} entity.
 */
public class WorkingDayDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 20)
    private String initialWorkingDay;

    @NotNull
    @Size(max = 40)
    private String nameWorkingDay;

    @NotNull
    @Size(max = 100)
    private String description;

    @Lob
    private byte[] imagenUrl;

    private String imagenUrlContentType;
    @NotNull
    private State stateWorkingDay;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInitialWorkingDay() {
        return initialWorkingDay;
    }

    public void setInitialWorkingDay(String initialWorkingDay) {
        this.initialWorkingDay = initialWorkingDay;
    }

    public String getNameWorkingDay() {
        return nameWorkingDay;
    }

    public void setNameWorkingDay(String nameWorkingDay) {
        this.nameWorkingDay = nameWorkingDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(byte[] imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getImagenUrlContentType() {
        return imagenUrlContentType;
    }

    public void setImagenUrlContentType(String imagenUrlContentType) {
        this.imagenUrlContentType = imagenUrlContentType;
    }

    public State getStateWorkingDay() {
        return stateWorkingDay;
    }

    public void setStateWorkingDay(State stateWorkingDay) {
        this.stateWorkingDay = stateWorkingDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WorkingDayDTO workingDayDTO = (WorkingDayDTO) o;
        if (workingDayDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workingDayDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkingDayDTO{" +
            "id=" + getId() +
            ", initialWorkingDay='" + getInitialWorkingDay() + "'" +
            ", nameWorkingDay='" + getNameWorkingDay() + "'" +
            ", description='" + getDescription() + "'" +
            ", imagenUrl='" + getImagenUrl() + "'" +
            ", stateWorkingDay='" + getStateWorkingDay() + "'" +
            "}";
    }
}
