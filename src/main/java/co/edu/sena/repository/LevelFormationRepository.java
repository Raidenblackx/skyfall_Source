package co.edu.sena.repository;
import co.edu.sena.domain.LevelFormation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LevelFormation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LevelFormationRepository extends JpaRepository<LevelFormation, Long> {

}
