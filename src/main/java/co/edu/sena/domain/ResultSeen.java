package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ResultSeen.
 */
@Entity
@Table(name = "result_seen")
public class ResultSeen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("resultSeens")
    private LearningResult learningResult;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("resultSeens")
    private CourseHasTrimester courseHasTrimester;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LearningResult getLearningResult() {
        return learningResult;
    }

    public ResultSeen learningResult(LearningResult learningResult) {
        this.learningResult = learningResult;
        return this;
    }

    public void setLearningResult(LearningResult learningResult) {
        this.learningResult = learningResult;
    }

    public CourseHasTrimester getCourseHasTrimester() {
        return courseHasTrimester;
    }

    public ResultSeen courseHasTrimester(CourseHasTrimester courseHasTrimester) {
        this.courseHasTrimester = courseHasTrimester;
        return this;
    }

    public void setCourseHasTrimester(CourseHasTrimester courseHasTrimester) {
        this.courseHasTrimester = courseHasTrimester;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResultSeen)) {
            return false;
        }
        return id != null && id.equals(((ResultSeen) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ResultSeen{" +
            "id=" + getId() +
            "}";
    }
}
