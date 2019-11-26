package co.edu.sena.web.rest;

import co.edu.sena.service.LearningResultService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.LearningResultDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.LearningResult}.
 */
@RestController
@RequestMapping("/api")
public class LearningResultResource {

    private final Logger log = LoggerFactory.getLogger(LearningResultResource.class);

    private static final String ENTITY_NAME = "learningResult";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LearningResultService learningResultService;

    public LearningResultResource(LearningResultService learningResultService) {
        this.learningResultService = learningResultService;
    }

    /**
     * {@code POST  /learning-results} : Create a new learningResult.
     *
     * @param learningResultDTO the learningResultDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new learningResultDTO, or with status {@code 400 (Bad Request)} if the learningResult has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/learning-results")
    public ResponseEntity<LearningResultDTO> createLearningResult(@Valid @RequestBody LearningResultDTO learningResultDTO) throws URISyntaxException {
        log.debug("REST request to save LearningResult : {}", learningResultDTO);
        if (learningResultDTO.getId() != null) {
            throw new BadRequestAlertException("A new learningResult cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LearningResultDTO result = learningResultService.save(learningResultDTO);
        return ResponseEntity.created(new URI("/api/learning-results/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /learning-results} : Updates an existing learningResult.
     *
     * @param learningResultDTO the learningResultDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated learningResultDTO,
     * or with status {@code 400 (Bad Request)} if the learningResultDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the learningResultDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/learning-results")
    public ResponseEntity<LearningResultDTO> updateLearningResult(@Valid @RequestBody LearningResultDTO learningResultDTO) throws URISyntaxException {
        log.debug("REST request to update LearningResult : {}", learningResultDTO);
        if (learningResultDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LearningResultDTO result = learningResultService.save(learningResultDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, learningResultDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /learning-results} : get all the learningResults.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of learningResults in body.
     */
    @GetMapping("/learning-results")
    public ResponseEntity<List<LearningResultDTO>> getAllLearningResults(Pageable pageable) {
        log.debug("REST request to get a page of LearningResults");
        Page<LearningResultDTO> page = learningResultService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /learning-results/:id} : get the "id" learningResult.
     *
     * @param id the id of the learningResultDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the learningResultDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/learning-results/{id}")
    public ResponseEntity<LearningResultDTO> getLearningResult(@PathVariable Long id) {
        log.debug("REST request to get LearningResult : {}", id);
        Optional<LearningResultDTO> learningResultDTO = learningResultService.findOne(id);
        return ResponseUtil.wrapOrNotFound(learningResultDTO);
    }

    /**
     * {@code DELETE  /learning-results/:id} : delete the "id" learningResult.
     *
     * @param id the id of the learningResultDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/learning-results/{id}")
    public ResponseEntity<Void> deleteLearningResult(@PathVariable Long id) {
        log.debug("REST request to delete LearningResult : {}", id);
        learningResultService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
