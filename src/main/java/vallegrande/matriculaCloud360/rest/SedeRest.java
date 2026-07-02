package vallegrande.matriculaCloud360.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vallegrande.matriculaCloud360.model.Sede;
import vallegrande.matriculaCloud360.service.SedeService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/sede")
@Tag(name = "Sedes", description = "Endpoints para Sede")
public class SedeRest {

    @Autowired
    private SedeService service;

    @GetMapping
    public List<Sede> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Sede listarPorId(@PathVariable String id) {
        return service.listarPorId(id);
    }

    @PostMapping
    public Sede guardar(@RequestBody Sede sede) {
        return service.guardar(sede);
    }

    @PutMapping("/{id}")
    public Sede actualizar(@PathVariable String id, @RequestBody Sede sede) {
        return service.actualizar(id, sede);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        service.eliminar(id);
    }
}
