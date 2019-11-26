package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.LimitationEnvironmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LimitationEnvironment} and its DTO {@link LimitationEnvironmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {LearningResultMapper.class, AmbientMapper.class})
public interface LimitationEnvironmentMapper extends EntityMapper<LimitationEnvironmentDTO, LimitationEnvironment> {

    @Mapping(source = "learningResult.id", target = "learningResultId")
    @Mapping(source = "learningResult.codeResult", target = "learningResultCodeResult")
    @Mapping(source = "ambient.id", target = "ambientId")
    @Mapping(source = "ambient.numberRoom", target = "ambientNumberRoom")
    LimitationEnvironmentDTO toDto(LimitationEnvironment limitationEnvironment);

    @Mapping(source = "learningResultId", target = "learningResult")
    @Mapping(source = "ambientId", target = "ambient")
    LimitationEnvironment toEntity(LimitationEnvironmentDTO limitationEnvironmentDTO);

    default LimitationEnvironment fromId(Long id) {
        if (id == null) {
            return null;
        }
        LimitationEnvironment limitationEnvironment = new LimitationEnvironment();
        limitationEnvironment.setId(id);
        return limitationEnvironment;
    }
}
