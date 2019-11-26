package co.edu.sena.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A Planning.
 */
@Entity
@Table(name = "planning")
public class Planning implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "code", length = 40, nullable = false)
    private String code;

    @NotNull
    @Column(name = "date", nullable = false)
    private ZonedDateTime date;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_plannig", nullable = false)
    private State statePlannig;

    @OneToMany(mappedBy = "planning")
    private Set<TrimesterPlanning> trimesterPlannings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Planning code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public Planning date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public State getStatePlannig() {
        return statePlannig;
    }

    public Planning statePlannig(State statePlannig) {
        this.statePlannig = statePlannig;
        return this;
    }

    public void setStatePlannig(State statePlannig) {
        this.statePlannig = statePlannig;
    }

    public Set<TrimesterPlanning> getTrimesterPlannings() {
        return trimesterPlannings;
    }

    public Planning trimesterPlannings(Set<TrimesterPlanning> trimesterPlannings) {
        this.trimesterPlannings = trimesterPlannings;
        return this;
    }

    public Planning addTrimesterPlanning(TrimesterPlanning trimesterPlanning) {
        this.trimesterPlannings.add(trimesterPlanning);
        trimesterPlanning.setPlanning(this);
        return this;
    }

    public Planning removeTrimesterPlanning(TrimesterPlanning trimesterPlanning) {
        this.trimesterPlannings.remove(trimesterPlanning);
        trimesterPlanning.setPlanning(null);
        return this;
    }

    public void setTrimesterPlannings(Set<TrimesterPlanning> trimesterPlannings) {
        this.trimesterPlannings = trimesterPlannings;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Planning)) {
            return false;
        }
        return id != null && id.equals(((Planning) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Planning{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", date='" + getDate() + "'" +
            ", statePlannig='" + getStatePlannig() + "'" +
            "}";
    }
}
