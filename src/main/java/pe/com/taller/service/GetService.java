package pe.com.taller.service;

import pe.com.taller.dto.GetResponse;

public interface GetService {
    GetResponse consultarTipoCambio(String dni);
}
