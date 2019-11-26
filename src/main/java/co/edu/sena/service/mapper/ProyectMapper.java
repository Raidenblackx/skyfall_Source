package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.ProyectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Proyect} and its DTO {@link ProyectDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProgramMapper.class})
public interface ProyectMapper extends EntityMapper<ProyectDTO, Proyect> {

    @Mapping(source = "program.id", target = "programId")
    @Mapping(source = "program.nameProgram", target = "programNameProgram")
    ProyectDTO toDto(Proyect proyect);

    @Mapping(target = "phases", ignore = true)
    @Mapping(target = "removePhase", ignore = true)
    @Mapping(source = "programId", target = "program")
    Proyect toEntity(ProyectDTO proyectDTO);

    default Proyect fromId(Long id) {
        if (id == null) {
            return null;
        }
        Proyect proyect = new Proyect();
        proyect.setId(id);
        return proyect;
    }
}
