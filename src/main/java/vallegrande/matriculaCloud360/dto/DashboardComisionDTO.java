package vallegrande.matriculaCloud360.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO que replica la vista V_Dashboard_Comisiones del SQL Server.
 * Muestra resumen de matrículas activas y comisiones por promotor (US-04).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardComisionDTO {

    private String codigoPromotor;
    private String promotor;
    private String sede;
    private Long totalMatriculasActivas;
    private BigDecimal ventasTotales;
    private BigDecimal comisionesAcumuladas; // 10% de ventasTotales
}
