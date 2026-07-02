package vallegrande.matriculaCloud360.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vallegrande.matriculaCloud360.model.Promotor;
import vallegrande.matriculaCloud360.service.PromotorService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/promotor")
@Tag(name = "Promotores", description = "Endpoints para Promotor")
public class PromotorRest {

    @Autowired
    private PromotorService service;

    @GetMapping
    public List<Promotor> listar() {
        return service.listar();
    }

    @GetMapping("/{dni}")
    public Promotor listarPorId(@PathVariable String dni) {
        return service.listarPorId(dni);
    }

    @GetMapping("/sede/{sedeID}")
    public List<Promotor> listarPorSede(@PathVariable String sedeID) {
        return service.listarPorSede(sedeID);
    }

    @PostMapping
    public Promotor guardar(@RequestBody Promotor promotor) {
        return service.guardar(promotor);
    }

    @PutMapping("/{dni}")
    public Promotor actualizar(@PathVariable String dni, @RequestBody Promotor promotor) {
        return service.actualizar(dni, promotor);
    }

    @DeleteMapping("/{dni}")
    public void eliminar(@PathVariable String dni) {
        service.eliminar(dni);
    }
}
