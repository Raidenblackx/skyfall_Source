package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.LearningResultDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LearningResult} and its DTO {@link LearningResultDTO}.
 */
@Mapper(componentModel = "spring", uses = {CompetitionMapper.class})
public interface LearningResultMapper extends EntityMapper<LearningResultDTO, LearningResult> {

    @Mapping(source = "competition.id", target = "competitionId")
    @Mapping(source = "competition.codeCompetition", target = "competitionCodeCompetition")
    LearningResultDTO toDto(LearningResult learningResult);

    @Mapping(target = "trimesterPlannings", ignore = true)
    @Mapping(target = "removeTrimesterPlanning", ignore = true)
    @Mapping(target = "limitationEnviroments", ignore = true)
    @Mapping(target = "removeLimitationEnviroment", ignore = true)
    @Mapping(target = "resultSeens", ignore = true)
    @Mapping(target = "removeResultSeen", ignore = true)
    @Mapping(source = "competitionId", target = "competition")
    LearningResult toEntity(LearningResultDTO learningResultDTO);

    default LearningResult fromId(Long id) {
        if (id == null) {
            return null;
        }
        LearningResult learningResult = new LearningResult();
        learningResult.setId(id);
        return learningResult;
    }
}
