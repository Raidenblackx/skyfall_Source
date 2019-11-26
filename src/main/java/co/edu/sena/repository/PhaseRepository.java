package co.edu.sena.repository;
import co.edu.sena.domain.Phase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Phase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PhaseRepository extends JpaRepository<Phase, Long> {

}
