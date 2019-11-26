package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.domain.CourseHasTrimester} entity.
 */
public class CourseHasTrimesterDTO implements Serializable {

    private Long id;


    private Long courseId;

    private String courseCourseNumber;

    private Long trimesterId;

    private String trimesterNameTrimester;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseCourseNumber() {
        return courseCourseNumber;
    }

    public void setCourseCourseNumber(String courseCourseNumber) {
        this.courseCourseNumber = courseCourseNumber;
    }

    public Long getTrimesterId() {
        return trimesterId;
    }

    public void setTrimesterId(Long trimesterId) {
        this.trimesterId = trimesterId;
    }

    public String getTrimesterNameTrimester() {
        return trimesterNameTrimester;
    }

    public void setTrimesterNameTrimester(String trimesterNameTrimester) {
        this.trimesterNameTrimester = trimesterNameTrimester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourseHasTrimesterDTO courseHasTrimesterDTO = (CourseHasTrimesterDTO) o;
        if (courseHasTrimesterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseHasTrimesterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseHasTrimesterDTO{" +
            "id=" + getId() +
            ", course=" + getCourseId() +
            ", course='" + getCourseCourseNumber() + "'" +
            ", trimester=" + getTrimesterId() +
            ", trimester='" + getTrimesterNameTrimester() + "'" +
            "}";
    }
}
