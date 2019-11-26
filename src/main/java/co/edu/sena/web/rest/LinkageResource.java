package co.edu.sena.web.rest;

import co.edu.sena.service.LinkageService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.LinkageDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.Linkage}.
 */
@RestController
@RequestMapping("/api")
public class LinkageResource {

    private final Logger log = LoggerFactory.getLogger(LinkageResource.class);

    private static final String ENTITY_NAME = "linkage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LinkageService linkageService;

    public LinkageResource(LinkageService linkageService) {
        this.linkageService = linkageService;
    }

    /**
     * {@code POST  /linkages} : Create a new linkage.
     *
     * @param linkageDTO the linkageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new linkageDTO, or with status {@code 400 (Bad Request)} if the linkage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/linkages")
    public ResponseEntity<LinkageDTO> createLinkage(@Valid @RequestBody LinkageDTO linkageDTO) throws URISyntaxException {
        log.debug("REST request to save Linkage : {}", linkageDTO);
        if (linkageDTO.getId() != null) {
            throw new BadRequestAlertException("A new linkage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LinkageDTO result = linkageService.save(linkageDTO);
        return ResponseEntity.created(new URI("/api/linkages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /linkages} : Updates an existing linkage.
     *
     * @param linkageDTO the linkageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated linkageDTO,
     * or with status {@code 400 (Bad Request)} if the linkageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the linkageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/linkages")
    public ResponseEntity<LinkageDTO> updateLinkage(@Valid @RequestBody LinkageDTO linkageDTO) throws URISyntaxException {
        log.debug("REST request to update Linkage : {}", linkageDTO);
        if (linkageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LinkageDTO result = linkageService.save(linkageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, linkageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /linkages} : get all the linkages.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of linkages in body.
     */
    @GetMapping("/linkages")
    public ResponseEntity<List<LinkageDTO>> getAllLinkages(Pageable pageable) {
        log.debug("REST request to get a page of Linkages");
        Page<LinkageDTO> page = linkageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /linkages/:id} : get the "id" linkage.
     *
     * @param id the id of the linkageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the linkageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/linkages/{id}")
    public ResponseEntity<LinkageDTO> getLinkage(@PathVariable Long id) {
        log.debug("REST request to get Linkage : {}", id);
        Optional<LinkageDTO> linkageDTO = linkageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(linkageDTO);
    }

    /**
     * {@code DELETE  /linkages/:id} : delete the "id" linkage.
     *
     * @param id the id of the linkageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/linkages/{id}")
    public ResponseEntity<Void> deleteLinkage(@PathVariable Long id) {
        log.debug("REST request to delete Linkage : {}", id);
        linkageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
