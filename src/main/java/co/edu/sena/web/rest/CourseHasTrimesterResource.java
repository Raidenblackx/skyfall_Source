package co.edu.sena.web.rest;

import co.edu.sena.service.CourseHasTrimesterService;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import co.edu.sena.service.dto.CourseHasTrimesterDTO;

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
 * REST controller for managing {@link co.edu.sena.domain.CourseHasTrimester}.
 */
@RestController
@RequestMapping("/api")
public class CourseHasTrimesterResource {

    private final Logger log = LoggerFactory.getLogger(CourseHasTrimesterResource.class);

    private static final String ENTITY_NAME = "courseHasTrimester";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CourseHasTrimesterService courseHasTrimesterService;

    public CourseHasTrimesterResource(CourseHasTrimesterService courseHasTrimesterService) {
        this.courseHasTrimesterService = courseHasTrimesterService;
    }

    /**
     * {@code POST  /course-has-trimesters} : Create a new courseHasTrimester.
     *
     * @param courseHasTrimesterDTO the courseHasTrimesterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new courseHasTrimesterDTO, or with status {@code 400 (Bad Request)} if the courseHasTrimester has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/course-has-trimesters")
    public ResponseEntity<CourseHasTrimesterDTO> createCourseHasTrimester(@Valid @RequestBody CourseHasTrimesterDTO courseHasTrimesterDTO) throws URISyntaxException {
        log.debug("REST request to save CourseHasTrimester : {}", courseHasTrimesterDTO);
        if (courseHasTrimesterDTO.getId() != null) {
            throw new BadRequestAlertException("A new courseHasTrimester cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CourseHasTrimesterDTO result = courseHasTrimesterService.save(courseHasTrimesterDTO);
        return ResponseEntity.created(new URI("/api/course-has-trimesters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /course-has-trimesters} : Updates an existing courseHasTrimester.
     *
     * @param courseHasTrimesterDTO the courseHasTrimesterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated courseHasTrimesterDTO,
     * or with status {@code 400 (Bad Request)} if the courseHasTrimesterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the courseHasTrimesterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/course-has-trimesters")
    public ResponseEntity<CourseHasTrimesterDTO> updateCourseHasTrimester(@Valid @RequestBody CourseHasTrimesterDTO courseHasTrimesterDTO) throws URISyntaxException {
        log.debug("REST request to update CourseHasTrimester : {}", courseHasTrimesterDTO);
        if (courseHasTrimesterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CourseHasTrimesterDTO result = courseHasTrimesterService.save(courseHasTrimesterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, courseHasTrimesterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /course-has-trimesters} : get all the courseHasTrimesters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of courseHasTrimesters in body.
     */
    @GetMapping("/course-has-trimesters")
    public ResponseEntity<List<CourseHasTrimesterDTO>> getAllCourseHasTrimesters(Pageable pageable) {
        log.debug("REST request to get a page of CourseHasTrimesters");
        Page<CourseHasTrimesterDTO> page = courseHasTrimesterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /course-has-trimesters/:id} : get the "id" courseHasTrimester.
     *
     * @param id the id of the courseHasTrimesterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the courseHasTrimesterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/course-has-trimesters/{id}")
    public ResponseEntity<CourseHasTrimesterDTO> getCourseHasTrimester(@PathVariable Long id) {
        log.debug("REST request to get CourseHasTrimester : {}", id);
        Optional<CourseHasTrimesterDTO> courseHasTrimesterDTO = courseHasTrimesterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(courseHasTrimesterDTO);
    }

    /**
     * {@code DELETE  /course-has-trimesters/:id} : delete the "id" courseHasTrimester.
     *
     * @param id the id of the courseHasTrimesterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/course-has-trimesters/{id}")
    public ResponseEntity<Void> deleteCourseHasTrimester(@PathVariable Long id) {
        log.debug("REST request to delete CourseHasTrimester : {}", id);
        courseHasTrimesterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
