package co.edu.sena.repository;
import co.edu.sena.domain.ScheduleAvailability;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ScheduleAvailability entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScheduleAvailabilityRepository extends JpaRepository<ScheduleAvailability, Long> {

}
