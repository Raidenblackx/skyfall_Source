package co.edu.sena.repository;
import co.edu.sena.domain.CourseState;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CourseState entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseStateRepository extends JpaRepository<CourseState, Long> {

}
