package vallegrande.matriculaCloud360.service;

import vallegrande.matriculaCloud360.model.Carrera;
import java.util.List;

public interface CarreraService {
    List<Carrera> listar();
    Carrera listarPorId(String id);
    Carrera guardar(Carrera carrera);
    Carrera actualizar(String id, Carrera carrera);
    void eliminar(String id);
}
