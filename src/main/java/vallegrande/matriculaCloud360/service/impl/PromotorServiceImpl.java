package vallegrande.matriculaCloud360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vallegrande.matriculaCloud360.model.Promotor;
import vallegrande.matriculaCloud360.repository.PromotorRepository;
import vallegrande.matriculaCloud360.service.PromotorService;

import java.util.List;

@Service
public class PromotorServiceImpl implements PromotorService {

    @Autowired
    private PromotorRepository repository;

    @Override
    public List<Promotor> listar() {
        return repository.findAll();
    }

    @Override
    public Promotor listarPorId(String dni) {
        return repository.findById(dni).orElse(null);
    }

    @Override
    public List<Promotor> listarPorSede(String sedeID) {
        return repository.findBySedeBase_SedeID(sedeID);
    }

    @Override
    public Promotor guardar(Promotor promotor) {
        return repository.save(promotor);
    }

    @Override
    public Promotor actualizar(String dni, Promotor promotor) {
        if (repository.existsById(dni)) {
            promotor.setDni(dni);
            return repository.save(promotor);
        }
        return null;
    }

    @Override
    public void eliminar(String dni) {
        repository.deleteById(dni);
    }
}
