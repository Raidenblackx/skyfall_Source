package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.AreaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Area} and its DTO {@link AreaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AreaMapper extends EntityMapper<AreaDTO, Area> {


    @Mapping(target = "instructorAreas", ignore = true)
    @Mapping(target = "removeInstructorArea", ignore = true)
    Area toEntity(AreaDTO areaDTO);

    default Area fromId(Long id) {
        if (id == null) {
            return null;
        }
        Area area = new Area();
        area.setId(id);
        return area;
    }
}
