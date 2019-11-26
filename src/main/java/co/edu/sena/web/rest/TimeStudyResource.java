package co.edu.sena.web.rest;

import co.edu.sena.service.TimeStudyService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.TimeStudyDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.TimeStudy}.
 */
@RestController
@RequestMapping("/api")
public class TimeStudyResource {

    private final Logger log = LoggerFactory.getLogger(TimeStudyResource.class);

    private static final String ENTITY_NAME = "timeStudy";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TimeStudyService timeStudyService;

    public TimeStudyResource(TimeStudyService timeStudyService) {
        this.timeStudyService = timeStudyService;
    }

    /**
     * {@code POST  /time-studies} : Create a new timeStudy.
     *
     * @param timeStudyDTO the timeStudyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new timeStudyDTO, or with status {@code 400 (Bad Request)} if the timeStudy has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/time-studies")
    public ResponseEntity<TimeStudyDTO> createTimeStudy(@Valid @RequestBody TimeStudyDTO timeStudyDTO) throws URISyntaxException {
        log.debug("REST request to save TimeStudy : {}", timeStudyDTO);
        if (timeStudyDTO.getId() != null) {
            throw new BadRequestAlertException("A new timeStudy cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TimeStudyDTO result = timeStudyService.save(timeStudyDTO);
        return ResponseEntity.created(new URI("/api/time-studies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /time-studies} : Updates an existing timeStudy.
     *
     * @param timeStudyDTO the timeStudyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated timeStudyDTO,
     * or with status {@code 400 (Bad Request)} if the timeStudyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the timeStudyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/time-studies")
    public ResponseEntity<TimeStudyDTO> updateTimeStudy(@Valid @RequestBody TimeStudyDTO timeStudyDTO) throws URISyntaxException {
        log.debug("REST request to update TimeStudy : {}", timeStudyDTO);
        if (timeStudyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TimeStudyDTO result = timeStudyService.save(timeStudyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, timeStudyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /time-studies} : get all the timeStudies.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of timeStudies in body.
     */
    @GetMapping("/time-studies")
    public ResponseEntity<List<TimeStudyDTO>> getAllTimeStudies(Pageable pageable) {
        log.debug("REST request to get a page of TimeStudies");
        Page<TimeStudyDTO> page = timeStudyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /time-studies/:id} : get the "id" timeStudy.
     *
     * @param id the id of the timeStudyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the timeStudyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/time-studies/{id}")
    public ResponseEntity<TimeStudyDTO> getTimeStudy(@PathVariable Long id) {
        log.debug("REST request to get TimeStudy : {}", id);
        Optional<TimeStudyDTO> timeStudyDTO = timeStudyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(timeStudyDTO);
    }

    /**
     * {@code DELETE  /time-studies/:id} : delete the "id" timeStudy.
     *
     * @param id the id of the timeStudyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/time-studies/{id}")
    public ResponseEntity<Void> deleteTimeStudy(@PathVariable Long id) {
        log.debug("REST request to delete TimeStudy : {}", id);
        timeStudyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
