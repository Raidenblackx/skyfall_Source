package co.edu.sena.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A JourneyInstructor.
 */
@Entity
@Table(name = "journey_instructor")
public class JourneyInstructor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "name_day", length = 40, nullable = false)
    private String nameDay;

    @NotNull
    @Size(max = 200)
    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_journey_instructor", nullable = false)
    private State stateJourneyInstructor;

    @OneToMany(mappedBy = "journeyInstructor")
    private Set<TimeStudy> timeStudies = new HashSet<>();

    @OneToMany(mappedBy = "journeyInstructor")
    private Set<ScheduleAvailability> scheduleAvailabilities = new HashSet<>();

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

    public JourneyInstructor nameDay(String nameDay) {
        this.nameDay = nameDay;
        return this;
    }

    public void setNameDay(String nameDay) {
        this.nameDay = nameDay;
    }

    public String getDescription() {
        return description;
    }

    public JourneyInstructor description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getStateJourneyInstructor() {
        return stateJourneyInstructor;
    }

    public JourneyInstructor stateJourneyInstructor(State stateJourneyInstructor) {
        this.stateJourneyInstructor = stateJourneyInstructor;
        return this;
    }

    public void setStateJourneyInstructor(State stateJourneyInstructor) {
        this.stateJourneyInstructor = stateJourneyInstructor;
    }

    public Set<TimeStudy> getTimeStudies() {
        return timeStudies;
    }

    public JourneyInstructor timeStudies(Set<TimeStudy> timeStudies) {
        this.timeStudies = timeStudies;
        return this;
    }

    public JourneyInstructor addTimeStudy(TimeStudy timeStudy) {
        this.timeStudies.add(timeStudy);
        timeStudy.setJourneyInstructor(this);
        return this;
    }

    public JourneyInstructor removeTimeStudy(TimeStudy timeStudy) {
        this.timeStudies.remove(timeStudy);
        timeStudy.setJourneyInstructor(null);
        return this;
    }

    public void setTimeStudies(Set<TimeStudy> timeStudies) {
        this.timeStudies = timeStudies;
    }

    public Set<ScheduleAvailability> getScheduleAvailabilities() {
        return scheduleAvailabilities;
    }

    public JourneyInstructor scheduleAvailabilities(Set<ScheduleAvailability> scheduleAvailabilities) {
        this.scheduleAvailabilities = scheduleAvailabilities;
        return this;
    }

    public JourneyInstructor addScheduleAvailability(ScheduleAvailability scheduleAvailability) {
        this.scheduleAvailabilities.add(scheduleAvailability);
        scheduleAvailability.setJourneyInstructor(this);
        return this;
    }

    public JourneyInstructor removeScheduleAvailability(ScheduleAvailability scheduleAvailability) {
        this.scheduleAvailabilities.remove(scheduleAvailability);
        scheduleAvailability.setJourneyInstructor(null);
        return this;
    }

    public void setScheduleAvailabilities(Set<ScheduleAvailability> scheduleAvailabilities) {
        this.scheduleAvailabilities = scheduleAvailabilities;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JourneyInstructor)) {
            return false;
        }
        return id != null && id.equals(((JourneyInstructor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "JourneyInstructor{" +
            "id=" + getId() +
            ", nameDay='" + getNameDay() + "'" +
            ", description='" + getDescription() + "'" +
            ", stateJourneyInstructor='" + getStateJourneyInstructor() + "'" +
            "}";
    }
}
