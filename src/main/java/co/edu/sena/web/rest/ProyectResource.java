package co.edu.sena.web.rest;

import co.edu.sena.service.ProyectService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.ProyectDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.Proyect}.
 */
@RestController
@RequestMapping("/api")
public class ProyectResource {

    private final Logger log = LoggerFactory.getLogger(ProyectResource.class);

    private static final String ENTITY_NAME = "proyect";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProyectService proyectService;

    public ProyectResource(ProyectService proyectService) {
        this.proyectService = proyectService;
    }

    /**
     * {@code POST  /proyects} : Create a new proyect.
     *
     * @param proyectDTO the proyectDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proyectDTO, or with status {@code 400 (Bad Request)} if the proyect has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/proyects")
    public ResponseEntity<ProyectDTO> createProyect(@Valid @RequestBody ProyectDTO proyectDTO) throws URISyntaxException {
        log.debug("REST request to save Proyect : {}", proyectDTO);
        if (proyectDTO.getId() != null) {
            throw new BadRequestAlertException("A new proyect cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProyectDTO result = proyectService.save(proyectDTO);
        return ResponseEntity.created(new URI("/api/proyects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /proyects} : Updates an existing proyect.
     *
     * @param proyectDTO the proyectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proyectDTO,
     * or with status {@code 400 (Bad Request)} if the proyectDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proyectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/proyects")
    public ResponseEntity<ProyectDTO> updateProyect(@Valid @RequestBody ProyectDTO proyectDTO) throws URISyntaxException {
        log.debug("REST request to update Proyect : {}", proyectDTO);
        if (proyectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProyectDTO result = proyectService.save(proyectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proyectDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /proyects} : get all the proyects.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proyects in body.
     */
    @GetMapping("/proyects")
    public ResponseEntity<List<ProyectDTO>> getAllProyects(Pageable pageable) {
        log.debug("REST request to get a page of Proyects");
        Page<ProyectDTO> page = proyectService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /proyects/:id} : get the "id" proyect.
     *
     * @param id the id of the proyectDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proyectDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/proyects/{id}")
    public ResponseEntity<ProyectDTO> getProyect(@PathVariable Long id) {
        log.debug("REST request to get Proyect : {}", id);
        Optional<ProyectDTO> proyectDTO = proyectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proyectDTO);
    }

    /**
     * {@code DELETE  /proyects/:id} : delete the "id" proyect.
     *
     * @param id the id of the proyectDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/proyects/{id}")
    public ResponseEntity<Void> deleteProyect(@PathVariable Long id) {
        log.debug("REST request to delete Proyect : {}", id);
        proyectService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
