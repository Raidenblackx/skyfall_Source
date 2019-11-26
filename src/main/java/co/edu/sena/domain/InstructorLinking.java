package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A InstructorLinking.
 */
@Entity
@Table(name = "instructor_linking")
public class InstructorLinking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private ZonedDateTime startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private ZonedDateTime endDate;

    @OneToMany(mappedBy = "instructorLinking")
    private Set<ScheduleAvailability> scheduleAvailabilities = new HashSet<>();

    @OneToMany(mappedBy = "instructorLinking")
    private Set<AvailabilityCompetition> availabilityCompetitions = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("instructorLinkings")
    private Year year;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("instructorLinkings")
    private Instructor instructor;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("instructorLinkings")
    private Linkage linkage;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public InstructorLinking startDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public InstructorLinking endDate(ZonedDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Set<ScheduleAvailability> getScheduleAvailabilities() {
        return scheduleAvailabilities;
    }

    public InstructorLinking scheduleAvailabilities(Set<ScheduleAvailability> scheduleAvailabilities) {
        this.scheduleAvailabilities = scheduleAvailabilities;
        return this;
    }

    public InstructorLinking addScheduleAvailability(ScheduleAvailability scheduleAvailability) {
        this.scheduleAvailabilities.add(scheduleAvailability);
        scheduleAvailability.setInstructorLinking(this);
        return this;
    }

    public InstructorLinking removeScheduleAvailability(ScheduleAvailability scheduleAvailability) {
        this.scheduleAvailabilities.remove(scheduleAvailability);
        scheduleAvailability.setInstructorLinking(null);
        return this;
    }

    public void setScheduleAvailabilities(Set<ScheduleAvailability> scheduleAvailabilities) {
        this.scheduleAvailabilities = scheduleAvailabilities;
    }

    public Set<AvailabilityCompetition> getAvailabilityCompetitions() {
        return availabilityCompetitions;
    }

    public InstructorLinking availabilityCompetitions(Set<AvailabilityCompetition> availabilityCompetitions) {
        this.availabilityCompetitions = availabilityCompetitions;
        return this;
    }

    public InstructorLinking addAvailabilityCompetition(AvailabilityCompetition availabilityCompetition) {
        this.availabilityCompetitions.add(availabilityCompetition);
        availabilityCompetition.setInstructorLinking(this);
        return this;
    }

    public InstructorLinking removeAvailabilityCompetition(AvailabilityCompetition availabilityCompetition) {
        this.availabilityCompetitions.remove(availabilityCompetition);
        availabilityCompetition.setInstructorLinking(null);
        return this;
    }

    public void setAvailabilityCompetitions(Set<AvailabilityCompetition> availabilityCompetitions) {
        this.availabilityCompetitions = availabilityCompetitions;
    }

    public Year getYear() {
        return year;
    }

    public InstructorLinking year(Year year) {
        this.year = year;
        return this;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public InstructorLinking instructor(Instructor instructor) {
        this.instructor = instructor;
        return this;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Linkage getLinkage() {
        return linkage;
    }

    public InstructorLinking linkage(Linkage linkage) {
        this.linkage = linkage;
        return this;
    }

    public void setLinkage(Linkage linkage) {
        this.linkage = linkage;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InstructorLinking)) {
            return false;
        }
        return id != null && id.equals(((InstructorLinking) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InstructorLinking{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
