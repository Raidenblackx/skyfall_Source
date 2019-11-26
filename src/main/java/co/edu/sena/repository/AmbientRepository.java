package co.edu.sena.repository;
import co.edu.sena.domain.Ambient;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Ambient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AmbientRepository extends JpaRepository<Ambient, Long> {

}
