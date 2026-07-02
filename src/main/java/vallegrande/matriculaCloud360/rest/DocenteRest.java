package vallegrande.matriculaCloud360.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vallegrande.matriculaCloud360.model.Docente;
import vallegrande.matriculaCloud360.service.DocenteService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/docente")
@Tag(name = "Docentes", description = "Endpoints para Docente")
public class DocenteRest {

    @Autowired
    private DocenteService service;

    @GetMapping
    public List<Docente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Docente listarPorId(@PathVariable Integer id) {
        return service.listarPorId(id);
    }

    @PostMapping
    public Docente guardar(@RequestBody Docente docente) {
        return service.guardar(docente);
    }

    @PutMapping("/{id}")
    public Docente actualizar(@PathVariable Integer id, @RequestBody Docente docente) {
        return service.actualizar(id, docente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
