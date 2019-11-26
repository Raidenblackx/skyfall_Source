package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A Instructor.
 */
@Entity
@Table(name = "instructor")
public class Instructor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_instructor", nullable = false)
    private State stateInstructor;

    @OneToMany(mappedBy = "instructor")
    private Set<InstructorArea> instructorAreas = new HashSet<>();

    @OneToMany(mappedBy = "instructor")
    private Set<InstructorLinking> instructorLinkings = new HashSet<>();

    @OneToMany(mappedBy = "instructor")
    private Set<Schedule> schedules = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("instructors")
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getStateInstructor() {
        return stateInstructor;
    }

    public Instructor stateInstructor(State stateInstructor) {
        this.stateInstructor = stateInstructor;
        return this;
    }

    public void setStateInstructor(State stateInstructor) {
        this.stateInstructor = stateInstructor;
    }

    public Set<InstructorArea> getInstructorAreas() {
        return instructorAreas;
    }

    public Instructor instructorAreas(Set<InstructorArea> instructorAreas) {
        this.instructorAreas = instructorAreas;
        return this;
    }

    public Instructor addInstructorArea(InstructorArea instructorArea) {
        this.instructorAreas.add(instructorArea);
        instructorArea.setInstructor(this);
        return this;
    }

    public Instructor removeInstructorArea(InstructorArea instructorArea) {
        this.instructorAreas.remove(instructorArea);
        instructorArea.setInstructor(null);
        return this;
    }

    public void setInstructorAreas(Set<InstructorArea> instructorAreas) {
        this.instructorAreas = instructorAreas;
    }

    public Set<InstructorLinking> getInstructorLinkings() {
        return instructorLinkings;
    }

    public Instructor instructorLinkings(Set<InstructorLinking> instructorLinkings) {
        this.instructorLinkings = instructorLinkings;
        return this;
    }

    public Instructor addInstructorLinking(InstructorLinking instructorLinking) {
        this.instructorLinkings.add(instructorLinking);
        instructorLinking.setInstructor(this);
        return this;
    }

    public Instructor removeInstructorLinking(InstructorLinking instructorLinking) {
        this.instructorLinkings.remove(instructorLinking);
        instructorLinking.setInstructor(null);
        return this;
    }

    public void setInstructorLinkings(Set<InstructorLinking> instructorLinkings) {
        this.instructorLinkings = instructorLinkings;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public Instructor schedules(Set<Schedule> schedules) {
        this.schedules = schedules;
        return this;
    }

    public Instructor addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
        schedule.setInstructor(this);
        return this;
    }

    public Instructor removeSchedule(Schedule schedule) {
        this.schedules.remove(schedule);
        schedule.setInstructor(null);
        return this;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Client getClient() {
        return client;
    }

    public Instructor client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Instructor)) {
            return false;
        }
        return id != null && id.equals(((Instructor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Instructor{" +
            "id=" + getId() +
            ", stateInstructor='" + getStateInstructor() + "'" +
            "}";
    }
}
