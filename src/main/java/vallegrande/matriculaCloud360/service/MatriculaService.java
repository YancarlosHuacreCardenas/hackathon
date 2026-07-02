package vallegrande.matriculaCloud360.service;

import vallegrande.matriculaCloud360.dto.DashboardComisionDTO;
import vallegrande.matriculaCloud360.dto.MatriculaRequest;
import vallegrande.matriculaCloud360.model.Matricula;

import java.util.List;

public interface MatriculaService {
    List<Matricula> listarActivas();
    List<Matricula> listarEliminadas();
    Matricula listarPorId(String id);
    Matricula guardar(MatriculaRequest request);
    Matricula actualizar(String id, MatriculaRequest request);
    Matricula eliminarLogico(String id);
    Matricula restaurar(String id);
    List<DashboardComisionDTO> obtenerDashboardComisiones();
    byte[] exportarPdf(String id) throws Exception;
}
