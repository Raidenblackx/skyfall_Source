package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TrimesterPlanning.
 */
@Entity
@Table(name = "trimester_planning")
public class TrimesterPlanning implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "trimesterPlanning")
    private Set<PlanningActivity> planningActivities = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("trimesterPlannings")
    private LearningResult learningResult;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("trimesterPlannings")
    private Trimester trimester;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("trimesterPlannings")
    private Planning planning;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<PlanningActivity> getPlanningActivities() {
        return planningActivities;
    }

    public TrimesterPlanning planningActivities(Set<PlanningActivity> planningActivities) {
        this.planningActivities = planningActivities;
        return this;
    }

    public TrimesterPlanning addPlanningActivity(PlanningActivity planningActivity) {
        this.planningActivities.add(planningActivity);
        planningActivity.setTrimesterPlanning(this);
        return this;
    }

    public TrimesterPlanning removePlanningActivity(PlanningActivity planningActivity) {
        this.planningActivities.remove(planningActivity);
        planningActivity.setTrimesterPlanning(null);
        return this;
    }

    public void setPlanningActivities(Set<PlanningActivity> planningActivities) {
        this.planningActivities = planningActivities;
    }

    public LearningResult getLearningResult() {
        return learningResult;
    }

    public TrimesterPlanning learningResult(LearningResult learningResult) {
        this.learningResult = learningResult;
        return this;
    }

    public void setLearningResult(LearningResult learningResult) {
        this.learningResult = learningResult;
    }

    public Trimester getTrimester() {
        return trimester;
    }

    public TrimesterPlanning trimester(Trimester trimester) {
        this.trimester = trimester;
        return this;
    }

    public void setTrimester(Trimester trimester) {
        this.trimester = trimester;
    }

    public Planning getPlanning() {
        return planning;
    }

    public TrimesterPlanning planning(Planning planning) {
        this.planning = planning;
        return this;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrimesterPlanning)) {
            return false;
        }
        return id != null && id.equals(((TrimesterPlanning) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TrimesterPlanning{" +
            "id=" + getId() +
            "}";
    }
}
