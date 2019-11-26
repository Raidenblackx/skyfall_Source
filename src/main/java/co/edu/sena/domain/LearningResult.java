package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A LearningResult.
 */
@Entity
@Table(name = "learning_result")
public class LearningResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "code_result", length = 40, nullable = false)
    private String codeResult;

    @NotNull
    @Size(max = 1000)
    @Column(name = "denomination", length = 1000, nullable = false)
    private String denomination;

    @OneToMany(mappedBy = "learningResult")
    private Set<TrimesterPlanning> trimesterPlannings = new HashSet<>();

    @OneToMany(mappedBy = "learningResult")
    private Set<LimitationEnvironment> limitationEnviroments = new HashSet<>();

    @OneToMany(mappedBy = "learningResult")
    private Set<ResultSeen> resultSeens = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("learningResults")
    private Competition competition;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeResult() {
        return codeResult;
    }

    public LearningResult codeResult(String codeResult) {
        this.codeResult = codeResult;
        return this;
    }

    public void setCodeResult(String codeResult) {
        this.codeResult = codeResult;
    }

    public String getDenomination() {
        return denomination;
    }

    public LearningResult denomination(String denomination) {
        this.denomination = denomination;
        return this;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Set<TrimesterPlanning> getTrimesterPlannings() {
        return trimesterPlannings;
    }

    public LearningResult trimesterPlannings(Set<TrimesterPlanning> trimesterPlannings) {
        this.trimesterPlannings = trimesterPlannings;
        return this;
    }

    public LearningResult addTrimesterPlanning(TrimesterPlanning trimesterPlanning) {
        this.trimesterPlannings.add(trimesterPlanning);
        trimesterPlanning.setLearningResult(this);
        return this;
    }

    public LearningResult removeTrimesterPlanning(TrimesterPlanning trimesterPlanning) {
        this.trimesterPlannings.remove(trimesterPlanning);
        trimesterPlanning.setLearningResult(null);
        return this;
    }

    public void setTrimesterPlannings(Set<TrimesterPlanning> trimesterPlannings) {
        this.trimesterPlannings = trimesterPlannings;
    }

    public Set<LimitationEnvironment> getLimitationEnviroments() {
        return limitationEnviroments;
    }

    public LearningResult limitationEnviroments(Set<LimitationEnvironment> limitationEnvironments) {
        this.limitationEnviroments = limitationEnvironments;
        return this;
    }

    public LearningResult addLimitationEnviroment(LimitationEnvironment limitationEnvironment) {
        this.limitationEnviroments.add(limitationEnvironment);
        limitationEnvironment.setLearningResult(this);
        return this;
    }

    public LearningResult removeLimitationEnviroment(LimitationEnvironment limitationEnvironment) {
        this.limitationEnviroments.remove(limitationEnvironment);
        limitationEnvironment.setLearningResult(null);
        return this;
    }

    public void setLimitationEnviroments(Set<LimitationEnvironment> limitationEnvironments) {
        this.limitationEnviroments = limitationEnvironments;
    }

    public Set<ResultSeen> getResultSeens() {
        return resultSeens;
    }

    public LearningResult resultSeens(Set<ResultSeen> resultSeens) {
        this.resultSeens = resultSeens;
        return this;
    }

    public LearningResult addResultSeen(ResultSeen resultSeen) {
        this.resultSeens.add(resultSeen);
        resultSeen.setLearningResult(this);
        return this;
    }

    public LearningResult removeResultSeen(ResultSeen resultSeen) {
        this.resultSeens.remove(resultSeen);
        resultSeen.setLearningResult(null);
        return this;
    }

    public void setResultSeens(Set<ResultSeen> resultSeens) {
        this.resultSeens = resultSeens;
    }

    public Competition getCompetition() {
        return competition;
    }

    public LearningResult competition(Competition competition) {
        this.competition = competition;
        return this;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LearningResult)) {
            return false;
        }
        return id != null && id.equals(((LearningResult) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LearningResult{" +
            "id=" + getId() +
            ", codeResult='" + getCodeResult() + "'" +
            ", denomination='" + getDenomination() + "'" +
            "}";
    }
}
