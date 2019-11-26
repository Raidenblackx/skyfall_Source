package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.ProyectActivityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProyectActivity} and its DTO {@link ProyectActivityDTO}.
 */
@Mapper(componentModel = "spring", uses = {PhaseMapper.class})
public interface ProyectActivityMapper extends EntityMapper<ProyectActivityDTO, ProyectActivity> {

    @Mapping(source = "phase.id", target = "phaseId")
    @Mapping(source = "phase.namePhase", target = "phaseNamePhase")
    ProyectActivityDTO toDto(ProyectActivity proyectActivity);

    @Mapping(target = "planningActivities", ignore = true)
    @Mapping(target = "removePlanningActivity", ignore = true)
    @Mapping(source = "phaseId", target = "phase")
    ProyectActivity toEntity(ProyectActivityDTO proyectActivityDTO);

    default ProyectActivity fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProyectActivity proyectActivity = new ProyectActivity();
        proyectActivity.setId(id);
        return proyectActivity;
    }
}
