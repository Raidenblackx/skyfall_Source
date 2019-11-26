package co.edu.sena.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A CourseState.
 */
@Entity
@Table(name = "course_state")
public class CourseState implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "name_course_state", length = 20, nullable = false)
    private String nameCourseState;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_course_state", nullable = false)
    private State stateCourseState;

    @OneToMany(mappedBy = "courseState")
    private Set<Course> courses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCourseState() {
        return nameCourseState;
    }

    public CourseState nameCourseState(String nameCourseState) {
        this.nameCourseState = nameCourseState;
        return this;
    }

    public void setNameCourseState(String nameCourseState) {
        this.nameCourseState = nameCourseState;
    }

    public State getStateCourseState() {
        return stateCourseState;
    }

    public CourseState stateCourseState(State stateCourseState) {
        this.stateCourseState = stateCourseState;
        return this;
    }

    public void setStateCourseState(State stateCourseState) {
        this.stateCourseState = stateCourseState;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public CourseState courses(Set<Course> courses) {
        this.courses = courses;
        return this;
    }

    public CourseState addCourse(Course course) {
        this.courses.add(course);
        course.setCourseState(this);
        return this;
    }

    public CourseState removeCourse(Course course) {
        this.courses.remove(course);
        course.setCourseState(null);
        return this;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourseState)) {
            return false;
        }
        return id != null && id.equals(((CourseState) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CourseState{" +
            "id=" + getId() +
            ", nameCourseState='" + getNameCourseState() + "'" +
            ", stateCourseState='" + getStateCourseState() + "'" +
            "}";
    }
}
