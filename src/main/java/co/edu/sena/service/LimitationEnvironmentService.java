package co.edu.sena.service;

import co.edu.sena.domain.LimitationEnvironment;
import co.edu.sena.repository.LimitationEnvironmentRepository;
import co.edu.sena.service.dto.LimitationEnvironmentDTO;
import co.edu.sena.service.mapper.LimitationEnvironmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LimitationEnvironment}.
 */
@Service
@Transactional
public class LimitationEnvironmentService {

    private final Logger log = LoggerFactory.getLogger(LimitationEnvironmentService.class);

    private final LimitationEnvironmentRepository limitationEnvironmentRepository;

    private final LimitationEnvironmentMapper limitationEnvironmentMapper;

    public LimitationEnvironmentService(LimitationEnvironmentRepository limitationEnvironmentRepository, LimitationEnvironmentMapper limitationEnvironmentMapper) {
        this.limitationEnvironmentRepository = limitationEnvironmentRepository;
        this.limitationEnvironmentMapper = limitationEnvironmentMapper;
    }

    /**
     * Save a limitationEnvironment.
     *
     * @param limitationEnvironmentDTO the entity to save.
     * @return the persisted entity.
     */
    public LimitationEnvironmentDTO save(LimitationEnvironmentDTO limitationEnvironmentDTO) {
        log.debug("Request to save LimitationEnvironment : {}", limitationEnvironmentDTO);
        LimitationEnvironment limitationEnvironment = limitationEnvironmentMapper.toEntity(limitationEnvironmentDTO);
        limitationEnvironment = limitationEnvironmentRepository.save(limitationEnvironment);
        return limitationEnvironmentMapper.toDto(limitationEnvironment);
    }

    /**
     * Get all the limitationEnvironments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LimitationEnvironmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LimitationEnvironments");
        return limitationEnvironmentRepository.findAll(pageable)
            .map(limitationEnvironmentMapper::toDto);
    }


    /**
     * Get one limitationEnvironment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LimitationEnvironmentDTO> findOne(Long id) {
        log.debug("Request to get LimitationEnvironment : {}", id);
        return limitationEnvironmentRepository.findById(id)
            .map(limitationEnvironmentMapper::toDto);
    }

    /**
     * Delete the limitationEnvironment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LimitationEnvironment : {}", id);
        limitationEnvironmentRepository.deleteById(id);
    }
}
