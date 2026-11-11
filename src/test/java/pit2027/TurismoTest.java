package pit2027;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TurismoTest {

    @Test
    public void constructorInicializaLosDatosEspecificos() {
        Turismo turismo = new Turismo("1234ABC", Categoria.ESPECIAL, 2020, 5, 450.0);

        assertEquals("1234ABC", turismo.getMatricula());
        assertEquals(Categoria.ESPECIAL, turismo.getCategoria());
        assertEquals(2020, turismo.getAnnio());
        assertEquals(5, turismo.getPlazas());
        assertEquals(450.0, turismo.getCapacidadMaletero(), 0.0);
        assertEquals("Turismo", turismo.getTipo());
    }

    @Test
    public void constructorJsonReconstruyeElTurismo() {
        Turismo turismo = new Turismo(
                "{\"matricula\":\"1234ABC\",\"categoria\":\"NORMAL\","
                        + "\"annio\":2020,\"disponible\":true,\"plazas\":5,"
                        + "\"capacidadMaletero\":450.0}");

        assertEquals("1234ABC", turismo.getMatricula());
        assertEquals(Categoria.NORMAL, turismo.getCategoria());
        assertEquals(2020, turismo.getAnnio());
        assertEquals(5, turismo.getPlazas());
        assertEquals(450.0, turismo.getCapacidadMaletero(), 0.0);
        assertEquals("Turismo", turismo.getTipo());
        assertEquals(true, turismo.isDisponible());
    }
}