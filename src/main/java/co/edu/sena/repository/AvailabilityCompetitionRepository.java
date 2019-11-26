package co.edu.sena.repository;
import co.edu.sena.domain.AvailabilityCompetition;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AvailabilityCompetition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvailabilityCompetitionRepository extends JpaRepository<AvailabilityCompetition, Long> {

}
