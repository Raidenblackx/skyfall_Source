package co.edu.sena.web.rest;

import co.edu.sena.service.ScheduleAvailabilityService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.ScheduleAvailabilityDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.ScheduleAvailability}.
 */
@RestController
@RequestMapping("/api")
public class ScheduleAvailabilityResource {

    private final Logger log = LoggerFactory.getLogger(ScheduleAvailabilityResource.class);

    private static final String ENTITY_NAME = "scheduleAvailability";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ScheduleAvailabilityService scheduleAvailabilityService;

    public ScheduleAvailabilityResource(ScheduleAvailabilityService scheduleAvailabilityService) {
        this.scheduleAvailabilityService = scheduleAvailabilityService;
    }

    /**
     * {@code POST  /schedule-availabilities} : Create a new scheduleAvailability.
     *
     * @param scheduleAvailabilityDTO the scheduleAvailabilityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new scheduleAvailabilityDTO, or with status {@code 400 (Bad Request)} if the scheduleAvailability has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/schedule-availabilities")
    public ResponseEntity<ScheduleAvailabilityDTO> createScheduleAvailability(@Valid @RequestBody ScheduleAvailabilityDTO scheduleAvailabilityDTO) throws URISyntaxException {
        log.debug("REST request to save ScheduleAvailability : {}", scheduleAvailabilityDTO);
        if (scheduleAvailabilityDTO.getId() != null) {
            throw new BadRequestAlertException("A new scheduleAvailability cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScheduleAvailabilityDTO result = scheduleAvailabilityService.save(scheduleAvailabilityDTO);
        return ResponseEntity.created(new URI("/api/schedule-availabilities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /schedule-availabilities} : Updates an existing scheduleAvailability.
     *
     * @param scheduleAvailabilityDTO the scheduleAvailabilityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scheduleAvailabilityDTO,
     * or with status {@code 400 (Bad Request)} if the scheduleAvailabilityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the scheduleAvailabilityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/schedule-availabilities")
    public ResponseEntity<ScheduleAvailabilityDTO> updateScheduleAvailability(@Valid @RequestBody ScheduleAvailabilityDTO scheduleAvailabilityDTO) throws URISyntaxException {
        log.debug("REST request to update ScheduleAvailability : {}", scheduleAvailabilityDTO);
        if (scheduleAvailabilityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ScheduleAvailabilityDTO result = scheduleAvailabilityService.save(scheduleAvailabilityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, scheduleAvailabilityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /schedule-availabilities} : get all the scheduleAvailabilities.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of scheduleAvailabilities in body.
     */
    @GetMapping("/schedule-availabilities")
    public ResponseEntity<List<ScheduleAvailabilityDTO>> getAllScheduleAvailabilities(Pageable pageable) {
        log.debug("REST request to get a page of ScheduleAvailabilities");
        Page<ScheduleAvailabilityDTO> page = scheduleAvailabilityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /schedule-availabilities/:id} : get the "id" scheduleAvailability.
     *
     * @param id the id of the scheduleAvailabilityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the scheduleAvailabilityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/schedule-availabilities/{id}")
    public ResponseEntity<ScheduleAvailabilityDTO> getScheduleAvailability(@PathVariable Long id) {
        log.debug("REST request to get ScheduleAvailability : {}", id);
        Optional<ScheduleAvailabilityDTO> scheduleAvailabilityDTO = scheduleAvailabilityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(scheduleAvailabilityDTO);
    }

    /**
     * {@code DELETE  /schedule-availabilities/:id} : delete the "id" scheduleAvailability.
     *
     * @param id the id of the scheduleAvailabilityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/schedule-availabilities/{id}")
    public ResponseEntity<Void> deleteScheduleAvailability(@PathVariable Long id) {
        log.debug("REST request to delete ScheduleAvailability : {}", id);
        scheduleAvailabilityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
