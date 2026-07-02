package vallegrande.matriculaCloud360.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vallegrande.matriculaCloud360.model.CargaDocente;
import vallegrande.matriculaCloud360.model.CargaDocenteId;
import vallegrande.matriculaCloud360.service.CargaDocenteService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cargadocente")
@Tag(name = "Carga Docente", description = "Endpoints para Carga Docente (Asignación)")
public class CargaDocenteRest {

    @Autowired
    private CargaDocenteService service;

    @GetMapping
    public List<CargaDocente> listar() {
        return service.listar();
    }

    @PostMapping("/buscar")
    public CargaDocente buscarPorId(@RequestBody CargaDocenteId id) {
        return service.listarPorId(id);
    }

    @GetMapping("/carrera/{carreraID}/periodo/{periodo}")
    public List<CargaDocente> listarPorCarreraYPeriodo(@PathVariable String carreraID, @PathVariable String periodo) {
        return service.listarPorCarreraYPeriodo(carreraID, periodo);
    }

    @PostMapping
    public CargaDocente guardar(@RequestBody CargaDocente cargaDocente) {
        return service.guardar(cargaDocente);
    }

    @PostMapping("/actualizar")
    public CargaDocente actualizar(@RequestBody CargaDocente cargaDocente) {
        return service.actualizar(cargaDocente.getId(), cargaDocente);
    }

    @PostMapping("/eliminar")
    public void eliminar(@RequestBody CargaDocenteId id) {
        service.eliminar(id);
    }
}
