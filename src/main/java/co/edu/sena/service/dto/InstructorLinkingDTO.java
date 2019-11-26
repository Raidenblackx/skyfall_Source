package co.edu.sena.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.domain.InstructorLinking} entity.
 */
public class InstructorLinkingDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime startDate;

    @NotNull
    private ZonedDateTime endDate;


    private Long yearId;

    private String yearNumberYear;

    private Long instructorId;

    private Long linkageId;

    private String linkageTypeLink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getYearId() {
        return yearId;
    }

    public void setYearId(Long yearId) {
        this.yearId = yearId;
    }

    public String getYearNumberYear() {
        return yearNumberYear;
    }

    public void setYearNumberYear(String yearNumberYear) {
        this.yearNumberYear = yearNumberYear;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public Long getLinkageId() {
        return linkageId;
    }

    public void setLinkageId(Long linkageId) {
        this.linkageId = linkageId;
    }

    public String getLinkageTypeLink() {
        return linkageTypeLink;
    }

    public void setLinkageTypeLink(String linkageTypeLink) {
        this.linkageTypeLink = linkageTypeLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InstructorLinkingDTO instructorLinkingDTO = (InstructorLinkingDTO) o;
        if (instructorLinkingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), instructorLinkingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InstructorLinkingDTO{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", year=" + getYearId() +
            ", year='" + getYearNumberYear() + "'" +
            ", instructor=" + getInstructorId() +
            ", linkage=" + getLinkageId() +
            ", linkage='" + getLinkageTypeLink() + "'" +
            "}";
    }
}
