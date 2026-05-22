package com.mgcss.api;

import com.mgcss.domain.Solicitud;
import org.springframework.stereotype.Component;

@Component
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