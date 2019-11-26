package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A ScheduleVersion.
 */
@Entity
@Table(name = "schedule_version")
public class ScheduleVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "number_version", length = 40, nullable = false)
    private String numberVersion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_schedule_version", nullable = false)
    private State stateScheduleVersion;

    @OneToMany(mappedBy = "scheduleVersion")
    private Set<Schedule> schedules = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("scheduleVersions")
    private CurrentQuarter currentQuarter;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberVersion() {
        return numberVersion;
    }

    public ScheduleVersion numberVersion(String numberVersion) {
        this.numberVersion = numberVersion;
        return this;
    }

    public void setNumberVersion(String numberVersion) {
        this.numberVersion = numberVersion;
    }

    public State getStateScheduleVersion() {
        return stateScheduleVersion;
    }

    public ScheduleVersion stateScheduleVersion(State stateScheduleVersion) {
        this.stateScheduleVersion = stateScheduleVersion;
        return this;
    }

    public void setStateScheduleVersion(State stateScheduleVersion) {
        this.stateScheduleVersion = stateScheduleVersion;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public ScheduleVersion schedules(Set<Schedule> schedules) {
        this.schedules = schedules;
        return this;
    }

    public ScheduleVersion addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
        schedule.setScheduleVersion(this);
        return this;
    }

    public ScheduleVersion removeSchedule(Schedule schedule) {
        this.schedules.remove(schedule);
        schedule.setScheduleVersion(null);
        return this;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public CurrentQuarter getCurrentQuarter() {
        return currentQuarter;
    }

    public ScheduleVersion currentQuarter(CurrentQuarter currentQuarter) {
        this.currentQuarter = currentQuarter;
        return this;
    }

    public void setCurrentQuarter(CurrentQuarter currentQuarter) {
        this.currentQuarter = currentQuarter;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScheduleVersion)) {
            return false;
        }
        return id != null && id.equals(((ScheduleVersion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ScheduleVersion{" +
            "id=" + getId() +
            ", numberVersion='" + getNumberVersion() + "'" +
            ", stateScheduleVersion='" + getStateScheduleVersion() + "'" +
            "}";
    }
}
