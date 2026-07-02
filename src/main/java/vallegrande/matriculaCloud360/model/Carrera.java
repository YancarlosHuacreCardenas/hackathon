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

@Entity
@Table(name = "Carreras")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Carrera {

    @Id
    @Column(name = "CarreraID", length = 10)
    private String carreraID;

    @NotNull(message = "El nombre de la carrera no puede ser nulo")
    @Size(max = 100)
    @Column(name = "NombreCarrera", length = 100, nullable = false)
    private String nombreCarrera;

    @NotNull(message = "La duración en ciclos no puede ser nula")
    @Column(name = "DuracionCiclos", nullable = false)
    private Integer duracionCiclos;

    @NotNull(message = "La inversión no puede ser nula")
    @Column(name = "Inversion", nullable = false, precision = 10, scale = 2)
    private BigDecimal inversion;
}
