package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.ScheduleAvailabilityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ScheduleAvailability} and its DTO {@link ScheduleAvailabilityDTO}.
 */
@Mapper(componentModel = "spring", uses = {InstructorLinkingMapper.class, JourneyInstructorMapper.class})
public interface ScheduleAvailabilityMapper extends EntityMapper<ScheduleAvailabilityDTO, ScheduleAvailability> {

    @Mapping(source = "instructorLinking.id", target = "instructorLinkingId")
    @Mapping(source = "journeyInstructor.id", target = "journeyInstructorId")
    @Mapping(source = "journeyInstructor.nameDay", target = "journeyInstructorNameDay")
    ScheduleAvailabilityDTO toDto(ScheduleAvailability scheduleAvailability);

    @Mapping(source = "instructorLinkingId", target = "instructorLinking")
    @Mapping(source = "journeyInstructorId", target = "journeyInstructor")
    ScheduleAvailability toEntity(ScheduleAvailabilityDTO scheduleAvailabilityDTO);

    default ScheduleAvailability fromId(Long id) {
        if (id == null) {
            return null;
        }
        ScheduleAvailability scheduleAvailability = new ScheduleAvailability();
        scheduleAvailability.setId(id);
        return scheduleAvailability;
    }
}
