package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.AvailabilityCompetitionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AvailabilityCompetition} and its DTO {@link AvailabilityCompetitionDTO}.
 */
@Mapper(componentModel = "spring", uses = {CompetitionMapper.class, InstructorLinkingMapper.class})
public interface AvailabilityCompetitionMapper extends EntityMapper<AvailabilityCompetitionDTO, AvailabilityCompetition> {

    @Mapping(source = "competition.id", target = "competitionId")
    @Mapping(source = "competition.codeCompetition", target = "competitionCodeCompetition")
    @Mapping(source = "instructorLinking.id", target = "instructorLinkingId")
    AvailabilityCompetitionDTO toDto(AvailabilityCompetition availabilityCompetition);

    @Mapping(source = "competitionId", target = "competition")
    @Mapping(source = "instructorLinkingId", target = "instructorLinking")
    AvailabilityCompetition toEntity(AvailabilityCompetitionDTO availabilityCompetitionDTO);

    default AvailabilityCompetition fromId(Long id) {
        if (id == null) {
            return null;
        }
        AvailabilityCompetition availabilityCompetition = new AvailabilityCompetition();
        availabilityCompetition.setId(id);
        return availabilityCompetition;
    }
}
