package vallegrande.matriculaCloud360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vallegrande.matriculaCloud360.model.Docente;
import vallegrande.matriculaCloud360.repository.DocenteRepository;
import vallegrande.matriculaCloud360.service.DocenteService;

import java.util.List;

@Service
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    private DocenteRepository repository;

    @Override
    public List<Docente> listar() {
        return repository.findAll();
    }

    @Override
    public Docente listarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Docente guardar(Docente docente) {
        return repository.save(docente);
    }

    @Override
    public Docente actualizar(Integer id, Docente docente) {
        if (repository.existsById(id)) {
            docente.setDocenteID(id);
            return repository.save(docente);
        }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
