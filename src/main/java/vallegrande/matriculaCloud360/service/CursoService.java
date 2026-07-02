package vallegrande.matriculaCloud360.service;

import vallegrande.matriculaCloud360.model.Curso;
import java.util.List;

public interface CursoService {
    List<Curso> listar();
    Curso listarPorId(String id);
    Curso guardar(Curso curso);
    Curso actualizar(String id, Curso curso);
    void eliminar(String id);
}
