package co.edu.sena.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.edu.sena.domain.PlanningActivity} entity.
 */
public class PlanningActivityDTO implements Serializable {

    private Long id;


    private Long trimesterPlanningId;

    private Long proyectActivityId;

    private String proyectActivityNumberProyectActivity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrimesterPlanningId() {
        return trimesterPlanningId;
    }

    public void setTrimesterPlanningId(Long trimesterPlanningId) {
        this.trimesterPlanningId = trimesterPlanningId;
    }

    public Long getProyectActivityId() {
        return proyectActivityId;
    }

    public void setProyectActivityId(Long proyectActivityId) {
        this.proyectActivityId = proyectActivityId;
    }

    public String getProyectActivityNumberProyectActivity() {
        return proyectActivityNumberProyectActivity;
    }

    public void setProyectActivityNumberProyectActivity(String proyectActivityNumberProyectActivity) {
        this.proyectActivityNumberProyectActivity = proyectActivityNumberProyectActivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlanningActivityDTO planningActivityDTO = (PlanningActivityDTO) o;
        if (planningActivityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planningActivityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlanningActivityDTO{" +
            "id=" + getId() +
            ", trimesterPlanning=" + getTrimesterPlanningId() +
            ", proyectActivity=" + getProyectActivityId() +
            ", proyectActivity='" + getProyectActivityNumberProyectActivity() + "'" +
            "}";
    }
}
