package co.edu.sena.repository;
import co.edu.sena.domain.Proyect;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Proyect entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProyectRepository extends JpaRepository<Proyect, Long> {

}
