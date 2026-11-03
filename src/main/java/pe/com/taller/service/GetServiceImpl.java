package pe.com.taller.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import pe.com.taller.dto.GetProxy;
import pe.com.taller.dto.GetResponse;
import pe.com.taller.model.ConsultaTipoCambioRealizado;
import pe.com.taller.repository.ConsultaRepository;
import pe.com.taller.repository.GetRepository;
import pe.com.taller.utils.PeruDate;

import java.time.LocalDate;
import java.util.Map;

@ApplicationScoped
@Slf4j
public class GetServiceImpl implements GetService{

    private static final long INCREMENTO_CONSULTA = 1L;

    @Inject
    GetRepository getRepository;

    @Inject
    ConsultaRepository consultaRepository;

    @ConfigProperty(
            name = "exchange-rate.daily-limit",
            defaultValue = "10"
    )
    long limiteDiario;

    @Override
    public GetResponse consultarTipoCambio(String dni) {

        LocalDate fechaActual = PeruDate.fechaActual();

        long consultasRealizadas = PeruDate.consultasRealizadas(
                consultaRepository,
                dni,
                fechaActual
        );

        if (consultasRealizadas >= limiteDiario) {

            log.warn("Limite diario de consultas alcanzado");

            throw new WebApplicationException(
                    Response.status(429)
                            .entity(Map.of(
                                    "mensaje",
                                    "Has superado el limite de consultas diarias.",
                                    "limiteDiario",
                                    limiteDiario,
                                    "consultasRealizadas",
                                    consultasRealizadas
                            ))
                            .build()
            );

        }

        GetProxy tipoCambio;

        try {

            tipoCambio = getRepository.consultarTipoCambio();

        } catch (Exception e) {

            log.error("Error al consultar servicio externo");

            throw new WebApplicationException(
                    Response.status(502)
                            .entity(Map.of(
                                    "mensaje",
                                    "No fue posible obtener el tipo de cambio."
                            ))
                            .build()
            );

        }

        ConsultaTipoCambioRealizado consulta = ConsultaTipoCambioRealizado.builder()
                .dni(dni)
                .fechaConsulta(fechaActual)
                .fechaTipoCambio(tipoCambio.fecha())
                .sunat(tipoCambio.sunat())
                .compra(tipoCambio.compra())
                .venta(tipoCambio.venta())
                .createdAt(PeruDate.fechaHoraActual())
                .build();

        consultaRepository.registrarConsulta(consulta);

      return GetResponse.builder()
                .dni(dni)
                .fechaConsulta(fechaActual)
                .fecha(tipoCambio.fecha())
                .sunat(tipoCambio.sunat())
                .compra(tipoCambio.compra())
                .venta(tipoCambio.venta())
                .consultasRealizadas(consultasRealizadas + INCREMENTO_CONSULTA)
                .limiteDiario(limiteDiario)
                .build();
    }
}
