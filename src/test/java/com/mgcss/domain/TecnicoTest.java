package com.mgcss.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TecnicoTest {

    @Test
    void debeConstruirseCorrectamente() {
        Tecnico tecnico = new Tecnico(1L, "Luis", "Hardware", true);

        assertEquals(1L, tecnico.getId());
        assertEquals("Luis", tecnico.getNombre());
        assertEquals("Hardware", tecnico.getEspecialidad());
        assertTrue(tecnico.esActivo());
    }
}