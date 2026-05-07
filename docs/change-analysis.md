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