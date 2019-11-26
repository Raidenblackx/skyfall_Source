package co.edu.sena.repository;
import co.edu.sena.domain.InstructorArea;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InstructorArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstructorAreaRepository extends JpaRepository<InstructorArea, Long> {

}
