package pe.com.taller.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import pe.com.taller.model.ConsultaTipoCambioRealizado;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class ConsultaRepositoryImpl implements PanacheRepository<ConsultaTipoCambioRealizado>,  ConsultaRepository {
    public long contarConsultasPorDni(String dni, LocalDate fecha) {
        return count(
                "dni = ?1 and fechaConsulta = ?2",
                dni,
                fecha
        );
    }

    @Transactional
    public void registrarConsulta(ConsultaTipoCambioRealizado consulta) {
        persist(consulta);
    }

    public List<ConsultaTipoCambioRealizado> listarConsultasPorDni(String dni) {
        return list("dni = ?1", dni);
    }
}
