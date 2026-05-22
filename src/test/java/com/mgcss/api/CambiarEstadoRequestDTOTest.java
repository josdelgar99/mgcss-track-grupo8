package com.mgcss.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CambiarEstadoRequestDTOTest {

    @Test
    void debeCrearDTOConConstructorVacioYSetter() {
        CambiarEstadoRequestDTO dto = new CambiarEstadoRequestDTO();
        dto.setEstado("CERRADA");

        assertThat(dto.getEstado()).isEqualTo("CERRADA");
    }

    @Test
    void debeCrearDTOConConstructorConParametros() {
        CambiarEstadoRequestDTO dto = new CambiarEstadoRequestDTO("EN_PROCESO");

        assertThat(dto.getEstado()).isEqualTo("EN_PROCESO");
    }
}