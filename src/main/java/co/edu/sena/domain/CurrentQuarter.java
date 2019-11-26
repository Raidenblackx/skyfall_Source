package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A CurrentQuarter.
 */
@Entity
@Table(name = "current_quarter")
public class CurrentQuarter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "scheduled_quarter", nullable = false)
    private Integer scheduledQuarter;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private ZonedDateTime startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private ZonedDateTime endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_current_quarter", nullable = false)
    private State stateCurrentQuarter;

    @OneToMany(mappedBy = "currentQuarter")
    private Set<ScheduleVersion> scheduleVersions = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("currentQuarters")
    private Year year;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScheduledQuarter() {
        return scheduledQuarter;
    }

    public CurrentQuarter scheduledQuarter(Integer scheduledQuarter) {
        this.scheduledQuarter = scheduledQuarter;
        return this;
    }

    public void setScheduledQuarter(Integer scheduledQuarter) {
        this.scheduledQuarter = scheduledQuarter;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public CurrentQuarter startDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public CurrentQuarter endDate(ZonedDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public State getStateCurrentQuarter() {
        return stateCurrentQuarter;
    }

    public CurrentQuarter stateCurrentQuarter(State stateCurrentQuarter) {
        this.stateCurrentQuarter = stateCurrentQuarter;
        return this;
    }

    public void setStateCurrentQuarter(State stateCurrentQuarter) {
        this.stateCurrentQuarter = stateCurrentQuarter;
    }

    public Set<ScheduleVersion> getScheduleVersions() {
        return scheduleVersions;
    }

    public CurrentQuarter scheduleVersions(Set<ScheduleVersion> scheduleVersions) {
        this.scheduleVersions = scheduleVersions;
        return this;
    }

    public CurrentQuarter addScheduleVersion(ScheduleVersion scheduleVersion) {
        this.scheduleVersions.add(scheduleVersion);
        scheduleVersion.setCurrentQuarter(this);
        return this;
    }

    public CurrentQuarter removeScheduleVersion(ScheduleVersion scheduleVersion) {
        this.scheduleVersions.remove(scheduleVersion);
        scheduleVersion.setCurrentQuarter(null);
        return this;
    }

    public void setScheduleVersions(Set<ScheduleVersion> scheduleVersions) {
        this.scheduleVersions = scheduleVersions;
    }

    public Year getYear() {
        return year;
    }

    public CurrentQuarter year(Year year) {
        this.year = year;
        return this;
    }

    public void setYear(Year year) {
        this.year = year;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CurrentQuarter)) {
            return false;
        }
        return id != null && id.equals(((CurrentQuarter) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CurrentQuarter{" +
            "id=" + getId() +
            ", scheduledQuarter=" + getScheduledQuarter() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", stateCurrentQuarter='" + getStateCurrentQuarter() + "'" +
            "}";
    }
}
