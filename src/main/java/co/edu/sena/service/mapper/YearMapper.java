package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.YearDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Year} and its DTO {@link YearDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface YearMapper extends EntityMapper<YearDTO, Year> {


    @Mapping(target = "instructorLinkings", ignore = true)
    @Mapping(target = "removeInstructorLinking", ignore = true)
    @Mapping(target = "currentQuarters", ignore = true)
    @Mapping(target = "removeCurrentQuarter", ignore = true)
    Year toEntity(YearDTO yearDTO);

    default Year fromId(Long id) {
        if (id == null) {
            return null;
        }
        Year year = new Year();
        year.setId(id);
        return year;
    }
}
