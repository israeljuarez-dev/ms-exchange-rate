package pe.com.taller.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import pe.com.taller.dto.GetProxy;

@Path("/tipo-cambio")
@RegisterRestClient(configKey = "api-proxy")
public interface ApiProxy {

    @GET
    @Path("/today.json")
    @Produces(MediaType.APPLICATION_JSON)
    GetProxy consultarTipoCambio();
}