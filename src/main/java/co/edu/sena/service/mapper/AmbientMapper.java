package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.AmbientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ambient} and its DTO {@link AmbientDTO}.
 */
@Mapper(componentModel = "spring", uses = {TypeEnvironmentMapper.class, SedeMapper.class})
public interface AmbientMapper extends EntityMapper<AmbientDTO, Ambient> {

    @Mapping(source = "typeEnvironment.id", target = "typeEnvironmentId")
    @Mapping(source = "typeEnvironment.type", target = "typeEnvironmentType")
    @Mapping(source = "sede.id", target = "sedeId")
    @Mapping(source = "sede.nameSede", target = "sedeNameSede")
    AmbientDTO toDto(Ambient ambient);

    @Mapping(target = "limitationEnvironments", ignore = true)
    @Mapping(target = "removeLimitationEnvironment", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    @Mapping(target = "removeSchedule", ignore = true)
    @Mapping(source = "typeEnvironmentId", target = "typeEnvironment")
    @Mapping(source = "sedeId", target = "sede")
    Ambient toEntity(AmbientDTO ambientDTO);

    default Ambient fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ambient ambient = new Ambient();
        ambient.setId(id);
        return ambient;
    }
}
