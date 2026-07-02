package vallegrande.matriculaCloud360.service;

import vallegrande.matriculaCloud360.model.Promotor;
import java.util.List;

public interface PromotorService {
    List<Promotor> listar();
    Promotor listarPorId(String dni);
    List<Promotor> listarPorSede(String sedeID);
    Promotor guardar(Promotor promotor);
    Promotor actualizar(String dni, Promotor promotor);
    void eliminar(String dni);
}
