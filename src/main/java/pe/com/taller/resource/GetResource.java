package pe.com.taller.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pe.com.taller.dto.GetRequest;
import pe.com.taller.dto.GetResponse;
import pe.com.taller.service.GetService;

@Path("/api/v1/exchange-rate")
@Produces(MediaType.APPLICATION_JSON)
public class GetResource {

    @Inject
    GetService getService;

    @GET
    public GetResponse consultarTipoCambio(@BeanParam @Valid GetRequest request) {
        return getService.consultarTipoCambio(request.dni());
    }
}
