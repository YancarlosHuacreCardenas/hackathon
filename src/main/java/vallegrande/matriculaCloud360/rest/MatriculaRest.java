package vallegrande.matriculaCloud360.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vallegrande.matriculaCloud360.dto.DashboardComisionDTO;
import vallegrande.matriculaCloud360.dto.MatriculaRequest;
import vallegrande.matriculaCloud360.model.Matricula;
import vallegrande.matriculaCloud360.service.MatriculaService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/matricula")
@Tag(name = "Matrículas", description = "Endpoints para Matrícula (US-01, US-02, US-04)")
public class MatriculaRest {

    @Autowired
    private MatriculaService service;

    @Operation(summary = "Listar matrículas activas", description = "Lista todas las matrículas que no han sido borradas lógicamente.")
    @GetMapping
    public List<Matricula> listarActivas() {
        return service.listarActivas();
    }

    @Operation(summary = "Listar matrículas eliminadas", description = "Lista todas las matrículas que están archivadas/borradas lógicamente.")
    @GetMapping("/eliminadas")
    public List<Matricula> listarEliminadas() {
        return service.listarEliminadas();
    }

    @GetMapping("/{id}")
    public Matricula buscarPorId(@PathVariable String id) {
        return service.listarPorId(id);
    }

    @Operation(summary = "Registrar nueva matrícula", description = "Registra una matrícula. Valida duplicados activos (US-01).")
    @PostMapping
    public Matricula guardar(@Valid @RequestBody MatriculaRequest request) {
        return service.guardar(request);
    }

    @Operation(summary = "Actualizar matrícula", description = "Actualiza los datos de una matrícula por ID.")
    @PutMapping("/{id}")
    public Matricula actualizar(@PathVariable String id, @Valid @RequestBody MatriculaRequest request) {
        return service.actualizar(id, request);
    }

    @Operation(summary = "Eliminación lógica", description = "Archiva/elimina lógicamente una matrícula de manera temporal (US-02).")
    @PatchMapping("/eliminar/{id}")
    public Matricula eliminarLogico(@PathVariable String id) {
        return service.eliminarLogico(id);
    }

    @Operation(summary = "Restaurar matrícula", description = "Restaura una matrícula previamente eliminada lógicamente (US-02).")
    @PatchMapping("/restaurar/{id}")
    public Matricula restaurar(@PathVariable String id) {
        return service.restaurar(id);
    }

    @Operation(summary = "Dashboard Gerencial", description = "Obtiene las ventas y comisiones acumuladas agrupadas por promotor (US-04).")
    @GetMapping("/dashboard")
    public List<DashboardComisionDTO> obtenerDashboardComisiones() {
        return service.obtenerDashboardComisiones();
    }

    @Operation(summary = "Exportar Matrícula a PDF", description = "Genera un archivo PDF con la cabecera-detalle de la matrícula especificada.")
    @GetMapping("/{id}/pdf")
    public org.springframework.http.ResponseEntity<byte[]> exportarPdf(@PathVariable String id) throws Exception {
        byte[] pdf = service.exportarPdf(id);
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);
        headers.setContentDisposition(org.springframework.http.ContentDisposition.builder("attachment").filename("matricula_" + id + ".pdf").build());
        return new org.springframework.http.ResponseEntity<>(pdf, headers, org.springframework.http.HttpStatus.OK);
    }
}
