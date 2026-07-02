package vallegrande.matriculaCloud360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vallegrande.matriculaCloud360.model.Promotor;

import java.util.List;

public interface PromotorRepository extends JpaRepository<Promotor, String> {

    List<Promotor> findBySedeBase_SedeID(String sedeID);
}
