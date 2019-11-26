package co.edu.sena.web.rest;

import co.edu.sena.service.CurrentQuarterService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.CurrentQuarterDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.CurrentQuarter}.
 */
@RestController
@RequestMapping("/api")
public class CurrentQuarterResource {

    private final Logger log = LoggerFactory.getLogger(CurrentQuarterResource.class);

    private static final String ENTITY_NAME = "currentQuarter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CurrentQuarterService currentQuarterService;

    public CurrentQuarterResource(CurrentQuarterService currentQuarterService) {
        this.currentQuarterService = currentQuarterService;
    }

    /**
     * {@code POST  /current-quarters} : Create a new currentQuarter.
     *
     * @param currentQuarterDTO the currentQuarterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new currentQuarterDTO, or with status {@code 400 (Bad Request)} if the currentQuarter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/current-quarters")
    public ResponseEntity<CurrentQuarterDTO> createCurrentQuarter(@Valid @RequestBody CurrentQuarterDTO currentQuarterDTO) throws URISyntaxException {
        log.debug("REST request to save CurrentQuarter : {}", currentQuarterDTO);
        if (currentQuarterDTO.getId() != null) {
            throw new BadRequestAlertException("A new currentQuarter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CurrentQuarterDTO result = currentQuarterService.save(currentQuarterDTO);
        return ResponseEntity.created(new URI("/api/current-quarters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /current-quarters} : Updates an existing currentQuarter.
     *
     * @param currentQuarterDTO the currentQuarterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated currentQuarterDTO,
     * or with status {@code 400 (Bad Request)} if the currentQuarterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the currentQuarterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/current-quarters")
    public ResponseEntity<CurrentQuarterDTO> updateCurrentQuarter(@Valid @RequestBody CurrentQuarterDTO currentQuarterDTO) throws URISyntaxException {
        log.debug("REST request to update CurrentQuarter : {}", currentQuarterDTO);
        if (currentQuarterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CurrentQuarterDTO result = currentQuarterService.save(currentQuarterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, currentQuarterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /current-quarters} : get all the currentQuarters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of currentQuarters in body.
     */
    @GetMapping("/current-quarters")
    public ResponseEntity<List<CurrentQuarterDTO>> getAllCurrentQuarters(Pageable pageable) {
        log.debug("REST request to get a page of CurrentQuarters");
        Page<CurrentQuarterDTO> page = currentQuarterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /current-quarters/:id} : get the "id" currentQuarter.
     *
     * @param id the id of the currentQuarterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the currentQuarterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/current-quarters/{id}")
    public ResponseEntity<CurrentQuarterDTO> getCurrentQuarter(@PathVariable Long id) {
        log.debug("REST request to get CurrentQuarter : {}", id);
        Optional<CurrentQuarterDTO> currentQuarterDTO = currentQuarterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(currentQuarterDTO);
    }

    /**
     * {@code DELETE  /current-quarters/:id} : delete the "id" currentQuarter.
     *
     * @param id the id of the currentQuarterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/current-quarters/{id}")
    public ResponseEntity<Void> deleteCurrentQuarter(@PathVariable Long id) {
        log.debug("REST request to delete CurrentQuarter : {}", id);
        currentQuarterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
