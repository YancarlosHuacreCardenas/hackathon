package vallegrande.matriculaCloud360.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Sedes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sede {

    @Id
    @Column(name = "SedeID", length = 10)
    private String sedeID;

    @NotNull(message = "El nombre de la sede no puede ser nulo")
    @Size(max = 100)
    @Column(name = "NombreSede", length = 100, nullable = false)
    private String nombreSede;

    @NotNull(message = "La ciudad no puede ser nula")
    @Size(max = 50)
    @Column(name = "Ciudad", length = 50, nullable = false)
    private String ciudad;
}
