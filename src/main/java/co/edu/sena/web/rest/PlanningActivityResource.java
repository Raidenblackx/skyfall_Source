package co.edu.sena.web.rest;

import co.edu.sena.service.PlanningActivityService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.PlanningActivityDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.PlanningActivity}.
 */
@RestController
@RequestMapping("/api")
public class PlanningActivityResource {

    private final Logger log = LoggerFactory.getLogger(PlanningActivityResource.class);

    private static final String ENTITY_NAME = "planningActivity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlanningActivityService planningActivityService;

    public PlanningActivityResource(PlanningActivityService planningActivityService) {
        this.planningActivityService = planningActivityService;
    }

    /**
     * {@code POST  /planning-activities} : Create a new planningActivity.
     *
     * @param planningActivityDTO the planningActivityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new planningActivityDTO, or with status {@code 400 (Bad Request)} if the planningActivity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/planning-activities")
    public ResponseEntity<PlanningActivityDTO> createPlanningActivity(@Valid @RequestBody PlanningActivityDTO planningActivityDTO) throws URISyntaxException {
        log.debug("REST request to save PlanningActivity : {}", planningActivityDTO);
        if (planningActivityDTO.getId() != null) {
            throw new BadRequestAlertException("A new planningActivity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanningActivityDTO result = planningActivityService.save(planningActivityDTO);
        return ResponseEntity.created(new URI("/api/planning-activities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /planning-activities} : Updates an existing planningActivity.
     *
     * @param planningActivityDTO the planningActivityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planningActivityDTO,
     * or with status {@code 400 (Bad Request)} if the planningActivityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the planningActivityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/planning-activities")
    public ResponseEntity<PlanningActivityDTO> updatePlanningActivity(@Valid @RequestBody PlanningActivityDTO planningActivityDTO) throws URISyntaxException {
        log.debug("REST request to update PlanningActivity : {}", planningActivityDTO);
        if (planningActivityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanningActivityDTO result = planningActivityService.save(planningActivityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, planningActivityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /planning-activities} : get all the planningActivities.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of planningActivities in body.
     */
    @GetMapping("/planning-activities")
    public ResponseEntity<List<PlanningActivityDTO>> getAllPlanningActivities(Pageable pageable) {
        log.debug("REST request to get a page of PlanningActivities");
        Page<PlanningActivityDTO> page = planningActivityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /planning-activities/:id} : get the "id" planningActivity.
     *
     * @param id the id of the planningActivityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the planningActivityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/planning-activities/{id}")
    public ResponseEntity<PlanningActivityDTO> getPlanningActivity(@PathVariable Long id) {
        log.debug("REST request to get PlanningActivity : {}", id);
        Optional<PlanningActivityDTO> planningActivityDTO = planningActivityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planningActivityDTO);
    }

    /**
     * {@code DELETE  /planning-activities/:id} : delete the "id" planningActivity.
     *
     * @param id the id of the planningActivityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/planning-activities/{id}")
    public ResponseEntity<Void> deletePlanningActivity(@PathVariable Long id) {
        log.debug("REST request to delete PlanningActivity : {}", id);
        planningActivityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
