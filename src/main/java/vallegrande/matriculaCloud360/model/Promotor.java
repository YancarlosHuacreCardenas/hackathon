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
@Table(name = "Promotores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Promotor {

    @Id
    @Column(name = "DNI", length = 8)
    private String dni;

    @NotNull(message = "El nombre completo no puede ser nulo")
    @Size(max = 150)
    @Column(name = "NombresApellidos", length = 150, nullable = false)
    private String nombresApellidos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SedeBaseID", referencedColumnName = "SedeID")
    private Sede sedeBase;

    @NotNull(message = "El email corporativo no puede ser nulo")
    @Email(message = "El formato del email no es válido")
    @Size(max = 100)
    @Column(name = "EmailCorporativo", length = 100, nullable = false)
    private String emailCorporativo;
}
