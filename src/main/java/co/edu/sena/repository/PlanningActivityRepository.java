package co.edu.sena.repository;
import co.edu.sena.domain.PlanningActivity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanningActivity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanningActivityRepository extends JpaRepository<PlanningActivity, Long> {

}
