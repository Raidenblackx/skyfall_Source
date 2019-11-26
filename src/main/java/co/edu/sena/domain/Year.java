package co.edu.sena.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A Year.
 */
@Entity
@Table(name = "year")
public class Year implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "number_year", nullable = false)
    private Integer numberYear;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_year", nullable = false)
    private State stateYear;

    @OneToMany(mappedBy = "year")
    private Set<InstructorLinking> instructorLinkings = new HashSet<>();

    @OneToMany(mappedBy = "year")
    private Set<CurrentQuarter> currentQuarters = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberYear() {
        return numberYear;
    }

    public Year numberYear(Integer numberYear) {
        this.numberYear = numberYear;
        return this;
    }

    public void setNumberYear(Integer numberYear) {
        this.numberYear = numberYear;
    }

    public State getStateYear() {
        return stateYear;
    }

    public Year stateYear(State stateYear) {
        this.stateYear = stateYear;
        return this;
    }

    public void setStateYear(State stateYear) {
        this.stateYear = stateYear;
    }

    public Set<InstructorLinking> getInstructorLinkings() {
        return instructorLinkings;
    }

    public Year instructorLinkings(Set<InstructorLinking> instructorLinkings) {
        this.instructorLinkings = instructorLinkings;
        return this;
    }

    public Year addInstructorLinking(InstructorLinking instructorLinking) {
        this.instructorLinkings.add(instructorLinking);
        instructorLinking.setYear(this);
        return this;
    }

    public Year removeInstructorLinking(InstructorLinking instructorLinking) {
        this.instructorLinkings.remove(instructorLinking);
        instructorLinking.setYear(null);
        return this;
    }

    public void setInstructorLinkings(Set<InstructorLinking> instructorLinkings) {
        this.instructorLinkings = instructorLinkings;
    }

    public Set<CurrentQuarter> getCurrentQuarters() {
        return currentQuarters;
    }

    public Year currentQuarters(Set<CurrentQuarter> currentQuarters) {
        this.currentQuarters = currentQuarters;
        return this;
    }

    public Year addCurrentQuarter(CurrentQuarter currentQuarter) {
        this.currentQuarters.add(currentQuarter);
        currentQuarter.setYear(this);
        return this;
    }

    public Year removeCurrentQuarter(CurrentQuarter currentQuarter) {
        this.currentQuarters.remove(currentQuarter);
        currentQuarter.setYear(null);
        return this;
    }

    public void setCurrentQuarters(Set<CurrentQuarter> currentQuarters) {
        this.currentQuarters = currentQuarters;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Year)) {
            return false;
        }
        return id != null && id.equals(((Year) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Year{" +
            "id=" + getId() +
            ", numberYear=" + getNumberYear() +
            ", stateYear='" + getStateYear() + "'" +
            "}";
    }
}
