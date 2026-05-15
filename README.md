![CI](https://github.com/Madro13/mgcss-track-grupo8/actions/workflows/ci.yml/badge.svg?branch=main)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Madro13_mgcss-track-grupo8&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Madro13_mgcss-track-grupo8)

# mgcss-track-grupo8

Antes de comenzar la refactorización, el proyecto presentaba las siguientes métricas:

- Complejidad ciclomática: 67
- Code smells: 6
- Debt ratio: 0,2%
- Deuda técnica estimada: 26 min

Tras la refactorización realizada, las métricas han mejorado notablemente:

- Sonar no da complejidad ciclomática
- Code smells: 0
- Debt ratio: 0,0%
- Deuda técnica estimada: 0

Actualmente tenemos 4 ramas distintas:
- main: Es la rama principal del proyecto hacia la que hacemos merge con todos los cambios que se van implementando.
- feature/Estructura-API: Es la rama donde vamos a implementar la API en base a lo que se nos indica en la sesión 10.
- feature/reopen-and-history: Implementación de los cambios produciodos por el cambio de opinión del cliente.
- feature/workflow: Se creó para crear el build/test con el archivo .yml
