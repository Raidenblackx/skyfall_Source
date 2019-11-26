package co.edu.sena.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A WorkingDay.
 */
@Entity
@Table(name = "working_day")
public class WorkingDay implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "initial_working_day", length = 20, nullable = false)
    private String initialWorkingDay;

    @NotNull
    @Size(max = 40)
    @Column(name = "name_working_day", length = 40, nullable = false)
    private String nameWorkingDay;

    @NotNull
    @Size(max = 100)
    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Lob
    @Column(name = "imagen_url")
    private byte[] imagenUrl;

    @Column(name = "imagen_url_content_type")
    private String imagenUrlContentType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_working_day", nullable = false)
    private State stateWorkingDay;

    @OneToMany(mappedBy = "workingDay")
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "workingDay")
    private Set<Trimester> trimesters = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInitialWorkingDay() {
        return initialWorkingDay;
    }

    public WorkingDay initialWorkingDay(String initialWorkingDay) {
        this.initialWorkingDay = initialWorkingDay;
        return this;
    }

    public void setInitialWorkingDay(String initialWorkingDay) {
        this.initialWorkingDay = initialWorkingDay;
    }

    public String getNameWorkingDay() {
        return nameWorkingDay;
    }

    public WorkingDay nameWorkingDay(String nameWorkingDay) {
        this.nameWorkingDay = nameWorkingDay;
        return this;
    }

    public void setNameWorkingDay(String nameWorkingDay) {
        this.nameWorkingDay = nameWorkingDay;
    }

    public String getDescription() {
        return description;
    }

    public WorkingDay description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImagenUrl() {
        return imagenUrl;
    }

    public WorkingDay imagenUrl(byte[] imagenUrl) {
        this.imagenUrl = imagenUrl;
        return this;
    }

    public void setImagenUrl(byte[] imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getImagenUrlContentType() {
        return imagenUrlContentType;
    }

    public WorkingDay imagenUrlContentType(String imagenUrlContentType) {
        this.imagenUrlContentType = imagenUrlContentType;
        return this;
    }

    public void setImagenUrlContentType(String imagenUrlContentType) {
        this.imagenUrlContentType = imagenUrlContentType;
    }

    public State getStateWorkingDay() {
        return stateWorkingDay;
    }

    public WorkingDay stateWorkingDay(State stateWorkingDay) {
        this.stateWorkingDay = stateWorkingDay;
        return this;
    }

    public void setStateWorkingDay(State stateWorkingDay) {
        this.stateWorkingDay = stateWorkingDay;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public WorkingDay courses(Set<Course> courses) {
        this.courses = courses;
        return this;
    }

    public WorkingDay addCourse(Course course) {
        this.courses.add(course);
        course.setWorkingDay(this);
        return this;
    }

    public WorkingDay removeCourse(Course course) {
        this.courses.remove(course);
        course.setWorkingDay(null);
        return this;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Trimester> getTrimesters() {
        return trimesters;
    }

    public WorkingDay trimesters(Set<Trimester> trimesters) {
        this.trimesters = trimesters;
        return this;
    }

    public WorkingDay addTrimester(Trimester trimester) {
        this.trimesters.add(trimester);
        trimester.setWorkingDay(this);
        return this;
    }

    public WorkingDay removeTrimester(Trimester trimester) {
        this.trimesters.remove(trimester);
        trimester.setWorkingDay(null);
        return this;
    }

    public void setTrimesters(Set<Trimester> trimesters) {
        this.trimesters = trimesters;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkingDay)) {
            return false;
        }
        return id != null && id.equals(((WorkingDay) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WorkingDay{" +
            "id=" + getId() +
            ", initialWorkingDay='" + getInitialWorkingDay() + "'" +
            ", nameWorkingDay='" + getNameWorkingDay() + "'" +
            ", description='" + getDescription() + "'" +
            ", imagenUrl='" + getImagenUrl() + "'" +
            ", imagenUrlContentType='" + getImagenUrlContentType() + "'" +
            ", stateWorkingDay='" + getStateWorkingDay() + "'" +
            "}";
    }
}
