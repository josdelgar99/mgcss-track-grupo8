package com.mgcss.infrastructure;

import java.util.Optional;
import org.springframework.stereotype.Component;
import com.mgcss.domain.*;

@Component
public class SolicitudAdapter implements SolicitudRepository {

    private final JpaSolicitudRepository jpaRepository;

    public SolicitudAdapter(JpaSolicitudRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Solicitud save(Solicitud solicitud) {
        SolicitudEntity entity = new SolicitudEntity();
        entity.setId(solicitud.getId());
        entity.setDescripcion(solicitud.getDescripcion());
        entity.setEstado(solicitud.getEstado());

        SolicitudEntity saved = jpaRepository.save(entity);

        return new Solicitud(
            saved.getId(),
            new Cliente(0L, "Cliente dummy", "dummy@test.com", EstadoCliente.STANDARD),
            saved.getDescripcion(),
            saved.getEstado()
        );
    }

    @Override
    public Optional<Solicitud> findById(Long id) {
        return jpaRepository.findById(id)
            .map(entity -> new Solicitud(
                entity.getId(),
                new Cliente(0L, "Cliente dummy", "dummy@test.com", EstadoCliente.STANDARD),
                entity.getDescripcion(),
                entity.getEstado()
            ));
    }
}