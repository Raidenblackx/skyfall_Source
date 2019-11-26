package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ScheduleAvailability.
 */
@Entity
@Table(name = "schedule_availability")
public class ScheduleAvailability implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("scheduleAvailabilities")
    private InstructorLinking instructorLinking;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("scheduleAvailabilities")
    private JourneyInstructor journeyInstructor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InstructorLinking getInstructorLinking() {
        return instructorLinking;
    }

    public ScheduleAvailability instructorLinking(InstructorLinking instructorLinking) {
        this.instructorLinking = instructorLinking;
        return this;
    }

    public void setInstructorLinking(InstructorLinking instructorLinking) {
        this.instructorLinking = instructorLinking;
    }

    public JourneyInstructor getJourneyInstructor() {
        return journeyInstructor;
    }

    public ScheduleAvailability journeyInstructor(JourneyInstructor journeyInstructor) {
        this.journeyInstructor = journeyInstructor;
        return this;
    }

    public void setJourneyInstructor(JourneyInstructor journeyInstructor) {
        this.journeyInstructor = journeyInstructor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScheduleAvailability)) {
            return false;
        }
        return id != null && id.equals(((ScheduleAvailability) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ScheduleAvailability{" +
            "id=" + getId() +
            "}";
    }
}
