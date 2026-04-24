package com.mgcss.service;

import java.util.NoSuchElementException;

import com.mgcss.domain.Cliente;
import com.mgcss.domain.EstadoSolicitud;
import com.mgcss.domain.Solicitud;
import com.mgcss.domain.Tecnico;
import com.mgcss.domain.SolicitudRepository;

public class ServicioSolicitud {

    private final SolicitudRepository repositorioSolicitud;	

    public ServicioSolicitud(SolicitudRepository repositorioSolicitud) {
        this.repositorioSolicitud = repositorioSolicitud;
    }

    public Solicitud crearSolicitud(Long id, Cliente cliente, String descripcion) {
        Solicitud solicitud = new Solicitud(id, cliente, descripcion, EstadoSolicitud.ABIERTA);
        return repositorioSolicitud.save(solicitud);
    }

    public Solicitud asignarTecnico(Long solicitudId, Tecnico tecnico) {
        Solicitud solicitud = repositorioSolicitud.findById(solicitudId)
                .orElseThrow(() -> new NoSuchElementException("Solicitud no encontrada"));

        solicitud.asignarTecnico(tecnico);
        return repositorioSolicitud.save(solicitud);
    }

    public Solicitud cerrarSolicitud(Long solicitudId) {
        Solicitud solicitud = repositorioSolicitud.findById(solicitudId)
                .orElseThrow(() -> new NoSuchElementException("Solicitud no encontrada"));

        solicitud.cerrar();
        return repositorioSolicitud.save(solicitud);
    }

    public Solicitud reabrirSolicitud(Long solicitudId) {
        Solicitud solicitud = repositorioSolicitud.findById(solicitudId)
                .orElseThrow(() -> new NoSuchElementException("Solicitud no encontrada"));

        solicitud.reabrir();
        return repositorioSolicitud.save(solicitud);
    }
}