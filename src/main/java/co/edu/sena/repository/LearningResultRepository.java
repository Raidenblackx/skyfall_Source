package co.edu.sena.repository;
import co.edu.sena.domain.LearningResult;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LearningResult entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LearningResultRepository extends JpaRepository<LearningResult, Long> {

}
