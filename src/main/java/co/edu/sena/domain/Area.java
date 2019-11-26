package co.edu.sena.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A Area.
 */
@Entity
@Table(name = "area")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "name_area", length = 40, nullable = false)
    private String nameArea;

    
    @Lob
    @Column(name = "url_logo", nullable = false)
    private byte[] urlLogo;

    @Column(name = "url_logo_content_type", nullable = false)
    private String urlLogoContentType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_area", nullable = false)
    private State stateArea;

    @OneToMany(mappedBy = "area")
    private Set<InstructorArea> instructorAreas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameArea() {
        return nameArea;
    }

    public Area nameArea(String nameArea) {
        this.nameArea = nameArea;
        return this;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }

    public byte[] getUrlLogo() {
        return urlLogo;
    }

    public Area urlLogo(byte[] urlLogo) {
        this.urlLogo = urlLogo;
        return this;
    }

    public void setUrlLogo(byte[] urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getUrlLogoContentType() {
        return urlLogoContentType;
    }

    public Area urlLogoContentType(String urlLogoContentType) {
        this.urlLogoContentType = urlLogoContentType;
        return this;
    }

    public void setUrlLogoContentType(String urlLogoContentType) {
        this.urlLogoContentType = urlLogoContentType;
    }

    public State getStateArea() {
        return stateArea;
    }

    public Area stateArea(State stateArea) {
        this.stateArea = stateArea;
        return this;
    }

    public void setStateArea(State stateArea) {
        this.stateArea = stateArea;
    }

    public Set<InstructorArea> getInstructorAreas() {
        return instructorAreas;
    }

    public Area instructorAreas(Set<InstructorArea> instructorAreas) {
        this.instructorAreas = instructorAreas;
        return this;
    }

    public Area addInstructorArea(InstructorArea instructorArea) {
        this.instructorAreas.add(instructorArea);
        instructorArea.setArea(this);
        return this;
    }

    public Area removeInstructorArea(InstructorArea instructorArea) {
        this.instructorAreas.remove(instructorArea);
        instructorArea.setArea(null);
        return this;
    }

    public void setInstructorAreas(Set<InstructorArea> instructorAreas) {
        this.instructorAreas = instructorAreas;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Area)) {
            return false;
        }
        return id != null && id.equals(((Area) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Area{" +
            "id=" + getId() +
            ", nameArea='" + getNameArea() + "'" +
            ", urlLogo='" + getUrlLogo() + "'" +
            ", urlLogoContentType='" + getUrlLogoContentType() + "'" +
            ", stateArea='" + getStateArea() + "'" +
            "}";
    }
}
