package pe.com.taller.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ConsultaTipoCambioRealizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 8)
    private String dni;

    @Column(name = "fecha_consulta", nullable = false)
    public LocalDate fechaConsulta;

    @Column(name = "fecha_tipo_cambio")
    public LocalDate fechaTipoCambio;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal sunat;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal compra;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal venta;

    @Column(name = "created_at", nullable = false)
    public LocalDateTime createdAt;
}

