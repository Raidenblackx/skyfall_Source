package co.edu.sena.repository;
import co.edu.sena.domain.ResultSeen;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ResultSeen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResultSeenRepository extends JpaRepository<ResultSeen, Long> {

}
