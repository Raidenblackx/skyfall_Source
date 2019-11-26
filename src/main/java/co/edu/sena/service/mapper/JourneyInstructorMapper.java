package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.JourneyInstructorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link JourneyInstructor} and its DTO {@link JourneyInstructorDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface JourneyInstructorMapper extends EntityMapper<JourneyInstructorDTO, JourneyInstructor> {


    @Mapping(target = "timeStudies", ignore = true)
    @Mapping(target = "removeTimeStudy", ignore = true)
    @Mapping(target = "scheduleAvailabilities", ignore = true)
    @Mapping(target = "removeScheduleAvailability", ignore = true)
    JourneyInstructor toEntity(JourneyInstructorDTO journeyInstructorDTO);

    default JourneyInstructor fromId(Long id) {
        if (id == null) {
            return null;
        }
        JourneyInstructor journeyInstructor = new JourneyInstructor();
        journeyInstructor.setId(id);
        return journeyInstructor;
    }
}
