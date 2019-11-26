package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.LinkageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Linkage} and its DTO {@link LinkageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LinkageMapper extends EntityMapper<LinkageDTO, Linkage> {


    @Mapping(target = "instructorLinkings", ignore = true)
    @Mapping(target = "removeInstructorLinking", ignore = true)
    Linkage toEntity(LinkageDTO linkageDTO);

    default Linkage fromId(Long id) {
        if (id == null) {
            return null;
        }
        Linkage linkage = new Linkage();
        linkage.setId(id);
        return linkage;
    }
}
