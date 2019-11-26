package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.TrimesterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Trimester} and its DTO {@link TrimesterDTO}.
 */
@Mapper(componentModel = "spring", uses = {WorkingDayMapper.class, LevelFormationMapper.class})
public interface TrimesterMapper extends EntityMapper<TrimesterDTO, Trimester> {

    @Mapping(source = "workingDay.id", target = "workingDayId")
    @Mapping(source = "workingDay.nameWorkingDay", target = "workingDayNameWorkingDay")
    @Mapping(source = "levelFormation.id", target = "levelFormationId")
    @Mapping(source = "levelFormation.level", target = "levelFormationLevel")
    TrimesterDTO toDto(Trimester trimester);

    @Mapping(target = "courseHasTrimesters", ignore = true)
    @Mapping(target = "removeCourseHasTrimester", ignore = true)
    @Mapping(target = "trimesterPlannings", ignore = true)
    @Mapping(target = "removeTrimesterPlanning", ignore = true)
    @Mapping(source = "workingDayId", target = "workingDay")
    @Mapping(source = "levelFormationId", target = "levelFormation")
    Trimester toEntity(TrimesterDTO trimesterDTO);

    default Trimester fromId(Long id) {
        if (id == null) {
            return null;
        }
        Trimester trimester = new Trimester();
        trimester.setId(id);
        return trimester;
    }
}
