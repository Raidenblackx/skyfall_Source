package co.edu.sena.web.rest;

import co.edu.sena.service.TrimesterPlanningService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.TrimesterPlanningDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.TrimesterPlanning}.
 */
@RestController
@RequestMapping("/api")
public class TrimesterPlanningResource {

    private final Logger log = LoggerFactory.getLogger(TrimesterPlanningResource.class);

    private static final String ENTITY_NAME = "trimesterPlanning";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TrimesterPlanningService trimesterPlanningService;

    public TrimesterPlanningResource(TrimesterPlanningService trimesterPlanningService) {
        this.trimesterPlanningService = trimesterPlanningService;
    }

    /**
     * {@code POST  /trimester-plannings} : Create a new trimesterPlanning.
     *
     * @param trimesterPlanningDTO the trimesterPlanningDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new trimesterPlanningDTO, or with status {@code 400 (Bad Request)} if the trimesterPlanning has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trimester-plannings")
    public ResponseEntity<TrimesterPlanningDTO> createTrimesterPlanning(@Valid @RequestBody TrimesterPlanningDTO trimesterPlanningDTO) throws URISyntaxException {
        log.debug("REST request to save TrimesterPlanning : {}", trimesterPlanningDTO);
        if (trimesterPlanningDTO.getId() != null) {
            throw new BadRequestAlertException("A new trimesterPlanning cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrimesterPlanningDTO result = trimesterPlanningService.save(trimesterPlanningDTO);
        return ResponseEntity.created(new URI("/api/trimester-plannings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /trimester-plannings} : Updates an existing trimesterPlanning.
     *
     * @param trimesterPlanningDTO the trimesterPlanningDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trimesterPlanningDTO,
     * or with status {@code 400 (Bad Request)} if the trimesterPlanningDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the trimesterPlanningDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trimester-plannings")
    public ResponseEntity<TrimesterPlanningDTO> updateTrimesterPlanning(@Valid @RequestBody TrimesterPlanningDTO trimesterPlanningDTO) throws URISyntaxException {
        log.debug("REST request to update TrimesterPlanning : {}", trimesterPlanningDTO);
        if (trimesterPlanningDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrimesterPlanningDTO result = trimesterPlanningService.save(trimesterPlanningDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, trimesterPlanningDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /trimester-plannings} : get all the trimesterPlannings.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trimesterPlannings in body.
     */
    @GetMapping("/trimester-plannings")
    public ResponseEntity<List<TrimesterPlanningDTO>> getAllTrimesterPlannings(Pageable pageable) {
        log.debug("REST request to get a page of TrimesterPlannings");
        Page<TrimesterPlanningDTO> page = trimesterPlanningService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /trimester-plannings/:id} : get the "id" trimesterPlanning.
     *
     * @param id the id of the trimesterPlanningDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the trimesterPlanningDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trimester-plannings/{id}")
    public ResponseEntity<TrimesterPlanningDTO> getTrimesterPlanning(@PathVariable Long id) {
        log.debug("REST request to get TrimesterPlanning : {}", id);
        Optional<TrimesterPlanningDTO> trimesterPlanningDTO = trimesterPlanningService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trimesterPlanningDTO);
    }

    /**
     * {@code DELETE  /trimester-plannings/:id} : delete the "id" trimesterPlanning.
     *
     * @param id the id of the trimesterPlanningDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trimester-plannings/{id}")
    public ResponseEntity<Void> deleteTrimesterPlanning(@PathVariable Long id) {
        log.debug("REST request to delete TrimesterPlanning : {}", id);
        trimesterPlanningService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
