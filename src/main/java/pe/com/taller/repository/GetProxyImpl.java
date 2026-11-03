package pe.com.taller.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import pe.com.taller.client.ApiProxy;
import pe.com.taller.dto.GetProxy;

@ApplicationScoped
public class GetProxyImpl implements GetRepository {

    @Inject
    @RestClient
    ApiProxy apiProxy;

    @Override
    public GetProxy consultarTipoCambio() {
        return apiProxy.consultarTipoCambio();
    }
}
