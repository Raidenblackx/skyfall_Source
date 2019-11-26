package co.edu.sena.service;

import co.edu.sena.domain.LearningResult;
import co.edu.sena.repository.LearningResultRepository;
import co.edu.sena.service.dto.LearningResultDTO;
import co.edu.sena.service.mapper.LearningResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LearningResult}.
 */
@Service
@Transactional
public class LearningResultService {

    private final Logger log = LoggerFactory.getLogger(LearningResultService.class);

    private final LearningResultRepository learningResultRepository;

    private final LearningResultMapper learningResultMapper;

    public LearningResultService(LearningResultRepository learningResultRepository, LearningResultMapper learningResultMapper) {
        this.learningResultRepository = learningResultRepository;
        this.learningResultMapper = learningResultMapper;
    }

    /**
     * Save a learningResult.
     *
     * @param learningResultDTO the entity to save.
     * @return the persisted entity.
     */
    public LearningResultDTO save(LearningResultDTO learningResultDTO) {
        log.debug("Request to save LearningResult : {}", learningResultDTO);
        LearningResult learningResult = learningResultMapper.toEntity(learningResultDTO);
        learningResult = learningResultRepository.save(learningResult);
        return learningResultMapper.toDto(learningResult);
    }

    /**
     * Get all the learningResults.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LearningResultDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LearningResults");
        return learningResultRepository.findAll(pageable)
            .map(learningResultMapper::toDto);
    }


    /**
     * Get one learningResult by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LearningResultDTO> findOne(Long id) {
        log.debug("Request to get LearningResult : {}", id);
        return learningResultRepository.findById(id)
            .map(learningResultMapper::toDto);
    }

    /**
     * Delete the learningResult by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LearningResult : {}", id);
        learningResultRepository.deleteById(id);
    }
}
