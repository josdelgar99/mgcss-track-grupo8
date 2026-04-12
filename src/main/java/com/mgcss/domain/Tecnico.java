package com.mgcss.domain;

public class Tecnico {
	private Long id;
	private String nombre;
	private String especialidad;
	private boolean activo;
	
	public Tecnico() {};

	public Tecnico(Long id, String nombre, String especialidad, boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.especialidad = especialidad;
		this.activo = activo;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getEspecialidad() {
		return especialidad;
	}
	
	public boolean esActivo() {
		return activo;
	}
	
	public void activar() {
		this.activo = true;
	}
	
	public void desactivar() {
		this.activo = false;
	}
}