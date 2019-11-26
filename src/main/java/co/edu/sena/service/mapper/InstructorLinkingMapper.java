package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.InstructorLinkingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InstructorLinking} and its DTO {@link InstructorLinkingDTO}.
 */
@Mapper(componentModel = "spring", uses = {YearMapper.class, InstructorMapper.class, LinkageMapper.class})
public interface InstructorLinkingMapper extends EntityMapper<InstructorLinkingDTO, InstructorLinking> {

    @Mapping(source = "year.id", target = "yearId")
    @Mapping(source = "year.numberYear", target = "yearNumberYear")
    @Mapping(source = "instructor.id", target = "instructorId")
    @Mapping(source = "linkage.id", target = "linkageId")
    @Mapping(source = "linkage.typeLink", target = "linkageTypeLink")
    InstructorLinkingDTO toDto(InstructorLinking instructorLinking);

    @Mapping(target = "scheduleAvailabilities", ignore = true)
    @Mapping(target = "removeScheduleAvailability", ignore = true)
    @Mapping(target = "availabilityCompetitions", ignore = true)
    @Mapping(target = "removeAvailabilityCompetition", ignore = true)
    @Mapping(source = "yearId", target = "year")
    @Mapping(source = "instructorId", target = "instructor")
    @Mapping(source = "linkageId", target = "linkage")
    InstructorLinking toEntity(InstructorLinkingDTO instructorLinkingDTO);

    default InstructorLinking fromId(Long id) {
        if (id == null) {
            return null;
        }
        InstructorLinking instructorLinking = new InstructorLinking();
        instructorLinking.setId(id);
        return instructorLinking;
    }
}
