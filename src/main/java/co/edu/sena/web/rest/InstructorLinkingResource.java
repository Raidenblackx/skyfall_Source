package co.edu.sena.web.rest;

import co.edu.sena.service.InstructorLinkingService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.InstructorLinkingDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.InstructorLinking}.
 */
@RestController
@RequestMapping("/api")
public class InstructorLinkingResource {

    private final Logger log = LoggerFactory.getLogger(InstructorLinkingResource.class);

    private static final String ENTITY_NAME = "instructorLinking";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InstructorLinkingService instructorLinkingService;

    public InstructorLinkingResource(InstructorLinkingService instructorLinkingService) {
        this.instructorLinkingService = instructorLinkingService;
    }

    /**
     * {@code POST  /instructor-linkings} : Create a new instructorLinking.
     *
     * @param instructorLinkingDTO the instructorLinkingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new instructorLinkingDTO, or with status {@code 400 (Bad Request)} if the instructorLinking has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/instructor-linkings")
    public ResponseEntity<InstructorLinkingDTO> createInstructorLinking(@Valid @RequestBody InstructorLinkingDTO instructorLinkingDTO) throws URISyntaxException {
        log.debug("REST request to save InstructorLinking : {}", instructorLinkingDTO);
        if (instructorLinkingDTO.getId() != null) {
            throw new BadRequestAlertException("A new instructorLinking cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InstructorLinkingDTO result = instructorLinkingService.save(instructorLinkingDTO);
        return ResponseEntity.created(new URI("/api/instructor-linkings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /instructor-linkings} : Updates an existing instructorLinking.
     *
     * @param instructorLinkingDTO the instructorLinkingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated instructorLinkingDTO,
     * or with status {@code 400 (Bad Request)} if the instructorLinkingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the instructorLinkingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/instructor-linkings")
    public ResponseEntity<InstructorLinkingDTO> updateInstructorLinking(@Valid @RequestBody InstructorLinkingDTO instructorLinkingDTO) throws URISyntaxException {
        log.debug("REST request to update InstructorLinking : {}", instructorLinkingDTO);
        if (instructorLinkingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InstructorLinkingDTO result = instructorLinkingService.save(instructorLinkingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, instructorLinkingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /instructor-linkings} : get all the instructorLinkings.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of instructorLinkings in body.
     */
    @GetMapping("/instructor-linkings")
    public ResponseEntity<List<InstructorLinkingDTO>> getAllInstructorLinkings(Pageable pageable) {
        log.debug("REST request to get a page of InstructorLinkings");
        Page<InstructorLinkingDTO> page = instructorLinkingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /instructor-linkings/:id} : get the "id" instructorLinking.
     *
     * @param id the id of the instructorLinkingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the instructorLinkingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/instructor-linkings/{id}")
    public ResponseEntity<InstructorLinkingDTO> getInstructorLinking(@PathVariable Long id) {
        log.debug("REST request to get InstructorLinking : {}", id);
        Optional<InstructorLinkingDTO> instructorLinkingDTO = instructorLinkingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(instructorLinkingDTO);
    }

    /**
     * {@code DELETE  /instructor-linkings/:id} : delete the "id" instructorLinking.
     *
     * @param id the id of the instructorLinkingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/instructor-linkings/{id}")
    public ResponseEntity<Void> deleteInstructorLinking(@PathVariable Long id) {
        log.debug("REST request to delete InstructorLinking : {}", id);
        instructorLinkingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
