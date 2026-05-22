package com.mgcss.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Datos necesarios para crear una solicitud")
public class SolicitudRequestDTO {
	
	@Schema(
            description = "Descripción de la incidencia o solicitud de servicio",
            example = "El ordenador no arranca"
    )
	@NotBlank(message = "Debes indicar una descripción en la solicitud")
    private String descripcion;

    public SolicitudRequestDTO() {}

    public SolicitudRequestDTO(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
