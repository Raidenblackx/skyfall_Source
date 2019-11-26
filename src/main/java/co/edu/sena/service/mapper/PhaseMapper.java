package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.PhaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Phase} and its DTO {@link PhaseDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProyectMapper.class})
public interface PhaseMapper extends EntityMapper<PhaseDTO, Phase> {

    @Mapping(source = "proyect.id", target = "proyectId")
    @Mapping(source = "proyect.codeProyect", target = "proyectCodeProyect")
    PhaseDTO toDto(Phase phase);

    @Mapping(target = "proyectActivities", ignore = true)
    @Mapping(target = "removeProyectActivity", ignore = true)
    @Mapping(source = "proyectId", target = "proyect")
    Phase toEntity(PhaseDTO phaseDTO);

    default Phase fromId(Long id) {
        if (id == null) {
            return null;
        }
        Phase phase = new Phase();
        phase.setId(id);
        return phase;
    }
}
