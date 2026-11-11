package pit2027;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

/** Pruebas internas del auxiliar suministrado por el profesorado, sin ficheros. */
public class ConversorAlquileresJsonTest {
    private final ConversorAlquileresJson conversor = new ConversorAlquileresJson();
    private final Cliente c = new Cliente("Ana", "001");
    private final Vehiculo v = new Turismo("TNA2020", Categoria.NORMAL, 2020, 5, 450);
    private final LocalDate dia = LocalDate.of(2026, 10, 28);
    private final String registro = "{\"dni\":\"001\",\"matricula\":\"TNA2020\","
            + "\"fechaInicio\":\"2026-10-28\",\"fechaFin\":\"2026-10-30\"}";

    @Test public void serializarProduceCuatroCadenasSinModificarOrigen() {
        Alquiler a = new Alquiler(v, c, dia, dia.plusDays(2));
        List<Alquiler> origen = Collections.singletonList(a);
        JSONArray json = new JSONArray(conversor.serializar(origen));
        assertEquals(1, json.length());
        JSONObject objeto = json.getJSONObject(0);
        assertEquals(4, objeto.length());
        assertEquals("001", objeto.getString("dni"));
        assertEquals("TNA2020", objeto.getString("matricula"));
        assertEquals("2026-10-28", objeto.getString("fechaInicio"));
        assertEquals("2026-10-30", objeto.getString("fechaFin"));
        assertSame(a, origen.get(0));
        assertTrue(v.isDisponible());
    }

    @Test public void idaYVueltaConTresTiposConservaOrdenDuplicadosYReferencias() {
        Vehiculo moto = new Motocicleta("MNA2021", Categoria.NORMAL, 2021, 200);
        Vehiculo industrial = new VehiculoIndustrial("CNA2019", Categoria.NORMAL, 2019, 1400, 2);
        Cliente otro = new Cliente("Aitor", "002");
        List<Cliente> clientes = Collections.unmodifiableList(Arrays.asList(c, otro));
        List<Vehiculo> vehiculos = Collections.unmodifiableList(Arrays.asList(v, moto, industrial));
        Alquiler a = new Alquiler(v, c, dia, dia.plusDays(2));
        List<Alquiler> origen = Arrays.asList(a, new Alquiler(moto, otro, dia, dia),
                new Alquiler(industrial, c, dia.plusDays(1), dia.plusDays(2)), a);
        moto.setDisponible(false);
        List<Alquiler> resultado = conversor.deserializar(conversor.serializar(origen), clientes, vehiculos);
        assertEquals(4, resultado.size());
        assertNotSame(origen, resultado);
        for (int i = 0; i < origen.size(); i++) {
            assertNotSame(origen.get(i), resultado.get(i));
            assertSame(origen.get(i).getCliente(), resultado.get(i).getCliente());
            assertSame(origen.get(i).getVehiculo(), resultado.get(i).getVehiculo());
            assertEquals(origen.get(i).getFechaInicio(), resultado.get(i).getFechaInicio());
            assertEquals(origen.get(i).getFechaFin(), resultado.get(i).getFechaFin());
        }
        assertFalse(moto.isDisponible());
        resultado.clear();
        assertEquals(4, origen.size());
        assertEquals(2, clientes.size());
        assertEquals(3, vehiculos.size());
    }

    @Test public void vacioProduceUnaListaNuevaCadaVez() {
        assertEquals("[]", conversor.serializar(Collections.<Alquiler>emptyList()));
        List<Alquiler> primera = conversor.deserializar("[]", Arrays.asList(c), Arrays.asList(v));
        List<Alquiler> segunda = conversor.deserializar("[]", Arrays.asList(c), Arrays.asList(v));
        assertTrue(primera.isEmpty());
        assertNotSame(primera, segunda);
    }

    @Test public void erroresEsencialesNoDevuelvenListaParcialNiModificanCatalogos() {
        List<Cliente> clientes = Collections.singletonList(c);
        List<Vehiculo> vehiculos = Collections.singletonList(v);
        String[] invalidos = {"", "no interpretable", "{}", "[{}]",
            "[" + registro + "," + registro.replace("001", "desconocido") + "]",
            "[" + registro + "," + registro.replace("TNA2020", "desconocida") + "]",
            "[" + registro.replace("2026-10-28", "2026-02-30") + "]",
            "[" + registro.replace("2026-10-30", "2026-10-01") + "]"};
        for (String texto : invalidos) {
            try {
                conversor.deserializar(texto, clientes, vehiculos);
                fail("Debe rechazar el documento sin devolver resultados parciales");
            } catch (IllegalArgumentException esperado) {
                assertNotNull(esperado.getMessage());
            }
            assertSame(c, clientes.get(0));
            assertSame(v, vehiculos.get(0));
            assertEquals("001", c.getDni());
            assertEquals("TNA2020", v.getMatricula());
            assertTrue(v.isDisponible());
        }
    }
}
