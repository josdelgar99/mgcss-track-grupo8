package com.mgcss.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SolicitudTest {

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
    void noDebePermitirCrearSolicitudSinDescripcion() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);

        assertThrows(IllegalArgumentException.class,
                () -> new Solicitud(1L, cliente, null, EstadoSolicitud.ABIERTA));
    }

    @Test
    void noDebePermitirCambiarDescripcionAVacia() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Descripcion inicial", EstadoSolicitud.ABIERTA);

        assertThrows(IllegalArgumentException.class, () -> solicitud.setDescripcion(""));
        assertEquals("Descripcion inicial", solicitud.getDescripcion());
    }

    @Test
    void noDebePermitirCambiarDescripcionASoloEspacios() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Descripcion inicial", EstadoSolicitud.ABIERTA);

        assertThrows(IllegalArgumentException.class, () -> solicitud.setDescripcion("   "));
        assertEquals("Descripcion inicial", solicitud.getDescripcion());
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
    void noDebePermitirCambiarDescripcionANull() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Descripcion inicial", EstadoSolicitud.ABIERTA);

        assertThrows(IllegalArgumentException.class, () -> solicitud.setDescripcion(null));
        assertEquals("Descripcion inicial", solicitud.getDescripcion());
    }

    @Test
    void debePermitirCambiarDescripcionValida() {
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Descripcion inicial", EstadoSolicitud.ABIERTA);

        solicitud.setDescripcion("Nueva descripcion");

        assertEquals("Nueva descripcion", solicitud.getDescripcion());
    }
}