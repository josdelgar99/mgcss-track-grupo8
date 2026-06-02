## Workflow de release

Hemos creado un workflow nuevo llamado `release.yml` para automatizar las releases del proyecto. Este workflow se activa cuando se sube un tag con formato `v*`, así que lo hemos conectado con el versionado que usamos para la entrega.

El workflow hace checkout del código, prepara Java 17 con Maven y compila el proyecto para generar el `.jar`. Después renombra el artefacto con la versión correspondiente y construye la imagen Docker.

Al final, el workflow crea la release en GitHub y sube el `.jar` como archivo adjunto. Con esto dejamos el proceso bastante automatizado y la versión queda trazada desde el tag hasta el artefacto final.