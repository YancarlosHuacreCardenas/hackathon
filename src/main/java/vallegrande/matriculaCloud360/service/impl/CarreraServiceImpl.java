package vallegrande.matriculaCloud360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vallegrande.matriculaCloud360.model.Carrera;
import vallegrande.matriculaCloud360.repository.CarreraRepository;
import vallegrande.matriculaCloud360.service.CarreraService;

import java.util.List;

@Service
public class CarreraServiceImpl implements CarreraService {

    @Autowired
    private CarreraRepository repository;

    @Override
    public List<Carrera> listar() {
        return repository.findAll();
    }

    @Override
    public Carrera listarPorId(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Carrera guardar(Carrera carrera) {
        return repository.save(carrera);
    }

    @Override
    public Carrera actualizar(String id, Carrera carrera) {
        if (repository.existsById(id)) {
            carrera.setCarreraID(id);
            return repository.save(carrera);
        }
        return null;
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }
}
