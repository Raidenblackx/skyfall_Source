package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import co.edu.sena.domain.enumeration.State;

/**
 * A DTO for the {@link co.edu.sena.domain.Area} entity.
 */
public class AreaDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String nameArea;

    
    @Lob
    private byte[] urlLogo;

    private String urlLogoContentType;
    @NotNull
    private State stateArea;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameArea() {
        return nameArea;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }

    public byte[] getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(byte[] urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getUrlLogoContentType() {
        return urlLogoContentType;
    }

    public void setUrlLogoContentType(String urlLogoContentType) {
        this.urlLogoContentType = urlLogoContentType;
    }

    public State getStateArea() {
        return stateArea;
    }

    public void setStateArea(State stateArea) {
        this.stateArea = stateArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AreaDTO areaDTO = (AreaDTO) o;
        if (areaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), areaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AreaDTO{" +
            "id=" + getId() +
            ", nameArea='" + getNameArea() + "'" +
            ", urlLogo='" + getUrlLogo() + "'" +
            ", stateArea='" + getStateArea() + "'" +
            "}";
    }
}
