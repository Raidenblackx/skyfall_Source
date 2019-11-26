package co.edu.sena.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.State;

/**
 * A DocumentType.
 */
@Entity
@Table(name = "document_type")
public class DocumentType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "initial", length = 10, nullable = false)
    private String initial;

    @NotNull
    @Size(max = 100)
    @Column(name = "document_name", length = 100, nullable = false)
    private String documentName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_document_type", nullable = false)
    private State stateDocumentType;

    @OneToMany(mappedBy = "documentType")
    private Set<Client> clients = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInitial() {
        return initial;
    }

    public DocumentType initial(String initial) {
        this.initial = initial;
        return this;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getDocumentName() {
        return documentName;
    }

    public DocumentType documentName(String documentName) {
        this.documentName = documentName;
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public State getStateDocumentType() {
        return stateDocumentType;
    }

    public DocumentType stateDocumentType(State stateDocumentType) {
        this.stateDocumentType = stateDocumentType;
        return this;
    }

    public void setStateDocumentType(State stateDocumentType) {
        this.stateDocumentType = stateDocumentType;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public DocumentType clients(Set<Client> clients) {
        this.clients = clients;
        return this;
    }

    public DocumentType addClient(Client client) {
        this.clients.add(client);
        client.setDocumentType(this);
        return this;
    }

    public DocumentType removeClient(Client client) {
        this.clients.remove(client);
        client.setDocumentType(null);
        return this;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentType)) {
            return false;
        }
        return id != null && id.equals(((DocumentType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DocumentType{" +
            "id=" + getId() +
            ", initial='" + getInitial() + "'" +
            ", documentName='" + getDocumentName() + "'" +
            ", stateDocumentType='" + getStateDocumentType() + "'" +
            "}";
    }
}
