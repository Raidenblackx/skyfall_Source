package co.edu.sena.repository;
import co.edu.sena.domain.CurrentQuarter;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CurrentQuarter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CurrentQuarterRepository extends JpaRepository<CurrentQuarter, Long> {

}
