package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A Phase.
 */
@Entity
@Table(name = "phase")
public class Phase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "name_phase", length = 40, nullable = false)
    private String namePhase;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_phase", nullable = false)
    private State statePhase;

    @OneToMany(mappedBy = "phase")
    private Set<ProyectActivity> proyectActivities = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("phases")
    private Proyect proyect;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamePhase() {
        return namePhase;
    }

    public Phase namePhase(String namePhase) {
        this.namePhase = namePhase;
        return this;
    }

    public void setNamePhase(String namePhase) {
        this.namePhase = namePhase;
    }

    public State getStatePhase() {
        return statePhase;
    }

    public Phase statePhase(State statePhase) {
        this.statePhase = statePhase;
        return this;
    }

    public void setStatePhase(State statePhase) {
        this.statePhase = statePhase;
    }

    public Set<ProyectActivity> getProyectActivities() {
        return proyectActivities;
    }

    public Phase proyectActivities(Set<ProyectActivity> proyectActivities) {
        this.proyectActivities = proyectActivities;
        return this;
    }

    public Phase addProyectActivity(ProyectActivity proyectActivity) {
        this.proyectActivities.add(proyectActivity);
        proyectActivity.setPhase(this);
        return this;
    }

    public Phase removeProyectActivity(ProyectActivity proyectActivity) {
        this.proyectActivities.remove(proyectActivity);
        proyectActivity.setPhase(null);
        return this;
    }

    public void setProyectActivities(Set<ProyectActivity> proyectActivities) {
        this.proyectActivities = proyectActivities;
    }

    public Proyect getProyect() {
        return proyect;
    }

    public Phase proyect(Proyect proyect) {
        this.proyect = proyect;
        return this;
    }

    public void setProyect(Proyect proyect) {
        this.proyect = proyect;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Phase)) {
            return false;
        }
        return id != null && id.equals(((Phase) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Phase{" +
            "id=" + getId() +
            ", namePhase='" + getNamePhase() + "'" +
            ", statePhase='" + getStatePhase() + "'" +
            "}";
    }
}
