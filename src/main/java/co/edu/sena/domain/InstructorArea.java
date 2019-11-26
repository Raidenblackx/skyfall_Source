package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import co.edu.sena.domain.enumeration.State;

/**
 * A InstructorArea.
 */
@Entity
@Table(name = "instructor_area")
public class InstructorArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_instructor_area", nullable = false)
    private State stateInstructorArea;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("instructorAreas")
    private Area area;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("instructorAreas")
    private Instructor instructor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getStateInstructorArea() {
        return stateInstructorArea;
    }

    public InstructorArea stateInstructorArea(State stateInstructorArea) {
        this.stateInstructorArea = stateInstructorArea;
        return this;
    }

    public void setStateInstructorArea(State stateInstructorArea) {
        this.stateInstructorArea = stateInstructorArea;
    }

    public Area getArea() {
        return area;
    }

    public InstructorArea area(Area area) {
        this.area = area;
        return this;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public InstructorArea instructor(Instructor instructor) {
        this.instructor = instructor;
        return this;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InstructorArea)) {
            return false;
        }
        return id != null && id.equals(((InstructorArea) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InstructorArea{" +
            "id=" + getId() +
            ", stateInstructorArea='" + getStateInstructorArea() + "'" +
            "}";
    }
}
