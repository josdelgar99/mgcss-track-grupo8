package com.mgcss.service;

import com.mgcss.domain.Cliente;
import com.mgcss.domain.EstadoSolicitud;
import com.mgcss.domain.Solicitud;

public class ServicioSolicitud {
	
	public Solicitud CrearSolicitud(Long id,Cliente cliente, String descripcion) {
		return new Solicitud(id, cliente, descripcion, EstadoSolicitud.ABIERTA);
	}

}