package co.edu.sena.web.rest;

import co.edu.sena.service.InstructorAreaService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.InstructorAreaDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.InstructorArea}.
 */
@RestController
@RequestMapping("/api")
public class InstructorAreaResource {

    private final Logger log = LoggerFactory.getLogger(InstructorAreaResource.class);

    private static final String ENTITY_NAME = "instructorArea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InstructorAreaService instructorAreaService;

    public InstructorAreaResource(InstructorAreaService instructorAreaService) {
        this.instructorAreaService = instructorAreaService;
    }

    /**
     * {@code POST  /instructor-areas} : Create a new instructorArea.
     *
     * @param instructorAreaDTO the instructorAreaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new instructorAreaDTO, or with status {@code 400 (Bad Request)} if the instructorArea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/instructor-areas")
    public ResponseEntity<InstructorAreaDTO> createInstructorArea(@Valid @RequestBody InstructorAreaDTO instructorAreaDTO) throws URISyntaxException {
        log.debug("REST request to save InstructorArea : {}", instructorAreaDTO);
        if (instructorAreaDTO.getId() != null) {
            throw new BadRequestAlertException("A new instructorArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InstructorAreaDTO result = instructorAreaService.save(instructorAreaDTO);
        return ResponseEntity.created(new URI("/api/instructor-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /instructor-areas} : Updates an existing instructorArea.
     *
     * @param instructorAreaDTO the instructorAreaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated instructorAreaDTO,
     * or with status {@code 400 (Bad Request)} if the instructorAreaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the instructorAreaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/instructor-areas")
    public ResponseEntity<InstructorAreaDTO> updateInstructorArea(@Valid @RequestBody InstructorAreaDTO instructorAreaDTO) throws URISyntaxException {
        log.debug("REST request to update InstructorArea : {}", instructorAreaDTO);
        if (instructorAreaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InstructorAreaDTO result = instructorAreaService.save(instructorAreaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, instructorAreaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /instructor-areas} : get all the instructorAreas.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of instructorAreas in body.
     */
    @GetMapping("/instructor-areas")
    public ResponseEntity<List<InstructorAreaDTO>> getAllInstructorAreas(Pageable pageable) {
        log.debug("REST request to get a page of InstructorAreas");
        Page<InstructorAreaDTO> page = instructorAreaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /instructor-areas/:id} : get the "id" instructorArea.
     *
     * @param id the id of the instructorAreaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the instructorAreaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/instructor-areas/{id}")
    public ResponseEntity<InstructorAreaDTO> getInstructorArea(@PathVariable Long id) {
        log.debug("REST request to get InstructorArea : {}", id);
        Optional<InstructorAreaDTO> instructorAreaDTO = instructorAreaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(instructorAreaDTO);
    }

    /**
     * {@code DELETE  /instructor-areas/:id} : delete the "id" instructorArea.
     *
     * @param id the id of the instructorAreaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/instructor-areas/{id}")
    public ResponseEntity<Void> deleteInstructorArea(@PathVariable Long id) {
        log.debug("REST request to delete InstructorArea : {}", id);
        instructorAreaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
