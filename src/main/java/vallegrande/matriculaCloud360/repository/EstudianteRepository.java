package vallegrande.matriculaCloud360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vallegrande.matriculaCloud360.model.Estudiante;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, String> {

    List<Estudiante> findByApellidosContainingIgnoreCase(String apellidos);

    List<Estudiante> findByNombresContainingIgnoreCase(String nombres);
}
