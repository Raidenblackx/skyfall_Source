package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.InstructorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Instructor} and its DTO {@link InstructorDTO}.
 */
@Mapper(componentModel = "spring", uses = {ClientMapper.class})
public interface InstructorMapper extends EntityMapper<InstructorDTO, Instructor> {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.documentNumber", target = "clientDocumentNumber")
    InstructorDTO toDto(Instructor instructor);

    @Mapping(target = "instructorAreas", ignore = true)
    @Mapping(target = "removeInstructorArea", ignore = true)
    @Mapping(target = "instructorLinkings", ignore = true)
    @Mapping(target = "removeInstructorLinking", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    @Mapping(target = "removeSchedule", ignore = true)
    @Mapping(source = "clientId", target = "client")
    Instructor toEntity(InstructorDTO instructorDTO);

    default Instructor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Instructor instructor = new Instructor();
        instructor.setId(id);
        return instructor;
    }
}
