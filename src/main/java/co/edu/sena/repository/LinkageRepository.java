package co.edu.sena.repository;
import co.edu.sena.domain.Linkage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Linkage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LinkageRepository extends JpaRepository<Linkage, Long> {

}
