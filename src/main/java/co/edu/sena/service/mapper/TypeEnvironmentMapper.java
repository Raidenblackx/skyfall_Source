package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.TypeEnvironmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeEnvironment} and its DTO {@link TypeEnvironmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeEnvironmentMapper extends EntityMapper<TypeEnvironmentDTO, TypeEnvironment> {


    @Mapping(target = "ambients", ignore = true)
    @Mapping(target = "removeAmbient", ignore = true)
    TypeEnvironment toEntity(TypeEnvironmentDTO typeEnvironmentDTO);

    default TypeEnvironment fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeEnvironment typeEnvironment = new TypeEnvironment();
        typeEnvironment.setId(id);
        return typeEnvironment;
    }
}
