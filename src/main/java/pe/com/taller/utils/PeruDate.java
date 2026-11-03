package pe.com.taller.utils;

import pe.com.taller.repository.ConsultaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public final class PeruDate {

    private static final ZoneId ZONA_PERU = ZoneId.of("America/Lima");

    private PeruDate() {
    }

    public static LocalDate fechaActual() {
        return LocalDate.now(ZONA_PERU);
    }

    public static LocalDateTime fechaHoraActual() {
        return LocalDateTime.now(ZONA_PERU);
    }

    public static long consultasRealizadas(
            ConsultaRepository consultaRepository,
            String dni,
            LocalDate fecha
    ) {
        return consultaRepository.contarConsultasPorDni(dni, fecha);
    }
}
