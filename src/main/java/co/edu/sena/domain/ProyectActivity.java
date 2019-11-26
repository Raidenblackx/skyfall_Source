package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A ProyectActivity.
 */
@Entity
@Table(name = "proyect_activity")
public class ProyectActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "number_proyect_activity", nullable = false)
    private Integer numberProyectActivity;

    @NotNull
    @Size(max = 400)
    @Column(name = "description_activity", length = 400, nullable = false)
    private String descriptionActivity;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_proyect_activity", nullable = false)
    private State stateProyectActivity;

    @OneToMany(mappedBy = "proyectActivity")
    private Set<PlanningActivity> planningActivities = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("proyectActivities")
    private Phase phase;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberProyectActivity() {
        return numberProyectActivity;
    }

    public ProyectActivity numberProyectActivity(Integer numberProyectActivity) {
        this.numberProyectActivity = numberProyectActivity;
        return this;
    }

    public void setNumberProyectActivity(Integer numberProyectActivity) {
        this.numberProyectActivity = numberProyectActivity;
    }

    public String getDescriptionActivity() {
        return descriptionActivity;
    }

    public ProyectActivity descriptionActivity(String descriptionActivity) {
        this.descriptionActivity = descriptionActivity;
        return this;
    }

    public void setDescriptionActivity(String descriptionActivity) {
        this.descriptionActivity = descriptionActivity;
    }

    public State getStateProyectActivity() {
        return stateProyectActivity;
    }

    public ProyectActivity stateProyectActivity(State stateProyectActivity) {
        this.stateProyectActivity = stateProyectActivity;
        return this;
    }

    public void setStateProyectActivity(State stateProyectActivity) {
        this.stateProyectActivity = stateProyectActivity;
    }

    public Set<PlanningActivity> getPlanningActivities() {
        return planningActivities;
    }

    public ProyectActivity planningActivities(Set<PlanningActivity> planningActivities) {
        this.planningActivities = planningActivities;
        return this;
    }

    public ProyectActivity addPlanningActivity(PlanningActivity planningActivity) {
        this.planningActivities.add(planningActivity);
        planningActivity.setProyectActivity(this);
        return this;
    }

    public ProyectActivity removePlanningActivity(PlanningActivity planningActivity) {
        this.planningActivities.remove(planningActivity);
        planningActivity.setProyectActivity(null);
        return this;
    }

    public void setPlanningActivities(Set<PlanningActivity> planningActivities) {
        this.planningActivities = planningActivities;
    }

    public Phase getPhase() {
        return phase;
    }

    public ProyectActivity phase(Phase phase) {
        this.phase = phase;
        return this;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProyectActivity)) {
            return false;
        }
        return id != null && id.equals(((ProyectActivity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProyectActivity{" +
            "id=" + getId() +
            ", numberProyectActivity=" + getNumberProyectActivity() +
            ", descriptionActivity='" + getDescriptionActivity() + "'" +
            ", stateProyectActivity='" + getStateProyectActivity() + "'" +
            "}";
    }
}
