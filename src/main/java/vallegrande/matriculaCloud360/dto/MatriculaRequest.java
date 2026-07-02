package vallegrande.matriculaCloud360.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaRequest {

    @NotNull(message = "El ID de la matrícula no puede ser nulo")
    @Size(max = 15)
    private String matriculaID;

    @NotNull(message = "El DNI del estudiante no puede ser nulo")
    @Size(max = 8)
    private String estudianteDNI;

    @NotNull(message = "La carrera no puede ser nula")
    @Size(max = 10)
    private String carreraID;

    @NotNull(message = "La sede no puede ser nula")
    @Size(max = 10)
    private String sedeID;

    @NotNull(message = "El promotor no puede ser nulo")
    @Size(max = 8)
    private String promotorDNI;

    @NotNull(message = "El monto no puede ser nulo")
    private BigDecimal monto;

    @NotNull(message = "El periodo no puede ser nulo")
    @Size(max = 10)
    private String periodo;
}
