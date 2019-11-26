package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.CurrentQuarterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CurrentQuarter} and its DTO {@link CurrentQuarterDTO}.
 */
@Mapper(componentModel = "spring", uses = {YearMapper.class})
public interface CurrentQuarterMapper extends EntityMapper<CurrentQuarterDTO, CurrentQuarter> {

    @Mapping(source = "year.id", target = "yearId")
    @Mapping(source = "year.numberYear", target = "yearNumberYear")
    CurrentQuarterDTO toDto(CurrentQuarter currentQuarter);

    @Mapping(target = "scheduleVersions", ignore = true)
    @Mapping(target = "removeScheduleVersion", ignore = true)
    @Mapping(source = "yearId", target = "year")
    CurrentQuarter toEntity(CurrentQuarterDTO currentQuarterDTO);

    default CurrentQuarter fromId(Long id) {
        if (id == null) {
            return null;
        }
        CurrentQuarter currentQuarter = new CurrentQuarter();
        currentQuarter.setId(id);
        return currentQuarter;
    }
}
