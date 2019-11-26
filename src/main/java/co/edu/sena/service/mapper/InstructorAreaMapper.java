package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.InstructorAreaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InstructorArea} and its DTO {@link InstructorAreaDTO}.
 */
@Mapper(componentModel = "spring", uses = {AreaMapper.class, InstructorMapper.class})
public interface InstructorAreaMapper extends EntityMapper<InstructorAreaDTO, InstructorArea> {

    @Mapping(source = "area.id", target = "areaId")
    @Mapping(source = "area.nameArea", target = "areaNameArea")
    @Mapping(source = "instructor.id", target = "instructorId")
    InstructorAreaDTO toDto(InstructorArea instructorArea);

    @Mapping(source = "areaId", target = "area")
    @Mapping(source = "instructorId", target = "instructor")
    InstructorArea toEntity(InstructorAreaDTO instructorAreaDTO);

    default InstructorArea fromId(Long id) {
        if (id == null) {
            return null;
        }
        InstructorArea instructorArea = new InstructorArea();
        instructorArea.setId(id);
        return instructorArea;
    }
}
