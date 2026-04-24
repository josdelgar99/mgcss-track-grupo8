package com.mgcss.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSolicitudRepository extends JpaRepository<SolicitudEntity, Long> {
}