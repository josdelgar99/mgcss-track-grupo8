package com.mgcss.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AsignarTecnicoRequestDTOTest {

    @Test
    void debeCrearDTOConConstructorVacioYSetter() {
        AsignarTecnicoRequestDTO dto = new AsignarTecnicoRequestDTO();
        dto.setTecnicoId(7L);

        assertThat(dto.getTecnicoId()).isEqualTo(7L);
    }

    @Test
    void debeCrearDTOConConstructorConParametros() {
        AsignarTecnicoRequestDTO dto = new AsignarTecnicoRequestDTO(9L);

        assertThat(dto.getTecnicoId()).isEqualTo(9L);
    }
}