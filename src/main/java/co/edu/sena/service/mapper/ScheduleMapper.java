package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.ScheduleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Schedule} and its DTO {@link ScheduleDTO}.
 */
@Mapper(componentModel = "spring", uses = {CourseHasTrimesterMapper.class, AmbientMapper.class, InstructorMapper.class, DayMapper.class, ScheduleVersionMapper.class, ModalityMapper.class})
public interface ScheduleMapper extends EntityMapper<ScheduleDTO, Schedule> {

    @Mapping(source = "courseHasTrimester.id", target = "courseHasTrimesterId")
    @Mapping(source = "ambient.id", target = "ambientId")
    @Mapping(source = "ambient.numberRoom", target = "ambientNumberRoom")
    @Mapping(source = "instructor.id", target = "instructorId")
    @Mapping(source = "day.id", target = "dayId")
    @Mapping(source = "day.nameDay", target = "dayNameDay")
    @Mapping(source = "scheduleVersion.id", target = "scheduleVersionId")
    @Mapping(source = "scheduleVersion.numberVersion", target = "scheduleVersionNumberVersion")
    @Mapping(source = "modality.id", target = "modalityId")
    @Mapping(source = "modality.nameModality", target = "modalityNameModality")
    ScheduleDTO toDto(Schedule schedule);

    @Mapping(source = "courseHasTrimesterId", target = "courseHasTrimester")
    @Mapping(source = "ambientId", target = "ambient")
    @Mapping(source = "instructorId", target = "instructor")
    @Mapping(source = "dayId", target = "day")
    @Mapping(source = "scheduleVersionId", target = "scheduleVersion")
    @Mapping(source = "modalityId", target = "modality")
    Schedule toEntity(ScheduleDTO scheduleDTO);

    default Schedule fromId(Long id) {
        if (id == null) {
            return null;
        }
        Schedule schedule = new Schedule();
        schedule.setId(id);
        return schedule;
    }
}
