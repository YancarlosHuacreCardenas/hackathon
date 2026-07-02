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
@Table(name = "Docentes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Docente {

    @Id
    @Column(name = "DocenteID")
    private Integer docenteID;

    @NotNull(message = "El nombre completo no puede ser nulo")
    @Size(max = 150)
    @Column(name = "NombresApellidos", length = 150, nullable = false)
    private String nombresApellidos;

    @NotNull(message = "La especialidad no puede ser nula")
    @Size(max = 100)
    @Column(name = "Especialidad", length = 100, nullable = false)
    private String especialidad;

    @NotNull(message = "El correo corporativo no puede ser nulo")
    @Email(message = "El formato del correo no es válido")
    @Size(max = 100)
    @Column(name = "CorreoCorporativo", length = 100, nullable = false)
    private String correoCorporativo;
}
