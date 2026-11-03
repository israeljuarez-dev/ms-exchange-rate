package pe.com.taller.repository;

import pe.com.taller.model.ConsultaTipoCambioRealizado;

import java.time.LocalDate;

public interface ConsultaRepository {
    long contarConsultasPorDni(String dni, LocalDate fecha);
    void registrarConsulta(ConsultaTipoCambioRealizado consulta);
}
