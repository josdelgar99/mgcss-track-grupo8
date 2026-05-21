package com.mgcss.infrastructure;

import com.mgcss.domain.EstadoSolicitud;
import jakarta.persistence.*;

@Entity
@Table(name = "solicitudes")
public class SolicitudEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSolicitud estado;

    public SolicitudEntity() {
    }

    public SolicitudEntity(Long id, String descripcion, EstadoSolicitud estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }
}