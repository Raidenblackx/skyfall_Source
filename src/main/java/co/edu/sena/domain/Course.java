package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A Course.
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "course_number", length = 40, nullable = false)
    private String courseNumber;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private ZonedDateTime startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private ZonedDateTime endDate;

    @NotNull
    @Size(max = 40)
    @Column(name = "route", length = 40, nullable = false)
    private String route;

    @OneToMany(mappedBy = "course")
    private Set<CourseHasTrimester> courseHasTrimesters = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("courses")
    private CourseState courseState;

    @ManyToOne
    @JsonIgnoreProperties("courses")
    private WorkingDay workingDay;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("courses")
    private Program program;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public Course courseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
        return this;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public Course startDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public Course endDate(ZonedDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public String getRoute() {
        return route;
    }

    public Course route(String route) {
        this.route = route;
        return this;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Set<CourseHasTrimester> getCourseHasTrimesters() {
        return courseHasTrimesters;
    }

    public Course courseHasTrimesters(Set<CourseHasTrimester> courseHasTrimesters) {
        this.courseHasTrimesters = courseHasTrimesters;
        return this;
    }

    public Course addCourseHasTrimester(CourseHasTrimester courseHasTrimester) {
        this.courseHasTrimesters.add(courseHasTrimester);
        courseHasTrimester.setCourse(this);
        return this;
    }

    public Course removeCourseHasTrimester(CourseHasTrimester courseHasTrimester) {
        this.courseHasTrimesters.remove(courseHasTrimester);
        courseHasTrimester.setCourse(null);
        return this;
    }

    public void setCourseHasTrimesters(Set<CourseHasTrimester> courseHasTrimesters) {
        this.courseHasTrimesters = courseHasTrimesters;
    }

    public CourseState getCourseState() {
        return courseState;
    }

    public Course courseState(CourseState courseState) {
        this.courseState = courseState;
        return this;
    }

    public void setCourseState(CourseState courseState) {
        this.courseState = courseState;
    }

    public WorkingDay getWorkingDay() {
        return workingDay;
    }

    public Course workingDay(WorkingDay workingDay) {
        this.workingDay = workingDay;
        return this;
    }

    public void setWorkingDay(WorkingDay workingDay) {
        this.workingDay = workingDay;
    }

    public Program getProgram() {
        return program;
    }

    public Course program(Program program) {
        this.program = program;
        return this;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }
        return id != null && id.equals(((Course) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", courseNumber='" + getCourseNumber() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", route='" + getRoute() + "'" +
            "}";
    }
}
