package co.edu.sena.service;

import co.edu.sena.domain.Trimester;
import co.edu.sena.repository.TrimesterRepository;
import co.edu.sena.service.dto.TrimesterDTO;
import co.edu.sena.service.mapper.TrimesterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Trimester}.
 */
@Service
@Transactional
public class TrimesterService {

    private final Logger log = LoggerFactory.getLogger(TrimesterService.class);

    private final TrimesterRepository trimesterRepository;

    private final TrimesterMapper trimesterMapper;

    public TrimesterService(TrimesterRepository trimesterRepository, TrimesterMapper trimesterMapper) {
        this.trimesterRepository = trimesterRepository;
        this.trimesterMapper = trimesterMapper;
    }

    /**
     * Save a trimester.
     *
     * @param trimesterDTO the entity to save.
     * @return the persisted entity.
     */
    public TrimesterDTO save(TrimesterDTO trimesterDTO) {
        log.debug("Request to save Trimester : {}", trimesterDTO);
        Trimester trimester = trimesterMapper.toEntity(trimesterDTO);
        trimester = trimesterRepository.save(trimester);
        return trimesterMapper.toDto(trimester);
    }

    /**
     * Get all the trimesters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TrimesterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Trimesters");
        return trimesterRepository.findAll(pageable)
            .map(trimesterMapper::toDto);
    }


    /**
     * Get one trimester by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TrimesterDTO> findOne(Long id) {
        log.debug("Request to get Trimester : {}", id);
        return trimesterRepository.findById(id)
            .map(trimesterMapper::toDto);
    }

    /**
     * Delete the trimester by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Trimester : {}", id);
        trimesterRepository.deleteById(id);
    }
}
