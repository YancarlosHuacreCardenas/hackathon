package vallegrande.matriculaCloud360.service;

import vallegrande.matriculaCloud360.model.Docente;
import java.util.List;

public interface DocenteService {
    List<Docente> listar();
    Docente listarPorId(Integer id);
    Docente guardar(Docente docente);
    Docente actualizar(Integer id, Docente docente);
    void eliminar(Integer id);
}
