package co.edu.sena.repository;
import co.edu.sena.domain.TrimesterPlanning;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TrimesterPlanning entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrimesterPlanningRepository extends JpaRepository<TrimesterPlanning, Long> {

}
