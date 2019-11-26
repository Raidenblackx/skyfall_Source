package co.edu.sena.repository;
import co.edu.sena.domain.TimeStudy;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TimeStudy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TimeStudyRepository extends JpaRepository<TimeStudy, Long> {

}
