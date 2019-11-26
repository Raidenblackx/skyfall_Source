package co.edu.sena.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A TypeEnvironment.
 */
@Entity
@Table(name = "type_environment")
public class TypeEnvironment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "type", length = 50, nullable = false)
    private String type;

    @NotNull
    @Size(max = 100)
    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_type_environment", nullable = false)
    private State stateTypeEnvironment;

    @OneToMany(mappedBy = "typeEnvironment")
    private Set<Ambient> ambients = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public TypeEnvironment type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public TypeEnvironment description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getStateTypeEnvironment() {
        return stateTypeEnvironment;
    }

    public TypeEnvironment stateTypeEnvironment(State stateTypeEnvironment) {
        this.stateTypeEnvironment = stateTypeEnvironment;
        return this;
    }

    public void setStateTypeEnvironment(State stateTypeEnvironment) {
        this.stateTypeEnvironment = stateTypeEnvironment;
    }

    public Set<Ambient> getAmbients() {
        return ambients;
    }

    public TypeEnvironment ambients(Set<Ambient> ambients) {
        this.ambients = ambients;
        return this;
    }

    public TypeEnvironment addAmbient(Ambient ambient) {
        this.ambients.add(ambient);
        ambient.setTypeEnvironment(this);
        return this;
    }

    public TypeEnvironment removeAmbient(Ambient ambient) {
        this.ambients.remove(ambient);
        ambient.setTypeEnvironment(null);
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
        if (!(o instanceof TypeEnvironment)) {
            return false;
        }
        return id != null && id.equals(((TypeEnvironment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TypeEnvironment{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", description='" + getDescription() + "'" +
            ", stateTypeEnvironment='" + getStateTypeEnvironment() + "'" +
            "}";
    }
}
