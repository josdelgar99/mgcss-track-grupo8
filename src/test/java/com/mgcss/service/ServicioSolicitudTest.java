package com.mgcss.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.mgcss.domain.Cliente;
import com.mgcss.domain.EstadoCliente;
import com.mgcss.domain.EstadoSolicitud;
import com.mgcss.domain.Solicitud;

public class ServicioSolicitudTest {
	
	@Test
    void debeCrearSolicitudEnEstadoAbierta() {
        ServicioSolicitud servicio = new ServicioSolicitud();
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);

        Solicitud solicitud = servicio.CrearSolicitud(1L, cliente, "Fallo en el teclado");

        assertNotNull(solicitud);
        assertEquals(EstadoSolicitud.ABIERTA, solicitud.getEstado());
        assertEquals(cliente, solicitud.getCliente());
        assertEquals("Fallo en el teclado", solicitud.getDescripcion());
    }
}