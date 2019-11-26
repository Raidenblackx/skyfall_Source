package co.edu.sena.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A Linkage.
 */
@Entity
@Table(name = "linkage")
public class Linkage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "type_link", length = 40, nullable = false)
    private String typeLink;

    @NotNull
    @Column(name = "hours", nullable = false)
    private Integer hours;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_link", nullable = false)
    private State stateLink;

    @OneToMany(mappedBy = "linkage")
    private Set<InstructorLinking> instructorLinkings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeLink() {
        return typeLink;
    }

    public Linkage typeLink(String typeLink) {
        this.typeLink = typeLink;
        return this;
    }

    public void setTypeLink(String typeLink) {
        this.typeLink = typeLink;
    }

    public Integer getHours() {
        return hours;
    }

    public Linkage hours(Integer hours) {
        this.hours = hours;
        return this;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public State getStateLink() {
        return stateLink;
    }

    public Linkage stateLink(State stateLink) {
        this.stateLink = stateLink;
        return this;
    }

    public void setStateLink(State stateLink) {
        this.stateLink = stateLink;
    }

    public Set<InstructorLinking> getInstructorLinkings() {
        return instructorLinkings;
    }

    public Linkage instructorLinkings(Set<InstructorLinking> instructorLinkings) {
        this.instructorLinkings = instructorLinkings;
        return this;
    }

    public Linkage addInstructorLinking(InstructorLinking instructorLinking) {
        this.instructorLinkings.add(instructorLinking);
        instructorLinking.setLinkage(this);
        return this;
    }

    public Linkage removeInstructorLinking(InstructorLinking instructorLinking) {
        this.instructorLinkings.remove(instructorLinking);
        instructorLinking.setLinkage(null);
        return this;
    }

    public void setInstructorLinkings(Set<InstructorLinking> instructorLinkings) {
        this.instructorLinkings = instructorLinkings;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Linkage)) {
            return false;
        }
        return id != null && id.equals(((Linkage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Linkage{" +
            "id=" + getId() +
            ", typeLink='" + getTypeLink() + "'" +
            ", hours=" + getHours() +
            ", stateLink='" + getStateLink() + "'" +
            "}";
    }
}
