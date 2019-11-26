package co.edu.sena.web.rest;

import co.edu.sena.service.ResultSeenService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.ResultSeenDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.ResultSeen}.
 */
@RestController
@RequestMapping("/api")
public class ResultSeenResource {

    private final Logger log = LoggerFactory.getLogger(ResultSeenResource.class);

    private static final String ENTITY_NAME = "resultSeen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResultSeenService resultSeenService;

    public ResultSeenResource(ResultSeenService resultSeenService) {
        this.resultSeenService = resultSeenService;
    }

    /**
     * {@code POST  /result-seens} : Create a new resultSeen.
     *
     * @param resultSeenDTO the resultSeenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resultSeenDTO, or with status {@code 400 (Bad Request)} if the resultSeen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/result-seens")
    public ResponseEntity<ResultSeenDTO> createResultSeen(@Valid @RequestBody ResultSeenDTO resultSeenDTO) throws URISyntaxException {
        log.debug("REST request to save ResultSeen : {}", resultSeenDTO);
        if (resultSeenDTO.getId() != null) {
            throw new BadRequestAlertException("A new resultSeen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResultSeenDTO result = resultSeenService.save(resultSeenDTO);
        return ResponseEntity.created(new URI("/api/result-seens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /result-seens} : Updates an existing resultSeen.
     *
     * @param resultSeenDTO the resultSeenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resultSeenDTO,
     * or with status {@code 400 (Bad Request)} if the resultSeenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resultSeenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/result-seens")
    public ResponseEntity<ResultSeenDTO> updateResultSeen(@Valid @RequestBody ResultSeenDTO resultSeenDTO) throws URISyntaxException {
        log.debug("REST request to update ResultSeen : {}", resultSeenDTO);
        if (resultSeenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResultSeenDTO result = resultSeenService.save(resultSeenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resultSeenDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /result-seens} : get all the resultSeens.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resultSeens in body.
     */
    @GetMapping("/result-seens")
    public ResponseEntity<List<ResultSeenDTO>> getAllResultSeens(Pageable pageable) {
        log.debug("REST request to get a page of ResultSeens");
        Page<ResultSeenDTO> page = resultSeenService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /result-seens/:id} : get the "id" resultSeen.
     *
     * @param id the id of the resultSeenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resultSeenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/result-seens/{id}")
    public ResponseEntity<ResultSeenDTO> getResultSeen(@PathVariable Long id) {
        log.debug("REST request to get ResultSeen : {}", id);
        Optional<ResultSeenDTO> resultSeenDTO = resultSeenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resultSeenDTO);
    }

    /**
     * {@code DELETE  /result-seens/:id} : delete the "id" resultSeen.
     *
     * @param id the id of the resultSeenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/result-seens/{id}")
    public ResponseEntity<Void> deleteResultSeen(@PathVariable Long id) {
        log.debug("REST request to delete ResultSeen : {}", id);
        resultSeenService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
