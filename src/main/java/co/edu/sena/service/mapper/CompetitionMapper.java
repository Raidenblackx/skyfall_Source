package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.CompetitionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Competition} and its DTO {@link CompetitionDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProgramMapper.class})
public interface CompetitionMapper extends EntityMapper<CompetitionDTO, Competition> {

    @Mapping(source = "program.id", target = "programId")
    @Mapping(source = "program.nameProgram", target = "programNameProgram")
    CompetitionDTO toDto(Competition competition);

    @Mapping(target = "learningResults", ignore = true)
    @Mapping(target = "removeLearningResult", ignore = true)
    @Mapping(target = "availabilityCompetitions", ignore = true)
    @Mapping(target = "removeAvailabilityCompetition", ignore = true)
    @Mapping(source = "programId", target = "program")
    Competition toEntity(CompetitionDTO competitionDTO);

    default Competition fromId(Long id) {
        if (id == null) {
            return null;
        }
        Competition competition = new Competition();
        competition.setId(id);
        return competition;
    }
}
