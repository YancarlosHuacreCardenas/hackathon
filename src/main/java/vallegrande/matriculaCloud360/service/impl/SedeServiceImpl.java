package vallegrande.matriculaCloud360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vallegrande.matriculaCloud360.model.Sede;
import vallegrande.matriculaCloud360.repository.SedeRepository;
import vallegrande.matriculaCloud360.service.SedeService;

import java.util.List;

@Service
public class SedeServiceImpl implements SedeService {

    @Autowired
    private SedeRepository repository;

    @Override
    public List<Sede> listar() {
        return repository.findAll();
    }

    @Override
    public Sede listarPorId(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Sede guardar(Sede sede) {
        return repository.save(sede);
    }

    @Override
    public Sede actualizar(String id, Sede sede) {
        if (repository.existsById(id)) {
            sede.setSedeID(id);
            return repository.save(sede);
        }
        return null;
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }
}
