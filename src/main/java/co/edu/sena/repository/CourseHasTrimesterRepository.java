package co.edu.sena.repository;
import co.edu.sena.domain.CourseHasTrimester;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CourseHasTrimester entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseHasTrimesterRepository extends JpaRepository<CourseHasTrimester, Long> {

}
