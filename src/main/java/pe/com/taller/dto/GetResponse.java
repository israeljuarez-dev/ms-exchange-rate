package pe.com.taller.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record GetResponse(
        String dni,
        LocalDate fechaConsulta,
        LocalDate fecha,
        BigDecimal sunat,
        BigDecimal compra,
        BigDecimal venta,
        long consultasRealizadas,
        long limiteDiario
) {
}