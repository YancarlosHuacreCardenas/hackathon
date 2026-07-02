package vallegrande.matriculaCloud360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vallegrande.matriculaCloud360.model.Curso;
import vallegrande.matriculaCloud360.repository.CursoRepository;
import vallegrande.matriculaCloud360.service.CursoService;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository repository;

    @Override
    public List<Curso> listar() {
        return repository.findAll();
    }

    @Override
    public Curso listarPorId(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Curso guardar(Curso curso) {
        return repository.save(curso);
    }

    @Override
    public Curso actualizar(String id, Curso curso) {
        if (repository.existsById(id)) {
            curso.setCursoID(id);
            return repository.save(curso);
        }
        return null;
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }
}
