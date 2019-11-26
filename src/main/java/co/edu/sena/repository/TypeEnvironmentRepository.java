package co.edu.sena.repository;
import co.edu.sena.domain.TypeEnvironment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeEnvironment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeEnvironmentRepository extends JpaRepository<TypeEnvironment, Long> {

}
