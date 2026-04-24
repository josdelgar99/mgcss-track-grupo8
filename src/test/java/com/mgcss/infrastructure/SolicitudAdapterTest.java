package com.mgcss.infrastructure;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mgcss.domain.*;

class SolicitudAdapterTest {

    @Test
    void debeGuardarSolicitudYDevolverDominio() {
        JpaSolicitudRepository jpaRepository = mock(JpaSolicitudRepository.class);
        SolicitudAdapter adapter = new SolicitudAdapter(jpaRepository);

        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Pantalla rota", EstadoSolicitud.ABIERTA);
        SolicitudEntity entityGuardada = new SolicitudEntity(1L, "Pantalla rota", EstadoSolicitud.ABIERTA);

        when(jpaRepository.save(any(SolicitudEntity.class))).thenReturn(entityGuardada);

        Solicitud resultado = adapter.save(solicitud);

        assertEquals(1L, resultado.getId());
        assertEquals("Pantalla rota", resultado.getDescripcion());
        assertEquals(EstadoSolicitud.ABIERTA, resultado.getEstado());
        verify(jpaRepository).save(any(SolicitudEntity.class));
    }

    @Test
    void debeDevolverSolicitudCuandoExiste() {
        JpaSolicitudRepository jpaRepository = mock(JpaSolicitudRepository.class);
        SolicitudAdapter adapter = new SolicitudAdapter(jpaRepository);

        when(jpaRepository.findById(1L))
                .thenReturn(Optional.of(new SolicitudEntity(1L, "Teclado roto", EstadoSolicitud.EN_PROCESO)));

        Optional<Solicitud> resultado = adapter.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Teclado roto", resultado.get().getDescripcion());
        assertEquals(EstadoSolicitud.EN_PROCESO, resultado.get().getEstado());
        verify(jpaRepository).findById(1L);
    }

    @Test
    void debeDevolverVacioCuandoNoExiste() {
        JpaSolicitudRepository jpaRepository = mock(JpaSolicitudRepository.class);
        SolicitudAdapter adapter = new SolicitudAdapter(jpaRepository);

        when(jpaRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Solicitud> resultado = adapter.findById(99L);

        assertTrue(resultado.isEmpty());
        verify(jpaRepository).findById(99L);
    }
}