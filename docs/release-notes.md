## Justificación del versionado semántico

Después de revisar los commits recientes, consideramos que la evolución del proyecto encaja en una versión **MINOR**, es decir, una evolución del tipo **1.1.0**, y no en una versión **PATCH** ni **MAJOR**.

No sería **PATCH (1.0.1)** porque los cambios que se han introducido no son solo correcciones pequeñas, sino que se han añadido funcionalidades nuevas bastante claras, como la API REST, la separación entre entidades y DTOs, la documentación con Swagger/OpenAPI, los tests de controlador y la containerización con Docker. Todo eso amplía de forma real las capacidades del proyecto.

Tampoco sería **MAJOR (2.0.0)** porque no hemos hecho cambios incompatibles con una versión pública anterior ni hemos roto el comportamiento esperado del sistema. Más bien hemos ampliado y preparado mejor el proyecto sobre la base que ya teníamos.

Aun así, la versión que hemos publicado como **v1.0.0** tiene sentido porque representa la primera versión estable y completa del proyecto, es decir, el primer punto en el que ya tenemos una base funcional, documentada, testeada y preparada para ejecutarse también en Docker.

Por tanto, nuestra conclusión es:
- **v1.0.0** se usa como primera release estable del proyecto.
- La siguiente evolución natural, si seguimos iterando sobre esta base sin romper compatibilidad, sería **1.1.0**.

## Cambios incluidos en la release

- Añadimos el controlador base de solicitudes.
- Creamos el endpoint para crear solicitudes.
- Separámos el contrato externo usando DTOs y un mapper.
- Documentamos la API con OpenAPI / Swagger.
- Añadimos tests de controlador y corregimos varios fallos en tests de servicio.
- Dejamos preparado el proyecto para ejecutarse con Docker.
- Actualizamos el README y la documentación del proyecto.

## Estado de la versión

Con esta release ya tenemos una base bastante completa y estable del proyecto. La aplicación se puede compilar, documentar, probar y ejecutar en contenedor, así que esta versión deja el proyecto listo para seguir evolucionando desde un punto sólido.