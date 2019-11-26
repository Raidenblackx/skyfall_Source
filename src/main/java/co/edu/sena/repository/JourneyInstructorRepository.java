package co.edu.sena.repository;
import co.edu.sena.domain.JourneyInstructor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the JourneyInstructor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JourneyInstructorRepository extends JpaRepository<JourneyInstructor, Long> {

}
