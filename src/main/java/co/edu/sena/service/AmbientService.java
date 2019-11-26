package co.edu.sena.service;

import co.edu.sena.domain.Ambient;
import co.edu.sena.repository.AmbientRepository;
import co.edu.sena.service.dto.AmbientDTO;
import co.edu.sena.service.mapper.AmbientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Ambient}.
 */
@Service
@Transactional
public class AmbientService {

    private final Logger log = LoggerFactory.getLogger(AmbientService.class);

    private final AmbientRepository ambientRepository;

    private final AmbientMapper ambientMapper;

    public AmbientService(AmbientRepository ambientRepository, AmbientMapper ambientMapper) {
        this.ambientRepository = ambientRepository;
        this.ambientMapper = ambientMapper;
    }

    /**
     * Save a ambient.
     *
     * @param ambientDTO the entity to save.
     * @return the persisted entity.
     */
    public AmbientDTO save(AmbientDTO ambientDTO) {
        log.debug("Request to save Ambient : {}", ambientDTO);
        Ambient ambient = ambientMapper.toEntity(ambientDTO);
        ambient = ambientRepository.save(ambient);
        return ambientMapper.toDto(ambient);
    }

    /**
     * Get all the ambients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AmbientDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ambients");
        return ambientRepository.findAll(pageable)
            .map(ambientMapper::toDto);
    }


    /**
     * Get one ambient by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AmbientDTO> findOne(Long id) {
        log.debug("Request to get Ambient : {}", id);
        return ambientRepository.findById(id)
            .map(ambientMapper::toDto);
    }

    /**
     * Delete the ambient by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Ambient : {}", id);
        ambientRepository.deleteById(id);
    }
}
