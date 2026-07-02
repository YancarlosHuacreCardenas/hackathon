package vallegrande.matriculaCloud360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vallegrande.matriculaCloud360.model.CargaDocente;
import vallegrande.matriculaCloud360.model.CargaDocenteId;

import java.util.List;

public interface CargaDocenteRepository extends JpaRepository<CargaDocente, CargaDocenteId> {

    @Query("SELECT c FROM CargaDocente c WHERE c.id.carreraID = :carreraID AND c.id.periodo = :periodo")
    List<CargaDocente> findByCarreraYPeriodo(@Param("carreraID") String carreraID, @Param("periodo") String periodo);

    @Query("SELECT c FROM CargaDocente c WHERE c.id.periodo = :periodo")
    List<CargaDocente> findByPeriodo(@Param("periodo") String periodo);
}
