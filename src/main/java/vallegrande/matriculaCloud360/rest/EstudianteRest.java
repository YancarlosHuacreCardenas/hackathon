package vallegrande.matriculaCloud360.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vallegrande.matriculaCloud360.model.Estudiante;
import vallegrande.matriculaCloud360.service.EstudianteService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/estudiante")
@Tag(name = "Estudiantes", description = "Endpoints para Estudiante")
public class EstudianteRest {

    @Autowired
    private EstudianteService service;

    @GetMapping
    public List<Estudiante> listar() {
        return service.listar();
    }

    @GetMapping("/{dni}")
    public Estudiante listarPorId(@PathVariable String dni) {
        return service.listarPorId(dni);
    }

    @GetMapping("/buscar/apellido/{apellidos}")
    public List<Estudiante> buscarPorApellidos(@PathVariable String apellidos) {
        return service.buscarPorApellidos(apellidos);
    }

    @PostMapping
    public Estudiante guardar(@RequestBody Estudiante estudiante) {
        return service.guardar(estudiante);
    }

    @PutMapping("/{dni}")
    public Estudiante actualizar(@PathVariable String dni, @RequestBody Estudiante estudiante) {
        return service.actualizar(dni, estudiante);
    }

    @DeleteMapping("/{dni}")
    public void eliminar(@PathVariable String dni) {
        service.eliminar(dni);
    }
}
