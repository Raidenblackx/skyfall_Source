package co.edu.sena.service;

import co.edu.sena.domain.Linkage;
import co.edu.sena.repository.LinkageRepository;
import co.edu.sena.service.dto.LinkageDTO;
import co.edu.sena.service.mapper.LinkageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Linkage}.
 */
@Service
@Transactional
public class LinkageService {

    private final Logger log = LoggerFactory.getLogger(LinkageService.class);

    private final LinkageRepository linkageRepository;

    private final LinkageMapper linkageMapper;

    public LinkageService(LinkageRepository linkageRepository, LinkageMapper linkageMapper) {
        this.linkageRepository = linkageRepository;
        this.linkageMapper = linkageMapper;
    }

    /**
     * Save a linkage.
     *
     * @param linkageDTO the entity to save.
     * @return the persisted entity.
     */
    public LinkageDTO save(LinkageDTO linkageDTO) {
        log.debug("Request to save Linkage : {}", linkageDTO);
        Linkage linkage = linkageMapper.toEntity(linkageDTO);
        linkage = linkageRepository.save(linkage);
        return linkageMapper.toDto(linkage);
    }

    /**
     * Get all the linkages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LinkageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Linkages");
        return linkageRepository.findAll(pageable)
            .map(linkageMapper::toDto);
    }


    /**
     * Get one linkage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LinkageDTO> findOne(Long id) {
        log.debug("Request to get Linkage : {}", id);
        return linkageRepository.findById(id)
            .map(linkageMapper::toDto);
    }

    /**
     * Delete the linkage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Linkage : {}", id);
        linkageRepository.deleteById(id);
    }
}
