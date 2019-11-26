package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.WorkingDayDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkingDay} and its DTO {@link WorkingDayDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkingDayMapper extends EntityMapper<WorkingDayDTO, WorkingDay> {


    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "removeCourse", ignore = true)
    @Mapping(target = "trimesters", ignore = true)
    @Mapping(target = "removeTrimester", ignore = true)
    WorkingDay toEntity(WorkingDayDTO workingDayDTO);

    default WorkingDay fromId(Long id) {
        if (id == null) {
            return null;
        }
        WorkingDay workingDay = new WorkingDay();
        workingDay.setId(id);
        return workingDay;
    }
}
