package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A CourseHasTrimester.
 */
@Entity
@Table(name = "course_has_trimester")
public class CourseHasTrimester implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "courseHasTrimester")
    private Set<Schedule> schedules = new HashSet<>();

    @OneToMany(mappedBy = "courseHasTrimester")
    private Set<ResultSeen> resultSeens = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("courseHasTrimesters")
    private Course course;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("courseHasTrimesters")
    private Trimester trimester;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public CourseHasTrimester schedules(Set<Schedule> schedules) {
        this.schedules = schedules;
        return this;
    }

    public CourseHasTrimester addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
        schedule.setCourseHasTrimester(this);
        return this;
    }

    public CourseHasTrimester removeSchedule(Schedule schedule) {
        this.schedules.remove(schedule);
        schedule.setCourseHasTrimester(null);
        return this;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Set<ResultSeen> getResultSeens() {
        return resultSeens;
    }

    public CourseHasTrimester resultSeens(Set<ResultSeen> resultSeens) {
        this.resultSeens = resultSeens;
        return this;
    }

    public CourseHasTrimester addResultSeen(ResultSeen resultSeen) {
        this.resultSeens.add(resultSeen);
        resultSeen.setCourseHasTrimester(this);
        return this;
    }

    public CourseHasTrimester removeResultSeen(ResultSeen resultSeen) {
        this.resultSeens.remove(resultSeen);
        resultSeen.setCourseHasTrimester(null);
        return this;
    }

    public void setResultSeens(Set<ResultSeen> resultSeens) {
        this.resultSeens = resultSeens;
    }

    public Course getCourse() {
        return course;
    }

    public CourseHasTrimester course(Course course) {
        this.course = course;
        return this;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Trimester getTrimester() {
        return trimester;
    }

    public CourseHasTrimester trimester(Trimester trimester) {
        this.trimester = trimester;
        return this;
    }

    public void setTrimester(Trimester trimester) {
        this.trimester = trimester;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourseHasTrimester)) {
            return false;
        }
        return id != null && id.equals(((CourseHasTrimester) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CourseHasTrimester{" +
            "id=" + getId() +
            "}";
    }
}
