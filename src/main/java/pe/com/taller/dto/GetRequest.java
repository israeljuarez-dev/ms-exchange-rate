package pe.com.taller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.QueryParam;

public record GetRequest(

        @QueryParam("dni")
        @NotBlank(message = "El DNI es obligatorio")
        @Pattern(
                regexp = "\\d{8}",
                message = "El DNI debe contener exactamente 8 dígitos"
        )
        String dni

) {
}
