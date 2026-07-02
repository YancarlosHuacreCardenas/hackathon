package vallegrande.matriculaCloud360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vallegrande.matriculaCloud360.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, String> {
}
