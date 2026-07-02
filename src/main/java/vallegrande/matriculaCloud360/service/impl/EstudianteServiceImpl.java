package vallegrande.matriculaCloud360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vallegrande.matriculaCloud360.model.Estudiante;
import vallegrande.matriculaCloud360.repository.EstudianteRepository;
import vallegrande.matriculaCloud360.service.EstudianteService;

import java.util.List;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository repository;

    @Override
    public List<Estudiante> listar() {
        return repository.findAll();
    }

    @Override
    public Estudiante listarPorId(String dni) {
        return repository.findById(dni).orElse(null);
    }

    @Override
    public List<Estudiante> buscarPorApellidos(String apellidos) {
        return repository.findByApellidosContainingIgnoreCase(apellidos);
    }

    @Override
    public Estudiante guardar(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    @Override
    public Estudiante actualizar(String dni, Estudiante estudiante) {
        if (repository.existsById(dni)) {
            estudiante.setDni(dni);
            return repository.save(estudiante);
        }
        return null;
    }

    @Override
    public void eliminar(String dni) {
        repository.deleteById(dni);
    }
}
