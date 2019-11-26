package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.SedeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Sede} and its DTO {@link SedeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SedeMapper extends EntityMapper<SedeDTO, Sede> {


    @Mapping(target = "ambients", ignore = true)
    @Mapping(target = "removeAmbient", ignore = true)
    Sede toEntity(SedeDTO sedeDTO);

    default Sede fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sede sede = new Sede();
        sede.setId(id);
        return sede;
    }
}
