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
@Table(name = "Cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Curso {

    @Id
    @Column(name = "CursoID", length = 10)
    private String cursoID;

    @NotNull(message = "El nombre del curso no puede ser nulo")
    @Size(max = 100)
    @Column(name = "NombreCurso", length = 100, nullable = false)
    private String nombreCurso;

    @NotNull(message = "Los créditos no pueden ser nulos")
    @Column(name = "Creditos", nullable = false)
    private Integer creditos;
}
