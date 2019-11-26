package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.LevelFormationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LevelFormation} and its DTO {@link LevelFormationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LevelFormationMapper extends EntityMapper<LevelFormationDTO, LevelFormation> {


    @Mapping(target = "programs", ignore = true)
    @Mapping(target = "removeProgram", ignore = true)
    @Mapping(target = "trimesters", ignore = true)
    @Mapping(target = "removeTrimester", ignore = true)
    LevelFormation toEntity(LevelFormationDTO levelFormationDTO);

    default LevelFormation fromId(Long id) {
        if (id == null) {
            return null;
        }
        LevelFormation levelFormation = new LevelFormation();
        levelFormation.setId(id);
        return levelFormation;
    }
}
