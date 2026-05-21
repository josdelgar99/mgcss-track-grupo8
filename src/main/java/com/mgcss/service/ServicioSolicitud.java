package com.mgcss.service;

import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

import com.mgcss.domain.Cliente;
import com.mgcss.domain.EstadoCliente;
import com.mgcss.domain.EstadoSolicitud;
import com.mgcss.domain.Solicitud;
import com.mgcss.domain.SolicitudRepository;
import com.mgcss.domain.Tecnico;

@Service
public class ServicioSolicitud {

    private static final String SOLICITUDNOENCONTRADA = "Solicitud no encontrada";

    private final SolicitudRepository repositorioSolicitud;

    public ServicioSolicitud(SolicitudRepository repositorioSolicitud) {
        this.repositorioSolicitud = repositorioSolicitud;
    }
    
    public Solicitud crearSolicitud(String descripcion) {

        Cliente clienteDummy =
                new Cliente(
                        1L,
                        "Cliente API",
                        "api@test.com",
                        EstadoCliente.STANDARD);

        Solicitud solicitud =
                new Solicitud(
                        null,
                        clienteDummy,
                        descripcion,
                        EstadoSolicitud.ABIERTA);

        return repositorioSolicitud.save(solicitud);
    }

    public Solicitud crearSolicitud(Long id, Cliente cliente, String descripcion) {
        Solicitud solicitud = new Solicitud(id, cliente, descripcion, EstadoSolicitud.ABIERTA);
        return repositorioSolicitud.save(solicitud);
    }

    public Solicitud asignarTecnico(Long solicitudId, Tecnico tecnico) {
        Solicitud solicitud = buscarSolicitudOError(solicitudId);
        solicitud.asignarTecnico(tecnico);
        return repositorioSolicitud.save(solicitud);
    }

    public Solicitud cerrarSolicitud(Long solicitudId) {
        Solicitud solicitud = buscarSolicitudOError(solicitudId);
        solicitud.cerrar();
        return repositorioSolicitud.save(solicitud);
    }

    public Solicitud reabrirSolicitud(Long solicitudId) {
        Solicitud solicitud = buscarSolicitudOError(solicitudId);
        solicitud.reabrir();
        return repositorioSolicitud.save(solicitud);
    }

    private Solicitud buscarSolicitudOError(Long solicitudId) {
        return repositorioSolicitud.findById(solicitudId)
                .orElseThrow(() -> new NoSuchElementException(SOLICITUDNOENCONTRADA));
    }
}