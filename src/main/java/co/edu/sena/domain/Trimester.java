package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A Trimester.
 */
@Entity
@Table(name = "trimester")
public class Trimester implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name_trimester", nullable = false)
    private Integer nameTrimester;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_trimester", nullable = false)
    private State stateTrimester;

    @OneToMany(mappedBy = "trimester")
    private Set<CourseHasTrimester> courseHasTrimesters = new HashSet<>();

    @OneToMany(mappedBy = "trimester")
    private Set<TrimesterPlanning> trimesterPlannings = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("trimesters")
    private WorkingDay workingDay;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("trimesters")
    private LevelFormation levelFormation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNameTrimester() {
        return nameTrimester;
    }

    public Trimester nameTrimester(Integer nameTrimester) {
        this.nameTrimester = nameTrimester;
        return this;
    }

    public void setNameTrimester(Integer nameTrimester) {
        this.nameTrimester = nameTrimester;
    }

    public State getStateTrimester() {
        return stateTrimester;
    }

    public Trimester stateTrimester(State stateTrimester) {
        this.stateTrimester = stateTrimester;
        return this;
    }

    public void setStateTrimester(State stateTrimester) {
        this.stateTrimester = stateTrimester;
    }

    public Set<CourseHasTrimester> getCourseHasTrimesters() {
        return courseHasTrimesters;
    }

    public Trimester courseHasTrimesters(Set<CourseHasTrimester> courseHasTrimesters) {
        this.courseHasTrimesters = courseHasTrimesters;
        return this;
    }

    public Trimester addCourseHasTrimester(CourseHasTrimester courseHasTrimester) {
        this.courseHasTrimesters.add(courseHasTrimester);
        courseHasTrimester.setTrimester(this);
        return this;
    }

    public Trimester removeCourseHasTrimester(CourseHasTrimester courseHasTrimester) {
        this.courseHasTrimesters.remove(courseHasTrimester);
        courseHasTrimester.setTrimester(null);
        return this;
    }

    public void setCourseHasTrimesters(Set<CourseHasTrimester> courseHasTrimesters) {
        this.courseHasTrimesters = courseHasTrimesters;
    }

    public Set<TrimesterPlanning> getTrimesterPlannings() {
        return trimesterPlannings;
    }

    public Trimester trimesterPlannings(Set<TrimesterPlanning> trimesterPlannings) {
        this.trimesterPlannings = trimesterPlannings;
        return this;
    }

    public Trimester addTrimesterPlanning(TrimesterPlanning trimesterPlanning) {
        this.trimesterPlannings.add(trimesterPlanning);
        trimesterPlanning.setTrimester(this);
        return this;
    }

    public Trimester removeTrimesterPlanning(TrimesterPlanning trimesterPlanning) {
        this.trimesterPlannings.remove(trimesterPlanning);
        trimesterPlanning.setTrimester(null);
        return this;
    }

    public void setTrimesterPlannings(Set<TrimesterPlanning> trimesterPlannings) {
        this.trimesterPlannings = trimesterPlannings;
    }

    public WorkingDay getWorkingDay() {
        return workingDay;
    }

    public Trimester workingDay(WorkingDay workingDay) {
        this.workingDay = workingDay;
        return this;
    }

    public void setWorkingDay(WorkingDay workingDay) {
        this.workingDay = workingDay;
    }

    public LevelFormation getLevelFormation() {
        return levelFormation;
    }

    public Trimester levelFormation(LevelFormation levelFormation) {
        this.levelFormation = levelFormation;
        return this;
    }

    public void setLevelFormation(LevelFormation levelFormation) {
        this.levelFormation = levelFormation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Trimester)) {
            return false;
        }
        return id != null && id.equals(((Trimester) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Trimester{" +
            "id=" + getId() +
            ", nameTrimester=" + getNameTrimester() +
            ", stateTrimester='" + getStateTrimester() + "'" +
            "}";
    }
}
