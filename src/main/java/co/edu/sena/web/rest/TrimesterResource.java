package co.edu.sena.web.rest;

import co.edu.sena.service.TrimesterService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.TrimesterDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.Trimester}.
 */
@RestController
@RequestMapping("/api")
public class TrimesterResource {

    private final Logger log = LoggerFactory.getLogger(TrimesterResource.class);

    private static final String ENTITY_NAME = "trimester";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TrimesterService trimesterService;

    public TrimesterResource(TrimesterService trimesterService) {
        this.trimesterService = trimesterService;
    }

    /**
     * {@code POST  /trimesters} : Create a new trimester.
     *
     * @param trimesterDTO the trimesterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new trimesterDTO, or with status {@code 400 (Bad Request)} if the trimester has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trimesters")
    public ResponseEntity<TrimesterDTO> createTrimester(@Valid @RequestBody TrimesterDTO trimesterDTO) throws URISyntaxException {
        log.debug("REST request to save Trimester : {}", trimesterDTO);
        if (trimesterDTO.getId() != null) {
            throw new BadRequestAlertException("A new trimester cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrimesterDTO result = trimesterService.save(trimesterDTO);
        return ResponseEntity.created(new URI("/api/trimesters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /trimesters} : Updates an existing trimester.
     *
     * @param trimesterDTO the trimesterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trimesterDTO,
     * or with status {@code 400 (Bad Request)} if the trimesterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the trimesterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trimesters")
    public ResponseEntity<TrimesterDTO> updateTrimester(@Valid @RequestBody TrimesterDTO trimesterDTO) throws URISyntaxException {
        log.debug("REST request to update Trimester : {}", trimesterDTO);
        if (trimesterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrimesterDTO result = trimesterService.save(trimesterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, trimesterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /trimesters} : get all the trimesters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trimesters in body.
     */
    @GetMapping("/trimesters")
    public ResponseEntity<List<TrimesterDTO>> getAllTrimesters(Pageable pageable) {
        log.debug("REST request to get a page of Trimesters");
        Page<TrimesterDTO> page = trimesterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /trimesters/:id} : get the "id" trimester.
     *
     * @param id the id of the trimesterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the trimesterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trimesters/{id}")
    public ResponseEntity<TrimesterDTO> getTrimester(@PathVariable Long id) {
        log.debug("REST request to get Trimester : {}", id);
        Optional<TrimesterDTO> trimesterDTO = trimesterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trimesterDTO);
    }

    /**
     * {@code DELETE  /trimesters/:id} : delete the "id" trimester.
     *
     * @param id the id of the trimesterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trimesters/{id}")
    public ResponseEntity<Void> deleteTrimester(@PathVariable Long id) {
        log.debug("REST request to delete Trimester : {}", id);
        trimesterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
