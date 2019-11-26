package co.edu.sena.web.rest;

import co.edu.sena.service.JourneyInstructorService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.JourneyInstructorDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.JourneyInstructor}.
 */
@RestController
@RequestMapping("/api")
public class JourneyInstructorResource {

    private final Logger log = LoggerFactory.getLogger(JourneyInstructorResource.class);

    private static final String ENTITY_NAME = "journeyInstructor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JourneyInstructorService journeyInstructorService;

    public JourneyInstructorResource(JourneyInstructorService journeyInstructorService) {
        this.journeyInstructorService = journeyInstructorService;
    }

    /**
     * {@code POST  /journey-instructors} : Create a new journeyInstructor.
     *
     * @param journeyInstructorDTO the journeyInstructorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new journeyInstructorDTO, or with status {@code 400 (Bad Request)} if the journeyInstructor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/journey-instructors")
    public ResponseEntity<JourneyInstructorDTO> createJourneyInstructor(@Valid @RequestBody JourneyInstructorDTO journeyInstructorDTO) throws URISyntaxException {
        log.debug("REST request to save JourneyInstructor : {}", journeyInstructorDTO);
        if (journeyInstructorDTO.getId() != null) {
            throw new BadRequestAlertException("A new journeyInstructor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JourneyInstructorDTO result = journeyInstructorService.save(journeyInstructorDTO);
        return ResponseEntity.created(new URI("/api/journey-instructors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /journey-instructors} : Updates an existing journeyInstructor.
     *
     * @param journeyInstructorDTO the journeyInstructorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated journeyInstructorDTO,
     * or with status {@code 400 (Bad Request)} if the journeyInstructorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the journeyInstructorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/journey-instructors")
    public ResponseEntity<JourneyInstructorDTO> updateJourneyInstructor(@Valid @RequestBody JourneyInstructorDTO journeyInstructorDTO) throws URISyntaxException {
        log.debug("REST request to update JourneyInstructor : {}", journeyInstructorDTO);
        if (journeyInstructorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JourneyInstructorDTO result = journeyInstructorService.save(journeyInstructorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, journeyInstructorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /journey-instructors} : get all the journeyInstructors.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of journeyInstructors in body.
     */
    @GetMapping("/journey-instructors")
    public ResponseEntity<List<JourneyInstructorDTO>> getAllJourneyInstructors(Pageable pageable) {
        log.debug("REST request to get a page of JourneyInstructors");
        Page<JourneyInstructorDTO> page = journeyInstructorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /journey-instructors/:id} : get the "id" journeyInstructor.
     *
     * @param id the id of the journeyInstructorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the journeyInstructorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/journey-instructors/{id}")
    public ResponseEntity<JourneyInstructorDTO> getJourneyInstructor(@PathVariable Long id) {
        log.debug("REST request to get JourneyInstructor : {}", id);
        Optional<JourneyInstructorDTO> journeyInstructorDTO = journeyInstructorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(journeyInstructorDTO);
    }

    /**
     * {@code DELETE  /journey-instructors/:id} : delete the "id" journeyInstructor.
     *
     * @param id the id of the journeyInstructorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/journey-instructors/{id}")
    public ResponseEntity<Void> deleteJourneyInstructor(@PathVariable Long id) {
        log.debug("REST request to delete JourneyInstructor : {}", id);
        journeyInstructorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
