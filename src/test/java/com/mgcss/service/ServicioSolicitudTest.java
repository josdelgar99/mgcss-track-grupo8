package com.mgcss.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mgcss.domain.Cliente;
import com.mgcss.domain.EstadoCliente;
import com.mgcss.domain.EstadoSolicitud;
import com.mgcss.domain.Solicitud;
import com.mgcss.domain.SolicitudRepository;
import com.mgcss.domain.Tecnico;

class ServicioSolicitudTest {

    @Test
    void debeGuardarSolicitudCreadaEnRepositorio() {
        SolicitudRepository repository = mock(SolicitudRepository.class);
        ServicioSolicitud servicio = new ServicioSolicitud(repository);

        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitudGuardada = new Solicitud(1L, cliente, "Fallo en el teclado", EstadoSolicitud.ABIERTA);

        when(repository.save(any(Solicitud.class))).thenReturn(solicitudGuardada);

        Solicitud resultado = servicio.crearSolicitud(1L, cliente, "Fallo en el teclado");

        assertEquals(EstadoSolicitud.ABIERTA, resultado.getEstado());
        assertEquals(cliente, resultado.getCliente());
        verify(repository).save(any(Solicitud.class));
    }

    @Test
    void debeGuardarSolicitudActualizadaCuandoSeAsignaTecnicoValido() {
    	SolicitudRepository repository = mock(SolicitudRepository.class);
        ServicioSolicitud servicio = new ServicioSolicitud(repository);

        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "No funciona teclado", EstadoSolicitud.ABIERTA);
        Tecnico tecnico = new Tecnico(1L, "Luis", "Hardware", true);

        when(repository.findById(1L)).thenReturn(Optional.of(solicitud));
        when(repository.save(any(Solicitud.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Solicitud resultado = servicio.asignarTecnico(1L, tecnico);

        assertEquals(tecnico, resultado.getTecnicoAsignado());
        verify(repository).findById(1L);
        verify(repository).save(solicitud);
    }

    @Test
    void debeLanzarExcepcionSiLaSolicitudNoExisteAlAsignarTecnico() {
    	SolicitudRepository repository = mock(SolicitudRepository.class);
        ServicioSolicitud servicio = new ServicioSolicitud(repository);
        Tecnico tecnico = new Tecnico(1L, "Luis", "Hardware", true);

        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> servicio.asignarTecnico(99L, tecnico));
        verify(repository).findById(99L);
        verify(repository, never()).save(any());
    }

    @Test
    void debeGuardarSolicitudActualizadaCuandoSeCierra() {
    	SolicitudRepository repository = mock(SolicitudRepository.class);
        ServicioSolicitud servicio = new ServicioSolicitud(repository);

        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Error pantalla", EstadoSolicitud.EN_PROCESO);
        Tecnico tecnico = new Tecnico(1L, "Luis", "Hardware", true);
        solicitud.asignarTecnico(tecnico);

        when(repository.findById(1L)).thenReturn(Optional.of(solicitud));
        when(repository.save(any(Solicitud.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Solicitud resultado = servicio.cerrarSolicitud(1L);

        assertEquals(EstadoSolicitud.CERRADA, resultado.getEstado());
        verify(repository).findById(1L);
        verify(repository).save(solicitud);
    }
    
    @Test
    void debeCrearSolicitudAbiertaYGuardarEnRepositorio() {
        SolicitudRepository repository = mock(SolicitudRepository.class);
        ServicioSolicitud servicio = new ServicioSolicitud(repository);
        
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud esperada = new Solicitud(1L, cliente, "Fallo disco", EstadoSolicitud.ABIERTA);
        
        when(repository.save(any(Solicitud.class))).thenReturn(esperada);
        
        Solicitud resultado = servicio.crearSolicitud(1L, cliente, "Fallo disco");
        
        assertEquals(EstadoSolicitud.ABIERTA, resultado.getEstado());
        verify(repository).save(argThat(s -> s.getDescripcion().equals("Fallo disco")));
    }
    
    @Test
    void debeReabrirSolicitudCerradaYGuardar() {
    	SolicitudRepository repository = mock(SolicitudRepository.class);
        ServicioSolicitud servicio = new ServicioSolicitud(repository);
        
        Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);
        Solicitud solicitud = new Solicitud(1L, cliente, "Arreglado", EstadoSolicitud.CERRADA);
        
        when(repository.findById(1L)).thenReturn(Optional.of(solicitud));
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        
        Solicitud resultado = servicio.reabrirSolicitud(1L);
        
        assertEquals(EstadoSolicitud.EN_PROCESO, resultado.getEstado());
        verify(repository).save(solicitud);
    }
}