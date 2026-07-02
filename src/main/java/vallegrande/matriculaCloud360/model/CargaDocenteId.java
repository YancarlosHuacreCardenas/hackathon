package vallegrande.matriculaCloud360.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargaDocenteId implements Serializable {

    @Column(name = "CarreraID", length = 10)
    private String carreraID;

    @Column(name = "CursoID", length = 10)
    private String cursoID;

    @Column(name = "Periodo", length = 10)
    private String periodo;
}
