package co.edu.sena.web.rest;

import co.edu.sena.service.ScheduleVersionService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.ScheduleVersionDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.ScheduleVersion}.
 */
@RestController
@RequestMapping("/api")
public class ScheduleVersionResource {

    private final Logger log = LoggerFactory.getLogger(ScheduleVersionResource.class);

    private static final String ENTITY_NAME = "scheduleVersion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ScheduleVersionService scheduleVersionService;

    public ScheduleVersionResource(ScheduleVersionService scheduleVersionService) {
        this.scheduleVersionService = scheduleVersionService;
    }

    /**
     * {@code POST  /schedule-versions} : Create a new scheduleVersion.
     *
     * @param scheduleVersionDTO the scheduleVersionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new scheduleVersionDTO, or with status {@code 400 (Bad Request)} if the scheduleVersion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/schedule-versions")
    public ResponseEntity<ScheduleVersionDTO> createScheduleVersion(@Valid @RequestBody ScheduleVersionDTO scheduleVersionDTO) throws URISyntaxException {
        log.debug("REST request to save ScheduleVersion : {}", scheduleVersionDTO);
        if (scheduleVersionDTO.getId() != null) {
            throw new BadRequestAlertException("A new scheduleVersion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScheduleVersionDTO result = scheduleVersionService.save(scheduleVersionDTO);
        return ResponseEntity.created(new URI("/api/schedule-versions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /schedule-versions} : Updates an existing scheduleVersion.
     *
     * @param scheduleVersionDTO the scheduleVersionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scheduleVersionDTO,
     * or with status {@code 400 (Bad Request)} if the scheduleVersionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the scheduleVersionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/schedule-versions")
    public ResponseEntity<ScheduleVersionDTO> updateScheduleVersion(@Valid @RequestBody ScheduleVersionDTO scheduleVersionDTO) throws URISyntaxException {
        log.debug("REST request to update ScheduleVersion : {}", scheduleVersionDTO);
        if (scheduleVersionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ScheduleVersionDTO result = scheduleVersionService.save(scheduleVersionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, scheduleVersionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /schedule-versions} : get all the scheduleVersions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of scheduleVersions in body.
     */
    @GetMapping("/schedule-versions")
    public ResponseEntity<List<ScheduleVersionDTO>> getAllScheduleVersions(Pageable pageable) {
        log.debug("REST request to get a page of ScheduleVersions");
        Page<ScheduleVersionDTO> page = scheduleVersionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /schedule-versions/:id} : get the "id" scheduleVersion.
     *
     * @param id the id of the scheduleVersionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the scheduleVersionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/schedule-versions/{id}")
    public ResponseEntity<ScheduleVersionDTO> getScheduleVersion(@PathVariable Long id) {
        log.debug("REST request to get ScheduleVersion : {}", id);
        Optional<ScheduleVersionDTO> scheduleVersionDTO = scheduleVersionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(scheduleVersionDTO);
    }

    /**
     * {@code DELETE  /schedule-versions/:id} : delete the "id" scheduleVersion.
     *
     * @param id the id of the scheduleVersionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/schedule-versions/{id}")
    public ResponseEntity<Void> deleteScheduleVersion(@PathVariable Long id) {
        log.debug("REST request to delete ScheduleVersion : {}", id);
        scheduleVersionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
