package co.edu.sena.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A Modality.
 */
@Entity
@Table(name = "modality")
public class Modality implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "name_modality", length = 40, nullable = false)
    private String nameModality;

    @NotNull
    @Size(max = 50)
    @Column(name = "color", length = 50, nullable = false)
    private String color;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_modality", nullable = false)
    private State stateModality;

    @OneToMany(mappedBy = "modality")
    private Set<Schedule> schedules = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameModality() {
        return nameModality;
    }

    public Modality nameModality(String nameModality) {
        this.nameModality = nameModality;
        return this;
    }

    public void setNameModality(String nameModality) {
        this.nameModality = nameModality;
    }

    public String getColor() {
        return color;
    }

    public Modality color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public State getStateModality() {
        return stateModality;
    }

    public Modality stateModality(State stateModality) {
        this.stateModality = stateModality;
        return this;
    }

    public void setStateModality(State stateModality) {
        this.stateModality = stateModality;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public Modality schedules(Set<Schedule> schedules) {
        this.schedules = schedules;
        return this;
    }

    public Modality addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
        schedule.setModality(this);
        return this;
    }

    public Modality removeSchedule(Schedule schedule) {
        this.schedules.remove(schedule);
        schedule.setModality(null);
        return this;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Modality)) {
            return false;
        }
        return id != null && id.equals(((Modality) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Modality{" +
            "id=" + getId() +
            ", nameModality='" + getNameModality() + "'" +
            ", color='" + getColor() + "'" +
            ", stateModality='" + getStateModality() + "'" +
            "}";
    }
}
