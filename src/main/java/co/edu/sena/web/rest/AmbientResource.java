package co.edu.sena.web.rest;

import co.edu.sena.service.AmbientService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.AmbientDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.Ambient}.
 */
@RestController
@RequestMapping("/api")
public class AmbientResource {

    private final Logger log = LoggerFactory.getLogger(AmbientResource.class);

    private static final String ENTITY_NAME = "ambient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AmbientService ambientService;

    public AmbientResource(AmbientService ambientService) {
        this.ambientService = ambientService;
    }

    /**
     * {@code POST  /ambients} : Create a new ambient.
     *
     * @param ambientDTO the ambientDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ambientDTO, or with status {@code 400 (Bad Request)} if the ambient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ambients")
    public ResponseEntity<AmbientDTO> createAmbient(@Valid @RequestBody AmbientDTO ambientDTO) throws URISyntaxException {
        log.debug("REST request to save Ambient : {}", ambientDTO);
        if (ambientDTO.getId() != null) {
            throw new BadRequestAlertException("A new ambient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AmbientDTO result = ambientService.save(ambientDTO);
        return ResponseEntity.created(new URI("/api/ambients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ambients} : Updates an existing ambient.
     *
     * @param ambientDTO the ambientDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ambientDTO,
     * or with status {@code 400 (Bad Request)} if the ambientDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ambientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ambients")
    public ResponseEntity<AmbientDTO> updateAmbient(@Valid @RequestBody AmbientDTO ambientDTO) throws URISyntaxException {
        log.debug("REST request to update Ambient : {}", ambientDTO);
        if (ambientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AmbientDTO result = ambientService.save(ambientDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ambientDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ambients} : get all the ambients.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ambients in body.
     */
    @GetMapping("/ambients")
    public ResponseEntity<List<AmbientDTO>> getAllAmbients(Pageable pageable) {
        log.debug("REST request to get a page of Ambients");
        Page<AmbientDTO> page = ambientService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ambients/:id} : get the "id" ambient.
     *
     * @param id the id of the ambientDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ambientDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ambients/{id}")
    public ResponseEntity<AmbientDTO> getAmbient(@PathVariable Long id) {
        log.debug("REST request to get Ambient : {}", id);
        Optional<AmbientDTO> ambientDTO = ambientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ambientDTO);
    }

    /**
     * {@code DELETE  /ambients/:id} : delete the "id" ambient.
     *
     * @param id the id of the ambientDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ambients/{id}")
    public ResponseEntity<Void> deleteAmbient(@PathVariable Long id) {
        log.debug("REST request to delete Ambient : {}", id);
        ambientService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
