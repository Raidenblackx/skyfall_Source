package co.edu.sena.service;

import co.edu.sena.domain.LevelFormation;
import co.edu.sena.repository.LevelFormationRepository;
import co.edu.sena.service.dto.LevelFormationDTO;
import co.edu.sena.service.mapper.LevelFormationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LevelFormation}.
 */
@Service
@Transactional
public class LevelFormationService {

    private final Logger log = LoggerFactory.getLogger(LevelFormationService.class);

    private final LevelFormationRepository levelFormationRepository;

    private final LevelFormationMapper levelFormationMapper;

    public LevelFormationService(LevelFormationRepository levelFormationRepository, LevelFormationMapper levelFormationMapper) {
        this.levelFormationRepository = levelFormationRepository;
        this.levelFormationMapper = levelFormationMapper;
    }

    /**
     * Save a levelFormation.
     *
     * @param levelFormationDTO the entity to save.
     * @return the persisted entity.
     */
    public LevelFormationDTO save(LevelFormationDTO levelFormationDTO) {
        log.debug("Request to save LevelFormation : {}", levelFormationDTO);
        LevelFormation levelFormation = levelFormationMapper.toEntity(levelFormationDTO);
        levelFormation = levelFormationRepository.save(levelFormation);
        return levelFormationMapper.toDto(levelFormation);
    }

    /**
     * Get all the levelFormations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LevelFormationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LevelFormations");
        return levelFormationRepository.findAll(pageable)
            .map(levelFormationMapper::toDto);
    }


    /**
     * Get one levelFormation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LevelFormationDTO> findOne(Long id) {
        log.debug("Request to get LevelFormation : {}", id);
        return levelFormationRepository.findById(id)
            .map(levelFormationMapper::toDto);
    }

    /**
     * Delete the levelFormation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LevelFormation : {}", id);
        levelFormationRepository.deleteById(id);
    }
}
