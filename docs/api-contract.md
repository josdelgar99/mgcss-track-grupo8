# API Contract - Solicitudes

## POST /api/solicitudes
Crea una nueva solicitud.

### Request
```json
{
  "descripcion": "Incidencia en impresora"
}
```

### Response
```json
{
  "id": 1,
  "descripcion": "Incidencia en impresora",
  "estado": "ABIERTA"
}
```

## GET /api/solicitudes/{id}
Obtiene una solicitud por identificador.

### Response
```json
{
  "id": 1,
  "descripcion": "Incidencia en impresora",
  "estado": "ABIERTA"
}
```

## GET /api/solicitudes
Lista todas las solicitudes.

## PUT /api/solicitudes/{id}/tecnico
Asigna un técnico a una solicitud.

### Request
```json
{
  "tecnicoId": 3
}
```

## PUT /api/solicitudes/{id}/estado
Cambia el estado de una solicitud.

### Request
```json
{
  "estado": "EN_PROCESO"
}
```

## PATCH /api/solicitudes/{id}/reabrir
Reabre una solicitud cerrada.