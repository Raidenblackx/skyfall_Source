package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.ResultSeenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResultSeen} and its DTO {@link ResultSeenDTO}.
 */
@Mapper(componentModel = "spring", uses = {LearningResultMapper.class, CourseHasTrimesterMapper.class})
public interface ResultSeenMapper extends EntityMapper<ResultSeenDTO, ResultSeen> {

    @Mapping(source = "learningResult.id", target = "learningResultId")
    @Mapping(source = "learningResult.codeResult", target = "learningResultCodeResult")
    @Mapping(source = "courseHasTrimester.id", target = "courseHasTrimesterId")
    ResultSeenDTO toDto(ResultSeen resultSeen);

    @Mapping(source = "learningResultId", target = "learningResult")
    @Mapping(source = "courseHasTrimesterId", target = "courseHasTrimester")
    ResultSeen toEntity(ResultSeenDTO resultSeenDTO);

    default ResultSeen fromId(Long id) {
        if (id == null) {
            return null;
        }
        ResultSeen resultSeen = new ResultSeen();
        resultSeen.setId(id);
        return resultSeen;
    }
}
