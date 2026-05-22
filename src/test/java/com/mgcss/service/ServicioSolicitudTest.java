package com.mgcss.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mgcss.domain.*;

class ServicioSolicitudTest {

    private SolicitudRepository repositorioSolicitud;
    private ServicioSolicitud servicioSolicitud;

    @BeforeEach
    void setUp() {
        repositorioSolicitud = Mockito.mock(SolicitudRepository.class);
        servicioSolicitud = new ServicioSolicitud(repositorioSolicitud);
    }

    @Test
    void debeCrearSolicitudConDescripcion() {
        when(repositorioSolicitud.save(any(Solicitud.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Solicitud resultado = servicioSolicitud.crearSolicitud("Pantalla rota");

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isNull();
        assertThat(resultado.getDescripcion()).isEqualTo("Pantalla rota");
        assertThat(resultado.getEstado()).isEqualTo(EstadoSolicitud.ABIERTA);
        assertThat(resultado.getCliente()).isNotNull();
        assertThat(resultado.getCliente().getId()).isEqualTo(1L);
        assertThat(resultado.getCliente().getNombre()).isEqualTo("Cliente API");
        assertThat(resultado.getCliente().getEmail()).isEqualTo("api@test.com");
        assertThat(resultado.getCliente().getEstado()).isEqualTo(EstadoCliente.STANDARD);

        verify(repositorioSolicitud).save(any(Solicitud.class));
    }

    @Test
    void debeCrearSolicitudConIdClienteYDescripcion() {
        Cliente cliente = new Cliente(2L, "Ana", "ana@test.com", EstadoCliente.STANDARD);

        when(repositorioSolicitud.save(any(Solicitud.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Solicitud resultado = servicioSolicitud.crearSolicitud(5L, cliente, "Teclado roto");

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(5L);
        assertThat(resultado.getCliente()).isEqualTo(cliente);
        assertThat(resultado.getDescripcion()).isEqualTo("Teclado roto");
        assertThat(resultado.getEstado()).isEqualTo(EstadoSolicitud.ABIERTA);

        verify(repositorioSolicitud).save(any(Solicitud.class));
    }

    @Test
    void debeAsignarTecnicoASolicitudExistente() {
        Cliente cliente = new Cliente(1L, "Pepe", "pepe@test.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Pantalla rota", EstadoSolicitud.ABIERTA);
        Tecnico tecnico = new Tecnico(3L, "Luis", "Hardware", true);

        when(repositorioSolicitud.findById(1L)).thenReturn(Optional.of(solicitud));
        when(repositorioSolicitud.save(any(Solicitud.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Solicitud resultado = servicioSolicitud.asignarTecnico(1L, tecnico);

        assertThat(resultado).isNotNull();
        verify(repositorioSolicitud).findById(1L);
        verify(repositorioSolicitud).save(solicitud);
    }

    @Test
    void debeCerrarSolicitudExistente() {
        Cliente cliente = new Cliente(1L, "Pepe", "pepe@test.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Pantalla rota", EstadoSolicitud.ABIERTA);

        when(repositorioSolicitud.findById(1L)).thenReturn(Optional.of(solicitud));
        when(repositorioSolicitud.save(any(Solicitud.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Solicitud resultado = servicioSolicitud.cerrarSolicitud(1L);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getEstado()).isEqualTo(EstadoSolicitud.CERRADA);
        verify(repositorioSolicitud).findById(1L);
        verify(repositorioSolicitud).save(solicitud);
    }

    @Test
    void debeReabrirSolicitudExistente() {
        Cliente cliente = new Cliente(1L, "Pepe", "pepe@test.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Pantalla rota", EstadoSolicitud.CERRADA);

        when(repositorioSolicitud.findById(1L)).thenReturn(Optional.of(solicitud));
        when(repositorioSolicitud.save(any(Solicitud.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Solicitud resultado = servicioSolicitud.reabrirSolicitud(1L);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getEstado()).isEqualTo(EstadoSolicitud.ABIERTA);
        verify(repositorioSolicitud).findById(1L);
        verify(repositorioSolicitud).save(solicitud);
    }

    @Test
    void debeLanzarExcepcionSiNoExisteSolicitudAlAsignarTecnico() {
        Tecnico tecnico = new Tecnico(3L, "Luis", "Hardware", true);
        when(repositorioSolicitud.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> servicioSolicitud.asignarTecnico(99L, tecnico))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Solicitud no encontrada");
    }

    @Test
    void debeLanzarExcepcionSiNoExisteSolicitudAlCerrar() {
        when(repositorioSolicitud.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> servicioSolicitud.cerrarSolicitud(99L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Solicitud no encontrada");
    }

    @Test
    void debeLanzarExcepcionSiNoExisteSolicitudAlReabrir() {
        when(repositorioSolicitud.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> servicioSolicitud.reabrirSolicitud(99L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Solicitud no encontrada");
    }
}