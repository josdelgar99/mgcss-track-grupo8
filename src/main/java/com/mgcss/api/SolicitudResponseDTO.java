package com.mgcss.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta con los datos públicos de una solicitud")
public class SolicitudResponseDTO {

	@Schema(description = "Identificador de la solicitud", example = "1")
	private Long id;
	
	@Schema(description = "Descripción de la solicitud", example = "El ordenador no arranca")
    private String descripcion;
    
	@Schema(description = "Estado actual de la solicitud", example = "ABIERTA")
	private String estado;

    public SolicitudResponseDTO() {
    }

    public SolicitudResponseDTO(Long id, String descripcion, String estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}