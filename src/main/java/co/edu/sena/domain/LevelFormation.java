package co.edu.sena.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A LevelFormation.
 */
@Entity
@Table(name = "level_formation")
public class LevelFormation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "level", length = 40, nullable = false)
    private String level;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_level_formation", nullable = false)
    private State stateLevelFormation;

    @OneToMany(mappedBy = "levelFormation")
    private Set<Program> programs = new HashSet<>();

    @OneToMany(mappedBy = "levelFormation")
    private Set<Trimester> trimesters = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public LevelFormation level(String level) {
        this.level = level;
        return this;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public State getStateLevelFormation() {
        return stateLevelFormation;
    }

    public LevelFormation stateLevelFormation(State stateLevelFormation) {
        this.stateLevelFormation = stateLevelFormation;
        return this;
    }

    public void setStateLevelFormation(State stateLevelFormation) {
        this.stateLevelFormation = stateLevelFormation;
    }

    public Set<Program> getPrograms() {
        return programs;
    }

    public LevelFormation programs(Set<Program> programs) {
        this.programs = programs;
        return this;
    }

    public LevelFormation addProgram(Program program) {
        this.programs.add(program);
        program.setLevelFormation(this);
        return this;
    }

    public LevelFormation removeProgram(Program program) {
        this.programs.remove(program);
        program.setLevelFormation(null);
        return this;
    }

    public void setPrograms(Set<Program> programs) {
        this.programs = programs;
    }

    public Set<Trimester> getTrimesters() {
        return trimesters;
    }

    public LevelFormation trimesters(Set<Trimester> trimesters) {
        this.trimesters = trimesters;
        return this;
    }

    public LevelFormation addTrimester(Trimester trimester) {
        this.trimesters.add(trimester);
        trimester.setLevelFormation(this);
        return this;
    }

    public LevelFormation removeTrimester(Trimester trimester) {
        this.trimesters.remove(trimester);
        trimester.setLevelFormation(null);
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
        if (!(o instanceof LevelFormation)) {
            return false;
        }
        return id != null && id.equals(((LevelFormation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LevelFormation{" +
            "id=" + getId() +
            ", level='" + getLevel() + "'" +
            ", stateLevelFormation='" + getStateLevelFormation() + "'" +
            "}";
    }
}
