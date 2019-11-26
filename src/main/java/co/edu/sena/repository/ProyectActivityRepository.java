package co.edu.sena.repository;
import co.edu.sena.domain.ProyectActivity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProyectActivity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProyectActivityRepository extends JpaRepository<ProyectActivity, Long> {

}
