package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.ScheduleVersionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ScheduleVersion} and its DTO {@link ScheduleVersionDTO}.
 */
@Mapper(componentModel = "spring", uses = {CurrentQuarterMapper.class})
public interface ScheduleVersionMapper extends EntityMapper<ScheduleVersionDTO, ScheduleVersion> {

    @Mapping(source = "currentQuarter.id", target = "currentQuarterId")
    @Mapping(source = "currentQuarter.scheduledQuarter", target = "currentQuarterScheduledQuarter")
    ScheduleVersionDTO toDto(ScheduleVersion scheduleVersion);

    @Mapping(target = "schedules", ignore = true)
    @Mapping(target = "removeSchedule", ignore = true)
    @Mapping(source = "currentQuarterId", target = "currentQuarter")
    ScheduleVersion toEntity(ScheduleVersionDTO scheduleVersionDTO);

    default ScheduleVersion fromId(Long id) {
        if (id == null) {
            return null;
        }
        ScheduleVersion scheduleVersion = new ScheduleVersion();
        scheduleVersion.setId(id);
        return scheduleVersion;
    }
}
