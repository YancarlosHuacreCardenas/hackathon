package vallegrande.matriculaCloud360.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vallegrande.matriculaCloud360.model.Curso;
import vallegrande.matriculaCloud360.service.CursoService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/curso")
@Tag(name = "Cursos", description = "Endpoints para Curso")
public class CursoRest {

    @Autowired
    private CursoService service;

    @GetMapping
    public List<Curso> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Curso listarPorId(@PathVariable String id) {
        return service.listarPorId(id);
    }

    @PostMapping
    public Curso guardar(@RequestBody Curso curso) {
        return service.guardar(curso);
    }

    @PutMapping("/{id}")
    public Curso actualizar(@PathVariable String id, @RequestBody Curso curso) {
        return service.actualizar(id, curso);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        service.eliminar(id);
    }
}
