package co.edu.sena.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A Sede.
 */
@Entity
@Table(name = "sede")
public class Sede implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "name_sede", length = 50, nullable = false)
    private String nameSede;

    @NotNull
    @Size(max = 400)
    @Column(name = "description", length = 400, nullable = false)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_sede", nullable = false)
    private State stateSede;

    @OneToMany(mappedBy = "sede")
    private Set<Ambient> ambients = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSede() {
        return nameSede;
    }

    public Sede nameSede(String nameSede) {
        this.nameSede = nameSede;
        return this;
    }

    public void setNameSede(String nameSede) {
        this.nameSede = nameSede;
    }

    public String getDescription() {
        return description;
    }

    public Sede description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getStateSede() {
        return stateSede;
    }

    public Sede stateSede(State stateSede) {
        this.stateSede = stateSede;
        return this;
    }

    public void setStateSede(State stateSede) {
        this.stateSede = stateSede;
    }

    public Set<Ambient> getAmbients() {
        return ambients;
    }

    public Sede ambients(Set<Ambient> ambients) {
        this.ambients = ambients;
        return this;
    }

    public Sede addAmbient(Ambient ambient) {
        this.ambients.add(ambient);
        ambient.setSede(this);
        return this;
    }

    public Sede removeAmbient(Ambient ambient) {
        this.ambients.remove(ambient);
        ambient.setSede(null);
        return this;
    }

    public void setAmbients(Set<Ambient> ambients) {
        this.ambients = ambients;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sede)) {
            return false;
        }
        return id != null && id.equals(((Sede) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Sede{" +
            "id=" + getId() +
            ", nameSede='" + getNameSede() + "'" +
            ", description='" + getDescription() + "'" +
            ", stateSede='" + getStateSede() + "'" +
            "}";
    }
}
