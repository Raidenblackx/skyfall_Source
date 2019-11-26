package co.edu.sena.web.rest;

import co.edu.sena.service.SedeService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.SedeDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.Sede}.
 */
@RestController
@RequestMapping("/api")
public class SedeResource {

    private final Logger log = LoggerFactory.getLogger(SedeResource.class);

    private static final String ENTITY_NAME = "sede";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SedeService sedeService;

    public SedeResource(SedeService sedeService) {
        this.sedeService = sedeService;
    }

    /**
     * {@code POST  /sedes} : Create a new sede.
     *
     * @param sedeDTO the sedeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sedeDTO, or with status {@code 400 (Bad Request)} if the sede has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sedes")
    public ResponseEntity<SedeDTO> createSede(@Valid @RequestBody SedeDTO sedeDTO) throws URISyntaxException {
        log.debug("REST request to save Sede : {}", sedeDTO);
        if (sedeDTO.getId() != null) {
            throw new BadRequestAlertException("A new sede cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SedeDTO result = sedeService.save(sedeDTO);
        return ResponseEntity.created(new URI("/api/sedes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sedes} : Updates an existing sede.
     *
     * @param sedeDTO the sedeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sedeDTO,
     * or with status {@code 400 (Bad Request)} if the sedeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sedeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sedes")
    public ResponseEntity<SedeDTO> updateSede(@Valid @RequestBody SedeDTO sedeDTO) throws URISyntaxException {
        log.debug("REST request to update Sede : {}", sedeDTO);
        if (sedeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SedeDTO result = sedeService.save(sedeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sedeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sedes} : get all the sedes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sedes in body.
     */
    @GetMapping("/sedes")
    public ResponseEntity<List<SedeDTO>> getAllSedes(Pageable pageable) {
        log.debug("REST request to get a page of Sedes");
        Page<SedeDTO> page = sedeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sedes/:id} : get the "id" sede.
     *
     * @param id the id of the sedeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sedeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sedes/{id}")
    public ResponseEntity<SedeDTO> getSede(@PathVariable Long id) {
        log.debug("REST request to get Sede : {}", id);
        Optional<SedeDTO> sedeDTO = sedeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sedeDTO);
    }

    /**
     * {@code DELETE  /sedes/:id} : delete the "id" sede.
     *
     * @param id the id of the sedeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sedes/{id}")
    public ResponseEntity<Void> deleteSede(@PathVariable Long id) {
        log.debug("REST request to delete Sede : {}", id);
        sedeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
