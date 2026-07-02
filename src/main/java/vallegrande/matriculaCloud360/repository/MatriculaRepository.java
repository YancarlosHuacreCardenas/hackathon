package vallegrande.matriculaCloud360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vallegrande.matriculaCloud360.model.Matricula;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, String> {

    // Listar solo matrículas activas (Soft Delete: deleted_at IS NULL)
    List<Matricula> findByDeletedAtIsNull();

    // Listar matrículas eliminadas (borrado lógico)
    List<Matricula> findByDeletedAtIsNotNull();

    // Buscar por periodo (activas)
    @Query("SELECT m FROM Matricula m WHERE m.periodo = :periodo AND m.deletedAt IS NULL")
    List<Matricula> findActivasByPeriodo(@Param("periodo") String periodo);

    // Buscar por carrera (activas)
    @Query("SELECT m FROM Matricula m WHERE m.carrera.carreraID = :carreraID AND m.deletedAt IS NULL")
    List<Matricula> findActivasByCarrera(@Param("carreraID") String carreraID);

    // Verificar si ya existe matrícula activa (evitar duplicados — replica el índice UX del SQL)
    @Query("SELECT COUNT(m) > 0 FROM Matricula m WHERE m.estudiante.dni = :dni AND m.carrera.carreraID = :carreraID AND m.periodo = :periodo AND m.deletedAt IS NULL")
    boolean existeMatriculaActiva(@Param("dni") String dni, @Param("carreraID") String carreraID, @Param("periodo") String periodo);

    // Dashboard gerencial — replica la vista V_Dashboard_Comisiones del SQL (US-04)
    @Query(value = """
        SELECT p.DNI                         AS codigoPromotor,
               p.NombresApellidos            AS promotor,
               s.NombreSede                  AS sede,
               COUNT(m.MatriculaID)          AS totalMatriculasActivas,
               COALESCE(SUM(m.Monto), 0)     AS ventasTotales,
               COALESCE(SUM(m.Monto), 0)*0.10 AS comisionesAcumuladas
        FROM   Promotores p
        JOIN   Sedes s ON p.SedeBaseID = s.SedeID
        LEFT JOIN Matriculas m ON p.DNI = m.PromotorDNI AND m.deleted_at IS NULL
        GROUP BY p.DNI, p.NombresApellidos, s.NombreSede
        """, nativeQuery = true)
    List<Object[]> getDashboardComisiones();
}
