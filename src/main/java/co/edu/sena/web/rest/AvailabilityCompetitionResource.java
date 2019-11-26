package co.edu.sena.web.rest;

import co.edu.sena.service.AvailabilityCompetitionService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.AvailabilityCompetitionDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.AvailabilityCompetition}.
 */
@RestController
@RequestMapping("/api")
public class AvailabilityCompetitionResource {

    private final Logger log = LoggerFactory.getLogger(AvailabilityCompetitionResource.class);

    private static final String ENTITY_NAME = "availabilityCompetition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AvailabilityCompetitionService availabilityCompetitionService;

    public AvailabilityCompetitionResource(AvailabilityCompetitionService availabilityCompetitionService) {
        this.availabilityCompetitionService = availabilityCompetitionService;
    }

    /**
     * {@code POST  /availability-competitions} : Create a new availabilityCompetition.
     *
     * @param availabilityCompetitionDTO the availabilityCompetitionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new availabilityCompetitionDTO, or with status {@code 400 (Bad Request)} if the availabilityCompetition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/availability-competitions")
    public ResponseEntity<AvailabilityCompetitionDTO> createAvailabilityCompetition(@Valid @RequestBody AvailabilityCompetitionDTO availabilityCompetitionDTO) throws URISyntaxException {
        log.debug("REST request to save AvailabilityCompetition : {}", availabilityCompetitionDTO);
        if (availabilityCompetitionDTO.getId() != null) {
            throw new BadRequestAlertException("A new availabilityCompetition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AvailabilityCompetitionDTO result = availabilityCompetitionService.save(availabilityCompetitionDTO);
        return ResponseEntity.created(new URI("/api/availability-competitions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /availability-competitions} : Updates an existing availabilityCompetition.
     *
     * @param availabilityCompetitionDTO the availabilityCompetitionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated availabilityCompetitionDTO,
     * or with status {@code 400 (Bad Request)} if the availabilityCompetitionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the availabilityCompetitionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/availability-competitions")
    public ResponseEntity<AvailabilityCompetitionDTO> updateAvailabilityCompetition(@Valid @RequestBody AvailabilityCompetitionDTO availabilityCompetitionDTO) throws URISyntaxException {
        log.debug("REST request to update AvailabilityCompetition : {}", availabilityCompetitionDTO);
        if (availabilityCompetitionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AvailabilityCompetitionDTO result = availabilityCompetitionService.save(availabilityCompetitionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, availabilityCompetitionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /availability-competitions} : get all the availabilityCompetitions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of availabilityCompetitions in body.
     */
    @GetMapping("/availability-competitions")
    public ResponseEntity<List<AvailabilityCompetitionDTO>> getAllAvailabilityCompetitions(Pageable pageable) {
        log.debug("REST request to get a page of AvailabilityCompetitions");
        Page<AvailabilityCompetitionDTO> page = availabilityCompetitionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /availability-competitions/:id} : get the "id" availabilityCompetition.
     *
     * @param id the id of the availabilityCompetitionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the availabilityCompetitionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/availability-competitions/{id}")
    public ResponseEntity<AvailabilityCompetitionDTO> getAvailabilityCompetition(@PathVariable Long id) {
        log.debug("REST request to get AvailabilityCompetition : {}", id);
        Optional<AvailabilityCompetitionDTO> availabilityCompetitionDTO = availabilityCompetitionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(availabilityCompetitionDTO);
    }

    /**
     * {@code DELETE  /availability-competitions/:id} : delete the "id" availabilityCompetition.
     *
     * @param id the id of the availabilityCompetitionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/availability-competitions/{id}")
    public ResponseEntity<Void> deleteAvailabilityCompetition(@PathVariable Long id) {
        log.debug("REST request to delete AvailabilityCompetition : {}", id);
        availabilityCompetitionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
