package com.mgcss.infrastructure;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.mgcss.domain.EstadoSolicitud;


@DataJpaTest
@Tag("integration")
class JpaSolicitudRepositoryTest {

    @Autowired
    private JpaSolicitudRepository repository;

    @Test
    void debeGuardarYRecuperarSolicitud() {
        SolicitudEntity entity = new SolicitudEntity(null, "Pantalla rota", EstadoSolicitud.ABIERTA);

        SolicitudEntity guardada = repository.save(entity);
        var resultado = repository.findById(guardada.getId());

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getDescripcion()).isEqualTo("Pantalla rota");
        assertThat(resultado.get().getEstado()).isEqualTo(EstadoSolicitud.ABIERTA);
    }
}