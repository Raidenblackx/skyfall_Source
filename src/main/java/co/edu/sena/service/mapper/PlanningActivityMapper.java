package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.PlanningActivityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PlanningActivity} and its DTO {@link PlanningActivityDTO}.
 */
@Mapper(componentModel = "spring", uses = {TrimesterPlanningMapper.class, ProyectActivityMapper.class})
public interface PlanningActivityMapper extends EntityMapper<PlanningActivityDTO, PlanningActivity> {

    @Mapping(source = "trimesterPlanning.id", target = "trimesterPlanningId")
    @Mapping(source = "proyectActivity.id", target = "proyectActivityId")
    @Mapping(source = "proyectActivity.numberProyectActivity", target = "proyectActivityNumberProyectActivity")
    PlanningActivityDTO toDto(PlanningActivity planningActivity);

    @Mapping(source = "trimesterPlanningId", target = "trimesterPlanning")
    @Mapping(source = "proyectActivityId", target = "proyectActivity")
    PlanningActivity toEntity(PlanningActivityDTO planningActivityDTO);

    default PlanningActivity fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlanningActivity planningActivity = new PlanningActivity();
        planningActivity.setId(id);
        return planningActivity;
    }
}
