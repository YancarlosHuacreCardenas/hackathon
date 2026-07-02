package vallegrande.matriculaCloud360.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CargaDocente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CargaDocente {

    @EmbeddedId
    private CargaDocenteId id;

    // insertable/updatable = false porque los columnas ya son manejadas por @EmbeddedId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CarreraID", referencedColumnName = "CarreraID", insertable = false, updatable = false)
    private Carrera carrera;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CursoID", referencedColumnName = "CursoID", insertable = false, updatable = false)
    private Curso curso;

    // DocenteID NO forma parte de la PK, solo es FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DocenteID", referencedColumnName = "DocenteID")
    private Docente docente;
}
