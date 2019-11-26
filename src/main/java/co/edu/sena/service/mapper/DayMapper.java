package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.DayDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Day} and its DTO {@link DayDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DayMapper extends EntityMapper<DayDTO, Day> {


    @Mapping(target = "schedules", ignore = true)
    @Mapping(target = "removeSchedule", ignore = true)
    @Mapping(target = "timeStudies", ignore = true)
    @Mapping(target = "removeTimeStudy", ignore = true)
    Day toEntity(DayDTO dayDTO);

    default Day fromId(Long id) {
        if (id == null) {
            return null;
        }
        Day day = new Day();
        day.setId(id);
        return day;
    }
}
