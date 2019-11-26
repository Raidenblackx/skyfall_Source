package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.CourseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Course} and its DTO {@link CourseDTO}.
 */
@Mapper(componentModel = "spring", uses = {CourseStateMapper.class, WorkingDayMapper.class, ProgramMapper.class})
public interface CourseMapper extends EntityMapper<CourseDTO, Course> {

    @Mapping(source = "courseState.id", target = "courseStateId")
    @Mapping(source = "courseState.nameCourseState", target = "courseStateNameCourseState")
    @Mapping(source = "workingDay.id", target = "workingDayId")
    @Mapping(source = "workingDay.nameWorkingDay", target = "workingDayNameWorkingDay")
    @Mapping(source = "program.id", target = "programId")
    @Mapping(source = "program.nameProgram", target = "programNameProgram")
    CourseDTO toDto(Course course);

    @Mapping(target = "courseHasTrimesters", ignore = true)
    @Mapping(target = "removeCourseHasTrimester", ignore = true)
    @Mapping(source = "courseStateId", target = "courseState")
    @Mapping(source = "workingDayId", target = "workingDay")
    @Mapping(source = "programId", target = "program")
    Course toEntity(CourseDTO courseDTO);

    default Course fromId(Long id) {
        if (id == null) {
            return null;
        }
        Course course = new Course();
        course.setId(id);
        return course;
    }
}
