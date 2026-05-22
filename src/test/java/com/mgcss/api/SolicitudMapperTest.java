package com.mgcss.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.mgcss.domain.Cliente;
import com.mgcss.domain.EstadoCliente;
import com.mgcss.domain.EstadoSolicitud;
import com.mgcss.domain.Solicitud;

class SolicitudMapperTest {

    private final SolicitudMapper mapper = new SolicitudMapper();

    @Test
    void debeMapearSolicitudAResponseDTO() {
        Cliente cliente = new Cliente(1L, "Pepe", "pepe@test.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(10L, cliente, "Pantalla rota", EstadoSolicitud.ABIERTA);

        SolicitudResponseDTO dto = mapper.toResponseDTO(solicitud);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(10L);
        assertThat(dto.getDescripcion()).isEqualTo("Pantalla rota");
        assertThat(dto.getEstado()).isEqualTo("ABIERTA");
    }

    @Test
    void debeDevolverNullSiSolicitudEsNull() {
        SolicitudResponseDTO dto = mapper.toResponseDTO(null);

        assertThat(dto).isNull();
    }
}