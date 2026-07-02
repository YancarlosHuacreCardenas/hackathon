package vallegrande.matriculaCloud360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vallegrande.matriculaCloud360.model.CargaDocente;
import vallegrande.matriculaCloud360.model.CargaDocenteId;
import vallegrande.matriculaCloud360.repository.CargaDocenteRepository;
import vallegrande.matriculaCloud360.service.CargaDocenteService;

import java.util.List;

@Service
public class CargaDocenteServiceImpl implements CargaDocenteService {

    @Autowired
    private CargaDocenteRepository repository;

    @Override
    public List<CargaDocente> listar() {
        return repository.findAll();
    }

    @Override
    public CargaDocente listarPorId(CargaDocenteId id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<CargaDocente> listarPorCarreraYPeriodo(String carreraID, String periodo) {
        return repository.findByCarreraYPeriodo(carreraID, periodo);
    }

    @Override
    public List<CargaDocente> listarPorPeriodo(String periodo) {
        return repository.findByPeriodo(periodo);
    }

    @Override
    public CargaDocente guardar(CargaDocente cargaDocente) {
        return repository.save(cargaDocente);
    }

    @Override
    public CargaDocente actualizar(CargaDocenteId id, CargaDocente cargaDocente) {
        if (repository.existsById(id)) {
            cargaDocente.setId(id);
            return repository.save(cargaDocente);
        }
        return null;
    }

    @Override
    public void eliminar(CargaDocenteId id) {
        repository.deleteById(id);
    }
}
