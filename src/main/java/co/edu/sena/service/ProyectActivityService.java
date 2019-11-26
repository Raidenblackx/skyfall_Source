package co.edu.sena.service;

import co.edu.sena.domain.ProyectActivity;
import co.edu.sena.repository.ProyectActivityRepository;
import co.edu.sena.service.dto.ProyectActivityDTO;
import co.edu.sena.service.mapper.ProyectActivityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProyectActivity}.
 */
@Service
@Transactional
public class ProyectActivityService {

    private final Logger log = LoggerFactory.getLogger(ProyectActivityService.class);

    private final ProyectActivityRepository proyectActivityRepository;

    private final ProyectActivityMapper proyectActivityMapper;

    public ProyectActivityService(ProyectActivityRepository proyectActivityRepository, ProyectActivityMapper proyectActivityMapper) {
        this.proyectActivityRepository = proyectActivityRepository;
        this.proyectActivityMapper = proyectActivityMapper;
    }

    /**
     * Save a proyectActivity.
     *
     * @param proyectActivityDTO the entity to save.
     * @return the persisted entity.
     */
    public ProyectActivityDTO save(ProyectActivityDTO proyectActivityDTO) {
        log.debug("Request to save ProyectActivity : {}", proyectActivityDTO);
        ProyectActivity proyectActivity = proyectActivityMapper.toEntity(proyectActivityDTO);
        proyectActivity = proyectActivityRepository.save(proyectActivity);
        return proyectActivityMapper.toDto(proyectActivity);
    }

    /**
     * Get all the proyectActivities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProyectActivityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProyectActivities");
        return proyectActivityRepository.findAll(pageable)
            .map(proyectActivityMapper::toDto);
    }


    /**
     * Get one proyectActivity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProyectActivityDTO> findOne(Long id) {
        log.debug("Request to get ProyectActivity : {}", id);
        return proyectActivityRepository.findById(id)
            .map(proyectActivityMapper::toDto);
    }

    /**
     * Delete the proyectActivity by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProyectActivity : {}", id);
        proyectActivityRepository.deleteById(id);
    }
}
