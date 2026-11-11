package pit2027;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VehiculoIndustrialTest {

    @Test
    public void constructorInicializaLosDatosEspecificos() {
        VehiculoIndustrial vehiculo = new VehiculoIndustrial(
                "9012GHI", Categoria.NORMAL, 2019, 2000, 4);

        assertEquals("9012GHI", vehiculo.getMatricula());
        assertEquals(Categoria.NORMAL, vehiculo.getCategoria());
        assertEquals(2019, vehiculo.getAnnio());
        assertEquals(2000, vehiculo.getCapacidadCarga());
        assertEquals(4, vehiculo.getNumeroEjes());
        assertEquals("VehiculoIndustrial", vehiculo.getTipo());
    }

    @Test
    public void constructorJsonReconstruyeElVehiculoIndustrial() {
        VehiculoIndustrial vehiculo = new VehiculoIndustrial(
                "{\"matricula\":\"9012GHI\",\"categoria\":\"NORMAL\","
                        + "\"annio\":2019,\"disponible\":true,\"capacidadCarga\":2000,"
                        + "\"numeroEjes\":4}");

        assertEquals("9012GHI", vehiculo.getMatricula());
        assertEquals(Categoria.NORMAL, vehiculo.getCategoria());
        assertEquals(2019, vehiculo.getAnnio());
        assertEquals(2000, vehiculo.getCapacidadCarga());
        assertEquals(4, vehiculo.getNumeroEjes());
        assertEquals("VehiculoIndustrial", vehiculo.getTipo());
        assertEquals(true, vehiculo.isDisponible());
    }
}