package co.edu.sena.repository;
import co.edu.sena.domain.WorkingDay;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WorkingDay entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkingDayRepository extends JpaRepository<WorkingDay, Long> {

}
