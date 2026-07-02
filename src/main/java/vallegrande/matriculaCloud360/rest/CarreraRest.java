package vallegrande.matriculaCloud360.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vallegrande.matriculaCloud360.model.Carrera;
import vallegrande.matriculaCloud360.service.CarreraService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/carrera")
@Tag(name = "Carreras", description = "Endpoints para Carrera")
public class CarreraRest {

    @Autowired
    private CarreraService service;

    @GetMapping
    public List<Carrera> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Carrera listarPorId(@PathVariable String id) {
        return service.listarPorId(id);
    }

    @PostMapping
    public Carrera guardar(@RequestBody Carrera carrera) {
        return service.guardar(carrera);
    }

    @PutMapping("/{id}")
    public Carrera actualizar(@PathVariable String id, @RequestBody Carrera carrera) {
        return service.actualizar(id, carrera);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        service.eliminar(id);
    }
}
