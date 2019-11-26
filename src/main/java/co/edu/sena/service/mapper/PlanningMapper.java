package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.PlanningDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Planning} and its DTO {@link PlanningDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlanningMapper extends EntityMapper<PlanningDTO, Planning> {


    @Mapping(target = "trimesterPlannings", ignore = true)
    @Mapping(target = "removeTrimesterPlanning", ignore = true)
    Planning toEntity(PlanningDTO planningDTO);

    default Planning fromId(Long id) {
        if (id == null) {
            return null;
        }
        Planning planning = new Planning();
        planning.setId(id);
        return planning;
    }
}
