package com.mgcss.api;

import com.mgcss.domain.Solicitud;

public class SolicitudMapper {

    public SolicitudResponseDTO toResponseDTO(Solicitud solicitud) {
        if (solicitud == null) {
            return null;
        }

        return new SolicitudResponseDTO(
                solicitud.getId(),
                solicitud.getDescripcion(),
                solicitud.getEstado().name()
        );
    }
}