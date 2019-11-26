package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.CourseStateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CourseState} and its DTO {@link CourseStateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CourseStateMapper extends EntityMapper<CourseStateDTO, CourseState> {


    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "removeCourse", ignore = true)
    CourseState toEntity(CourseStateDTO courseStateDTO);

    default CourseState fromId(Long id) {
        if (id == null) {
            return null;
        }
        CourseState courseState = new CourseState();
        courseState.setId(id);
        return courseState;
    }
}
