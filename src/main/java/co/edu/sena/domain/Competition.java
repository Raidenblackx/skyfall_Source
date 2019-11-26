package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Competition.
 */
@Entity
@Table(name = "competition")
public class Competition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "code_competition", length = 50, nullable = false)
    private String codeCompetition;

    @NotNull
    @Size(max = 1000)
    @Column(name = "denomination", length = 1000, nullable = false)
    private String denomination;

    @OneToMany(mappedBy = "competition")
    private Set<LearningResult> learningResults = new HashSet<>();

    @OneToMany(mappedBy = "competition")
    private Set<AvailabilityCompetition> availabilityCompetitions = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("competitions")
    private Program program;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeCompetition() {
        return codeCompetition;
    }

    public Competition codeCompetition(String codeCompetition) {
        this.codeCompetition = codeCompetition;
        return this;
    }

    public void setCodeCompetition(String codeCompetition) {
        this.codeCompetition = codeCompetition;
    }

    public String getDenomination() {
        return denomination;
    }

    public Competition denomination(String denomination) {
        this.denomination = denomination;
        return this;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Set<LearningResult> getLearningResults() {
        return learningResults;
    }

    public Competition learningResults(Set<LearningResult> learningResults) {
        this.learningResults = learningResults;
        return this;
    }

    public Competition addLearningResult(LearningResult learningResult) {
        this.learningResults.add(learningResult);
        learningResult.setCompetition(this);
        return this;
    }

    public Competition removeLearningResult(LearningResult learningResult) {
        this.learningResults.remove(learningResult);
        learningResult.setCompetition(null);
        return this;
    }

    public void setLearningResults(Set<LearningResult> learningResults) {
        this.learningResults = learningResults;
    }

    public Set<AvailabilityCompetition> getAvailabilityCompetitions() {
        return availabilityCompetitions;
    }

    public Competition availabilityCompetitions(Set<AvailabilityCompetition> availabilityCompetitions) {
        this.availabilityCompetitions = availabilityCompetitions;
        return this;
    }

    public Competition addAvailabilityCompetition(AvailabilityCompetition availabilityCompetition) {
        this.availabilityCompetitions.add(availabilityCompetition);
        availabilityCompetition.setCompetition(this);
        return this;
    }

    public Competition removeAvailabilityCompetition(AvailabilityCompetition availabilityCompetition) {
        this.availabilityCompetitions.remove(availabilityCompetition);
        availabilityCompetition.setCompetition(null);
        return this;
    }

    public void setAvailabilityCompetitions(Set<AvailabilityCompetition> availabilityCompetitions) {
        this.availabilityCompetitions = availabilityCompetitions;
    }

    public Program getProgram() {
        return program;
    }

    public Competition program(Program program) {
        this.program = program;
        return this;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Competition)) {
            return false;
        }
        return id != null && id.equals(((Competition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Competition{" +
            "id=" + getId() +
            ", codeCompetition='" + getCodeCompetition() + "'" +
            ", denomination='" + getDenomination() + "'" +
            "}";
    }
}
