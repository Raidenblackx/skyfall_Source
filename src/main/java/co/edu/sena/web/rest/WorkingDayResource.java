package co.edu.sena.web.rest;

import co.edu.sena.service.WorkingDayService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.WorkingDayDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.WorkingDay}.
 */
@RestController
@RequestMapping("/api")
public class WorkingDayResource {

    private final Logger log = LoggerFactory.getLogger(WorkingDayResource.class);

    private static final String ENTITY_NAME = "workingDay";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkingDayService workingDayService;

    public WorkingDayResource(WorkingDayService workingDayService) {
        this.workingDayService = workingDayService;
    }

    /**
     * {@code POST  /working-days} : Create a new workingDay.
     *
     * @param workingDayDTO the workingDayDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workingDayDTO, or with status {@code 400 (Bad Request)} if the workingDay has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/working-days")
    public ResponseEntity<WorkingDayDTO> createWorkingDay(@Valid @RequestBody WorkingDayDTO workingDayDTO) throws URISyntaxException {
        log.debug("REST request to save WorkingDay : {}", workingDayDTO);
        if (workingDayDTO.getId() != null) {
            throw new BadRequestAlertException("A new workingDay cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkingDayDTO result = workingDayService.save(workingDayDTO);
        return ResponseEntity.created(new URI("/api/working-days/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /working-days} : Updates an existing workingDay.
     *
     * @param workingDayDTO the workingDayDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workingDayDTO,
     * or with status {@code 400 (Bad Request)} if the workingDayDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workingDayDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/working-days")
    public ResponseEntity<WorkingDayDTO> updateWorkingDay(@Valid @RequestBody WorkingDayDTO workingDayDTO) throws URISyntaxException {
        log.debug("REST request to update WorkingDay : {}", workingDayDTO);
        if (workingDayDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkingDayDTO result = workingDayService.save(workingDayDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, workingDayDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /working-days} : get all the workingDays.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workingDays in body.
     */
    @GetMapping("/working-days")
    public ResponseEntity<List<WorkingDayDTO>> getAllWorkingDays(Pageable pageable) {
        log.debug("REST request to get a page of WorkingDays");
        Page<WorkingDayDTO> page = workingDayService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /working-days/:id} : get the "id" workingDay.
     *
     * @param id the id of the workingDayDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workingDayDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/working-days/{id}")
    public ResponseEntity<WorkingDayDTO> getWorkingDay(@PathVariable Long id) {
        log.debug("REST request to get WorkingDay : {}", id);
        Optional<WorkingDayDTO> workingDayDTO = workingDayService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workingDayDTO);
    }

    /**
     * {@code DELETE  /working-days/:id} : delete the "id" workingDay.
     *
     * @param id the id of the workingDayDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/working-days/{id}")
    public ResponseEntity<Void> deleteWorkingDay(@PathVariable Long id) {
        log.debug("REST request to delete WorkingDay : {}", id);
        workingDayService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
