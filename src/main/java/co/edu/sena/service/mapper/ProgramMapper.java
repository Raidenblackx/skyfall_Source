package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.ProgramDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Program} and its DTO {@link ProgramDTO}.
 */
@Mapper(componentModel = "spring", uses = {LevelFormationMapper.class})
public interface ProgramMapper extends EntityMapper<ProgramDTO, Program> {

    @Mapping(source = "levelFormation.id", target = "levelFormationId")
    @Mapping(source = "levelFormation.level", target = "levelFormationLevel")
    ProgramDTO toDto(Program program);

    @Mapping(target = "competitions", ignore = true)
    @Mapping(target = "removeCompetition", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "removeCourse", ignore = true)
    @Mapping(target = "proyects", ignore = true)
    @Mapping(target = "removeProyect", ignore = true)
    @Mapping(source = "levelFormationId", target = "levelFormation")
    Program toEntity(ProgramDTO programDTO);

    default Program fromId(Long id) {
        if (id == null) {
            return null;
        }
        Program program = new Program();
        program.setId(id);
        return program;
    }
}
