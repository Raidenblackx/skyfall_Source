package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.TrimesterPlanningDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TrimesterPlanning} and its DTO {@link TrimesterPlanningDTO}.
 */
@Mapper(componentModel = "spring", uses = {LearningResultMapper.class, TrimesterMapper.class, PlanningMapper.class})
public interface TrimesterPlanningMapper extends EntityMapper<TrimesterPlanningDTO, TrimesterPlanning> {

    @Mapping(source = "learningResult.id", target = "learningResultId")
    @Mapping(source = "learningResult.codeResult", target = "learningResultCodeResult")
    @Mapping(source = "trimester.id", target = "trimesterId")
    @Mapping(source = "trimester.nameTrimester", target = "trimesterNameTrimester")
    @Mapping(source = "planning.id", target = "planningId")
    @Mapping(source = "planning.code", target = "planningCode")
    TrimesterPlanningDTO toDto(TrimesterPlanning trimesterPlanning);

    @Mapping(target = "planningActivities", ignore = true)
    @Mapping(target = "removePlanningActivity", ignore = true)
    @Mapping(source = "learningResultId", target = "learningResult")
    @Mapping(source = "trimesterId", target = "trimester")
    @Mapping(source = "planningId", target = "planning")
    TrimesterPlanning toEntity(TrimesterPlanningDTO trimesterPlanningDTO);

    default TrimesterPlanning fromId(Long id) {
        if (id == null) {
            return null;
        }
        TrimesterPlanning trimesterPlanning = new TrimesterPlanning();
        trimesterPlanning.setId(id);
        return trimesterPlanning;
    }
}
