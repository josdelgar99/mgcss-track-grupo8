package com.mgcss.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class SolicitudTest {
	
	@ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "   " })
	void noDebePermitirCambiarDescripcionInvalida(String descripcionInvalida) {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Descripcion inicial", EstadoSolicitud.ABIERTA);

        assertThrows(IllegalArgumentException.class, () -> solicitud.setDescripcion(descripcionInvalida));
        assertEquals("Descripcion inicial", solicitud.getDescripcion());
    }
	
	@Test
	void noDebePermitirCrearSolicitudSinCliente() {
	    assertThrows(IllegalArgumentException.class,
	            () -> new Solicitud(1L, null, "Fallo en el teclado", EstadoSolicitud.ABIERTA));
	}

    @Test
    void noDebePermitirCerrarSolicitudSiNoEstaEnProceso() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Fallo en el teclado", EstadoSolicitud.ABIERTA);

        assertThrows(IllegalStateException.class, solicitud::cerrar);
        assertEquals(EstadoSolicitud.ABIERTA, solicitud.getEstado());
    }

    @Test
    void noDebePermitirCerrarSolicitudEnProcesoSinTecnicoAsignado() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Fallo en el teclado", EstadoSolicitud.EN_PROCESO);

        assertThrows(IllegalStateException.class, solicitud::cerrar);
        assertEquals(EstadoSolicitud.EN_PROCESO, solicitud.getEstado());
        assertNull(solicitud.getFechaCierre());
    }

    @Test
    void debePermitirCerrarSolicitudSiEstaEnProcesoYTieneTecnicoAsignado() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Fallo en el teclado", EstadoSolicitud.EN_PROCESO);
        Tecnico tecnico = new Tecnico(1L, "Luis", "Hardware", true);

        solicitud.asignarTecnico(tecnico);
        solicitud.cerrar();

        assertEquals(EstadoSolicitud.CERRADA, solicitud.getEstado());
        assertNotNull(solicitud.getFechaCierre());
    }

    @Test
    void noDebePermitirAsignarTecnicoInactivo() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "No deja escribir", EstadoSolicitud.ABIERTA);
        Tecnico tecnico = new Tecnico(1L, "Luis", "Hardware", false);

        assertThrows(IllegalStateException.class, () -> solicitud.asignarTecnico(tecnico));
        assertNull(solicitud.getTecnicoAsignado());
    }

    @Test
    void debePermitirAsignarTecnicoActivo() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "No deja escribir", EstadoSolicitud.ABIERTA);
        Tecnico tecnico = new Tecnico(1L, "Luis", "Hardware", true);

        solicitud.asignarTecnico(tecnico);

        assertEquals(tecnico, solicitud.getTecnicoAsignado());
    }

    @Test
    void clientePremiumTienePrioridad() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.PREMIUM);
        Solicitud solicitud = new Solicitud(1L, cliente, "Fallo urgente", EstadoSolicitud.ABIERTA);

        assertTrue(solicitud.tienePrioridad());
    }

    @Test
    void clienteStandardNoTienePrioridad() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Fallo normal", EstadoSolicitud.ABIERTA);

        assertFalse(solicitud.tienePrioridad());
    }
    
    @Test
    void noDebePermitirCrearSolicitudSinEstado() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);

        assertThrows(IllegalArgumentException.class,
                () -> new Solicitud(1L, cliente, "Fallo en el teclado", null));
    }

    @Test
    void noDebePermitirCrearSolicitudSinDescripcion() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);

        assertThrows(IllegalArgumentException.class,
                () -> new Solicitud(1L, cliente, null, EstadoSolicitud.ABIERTA));
    }

    @Test
    void noDebePermitirAsignarTecnicoNull() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "No deja escribir", EstadoSolicitud.ABIERTA);

        assertThrows(IllegalArgumentException.class, () -> solicitud.asignarTecnico(null));
    }

    @Test
    void noDebePermitirAsignarTecnicoASolicitudCerrada() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "No deja escribir", EstadoSolicitud.CERRADA);
        Tecnico tecnico = new Tecnico(1L, "Luis", "Hardware", true);

        assertThrows(IllegalStateException.class, () -> solicitud.asignarTecnico(tecnico));
        assertNull(solicitud.getTecnicoAsignado());
    }

    @Test
    void noDebePermitirReasignarTecnicoSiYaTieneUno() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "No deja escribir", EstadoSolicitud.ABIERTA);
        Tecnico tecnico1 = new Tecnico(1L, "Luis", "Hardware", true);
        Tecnico tecnico2 = new Tecnico(2L, "Ana", "Software", true);

        solicitud.asignarTecnico(tecnico1);

        assertThrows(IllegalStateException.class, () -> solicitud.asignarTecnico(tecnico2));
        assertEquals(tecnico1, solicitud.getTecnicoAsignado());
    }

    @Test
    void debePermitirCambiarDescripcionValida() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Descripcion inicial", EstadoSolicitud.ABIERTA);

        solicitud.setDescripcion("Nueva descripcion");

        assertEquals("Nueva descripcion", solicitud.getDescripcion());
    }

    @Test
    void noDebePermitirReabrirSolicitudNoCerrada() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitudAbierta = new Solicitud(1L, cliente, "Abierta", EstadoSolicitud.ABIERTA);
        Solicitud solicitudEnProceso = new Solicitud(2L, cliente, "En proceso", EstadoSolicitud.EN_PROCESO);

        assertThrows(IllegalStateException.class, solicitudAbierta::reabrir);
        assertThrows(IllegalStateException.class, solicitudEnProceso::reabrir);

        assertEquals(EstadoSolicitud.ABIERTA, solicitudAbierta.getEstado());
        assertEquals(EstadoSolicitud.EN_PROCESO, solicitudEnProceso.getEstado());
    }

    @Test
    void debePermitirReabrirSolicitudCerrada() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Cerrada", EstadoSolicitud.EN_PROCESO);
        Tecnico tecnico = new Tecnico(1L, "Luis", "Hardware", true);

        solicitud.asignarTecnico(tecnico);
        solicitud.cerrar();
        solicitud.reabrir();

        assertEquals(EstadoSolicitud.ABIERTA, solicitud.getEstado());
        assertNull(solicitud.getFechaCierre());
    }
}