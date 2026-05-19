# Contrato inicial de Solicitud

## Endpoints a exponer
- POST /api/solicitudes
- GET /api/solicitudes/{id}
- GET /api/solicitudes
- PUT /api/solicitudes/{id}/tecnico
- PUT /api/solicitudes/{id}/estado
- PATCH /api/solicitudes/{id}/reabrir

## Datos mínimos del contrato
- Crear solicitud: descripcion
- Consultar solicitud: id
- Asignar técnico: idTecnico
- Cambiar estado: nuevoEstado
- Reabrir solicitud: sin datos adicionales
