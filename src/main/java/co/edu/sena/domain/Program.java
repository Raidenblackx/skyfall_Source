package co.edu.sena.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import co.edu.sena.domain.enumeration.StateProgram;

/**
 * A Program.
 */
@Entity
@Table(name = "program")
public class Program implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "code_program", length = 50, nullable = false)
    private String codeProgram;

    @NotNull
    @Size(max = 40)
    @Column(name = "version", length = 40, nullable = false)
    private String version;

    @NotNull
    @Size(max = 500)
    @Column(name = "name_program", length = 500, nullable = false)
    private String nameProgram;

    @NotNull
    @Size(max = 40)
    @Column(name = "initial", length = 40, nullable = false)
    private String initial;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_program", nullable = false)
    private StateProgram stateProgram;

    @OneToMany(mappedBy = "program")
    private Set<Competition> competitions = new HashSet<>();

    @OneToMany(mappedBy = "program")
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "program")
    private Set<Proyect> proyects = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("programs")
    private LevelFormation levelFormation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeProgram() {
        return codeProgram;
    }

    public Program codeProgram(String codeProgram) {
        this.codeProgram = codeProgram;
        return this;
    }

    public void setCodeProgram(String codeProgram) {
        this.codeProgram = codeProgram;
    }

    public String getVersion() {
        return version;
    }

    public Program version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNameProgram() {
        return nameProgram;
    }

    public Program nameProgram(String nameProgram) {
        this.nameProgram = nameProgram;
        return this;
    }

    public void setNameProgram(String nameProgram) {
        this.nameProgram = nameProgram;
    }

    public String getInitial() {
        return initial;
    }

    public Program initial(String initial) {
        this.initial = initial;
        return this;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public StateProgram getStateProgram() {
        return stateProgram;
    }

    public Program stateProgram(StateProgram stateProgram) {
        this.stateProgram = stateProgram;
        return this;
    }

    public void setStateProgram(StateProgram stateProgram) {
        this.stateProgram = stateProgram;
    }

    public Set<Competition> getCompetitions() {
        return competitions;
    }

    public Program competitions(Set<Competition> competitions) {
        this.competitions = competitions;
        return this;
    }

    public Program addCompetition(Competition competition) {
        this.competitions.add(competition);
        competition.setProgram(this);
        return this;
    }

    public Program removeCompetition(Competition competition) {
        this.competitions.remove(competition);
        competition.setProgram(null);
        return this;
    }

    public void setCompetitions(Set<Competition> competitions) {
        this.competitions = competitions;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Program courses(Set<Course> courses) {
        this.courses = courses;
        return this;
    }

    public Program addCourse(Course course) {
        this.courses.add(course);
        course.setProgram(this);
        return this;
    }

    public Program removeCourse(Course course) {
        this.courses.remove(course);
        course.setProgram(null);
        return this;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Proyect> getProyects() {
        return proyects;
    }

    public Program proyects(Set<Proyect> proyects) {
        this.proyects = proyects;
        return this;
    }

    public Program addProyect(Proyect proyect) {
        this.proyects.add(proyect);
        proyect.setProgram(this);
        return this;
    }

    public Program removeProyect(Proyect proyect) {
        this.proyects.remove(proyect);
        proyect.setProgram(null);
        return this;
    }

    public void setProyects(Set<Proyect> proyects) {
        this.proyects = proyects;
    }

    public LevelFormation getLevelFormation() {
        return levelFormation;
    }

    public Program levelFormation(LevelFormation levelFormation) {
        this.levelFormation = levelFormation;
        return this;
    }

    public void setLevelFormation(LevelFormation levelFormation) {
        this.levelFormation = levelFormation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Program)) {
            return false;
        }
        return id != null && id.equals(((Program) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Program{" +
            "id=" + getId() +
            ", codeProgram='" + getCodeProgram() + "'" +
            ", version='" + getVersion() + "'" +
            ", nameProgram='" + getNameProgram() + "'" +
            ", initial='" + getInitial() + "'" +
            ", stateProgram='" + getStateProgram() + "'" +
            "}";
    }
}
