package co.edu.sena.web.rest;

import co.edu.sena.service.CourseStateService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.CourseStateDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.CourseState}.
 */
@RestController
@RequestMapping("/api")
public class CourseStateResource {

    private final Logger log = LoggerFactory.getLogger(CourseStateResource.class);

    private static final String ENTITY_NAME = "courseState";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CourseStateService courseStateService;

    public CourseStateResource(CourseStateService courseStateService) {
        this.courseStateService = courseStateService;
    }

    /**
     * {@code POST  /course-states} : Create a new courseState.
     *
     * @param courseStateDTO the courseStateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new courseStateDTO, or with status {@code 400 (Bad Request)} if the courseState has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/course-states")
    public ResponseEntity<CourseStateDTO> createCourseState(@Valid @RequestBody CourseStateDTO courseStateDTO) throws URISyntaxException {
        log.debug("REST request to save CourseState : {}", courseStateDTO);
        if (courseStateDTO.getId() != null) {
            throw new BadRequestAlertException("A new courseState cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CourseStateDTO result = courseStateService.save(courseStateDTO);
        return ResponseEntity.created(new URI("/api/course-states/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /course-states} : Updates an existing courseState.
     *
     * @param courseStateDTO the courseStateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated courseStateDTO,
     * or with status {@code 400 (Bad Request)} if the courseStateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the courseStateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/course-states")
    public ResponseEntity<CourseStateDTO> updateCourseState(@Valid @RequestBody CourseStateDTO courseStateDTO) throws URISyntaxException {
        log.debug("REST request to update CourseState : {}", courseStateDTO);
        if (courseStateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CourseStateDTO result = courseStateService.save(courseStateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, courseStateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /course-states} : get all the courseStates.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of courseStates in body.
     */
    @GetMapping("/course-states")
    public ResponseEntity<List<CourseStateDTO>> getAllCourseStates(Pageable pageable) {
        log.debug("REST request to get a page of CourseStates");
        Page<CourseStateDTO> page = courseStateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /course-states/:id} : get the "id" courseState.
     *
     * @param id the id of the courseStateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the courseStateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/course-states/{id}")
    public ResponseEntity<CourseStateDTO> getCourseState(@PathVariable Long id) {
        log.debug("REST request to get CourseState : {}", id);
        Optional<CourseStateDTO> courseStateDTO = courseStateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(courseStateDTO);
    }

    /**
     * {@code DELETE  /course-states/:id} : delete the "id" courseState.
     *
     * @param id the id of the courseStateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/course-states/{id}")
    public ResponseEntity<Void> deleteCourseState(@PathVariable Long id) {
        log.debug("REST request to delete CourseState : {}", id);
        courseStateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
