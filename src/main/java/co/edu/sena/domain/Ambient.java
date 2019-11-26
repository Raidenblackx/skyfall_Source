package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.Limitation;

import co.edu.sena.domain.enumeration.State;

/**
 * A Ambient.
 */
@Entity
@Table(name = "ambient")
public class Ambient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "number_room", length = 50, nullable = false)
    private String numberRoom;

    @NotNull
    @Size(max = 1000)
    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "limitation", nullable = false)
    private Limitation limitation;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_ambient", nullable = false)
    private State stateAmbient;

    @OneToMany(mappedBy = "ambient")
    private Set<LimitationEnvironment> limitationEnvironments = new HashSet<>();

    @OneToMany(mappedBy = "ambient")
    private Set<Schedule> schedules = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("ambients")
    private TypeEnvironment typeEnvironment;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("ambients")
    private Sede sede;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public Ambient numberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
        return this;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    public String getDescription() {
        return description;
    }

    public Ambient description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Limitation getLimitation() {
        return limitation;
    }

    public Ambient limitation(Limitation limitation) {
        this.limitation = limitation;
        return this;
    }

    public void setLimitation(Limitation limitation) {
        this.limitation = limitation;
    }

    public State getStateAmbient() {
        return stateAmbient;
    }

    public Ambient stateAmbient(State stateAmbient) {
        this.stateAmbient = stateAmbient;
        return this;
    }

    public void setStateAmbient(State stateAmbient) {
        this.stateAmbient = stateAmbient;
    }

    public Set<LimitationEnvironment> getLimitationEnvironments() {
        return limitationEnvironments;
    }

    public Ambient limitationEnvironments(Set<LimitationEnvironment> limitationEnvironments) {
        this.limitationEnvironments = limitationEnvironments;
        return this;
    }

    public Ambient addLimitationEnvironment(LimitationEnvironment limitationEnvironment) {
        this.limitationEnvironments.add(limitationEnvironment);
        limitationEnvironment.setAmbient(this);
        return this;
    }

    public Ambient removeLimitationEnvironment(LimitationEnvironment limitationEnvironment) {
        this.limitationEnvironments.remove(limitationEnvironment);
        limitationEnvironment.setAmbient(null);
        return this;
    }

    public void setLimitationEnvironments(Set<LimitationEnvironment> limitationEnvironments) {
        this.limitationEnvironments = limitationEnvironments;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public Ambient schedules(Set<Schedule> schedules) {
        this.schedules = schedules;
        return this;
    }

    public Ambient addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
        schedule.setAmbient(this);
        return this;
    }

    public Ambient removeSchedule(Schedule schedule) {
        this.schedules.remove(schedule);
        schedule.setAmbient(null);
        return this;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public TypeEnvironment getTypeEnvironment() {
        return typeEnvironment;
    }

    public Ambient typeEnvironment(TypeEnvironment typeEnvironment) {
        this.typeEnvironment = typeEnvironment;
        return this;
    }

    public void setTypeEnvironment(TypeEnvironment typeEnvironment) {
        this.typeEnvironment = typeEnvironment;
    }

    public Sede getSede() {
        return sede;
    }

    public Ambient sede(Sede sede) {
        this.sede = sede;
        return this;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ambient)) {
            return false;
        }
        return id != null && id.equals(((Ambient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Ambient{" +
            "id=" + getId() +
            ", numberRoom='" + getNumberRoom() + "'" +
            ", description='" + getDescription() + "'" +
            ", limitation='" + getLimitation() + "'" +
            ", stateAmbient='" + getStateAmbient() + "'" +
            "}";
    }
}
