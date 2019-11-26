package co.edu.sena.web.rest;

import co.edu.sena.service.LimitationEnvironmentService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.LimitationEnvironmentDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.LimitationEnvironment}.
 */
@RestController
@RequestMapping("/api")
public class LimitationEnvironmentResource {

    private final Logger log = LoggerFactory.getLogger(LimitationEnvironmentResource.class);

    private static final String ENTITY_NAME = "limitationEnvironment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LimitationEnvironmentService limitationEnvironmentService;

    public LimitationEnvironmentResource(LimitationEnvironmentService limitationEnvironmentService) {
        this.limitationEnvironmentService = limitationEnvironmentService;
    }

    /**
     * {@code POST  /limitation-environments} : Create a new limitationEnvironment.
     *
     * @param limitationEnvironmentDTO the limitationEnvironmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new limitationEnvironmentDTO, or with status {@code 400 (Bad Request)} if the limitationEnvironment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/limitation-environments")
    public ResponseEntity<LimitationEnvironmentDTO> createLimitationEnvironment(@Valid @RequestBody LimitationEnvironmentDTO limitationEnvironmentDTO) throws URISyntaxException {
        log.debug("REST request to save LimitationEnvironment : {}", limitationEnvironmentDTO);
        if (limitationEnvironmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new limitationEnvironment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LimitationEnvironmentDTO result = limitationEnvironmentService.save(limitationEnvironmentDTO);
        return ResponseEntity.created(new URI("/api/limitation-environments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /limitation-environments} : Updates an existing limitationEnvironment.
     *
     * @param limitationEnvironmentDTO the limitationEnvironmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated limitationEnvironmentDTO,
     * or with status {@code 400 (Bad Request)} if the limitationEnvironmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the limitationEnvironmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/limitation-environments")
    public ResponseEntity<LimitationEnvironmentDTO> updateLimitationEnvironment(@Valid @RequestBody LimitationEnvironmentDTO limitationEnvironmentDTO) throws URISyntaxException {
        log.debug("REST request to update LimitationEnvironment : {}", limitationEnvironmentDTO);
        if (limitationEnvironmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LimitationEnvironmentDTO result = limitationEnvironmentService.save(limitationEnvironmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, limitationEnvironmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /limitation-environments} : get all the limitationEnvironments.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of limitationEnvironments in body.
     */
    @GetMapping("/limitation-environments")
    public ResponseEntity<List<LimitationEnvironmentDTO>> getAllLimitationEnvironments(Pageable pageable) {
        log.debug("REST request to get a page of LimitationEnvironments");
        Page<LimitationEnvironmentDTO> page = limitationEnvironmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /limitation-environments/:id} : get the "id" limitationEnvironment.
     *
     * @param id the id of the limitationEnvironmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the limitationEnvironmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/limitation-environments/{id}")
    public ResponseEntity<LimitationEnvironmentDTO> getLimitationEnvironment(@PathVariable Long id) {
        log.debug("REST request to get LimitationEnvironment : {}", id);
        Optional<LimitationEnvironmentDTO> limitationEnvironmentDTO = limitationEnvironmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(limitationEnvironmentDTO);
    }

    /**
     * {@code DELETE  /limitation-environments/:id} : delete the "id" limitationEnvironment.
     *
     * @param id the id of the limitationEnvironmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/limitation-environments/{id}")
    public ResponseEntity<Void> deleteLimitationEnvironment(@PathVariable Long id) {
        log.debug("REST request to delete LimitationEnvironment : {}", id);
        limitationEnvironmentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
