package vallegrande.matriculaCloud360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vallegrande.matriculaCloud360.dto.DashboardComisionDTO;
import vallegrande.matriculaCloud360.dto.MatriculaRequest;
import vallegrande.matriculaCloud360.model.*;
import vallegrande.matriculaCloud360.repository.*;
import vallegrande.matriculaCloud360.service.MatriculaService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.ByteArrayOutputStream;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private PromotorRepository promotorRepository;

    @Override
    public List<Matricula> listarActivas() {
        return repository.findByDeletedAtIsNull();
    }

    @Override
    public List<Matricula> listarEliminadas() {
        return repository.findByDeletedAtIsNotNull();
    }

    @Override
    public Matricula listarPorId(String id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Matricula guardar(MatriculaRequest request) {
        // US-01: Validar si ya existe matrícula activa (Unique Index rule)
        if (repository.existeMatriculaActiva(request.getEstudianteDNI(), request.getCarreraID(), request.getPeriodo())) {
            throw new IllegalArgumentException("El estudiante ya cuenta con una matrícula activa en esta carrera para el periodo indicado.");
        }

        Estudiante estudiante = estudianteRepository.findById(request.getEstudianteDNI())
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));
        Carrera carrera = carreraRepository.findById(request.getCarreraID())
                .orElseThrow(() -> new IllegalArgumentException("Carrera no encontrada"));
        Sede sede = sedeRepository.findById(request.getSedeID())
                .orElseThrow(() -> new IllegalArgumentException("Sede no encontrada"));
        Promotor promotor = promotorRepository.findById(request.getPromotorDNI())
                .orElseThrow(() -> new IllegalArgumentException("Promotor no encontrado"));

        Matricula matricula = new Matricula();
        matricula.setMatriculaID(request.getMatriculaID());
        matricula.setEstudiante(estudiante);
        matricula.setCarrera(carrera);
        matricula.setSede(sede);
        matricula.setPromotor(promotor);
        matricula.setMonto(request.getMonto());
        matricula.setPeriodo(request.getPeriodo());

        return repository.save(matricula);
    }

    @Transactional
    @Override
    public Matricula actualizar(String id, MatriculaRequest request) {
        Matricula existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula no encontrada"));

        // Si se cambia estudiante, carrera o periodo, verificar duplicados
        if (!existing.getEstudiante().getDni().equals(request.getEstudianteDNI()) ||
            !existing.getCarrera().getCarreraID().equals(request.getCarreraID()) ||
            !existing.getPeriodo().equals(request.getPeriodo())) {
            if (repository.existeMatriculaActiva(request.getEstudianteDNI(), request.getCarreraID(), request.getPeriodo())) {
                throw new IllegalArgumentException("Ya existe una matrícula activa para este estudiante, carrera y periodo.");
            }
        }

        Estudiante estudiante = estudianteRepository.findById(request.getEstudianteDNI())
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));
        Carrera carrera = carreraRepository.findById(request.getCarreraID())
                .orElseThrow(() -> new IllegalArgumentException("Carrera no encontrada"));
        Sede sede = sedeRepository.findById(request.getSedeID())
                .orElseThrow(() -> new IllegalArgumentException("Sede no encontrada"));
        Promotor promotor = promotorRepository.findById(request.getPromotorDNI())
                .orElseThrow(() -> new IllegalArgumentException("Promotor no encontrado"));

        existing.setEstudiante(estudiante);
        existing.setCarrera(carrera);
        existing.setSede(sede);
        existing.setPromotor(promotor);
        existing.setMonto(request.getMonto());
        existing.setPeriodo(request.getPeriodo());

        return repository.save(existing);
    }

    @Transactional
    @Override
    public Matricula eliminarLogico(String id) {
        Matricula existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula no encontrada"));
        existing.setDeletedAt(LocalDateTime.now(ZoneId.of("America/Lima")));
        return repository.save(existing);
    }

    @Transactional
    @Override
    public Matricula restaurar(String id) {
        Matricula existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula no encontrada"));
        
        // Antes de restaurar, validar que no se genere duplicado con otra matrícula que haya quedado activa en el ínterin
        if (repository.existeMatriculaActiva(existing.getEstudiante().getDni(), existing.getCarrera().getCarreraID(), existing.getPeriodo())) {
            throw new IllegalArgumentException("No se puede restaurar. Ya existe una matrícula activa para este estudiante, carrera y periodo.");
        }
        
        existing.setDeletedAt(null);
        return repository.save(existing);
    }

    @Override
    public List<DashboardComisionDTO> obtenerDashboardComisiones() {
        List<Object[]> rawData = repository.getDashboardComisiones();
        List<DashboardComisionDTO> list = new ArrayList<>();
        for (Object[] row : rawData) {
            DashboardComisionDTO dto = new DashboardComisionDTO(
                    (String) row[0],
                    (String) row[1],
                    (String) row[2],
                    row[3] != null ? ((Number) row[3]).longValue() : 0L,
                    row[4] != null ? new BigDecimal(row[4].toString()) : BigDecimal.ZERO,
                    row[5] != null ? new BigDecimal(row[5].toString()) : BigDecimal.ZERO
            );
            list.add(dto);
        }
        return list;
    }

    @Override
    public byte[] exportarPdf(String id) throws Exception {
        Matricula matricula = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula no encontrada"));

        Context context = new Context();
        context.setVariable("matricula", matricula);

        String htmlContent = templateEngine.process("matricula_detalle", context);

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        }
    }
}
