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

Actualmente tenemos 5 ramas distintas:
- main: Es la rama principal del proyecto hacia la que hacemos merge con todos los cambios que se van implementando.
- feature/apio-dto-contract: Es la rama en la que trabajo Apio la parte de los DTOs. (El docker también se hizo en esta rama)
- feature/madro-rest-controller: Es la rama en la que trabaja Madro la parte de Controllers.
- feature/jose-tests-docs-docker: Es la rama en la que trabaja Jose la parte de los Tests.
- feature/workflow: Se creó para crear el build/test con el archivo .yml
