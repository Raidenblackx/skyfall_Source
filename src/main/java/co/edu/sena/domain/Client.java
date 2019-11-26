package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "document_number", nullable = false)
    private Integer documentNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Size(max = 50)
    @Column(name = "second_name", length = 50)
    private String secondName;

    @NotNull
    @Size(max = 50)
    @Column(name = "first_last_name", length = 50, nullable = false)
    private String firstLastName;

    @Size(max = 50)
    @Column(name = "second_last_name", length = 50)
    private String secondLastName;

    @OneToMany(mappedBy = "client")
    private Set<Instructor> instructors = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("clients")
    private DocumentType documentType;

    @OneToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("client")
    @JoinColumn(unique = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDocumentNumber() {
        return documentNumber;
    }

    public Client documentNumber(Integer documentNumber) {
        this.documentNumber = documentNumber;
        return this;
    }

    public void setDocumentNumber(Integer documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public Client firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Client secondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public Client firstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
        return this;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public Client secondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
        return this;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public Set<Instructor> getInstructors() {
        return instructors;
    }

    public Client instructors(Set<Instructor> instructors) {
        this.instructors = instructors;
        return this;
    }

    public Client addInstructor(Instructor instructor) {
        this.instructors.add(instructor);
        instructor.setClient(this);
        return this;
    }

    public Client removeInstructor(Instructor instructor) {
        this.instructors.remove(instructor);
        instructor.setClient(null);
        return this;
    }

    public void setInstructors(Set<Instructor> instructors) {
        this.instructors = instructors;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public Client documentType(DocumentType documentType) {
        this.documentType = documentType;
        return this;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public User getUser() {
        return user;
    }

    public Client user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", documentNumber=" + getDocumentNumber() +
            ", firstName='" + getFirstName() + "'" +
            ", secondName='" + getSecondName() + "'" +
            ", firstLastName='" + getFirstLastName() + "'" +
            ", secondLastName='" + getSecondLastName() + "'" +
            "}";
    }
}
