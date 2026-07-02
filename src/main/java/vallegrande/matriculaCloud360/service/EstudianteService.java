package vallegrande.matriculaCloud360.service;

import vallegrande.matriculaCloud360.model.Estudiante;
import java.util.List;

public interface EstudianteService {
    List<Estudiante> listar();
    Estudiante listarPorId(String dni);
    List<Estudiante> buscarPorApellidos(String apellidos);
    Estudiante guardar(Estudiante estudiante);
    Estudiante actualizar(String dni, Estudiante estudiante);
    void eliminar(String dni);
}
