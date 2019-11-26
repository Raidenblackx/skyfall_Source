package co.edu.sena.web.rest;

import co.edu.sena.service.LevelFormationService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.LevelFormationDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.LevelFormation}.
 */
@RestController
@RequestMapping("/api")
public class LevelFormationResource {

    private final Logger log = LoggerFactory.getLogger(LevelFormationResource.class);

    private static final String ENTITY_NAME = "levelFormation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LevelFormationService levelFormationService;

    public LevelFormationResource(LevelFormationService levelFormationService) {
        this.levelFormationService = levelFormationService;
    }

    /**
     * {@code POST  /level-formations} : Create a new levelFormation.
     *
     * @param levelFormationDTO the levelFormationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new levelFormationDTO, or with status {@code 400 (Bad Request)} if the levelFormation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/level-formations")
    public ResponseEntity<LevelFormationDTO> createLevelFormation(@Valid @RequestBody LevelFormationDTO levelFormationDTO) throws URISyntaxException {
        log.debug("REST request to save LevelFormation : {}", levelFormationDTO);
        if (levelFormationDTO.getId() != null) {
            throw new BadRequestAlertException("A new levelFormation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LevelFormationDTO result = levelFormationService.save(levelFormationDTO);
        return ResponseEntity.created(new URI("/api/level-formations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /level-formations} : Updates an existing levelFormation.
     *
     * @param levelFormationDTO the levelFormationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated levelFormationDTO,
     * or with status {@code 400 (Bad Request)} if the levelFormationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the levelFormationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/level-formations")
    public ResponseEntity<LevelFormationDTO> updateLevelFormation(@Valid @RequestBody LevelFormationDTO levelFormationDTO) throws URISyntaxException {
        log.debug("REST request to update LevelFormation : {}", levelFormationDTO);
        if (levelFormationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LevelFormationDTO result = levelFormationService.save(levelFormationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, levelFormationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /level-formations} : get all the levelFormations.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of levelFormations in body.
     */
    @GetMapping("/level-formations")
    public ResponseEntity<List<LevelFormationDTO>> getAllLevelFormations(Pageable pageable) {
        log.debug("REST request to get a page of LevelFormations");
        Page<LevelFormationDTO> page = levelFormationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /level-formations/:id} : get the "id" levelFormation.
     *
     * @param id the id of the levelFormationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the levelFormationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/level-formations/{id}")
    public ResponseEntity<LevelFormationDTO> getLevelFormation(@PathVariable Long id) {
        log.debug("REST request to get LevelFormation : {}", id);
        Optional<LevelFormationDTO> levelFormationDTO = levelFormationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(levelFormationDTO);
    }

    /**
     * {@code DELETE  /level-formations/:id} : delete the "id" levelFormation.
     *
     * @param id the id of the levelFormationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/level-formations/{id}")
    public ResponseEntity<Void> deleteLevelFormation(@PathVariable Long id) {
        log.debug("REST request to delete LevelFormation : {}", id);
        levelFormationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
