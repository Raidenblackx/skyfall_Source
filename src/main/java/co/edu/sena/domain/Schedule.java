package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A Schedule.
 */
@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private ZonedDateTime startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private ZonedDateTime endTime;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("schedules")
    private CourseHasTrimester courseHasTrimester;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("schedules")
    private Ambient ambient;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("schedules")
    private Instructor instructor;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("schedules")
    private Day day;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("schedules")
    private ScheduleVersion scheduleVersion;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("schedules")
    private Modality modality;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public Schedule startTime(ZonedDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public Schedule endTime(ZonedDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public CourseHasTrimester getCourseHasTrimester() {
        return courseHasTrimester;
    }

    public Schedule courseHasTrimester(CourseHasTrimester courseHasTrimester) {
        this.courseHasTrimester = courseHasTrimester;
        return this;
    }

    public void setCourseHasTrimester(CourseHasTrimester courseHasTrimester) {
        this.courseHasTrimester = courseHasTrimester;
    }

    public Ambient getAmbient() {
        return ambient;
    }

    public Schedule ambient(Ambient ambient) {
        this.ambient = ambient;
        return this;
    }

    public void setAmbient(Ambient ambient) {
        this.ambient = ambient;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public Schedule instructor(Instructor instructor) {
        this.instructor = instructor;
        return this;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Day getDay() {
        return day;
    }

    public Schedule day(Day day) {
        this.day = day;
        return this;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public ScheduleVersion getScheduleVersion() {
        return scheduleVersion;
    }

    public Schedule scheduleVersion(ScheduleVersion scheduleVersion) {
        this.scheduleVersion = scheduleVersion;
        return this;
    }

    public void setScheduleVersion(ScheduleVersion scheduleVersion) {
        this.scheduleVersion = scheduleVersion;
    }

    public Modality getModality() {
        return modality;
    }

    public Schedule modality(Modality modality) {
        this.modality = modality;
        return this;
    }

    public void setModality(Modality modality) {
        this.modality = modality;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Schedule)) {
            return false;
        }
        return id != null && id.equals(((Schedule) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Schedule{" +
            "id=" + getId() +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            "}";
    }
}
