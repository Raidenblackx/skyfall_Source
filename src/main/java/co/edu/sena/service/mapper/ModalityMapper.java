package co.edu.sena.service.mapper;

import co.edu.sena.domain.*;
import co.edu.sena.service.dto.ModalityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Modality} and its DTO {@link ModalityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ModalityMapper extends EntityMapper<ModalityDTO, Modality> {


    @Mapping(target = "schedules", ignore = true)
    @Mapping(target = "removeSchedule", ignore = true)
    Modality toEntity(ModalityDTO modalityDTO);

    default Modality fromId(Long id) {
        if (id == null) {
            return null;
        }
        Modality modality = new Modality();
        modality.setId(id);
        return modality;
    }
}
