package com.mgcss.domain;

import java.util.Date;

public class Solicitud {
	
	private Long id;
	private String descripcion;
	private EstadoSolicitud estado;
	private Cliente cliente;
	private Date fechaCreacion;
	private Date fechaCierre;
	private Tecnico tecnicoAsignado;
	
	public Solicitud() {}
	
	public Solicitud(Long id, Cliente cliente, String descripcion, EstadoSolicitud estado) {
		if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
		
		this.id = id;
		this.cliente = cliente;
		this.descripcion = descripcion.trim();
		this.estado = estado;
		this.fechaCreacion = new Date();
		this.fechaCierre = null;
	}
	
	public Long getId() {
		return id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public EstadoSolicitud getEstado() {
		return estado;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	public Date getFechaCierre() {
		return fechaCierre;
	}
	
	public Tecnico getTecnicoAsignado() {
		return tecnicoAsignado;
	}
	
	public boolean tienePrioridad() {
        return cliente != null && cliente.getEstado() == EstadoCliente.PREMIUM;
    }
	
	public void cerrar() {
		if (estado != EstadoSolicitud.EN_PROCESO) {
			throw new IllegalStateException("Sólo se podría cerrar la solicitud que está EN_PROCESO");
		}
		estado = EstadoSolicitud.CERRADA;
		fechaCierre = new Date();
	}
	
	public void asignarTecnico(Tecnico tecnico) {
	    if (tecnico == null) {
	        throw new IllegalArgumentException("El técnico no puede ser null");
	    }
	    if (tecnicoAsignado != null) {
	        throw new IllegalStateException("La solicitud ya tiene un técnico asignado");
	    }
	    if (!tecnico.esActivo()) {
	        throw new IllegalStateException("Sólo se puede asignar un técnico");
	    }
	    if (estado == EstadoSolicitud.CERRADA) {
	        throw new IllegalStateException("No se puede asignar un técnico a una solicitud cerrada");
	    }
	    this.tecnicoAsignado = tecnico;
	}

}