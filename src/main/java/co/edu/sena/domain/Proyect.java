package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A Proyect.
 */
@Entity
@Table(name = "proyect")
public class Proyect implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "code_proyect", length = 40, nullable = false)
    private String codeProyect;

    @NotNull
    @Size(max = 500)
    @Column(name = "name_proyect", length = 500, nullable = false)
    private String nameProyect;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_proyect", nullable = false)
    private State stateProyect;

    @OneToMany(mappedBy = "proyect")
    private Set<Phase> phases = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("proyects")
    private Program program;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeProyect() {
        return codeProyect;
    }

    public Proyect codeProyect(String codeProyect) {
        this.codeProyect = codeProyect;
        return this;
    }

    public void setCodeProyect(String codeProyect) {
        this.codeProyect = codeProyect;
    }

    public String getNameProyect() {
        return nameProyect;
    }

    public Proyect nameProyect(String nameProyect) {
        this.nameProyect = nameProyect;
        return this;
    }

    public void setNameProyect(String nameProyect) {
        this.nameProyect = nameProyect;
    }

    public State getStateProyect() {
        return stateProyect;
    }

    public Proyect stateProyect(State stateProyect) {
        this.stateProyect = stateProyect;
        return this;
    }

    public void setStateProyect(State stateProyect) {
        this.stateProyect = stateProyect;
    }

    public Set<Phase> getPhases() {
        return phases;
    }

    public Proyect phases(Set<Phase> phases) {
        this.phases = phases;
        return this;
    }

    public Proyect addPhase(Phase phase) {
        this.phases.add(phase);
        phase.setProyect(this);
        return this;
    }

    public Proyect removePhase(Phase phase) {
        this.phases.remove(phase);
        phase.setProyect(null);
        return this;
    }

    public void setPhases(Set<Phase> phases) {
        this.phases = phases;
    }

    public Program getProgram() {
        return program;
    }

    public Proyect program(Program program) {
        this.program = program;
        return this;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Proyect)) {
            return false;
        }
        return id != null && id.equals(((Proyect) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Proyect{" +
            "id=" + getId() +
            ", codeProyect='" + getCodeProyect() + "'" +
            ", nameProyect='" + getNameProyect() + "'" +
            ", stateProyect='" + getStateProyect() + "'" +
            "}";
    }
}
