package co.edu.sena.service;

import co.edu.sena.domain.ResultSeen;
import co.edu.sena.repository.ResultSeenRepository;
import co.edu.sena.service.dto.ResultSeenDTO;
import co.edu.sena.service.mapper.ResultSeenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ResultSeen}.
 */
@Service
@Transactional
public class ResultSeenService {

    private final Logger log = LoggerFactory.getLogger(ResultSeenService.class);

    private final ResultSeenRepository resultSeenRepository;

    private final ResultSeenMapper resultSeenMapper;

    public ResultSeenService(ResultSeenRepository resultSeenRepository, ResultSeenMapper resultSeenMapper) {
        this.resultSeenRepository = resultSeenRepository;
        this.resultSeenMapper = resultSeenMapper;
    }

    /**
     * Save a resultSeen.
     *
     * @param resultSeenDTO the entity to save.
     * @return the persisted entity.
     */
    public ResultSeenDTO save(ResultSeenDTO resultSeenDTO) {
        log.debug("Request to save ResultSeen : {}", resultSeenDTO);
        ResultSeen resultSeen = resultSeenMapper.toEntity(resultSeenDTO);
        resultSeen = resultSeenRepository.save(resultSeen);
        return resultSeenMapper.toDto(resultSeen);
    }

    /**
     * Get all the resultSeens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ResultSeenDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ResultSeens");
        return resultSeenRepository.findAll(pageable)
            .map(resultSeenMapper::toDto);
    }


    /**
     * Get one resultSeen by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ResultSeenDTO> findOne(Long id) {
        log.debug("Request to get ResultSeen : {}", id);
        return resultSeenRepository.findById(id)
            .map(resultSeenMapper::toDto);
    }

    /**
     * Delete the resultSeen by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ResultSeen : {}", id);
        resultSeenRepository.deleteById(id);
    }
}
