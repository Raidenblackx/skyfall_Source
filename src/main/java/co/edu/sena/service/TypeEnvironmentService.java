package co.edu.sena.service;

import co.edu.sena.domain.TypeEnvironment;
import co.edu.sena.repository.TypeEnvironmentRepository;
import co.edu.sena.service.dto.TypeEnvironmentDTO;
import co.edu.sena.service.mapper.TypeEnvironmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeEnvironment}.
 */
@Service
@Transactional
public class TypeEnvironmentService {

    private final Logger log = LoggerFactory.getLogger(TypeEnvironmentService.class);

    private final TypeEnvironmentRepository typeEnvironmentRepository;

    private final TypeEnvironmentMapper typeEnvironmentMapper;

    public TypeEnvironmentService(TypeEnvironmentRepository typeEnvironmentRepository, TypeEnvironmentMapper typeEnvironmentMapper) {
        this.typeEnvironmentRepository = typeEnvironmentRepository;
        this.typeEnvironmentMapper = typeEnvironmentMapper;
    }

    /**
     * Save a typeEnvironment.
     *
     * @param typeEnvironmentDTO the entity to save.
     * @return the persisted entity.
     */
    public TypeEnvironmentDTO save(TypeEnvironmentDTO typeEnvironmentDTO) {
        log.debug("Request to save TypeEnvironment : {}", typeEnvironmentDTO);
        TypeEnvironment typeEnvironment = typeEnvironmentMapper.toEntity(typeEnvironmentDTO);
        typeEnvironment = typeEnvironmentRepository.save(typeEnvironment);
        return typeEnvironmentMapper.toDto(typeEnvironment);
    }

    /**
     * Get all the typeEnvironments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeEnvironmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeEnvironments");
        return typeEnvironmentRepository.findAll(pageable)
            .map(typeEnvironmentMapper::toDto);
    }


    /**
     * Get one typeEnvironment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TypeEnvironmentDTO> findOne(Long id) {
        log.debug("Request to get TypeEnvironment : {}", id);
        return typeEnvironmentRepository.findById(id)
            .map(typeEnvironmentMapper::toDto);
    }

    /**
     * Delete the typeEnvironment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TypeEnvironment : {}", id);
        typeEnvironmentRepository.deleteById(id);
    }
}
