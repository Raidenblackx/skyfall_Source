package co.edu.sena.web.rest;

import co.edu.sena.service.ProyectActivityService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.ProyectActivityDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.ProyectActivity}.
 */
@RestController
@RequestMapping("/api")
public class ProyectActivityResource {

    private final Logger log = LoggerFactory.getLogger(ProyectActivityResource.class);

    private static final String ENTITY_NAME = "proyectActivity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProyectActivityService proyectActivityService;

    public ProyectActivityResource(ProyectActivityService proyectActivityService) {
        this.proyectActivityService = proyectActivityService;
    }

    /**
     * {@code POST  /proyect-activities} : Create a new proyectActivity.
     *
     * @param proyectActivityDTO the proyectActivityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proyectActivityDTO, or with status {@code 400 (Bad Request)} if the proyectActivity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/proyect-activities")
    public ResponseEntity<ProyectActivityDTO> createProyectActivity(@Valid @RequestBody ProyectActivityDTO proyectActivityDTO) throws URISyntaxException {
        log.debug("REST request to save ProyectActivity : {}", proyectActivityDTO);
        if (proyectActivityDTO.getId() != null) {
            throw new BadRequestAlertException("A new proyectActivity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProyectActivityDTO result = proyectActivityService.save(proyectActivityDTO);
        return ResponseEntity.created(new URI("/api/proyect-activities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /proyect-activities} : Updates an existing proyectActivity.
     *
     * @param proyectActivityDTO the proyectActivityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proyectActivityDTO,
     * or with status {@code 400 (Bad Request)} if the proyectActivityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proyectActivityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/proyect-activities")
    public ResponseEntity<ProyectActivityDTO> updateProyectActivity(@Valid @RequestBody ProyectActivityDTO proyectActivityDTO) throws URISyntaxException {
        log.debug("REST request to update ProyectActivity : {}", proyectActivityDTO);
        if (proyectActivityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProyectActivityDTO result = proyectActivityService.save(proyectActivityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proyectActivityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /proyect-activities} : get all the proyectActivities.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proyectActivities in body.
     */
    @GetMapping("/proyect-activities")
    public ResponseEntity<List<ProyectActivityDTO>> getAllProyectActivities(Pageable pageable) {
        log.debug("REST request to get a page of ProyectActivities");
        Page<ProyectActivityDTO> page = proyectActivityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /proyect-activities/:id} : get the "id" proyectActivity.
     *
     * @param id the id of the proyectActivityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proyectActivityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/proyect-activities/{id}")
    public ResponseEntity<ProyectActivityDTO> getProyectActivity(@PathVariable Long id) {
        log.debug("REST request to get ProyectActivity : {}", id);
        Optional<ProyectActivityDTO> proyectActivityDTO = proyectActivityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proyectActivityDTO);
    }

    /**
     * {@code DELETE  /proyect-activities/:id} : delete the "id" proyectActivity.
     *
     * @param id the id of the proyectActivityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/proyect-activities/{id}")
    public ResponseEntity<Void> deleteProyectActivity(@PathVariable Long id) {
        log.debug("REST request to delete ProyectActivity : {}", id);
        proyectActivityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
