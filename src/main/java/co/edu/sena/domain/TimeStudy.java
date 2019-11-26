package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A TimeStudy.
 */
@Entity
@Table(name = "time_study")
public class TimeStudy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private Integer startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private Integer endTime;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("timeStudies")
    private JourneyInstructor journeyInstructor;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("timeStudies")
    private Day day;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public TimeStudy startTime(Integer startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public TimeStudy endTime(Integer endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public JourneyInstructor getJourneyInstructor() {
        return journeyInstructor;
    }

    public TimeStudy journeyInstructor(JourneyInstructor journeyInstructor) {
        this.journeyInstructor = journeyInstructor;
        return this;
    }

    public void setJourneyInstructor(JourneyInstructor journeyInstructor) {
        this.journeyInstructor = journeyInstructor;
    }

    public Day getDay() {
        return day;
    }

    public TimeStudy day(Day day) {
        this.day = day;
        return this;
    }

    public void setDay(Day day) {
        this.day = day;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TimeStudy)) {
            return false;
        }
        return id != null && id.equals(((TimeStudy) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TimeStudy{" +
            "id=" + getId() +
            ", startTime=" + getStartTime() +
            ", endTime=" + getEndTime() +
            "}";
    }
}
