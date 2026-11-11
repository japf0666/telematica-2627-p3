package pit2027;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Auxiliar resuelto por el profesorado: convierte alquileres entre Java y texto
 * JSON según CONTRATO-JSON.md v0.4. El alumnado lo utiliza sin modificarlo.
 * No realiza E/S ni modifica listas, objetos del dominio o estado del gestor.
 * No es un analizador sintáctico estricto: conserva la tolerancia de org.json.
 */
public class ConversorAlquileresJson {
    /**
     * Produce un array JSON de cuatro cadenas por alquiler; una lista vacía
     * produce {@code []}. Conserva orden y registros repetidos.
     *
     * @param alquileres lista no nula de alquileres completos, con identificadores
     *                  no nulos, fechas ordenadas y años entre 0000 y 9999
     * @return texto JSON estándar, independiente de la representación toString
     * @throws IllegalArgumentException si un alquiler no cumple esas condiciones
     */
    public String serializar(List<Alquiler> alquileres) {
        if (alquileres == null) throw new IllegalArgumentException("Falta la lista de alquileres.");
        JSONArray registros = new JSONArray();
        for (Alquiler a : alquileres) {
            validarAlquiler(a);
            JSONObject registro = new JSONObject();
            registro.put("dni", a.getCliente().getDni());
            registro.put("matricula", a.getVehiculo().getMatricula());
            registro.put("fechaInicio", a.getFechaInicio().toString());
            registro.put("fechaFin", a.getFechaFin().toString());
            registros.put(registro);
        }
        return registros.toString(2);
    }

    /**
     * Reconstruye una lista nueva y completa. Cada alquiler utiliza exactamente
     * los objetos de los catálogos; no se crean clientes ni vehículos.
     * Si falla cualquier registro, lanza una excepción sin devolver lista parcial
     * ni modificar las entradas. El llamante decide cuándo sustituir su estado.
     *
     * @param textoJson texto no nulo con array raíz y registros del contrato
     * @param clientes catálogo no nulo con clientes y DNI no nulos y únicos
     * @param vehiculos catálogo no nulo con vehículos y matrículas no nulas y únicas
     * @return lista nueva, en el orden del documento y con sus duplicados
     * @throws IllegalArgumentException ante texto ininterpretable, campos o fechas
     *         inválidos, referencias desconocidas o catálogos inválidos/ambiguos
     */
    public List<Alquiler> deserializar(String textoJson, List<Cliente> clientes,
            List<Vehiculo> vehiculos) {
        if (textoJson == null || clientes == null || vehiculos == null) {
            throw new IllegalArgumentException("Falta el texto JSON o un catálogo.");
        }
        Map<String, Cliente> porDni = new HashMap<>();
        for (Cliente c : clientes) {
            if (c == null || c.getDni() == null) {
                throw new IllegalArgumentException("Cliente sin DNI en el catálogo.");
            }
            if (porDni.put(c.getDni(), c) != null) {
                throw new IllegalArgumentException("DNI ambiguo en el catálogo: " + c.getDni());
            }
        }
        Map<String, Vehiculo> porMatricula = new HashMap<>();
        for (Vehiculo v : vehiculos) {
            if (v == null || v.getMatricula() == null) {
                throw new IllegalArgumentException("Vehículo sin matrícula en el catálogo.");
            }
            if (porMatricula.put(v.getMatricula(), v) != null) {
                throw new IllegalArgumentException("Matrícula ambigua en el catálogo: " + v.getMatricula());
            }
        }
        try {
            // org.json tolera comillas simples, claves sin comillas y comas finales.
            // No se exige implementar un analizador estricto en esta práctica.
            JSONTokener tokener = new JSONTokener(textoJson);
            JSONArray registros = new JSONArray(tokener);
            if (tokener.nextClean() != 0) {
                throw new IllegalArgumentException("Contenido después del array JSON.");
            }
            List<Alquiler> resultado = new ArrayList<>();
            for (int i = 0; i < registros.length(); i++) {
                JSONObject registro = registros.getJSONObject(i);
                if (registro.length() != 4) {
                    throw new IllegalArgumentException("Cada registro debe tener exactamente cuatro campos.");
                }
                String dni = cadena(registro, "dni");
                String matricula = cadena(registro, "matricula");
                LocalDate inicio = fecha(cadena(registro, "fechaInicio"));
                LocalDate fin = fecha(cadena(registro, "fechaFin"));
                if (fin.isBefore(inicio)) {
                    throw new IllegalArgumentException("La fecha final precede a la inicial.");
                }
                Cliente cliente = porDni.get(dni);
                Vehiculo vehiculo = porMatricula.get(matricula);
                if (cliente == null) throw new IllegalArgumentException("DNI desconocido: " + dni);
                if (vehiculo == null) throw new IllegalArgumentException("Matrícula desconocida: " + matricula);
                resultado.add(new Alquiler(vehiculo, cliente, inicio, fin));
            }
            return resultado;
        } catch (JSONException | DateTimeException e) {
            throw new IllegalArgumentException("No se pueden convertir los alquileres: " + e.getMessage(), e);
        }
    }

    private String cadena(JSONObject objeto, String campo) {
        Object valor = objeto.get(campo);
        if (!(valor instanceof String)) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser una cadena no nula.");
        }
        return (String) valor;
    }

    private LocalDate fecha(String texto) {
        if (!texto.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            throw new IllegalArgumentException("Fecha fuera del formato yyyy-MM-dd: " + texto);
        }
        return LocalDate.parse(texto);
    }

    private void validarAlquiler(Alquiler a) {
        if (a == null || a.getCliente() == null || a.getVehiculo() == null
                || a.getCliente().getDni() == null || a.getVehiculo().getMatricula() == null
                || a.getFechaInicio() == null || a.getFechaFin() == null
                || a.getFechaFin().isBefore(a.getFechaInicio())) {
            throw new IllegalArgumentException("Alquiler incompleto o fechas inválidas.");
        }
        if (a.getFechaInicio().getYear() < 0 || a.getFechaInicio().getYear() > 9999
                || a.getFechaFin().getYear() < 0 || a.getFechaFin().getYear() > 9999) {
            throw new IllegalArgumentException("Las fechas del alquiler deben tener años entre 0000 y 9999.");
        }
    }
}
