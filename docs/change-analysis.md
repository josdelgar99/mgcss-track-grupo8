# Change analysis

## 1. ¿Qué métodos del dominio se ven afectados?
Los métodos del dominio más claramente afectados son `reabrir()` y `cerrar()`, porque participan directamente en las transiciones de estado de una `Solicitud`. También puede verse afectado el constructor de `Solicitud`, ya que si se implementa un histórico de estados, conviene registrar el estado inicial desde el momento de creación. Si se decide centralizar la lógica de transición en un método auxiliar, ese método también formará parte del cambio.

## 2. ¿Qué reglas actuales cambian?
Antes del cambio, una solicitud cerrada podía reabrirse volviendo al estado `ABIERTA`. Con el nuevo requisito, una solicitud en estado `CERRADA` debe pasar a `EN_PROCESO` al reabrirse. Además, ahora cada cambio de estado debe quedar registrado en un histórico ordenado, incluyendo el estado inicial de la solicitud.

## 3. ¿Qué tests deberían romperse?
El test que debería romperse de forma directa es el que comprobaba que `reabrir()` dejaba la solicitud en estado `ABIERTA`, porque esa regla ya no es válida. También pueden fallar tests antiguos si asumen que no existe histórico de estados o si verifican expectativas ligadas al comportamiento anterior de `Solicitud`. Según la guía, estos tests solo deben ajustarse cuando el nuevo requisito lo exija expresamente.

## 4. ¿Qué parte del modelo debe extenderse?
La clase principal a extender es `Solicitud`, ya que es la entidad central afectada por el cambio. La opción elegida es añadir una lista interna con el histórico de estados, porque permite mantener la responsabilidad dentro del dominio sin introducir nuevas clases innecesarias en esta fase. Esta solución simplifica la implementación inicial y facilita verificar el comportamiento mediante tests.

## 5. ¿Qué impacto tiene en persistencia?
De momento, se decide no persistir el histórico de estados y dejarlo documentado como decisión técnica. Esta decisión se justifica porque el requisito obliga a mantener el histórico en el modelo, pero no exige todavía almacenarlo en base de datos. Si en una iteración posterior fuera necesario persistirlo, habría que ampliar `SolicitudEntity`, adaptar el mapeo JPA y añadir tests de integración específicos para validar su almacenamiento.


## Fase 4 - Impacto en persistencia

De momento no se persiste el histórico de estados en base de datos. Se ha decidido mantenerlo como una estructura interna del dominio dentro de `Solicitud`, ya que el requisito funcional de esta fase exige registrar el histórico, pero no obliga a almacenarlo de forma persistente.

Esta decisión reduce el impacto sobre la capa de infraestructura y evita modificar `SolicitudEntity`, el mapeo JPA y los tests de integración en esta iteración. Además, mantiene el cambio incremental y limita la complejidad introducida por la evolución del requisito.

Si en una versión posterior fuera necesario persistir el histórico, será necesario ampliar la entidad de persistencia, adaptar el repositorio y añadir un test de integración específico para validar el almacenamiento correcto.

## Fase 5 - Revisión de métricas

El análisis de Sonar identificó a `Solicitud` como la clase más compleja del proyecto, con una complejidad ciclomática de 36. Esta complejidad refleja la acumulación de responsabilidades de dominio en una sola clase: validación de datos, asignación de técnico, cambios de estado, gestión de fechas y registro del histórico.

Para mejorar la mantenibilidad se aplicó una refactorización incremental centrada en simplificar la lógica de transición de estados y agrupar validaciones relacionadas en métodos privados más expresivos. La técnica utilizada fue principalmente `Extract Method`, junto con `Rename for clarity` y encapsulación de lógica repetida.

Esta refactorización no modifica el comportamiento observable, pero deja la clase mejor estructurada, más fácil de leer y más preparada para futuros cambios en el modelo de estados. Además, reduce el riesgo de introducir errores al modificar reglas de negocio relacionadas con reapertura, cierre o histórico.