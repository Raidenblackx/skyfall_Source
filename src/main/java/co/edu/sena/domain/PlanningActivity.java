package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PlanningActivity.
 */
@Entity
@Table(name = "planning_activity")
public class PlanningActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("planningActivities")
    private TrimesterPlanning trimesterPlanning;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("planningActivities")
    private ProyectActivity proyectActivity;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrimesterPlanning getTrimesterPlanning() {
        return trimesterPlanning;
    }

    public PlanningActivity trimesterPlanning(TrimesterPlanning trimesterPlanning) {
        this.trimesterPlanning = trimesterPlanning;
        return this;
    }

    public void setTrimesterPlanning(TrimesterPlanning trimesterPlanning) {
        this.trimesterPlanning = trimesterPlanning;
    }

    public ProyectActivity getProyectActivity() {
        return proyectActivity;
    }

    public PlanningActivity proyectActivity(ProyectActivity proyectActivity) {
        this.proyectActivity = proyectActivity;
        return this;
    }

    public void setProyectActivity(ProyectActivity proyectActivity) {
        this.proyectActivity = proyectActivity;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlanningActivity)) {
            return false;
        }
        return id != null && id.equals(((PlanningActivity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PlanningActivity{" +
            "id=" + getId() +
            "}";
    }
}
