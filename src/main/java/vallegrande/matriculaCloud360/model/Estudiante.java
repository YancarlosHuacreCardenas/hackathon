package vallegrande.matriculaCloud360.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Estudiantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Estudiante {

    @Id
    @Column(name = "DNI", length = 8)
    private String dni;

    @NotNull(message = "Los nombres no pueden ser nulos")
    @Size(max = 100)
    @Column(name = "Nombres", length = 100, nullable = false)
    private String nombres;

    @NotNull(message = "Los apellidos no pueden ser nulos")
    @Size(max = 100)
    @Column(name = "Apellidos", length = 100, nullable = false)
    private String apellidos;

    @NotNull(message = "El correo personal no puede ser nulo")
    @Email(message = "El formato del correo no es válido")
    @Size(max = 100)
    @Column(name = "CorreoPersonal", length = 100, nullable = false)
    private String correoPersonal;

    @NotNull(message = "El teléfono no puede ser nulo")
    @Size(max = 15)
    @Column(name = "Telefono", length = 15, nullable = false)
    private String telefono;
}
