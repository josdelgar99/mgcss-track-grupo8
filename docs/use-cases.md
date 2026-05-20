# Use cases - Solicitudes

## 1. Crear solicitud
POST /api/solicitudes

```json
{
  "descripcion": "Incidencia en impresora"
}
```

Esperado:
- HTTP 201 Created
- La solicitud se crea en estado ABIERTA

## 2. Consultar solicitud
GET /api/solicitudes/1

Esperado:
- HTTP 200 OK
- Devuelve id, descripcion y estado

## 3. Listar solicitudes
GET /api/solicitudes

Esperado:
- HTTP 200 OK
- Devuelve una lista de solicitudes

## 4. Asignar técnico
PUT /api/solicitudes/1/tecnico

```json
{
  "tecnicoId": 3
}
```

Esperado:
- HTTP 200 OK
- La solicitud queda asignada

## 5. Cambiar estado
PUT /api/solicitudes/1/estado

```json
{
  "estado": "EN_PROCESO"
}
```

Esperado:
- HTTP 200 OK
- El cambio solo se aplica si la transición es válida

## 6. Reabrir solicitud
PATCH /api/solicitudes/1/reabrir

Esperado:
- HTTP 200 OK
- La solicitud vuelve a estar operativa si estaba cerrada

## 7. Validación de entrada
POST /api/solicitudes

```json
{
  "descripcion": ""
}
```

Esperado:
- HTTP 400 Bad Request
- Error por campo obligatorio vacío