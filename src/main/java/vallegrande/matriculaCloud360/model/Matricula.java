package vallegrande.matriculaCloud360.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "Matriculas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Matricula {

    @Id
    @Column(name = "MatriculaID", length = 15)
    private String matriculaID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EstudianteDNI", referencedColumnName = "DNI", nullable = false)
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CarreraID", referencedColumnName = "CarreraID", nullable = false)
    private Carrera carrera;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SedeID", referencedColumnName = "SedeID", nullable = false)
    private Sede sede;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PromotorDNI", referencedColumnName = "DNI", nullable = false)
    private Promotor promotor;

    @NotNull(message = "El monto no puede ser nulo")
    @Column(name = "Monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @NotNull(message = "El periodo no puede ser nulo")
    @Size(max = 10)
    @Column(name = "Periodo", length = 10, nullable = false)
    private String periodo;

    @Column(name = "FechaRegistro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    // Columna clave para Soft Delete (Borrado Lógico) — US-02
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now(ZoneId.of("America/Lima"));
        }
    }
}
