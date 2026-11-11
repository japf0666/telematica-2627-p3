# Proyecto inicial de P3

Base Maven de P2 con el auxiliar JSON proporcionado por el profesorado. Este documento explica el material inicial; no sustituye al futuro enunciado.

## Entorno y ejecución

Java 8 o compatible, Maven y VS Code. Se conserva el POM original: JUnit 4.11 y org.json 20230227, sin dependencias nuevas.

- Desde la raíz: `mvn clean test`.
- Para la consola: abrir el proyecto Maven en VS Code y ejecutar el método `main` de `pit2027.GestorAlquileresApp` con su classpath Maven.
- Introducir una respuesta por pregunta. El teclado heredado usa lectores separados: no pegar toda una sesión de respuestas de una vez.

## Qué está resuelto

Se conserva la solución P2: dominio, jerarquía, factoría, cuatro políticas de precios, catálogos de seis clientes y nueve vehículos, identificación, selección por tipo/categoría, registro e históricos por cliente y vehículo. El gestor todavía utiliza arrays y un contador, con capacidad de 1000 alquileres. La devolución sigue sin implementar, como en P2.

Se añade `ConversorAlquileresJson`, completo, sin E/S de ficheros, y el ejemplo `ejemplos/alquileres.json`. El contrato de uso está en `CONTRATO-JSON.md` y en el Javadoc del auxiliar. No hay que modificarlo ni diseñar el formato JSON.

Métodos de instancia del auxiliar:

```java
public String serializar(List<Alquiler> alquileres);
public List<Alquiler> deserializar(String textoJson,
        List<Cliente> clientes, List<Vehiculo> vehiculos);
```

El auxiliar trabaja con listas completas, sin elementos nulos. Al recuperar, reutiliza los objetos de los catálogos. No realiza E/S ni cambia el gestor. Los errores se comunican mediante `IllegalArgumentException`, sin resultados parciales. Puede utilizarse independientemente del gestor desde el inicio; su integración se hará al desarrollar las colecciones.

## Qué queda pendiente

RF-01 y RF-02: migración de alquileres y catálogos a colecciones genéricas. RF-03, RF-04 y RF-05: nuevas consultas, ordenación e informe. RF-06 y RF-07: escritura y lectura de ficheros. RF-08 y RF-09: integración de las funciones nuevas y sus errores. Las consultas existentes de P2 se conservan y deberán seguir funcionando.

No se proporcionan clases de persistencia ni esqueletos que condicionen el diseño. Las pruebas heredadas del gestor usan arrays: se adaptarán cuando cambien los contratos. Las pruebas `ConversorAlquileresJsonTest` verifican exclusivamente la infraestructura proporcionada; no son pruebas de aceptación de la P3 pendiente.

La planificación aprobada es de 23,5 horas-persona y 8,5 de reserva, máximo 32 entre dos estudiantes, incluyendo pruebas, integración, Git y documentación. No se exigen bibliotecas nuevas, Swing, SQLite, concurrencia ni patrones nuevos.

## Comprobación inicial reproducible

En consola, elegir no salir, cliente 0 y opción 1; seleccionar Turismo, categoría Normal, vehículo 0 y 3 días. Repetir tres veces: los costes son 150, 150 y 120. Después, cliente 0 y opción 3 muestran los tres registros. Salir con S. Cada respuesta se introduce cuando aparece la pregunta.

La batería inicial contiene 27 pruebas heredadas de P2 y 4 pruebas básicas del auxiliar suministrado. No incluye pruebas de persistencia o consultas nuevas aún sin desarrollar.
