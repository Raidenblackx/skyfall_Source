package co.edu.sena.web.rest;

import co.edu.sena.service.TypeEnvironmentService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.TypeEnvironmentDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link co.edu.sena.domain.TypeEnvironment}.
 */
@RestController
@RequestMapping("/api")
public class TypeEnvironmentResource {

    private final Logger log = LoggerFactory.getLogger(TypeEnvironmentResource.class);

    private static final String ENTITY_NAME = "typeEnvironment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeEnvironmentService typeEnvironmentService;

    public TypeEnvironmentResource(TypeEnvironmentService typeEnvironmentService) {
        this.typeEnvironmentService = typeEnvironmentService;
    }

    /**
     * {@code POST  /type-environments} : Create a new typeEnvironment.
     *
     * @param typeEnvironmentDTO the typeEnvironmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeEnvironmentDTO, or with status {@code 400 (Bad Request)} if the typeEnvironment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-environments")
    public ResponseEntity<TypeEnvironmentDTO> createTypeEnvironment(@Valid @RequestBody TypeEnvironmentDTO typeEnvironmentDTO) throws URISyntaxException {
        log.debug("REST request to save TypeEnvironment : {}", typeEnvironmentDTO);
        if (typeEnvironmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeEnvironment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeEnvironmentDTO result = typeEnvironmentService.save(typeEnvironmentDTO);
        return ResponseEntity.created(new URI("/api/type-environments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-environments} : Updates an existing typeEnvironment.
     *
     * @param typeEnvironmentDTO the typeEnvironmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeEnvironmentDTO,
     * or with status {@code 400 (Bad Request)} if the typeEnvironmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeEnvironmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-environments")
    public ResponseEntity<TypeEnvironmentDTO> updateTypeEnvironment(@Valid @RequestBody TypeEnvironmentDTO typeEnvironmentDTO) throws URISyntaxException {
        log.debug("REST request to update TypeEnvironment : {}", typeEnvironmentDTO);
        if (typeEnvironmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeEnvironmentDTO result = typeEnvironmentService.save(typeEnvironmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeEnvironmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-environments} : get all the typeEnvironments.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeEnvironments in body.
     */
    @GetMapping("/type-environments")
    public ResponseEntity<List<TypeEnvironmentDTO>> getAllTypeEnvironments(Pageable pageable) {
        log.debug("REST request to get a page of TypeEnvironments");
        Page<TypeEnvironmentDTO> page = typeEnvironmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-environments/:id} : get the "id" typeEnvironment.
     *
     * @param id the id of the typeEnvironmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeEnvironmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-environments/{id}")
    public ResponseEntity<TypeEnvironmentDTO> getTypeEnvironment(@PathVariable Long id) {
        log.debug("REST request to get TypeEnvironment : {}", id);
        Optional<TypeEnvironmentDTO> typeEnvironmentDTO = typeEnvironmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeEnvironmentDTO);
    }

    /**
     * {@code DELETE  /type-environments/:id} : delete the "id" typeEnvironment.
     *
     * @param id the id of the typeEnvironmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-environments/{id}")
    public ResponseEntity<Void> deleteTypeEnvironment(@PathVariable Long id) {
        log.debug("REST request to delete TypeEnvironment : {}", id);
        typeEnvironmentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
