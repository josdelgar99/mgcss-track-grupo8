## Comparativa de métricas y análisis de deuda técnica

El análisis realizado con SonarQube muestra una situación general muy favorable del proyecto. En primer lugar, no se detectan bugs ni vulnerabilidades, por lo que las métricas de fiabilidad y seguridad obtienen una valoración A. Esto indica que el código no presenta problemas relevantes en esos dos aspectos y que el sistema se comporta de forma estable desde el punto de vista del análisis automático.

En cuanto a la cobertura, el proyecto alcanza un 93.0% de cobertura global, con un 92.6% de cobertura de líneas y un 96.4% de cobertura de condiciones. Estos valores son altos y reflejan que una parte muy amplia del código está cubierta por pruebas, lo que reduce el riesgo de errores no detectados y mejora la confianza en el sistema.

Respecto a la duplicación, SonarQube no detecta líneas ni bloques duplicados, con un 0.0% de duplicación. Este resultado es positivo porque indica que no hay copia innecesaria de código y que la estructura del proyecto mantiene un nivel adecuado de reutilización.

La complejidad ciclomática total del proyecto es de 104, mientras que la complejidad cognitiva es de 13. Aunque la complejidad ciclomática total puede parecer elevada, debe interpretarse teniendo en cuenta el tamaño del proyecto y el número de funciones y clases existentes. La complejidad cognitiva, en cambio, es bastante baja, lo que sugiere que la lógica resulta relativamente clara y legible para una persona que revise el código.

Por último, el análisis de mantenibilidad muestra 4 code smells y una deuda técnica estimada de 10 minutos, con un debt ratio de 0.0%. Esto significa que existen algunos puntos concretos que podrían mejorarse, pero sin que afecten de forma significativa a la calidad global del proyecto. En conjunto, el sistema presenta un estado muy sólido, con pocas incidencias, buena cobertura y una deuda técnica prácticamente muy buena.