package co.edu.sena.repository;
import co.edu.sena.domain.LimitationEnvironment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LimitationEnvironment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LimitationEnvironmentRepository extends JpaRepository<LimitationEnvironment, Long> {

}
