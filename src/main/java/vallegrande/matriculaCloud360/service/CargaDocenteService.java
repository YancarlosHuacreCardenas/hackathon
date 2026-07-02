package vallegrande.matriculaCloud360.service;

import vallegrande.matriculaCloud360.model.CargaDocente;
import vallegrande.matriculaCloud360.model.CargaDocenteId;
import java.util.List;

public interface CargaDocenteService {
    List<CargaDocente> listar();
    CargaDocente listarPorId(CargaDocenteId id);
    List<CargaDocente> listarPorCarreraYPeriodo(String carreraID, String periodo);
    List<CargaDocente> listarPorPeriodo(String periodo);
    CargaDocente guardar(CargaDocente cargaDocente);
    CargaDocente actualizar(CargaDocenteId id, CargaDocente cargaDocente);
    void eliminar(CargaDocenteId id);
}
