package com.mgcss.domain;

public class Cliente {
	
	private Long id;
	private String nombre;
	private String email;
	private EstadoCliente estado;
	
	public Cliente() {};
	
	public Cliente (Long id, String nombre, String email, EstadoCliente estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.estado= estado;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public EstadoCliente getEstado() {
		return estado;
	}
	
	public void setEstado(EstadoCliente estado) {
		this.estado = estado;
	}
}