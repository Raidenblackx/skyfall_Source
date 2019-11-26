package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A LimitationEnvironment.
 */
@Entity
@Table(name = "limitation_environment")
public class LimitationEnvironment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("limitationEnviroments")
    private LearningResult learningResult;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("limitationEnvironments")
    private Ambient ambient;

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

    public LimitationEnvironment learningResult(LearningResult learningResult) {
        this.learningResult = learningResult;
        return this;
    }

    public void setLearningResult(LearningResult learningResult) {
        this.learningResult = learningResult;
    }

    public Ambient getAmbient() {
        return ambient;
    }

    public LimitationEnvironment ambient(Ambient ambient) {
        this.ambient = ambient;
        return this;
    }

    public void setAmbient(Ambient ambient) {
        this.ambient = ambient;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LimitationEnvironment)) {
            return false;
        }
        return id != null && id.equals(((LimitationEnvironment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LimitationEnvironment{" +
            "id=" + getId() +
            "}";
    }
}
