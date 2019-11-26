package co.edu.sena.service;

import co.edu.sena.domain.Proyect;
import co.edu.sena.repository.ProyectRepository;
import co.edu.sena.service.dto.ProyectDTO;
import co.edu.sena.service.mapper.ProyectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Proyect}.
 */
@Service
@Transactional
public class ProyectService {

    private final Logger log = LoggerFactory.getLogger(ProyectService.class);

    private final ProyectRepository proyectRepository;

    private final ProyectMapper proyectMapper;

    public ProyectService(ProyectRepository proyectRepository, ProyectMapper proyectMapper) {
        this.proyectRepository = proyectRepository;
        this.proyectMapper = proyectMapper;
    }

    /**
     * Save a proyect.
     *
     * @param proyectDTO the entity to save.
     * @return the persisted entity.
     */
    public ProyectDTO save(ProyectDTO proyectDTO) {
        log.debug("Request to save Proyect : {}", proyectDTO);
        Proyect proyect = proyectMapper.toEntity(proyectDTO);
        proyect = proyectRepository.save(proyect);
        return proyectMapper.toDto(proyect);
    }

    /**
     * Get all the proyects.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProyectDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Proyects");
        return proyectRepository.findAll(pageable)
            .map(proyectMapper::toDto);
    }


    /**
     * Get one proyect by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProyectDTO> findOne(Long id) {
        log.debug("Request to get Proyect : {}", id);
        return proyectRepository.findById(id)
            .map(proyectMapper::toDto);
    }

    /**
     * Delete the proyect by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Proyect : {}", id);
        proyectRepository.deleteById(id);
    }
}
