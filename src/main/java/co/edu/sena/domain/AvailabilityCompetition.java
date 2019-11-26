package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A AvailabilityCompetition.
 */
@Entity
@Table(name = "availability_competition")
public class AvailabilityCompetition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("availabilityCompetitions")
    private Competition competition;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("availabilityCompetitions")
    private InstructorLinking instructorLinking;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public AvailabilityCompetition competition(Competition competition) {
        this.competition = competition;
        return this;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public InstructorLinking getInstructorLinking() {
        return instructorLinking;
    }

    public AvailabilityCompetition instructorLinking(InstructorLinking instructorLinking) {
        this.instructorLinking = instructorLinking;
        return this;
    }

    public void setInstructorLinking(InstructorLinking instructorLinking) {
        this.instructorLinking = instructorLinking;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AvailabilityCompetition)) {
            return false;
        }
        return id != null && id.equals(((AvailabilityCompetition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AvailabilityCompetition{" +
            "id=" + getId() +
            "}";
    }
}
