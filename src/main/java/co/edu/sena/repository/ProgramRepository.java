package co.edu.sena.repository;
import co.edu.sena.domain.Program;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Program entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

}
