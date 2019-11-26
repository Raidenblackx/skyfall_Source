package co.edu.sena.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A Day.
 */
@Entity
@Table(name = "day")
public class Day implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "name_day", length = 40, nullable = false)
    private String nameDay;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_day", nullable = false)
    private State stateDay;

    @OneToMany(mappedBy = "day")
    private Set<Schedule> schedules = new HashSet<>();

    @OneToMany(mappedBy = "day")
    private Set<TimeStudy> timeStudies = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDay() {
        return nameDay;
    }

    public Day nameDay(String nameDay) {
        this.nameDay = nameDay;
        return this;
    }

    public void setNameDay(String nameDay) {
        this.nameDay = nameDay;
    }

    public State getStateDay() {
        return stateDay;
    }

    public Day stateDay(State stateDay) {
        this.stateDay = stateDay;
        return this;
    }

    public void setStateDay(State stateDay) {
        this.stateDay = stateDay;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public Day schedules(Set<Schedule> schedules) {
        this.schedules = schedules;
        return this;
    }

    public Day addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
        schedule.setDay(this);
        return this;
    }

    public Day removeSchedule(Schedule schedule) {
        this.schedules.remove(schedule);
        schedule.setDay(null);
        return this;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Set<TimeStudy> getTimeStudies() {
        return timeStudies;
    }

    public Day timeStudies(Set<TimeStudy> timeStudies) {
        this.timeStudies = timeStudies;
        return this;
    }

    public Day addTimeStudy(TimeStudy timeStudy) {
        this.timeStudies.add(timeStudy);
        timeStudy.setDay(this);
        return this;
    }

    public Day removeTimeStudy(TimeStudy timeStudy) {
        this.timeStudies.remove(timeStudy);
        timeStudy.setDay(null);
        return this;
    }

    public void setTimeStudies(Set<TimeStudy> timeStudies) {
        this.timeStudies = timeStudies;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Day)) {
            return false;
        }
        return id != null && id.equals(((Day) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Day{" +
            "id=" + getId() +
            ", nameDay='" + getNameDay() + "'" +
            ", stateDay='" + getStateDay() + "'" +
            "}";
    }
}
