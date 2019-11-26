package co.edu.sena.repository;
import co.edu.sena.domain.InstructorLinking;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InstructorLinking entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstructorLinkingRepository extends JpaRepository<InstructorLinking, Long> {

}
