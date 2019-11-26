package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.CourseHasTrimesterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CourseHasTrimester} and its DTO {@link CourseHasTrimesterDTO}.
 */
@Mapper(componentModel = "spring", uses = {CourseMapper.class, TrimesterMapper.class})
public interface CourseHasTrimesterMapper extends EntityMapper<CourseHasTrimesterDTO, CourseHasTrimester> {

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.courseNumber", target = "courseCourseNumber")
    @Mapping(source = "trimester.id", target = "trimesterId")
    @Mapping(source = "trimester.nameTrimester", target = "trimesterNameTrimester")
    CourseHasTrimesterDTO toDto(CourseHasTrimester courseHasTrimester);

    @Mapping(target = "schedules", ignore = true)
    @Mapping(target = "removeSchedule", ignore = true)
    @Mapping(target = "resultSeens", ignore = true)
    @Mapping(target = "removeResultSeen", ignore = true)
    @Mapping(source = "courseId", target = "course")
    @Mapping(source = "trimesterId", target = "trimester")
    CourseHasTrimester toEntity(CourseHasTrimesterDTO courseHasTrimesterDTO);

    default CourseHasTrimester fromId(Long id) {
        if (id == null) {
            return null;
        }
        CourseHasTrimester courseHasTrimester = new CourseHasTrimester();
        courseHasTrimester.setId(id);
        return courseHasTrimester;
    }
}
