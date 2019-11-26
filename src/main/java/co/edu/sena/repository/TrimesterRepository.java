package co.edu.sena.repository;
import co.edu.sena.domain.Trimester;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Trimester entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrimesterRepository extends JpaRepository<Trimester, Long> {

}
