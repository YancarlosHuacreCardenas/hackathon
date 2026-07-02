package vallegrande.matriculaCloud360.service;

import vallegrande.matriculaCloud360.model.Sede;
import java.util.List;

public interface SedeService {
    List<Sede> listar();
    Sede listarPorId(String id);
    Sede guardar(Sede sede);
    Sede actualizar(String id, Sede sede);
    void eliminar(String id);
}
