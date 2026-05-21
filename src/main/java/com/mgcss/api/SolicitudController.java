package com.mgcss.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgcss.service.ServicioSolicitud;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final ServicioSolicitud servicioSolicitud;
    private final SolicitudMapper solicitudMapper;

    public SolicitudController(
            ServicioSolicitud servicioSolicitud,
            SolicitudMapper solicitudMapper) {

        this.servicioSolicitud = servicioSolicitud;
        this.solicitudMapper = solicitudMapper;
    }
}