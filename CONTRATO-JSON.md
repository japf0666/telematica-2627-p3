# Contrato JSON de alquileres — P3

**Versión 0.4 — adaptación docente de la alternativa B (11-11-2026).**
**Carácter:** contrato de uso de la clase auxiliar suministrada para P3. No cambia el dominio de P2.

## 1. Formato de intercambio

- Codificación **UTF-8**.
- Raíz: un **array JSON** con un objeto por alquiler, en orden de almacenamiento.
- Cada objeto generado tendrá **cuatro campos de cadena**: `dni`, `matricula`, `fechaInicio` y `fechaFin`.
- Fechas ISO de cuatro cifras de año `yyyy-MM-dd`; fecha final igual o posterior a la inicial.
- DNI y matrícula se usan literalmente, incluidos ceros iniciales y mayúsculas, para encontrar los clientes y vehículos de los **catálogos existentes**, que en los datos iniciales tienen identificadores únicos.
- No se guardan los catálogos completos ni disponibilidad, precios o políticas de cálculo.
- No se eliminan registros repetidos: su número y orden se conservan.
- La colección vacía se representa mediante `[]`.
- La recuperación **sustituye** los alquileres existentes, no los fusiona.

El profesorado entregará el fichero `ejemplos/alquileres.json` y este contrato antes de iniciar RF-06 y RF-07.

### Ejemplo

```json
[
  {
    "dni": "001",
    "matricula": "TNA2020",
    "fechaInicio": "2026-10-28",
    "fechaFin": "2026-10-30"
  }
]
```

## 2. Clase auxiliar facilitada por el profesorado

**Responsabilidad exclusiva:** convertir un conjunto de alquileres a texto JSON, y convertir texto JSON a una lista de alquileres asociados a los clientes y vehículos ya disponibles. **No abre, lee, escribe ni cierra ficheros.**

La conversión está implementada en `ConversorAlquileresJson`. El alumnado utiliza esta clase sin modificarla. Sus pruebas básicas se incluyen como comprobación del material suministrado y no implementan las operaciones de fichero ni la integración del gestor.

### API pública del auxiliar suministrado

Clase `pit2027.ConversorAlquileresJson`, instanciable y sin estado propio:

```java
public String serializar(List<Alquiler> alquileres);
public List<Alquiler> deserializar(String textoJson,
        List<Cliente> clientes, List<Vehiculo> vehiculos);
```

Uso: crear `new ConversorAlquileresJson()` y llamar a sus métodos. Ninguno
recibe rutas, flujos ni lectores/escritores, y ninguno declara `IOException`.
Ambos comunican entradas inválidas con `IllegalArgumentException` y un mensaje.
Las listas, sus elementos y los identificadores deben ser no nulos; las fechas
deben ser válidas, ordenadas y tener años entre 0000 y 9999.

`serializar` no recibe catálogos ni verifica pertenencia a ellos. Para recuperar
posteriormente el texto, los catálogos deben contener los identificadores guardados.
`deserializar` exige catálogos con identificadores únicos, devuelve una lista nueva
y alquileres nuevos que apuntan a los mismos clientes y vehículos recibidos.
No altera disponibilidad, catálogos ni estado del gestor, tampoco ante un fallo.

La implementación conserva comprobaciones internas de cuatro campos exactos,
tipos cadena, fechas, referencias y catálogos ambiguos; estas verificaciones son
infraestructura resuelta por el profesorado, no trabajo adicional del alumnado.
Se conserva la tolerancia sintáctica de `org.json`, sin añadir un analizador estricto.

**Errores del conversor:** ante datos que impidan la reconstrucción (texto ininterpretable, estructura o campos necesarios incorrectos, fechas inválidas, DNI o matrícula sin correspondencia), el método comunica el fallo mediante `IllegalArgumentException`, excepción no comprobada documentada en la API. No devuelve resultados parciales. El conversor conserva comprobaciones adicionales por robustez, sin convertirlas en obligaciones de implementación ni en pruebas específicas para los estudiantes.

La lectura/escritura de archivos y los `IOException` asociados corresponden a la **capa desarrollada por el estudiante**. Se permite que el gestor comunique las excepciones del conversor con un mensaje comprensible; no se exige transformar todas las excepciones en un tipo único.

## 3. Responsabilidades obligatorias de los estudiantes

1. Obtener la colección actual de alquileres y usar la clase auxiliar para generar el texto JSON.
2. Abrir un fichero de destino, escribir el texto en UTF-8 y gestionar/cerrar adecuadamente los recursos.
3. Abrir y leer un fichero UTF-8 y facilitar el texto a la clase auxiliar junto con las colecciones de clientes y vehículos.
4. Sustituir **solo después de una conversión completa y satisfactoria** la colección de alquileres. Ante un fallo de lectura o de conversión, conservar **íntegramente** el conjunto anterior.
5. Integrar ambas operaciones en la consola existente y comunicar errores recuperables sin terminar inesperadamente la aplicación.
6. Desarrollar pruebas JUnit y una prueba funcional reproducible de guardado–recuperación y errores básicos.

El alumnado **no tiene que programar** `JSONObject`, `JSONArray`, `JSONTokener`, extracción de campos ni validación sintáctica exhaustiva de JSON; sí debe comprender qué representa el fichero y qué recibe/devuelve el conversor.

## 4. Validación evaluable: núcleo mínimo

Se exige verificar mediante el comportamiento de la aplicación:

- Guardar y recuperar varios alquileres correctos, con su número, fechas y asociaciones con cliente y vehículo.
- Recuperar un fichero sustituye los alquileres anteriores, sin acumular registros.
- Los fallos habituales de lectura/escritura se comunican y permiten continuar.
- Un fichero inexistente o cuyo contenido no pueda interpretar la clase auxiliar no modifica los alquileres existentes.
- Una referencia a cliente o vehículo inexistente, detectada por la clase auxiliar, impide sustituir el conjunto anterior.
- Una recuperación que falla **después** de encontrar registros válidos tampoco puede sustituir parcialmente el conjunto: la garantía de estado es global.

## 5. Fuera de la exigencia específica al alumnado

No se exige desarrollar ni evaluar individualmente la detección de:

- Sintaxis JSON no estándar tolerada por `org.json` (comillas simples, claves sin comillas o comas finales).
- Campos adicionales o todos los casos posibles de tipos y valores nulos; estas verificaciones pueden quedar implementadas en la clase proporcionada.
- Identificadores duplicados en catálogos fabricados artificialmente, puesto que el catálogo inicial docente es único.
- Fechas extremas (años 0000/9999, años negativos o mayores) o desbordamientos de precios, salvo lo ya resuelto en el proyecto inicial de P2.
- Garantía de conservación del *fichero anterior* ante cualquier fallo físico de escritura. La garantía de recuperación se refiere a los **alquileres en memoria**, no al fichero de destino.

Un fichero de entrada vacío o sintácticamente incorrecto podrá ser rechazado por el conversor. **No se exige al alumnado desarrollar un analizador JSON estricto**. Los archivos generados por la aplicación y el ejemplo facilitado deben utilizar JSON estándar.

## 6. Separación de pruebas

- **Obligatorias del estudiante:** ida y vuelta, sustitución sin duplicar, asociaciones correctas, archivo inexistente, y conservación del estado tras un fallo de recuperación; error de escritura verificable.
- **Recomendables:** caso `[]`, registros repetidos, archivo con un registro válido seguido de uno incorrecto, fecha errónea y otra referencia desconocida.
- **Del profesorado:** pruebas internas de conversión y validaciones extraordinarias, incluidas las tres de caracterización de tolerancia de `org.json`.

