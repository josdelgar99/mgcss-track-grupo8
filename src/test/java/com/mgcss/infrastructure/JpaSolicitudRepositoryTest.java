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
        // Arrange
        SolicitudEntity entity = new SolicitudEntity(1L, "Pantalla rota", EstadoSolicitud.ABIERTA);

        // Act
        repository.save(entity);
        var resultado = repository.findById(1L);

        // Assert
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getDescripcion()).isEqualTo("Pantalla rota");
        assertThat(resultado.get().getEstado()).isEqualTo(EstadoSolicitud.ABIERTA);
    }
}