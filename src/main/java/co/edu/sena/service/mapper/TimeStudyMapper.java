package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.TimeStudyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TimeStudy} and its DTO {@link TimeStudyDTO}.
 */
@Mapper(componentModel = "spring", uses = {JourneyInstructorMapper.class, DayMapper.class})
public interface TimeStudyMapper extends EntityMapper<TimeStudyDTO, TimeStudy> {

    @Mapping(source = "journeyInstructor.id", target = "journeyInstructorId")
    @Mapping(source = "journeyInstructor.nameDay", target = "journeyInstructorNameDay")
    @Mapping(source = "day.id", target = "dayId")
    @Mapping(source = "day.nameDay", target = "dayNameDay")
    TimeStudyDTO toDto(TimeStudy timeStudy);

    @Mapping(source = "journeyInstructorId", target = "journeyInstructor")
    @Mapping(source = "dayId", target = "day")
    TimeStudy toEntity(TimeStudyDTO timeStudyDTO);

    default TimeStudy fromId(Long id) {
        if (id == null) {
            return null;
        }
        TimeStudy timeStudy = new TimeStudy();
        timeStudy.setId(id);
        return timeStudy;
    }
}
