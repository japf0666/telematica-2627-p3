package pit2027;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FactoriaVehiculosTest {

    @Test
    public void creaUnTurismoAPartirDeJson() {
        Vehiculo vehiculo = FactoriaVehiculos.getVehiculo(
                "{\"tipo\":\"Turismo\",\"matricula\":\"1234ABC\","
                        + "\"categoria\":\"NORMAL\",\"annio\":2020,\"disponible\":true,"
                        + "\"plazas\":5,\"capacidadMaletero\":450.0}");

        assertTrue(vehiculo instanceof Turismo);
        assertEquals("1234ABC", vehiculo.getMatricula());
        assertEquals("Turismo", vehiculo.getTipo());
    }

    @Test
    public void creaUnaMotocicletaAPartirDeJson() {
        Vehiculo vehiculo = FactoriaVehiculos.getVehiculo(
                "{\"tipo\":\"Motocicleta\",\"matricula\":\"5678DEF\","
                        + "\"categoria\":\"NORMAL\",\"annio\":2021,\"disponible\":false,"
                        + "\"cilindrada\":600}");

        assertTrue(vehiculo instanceof Motocicleta);
        assertEquals("5678DEF", vehiculo.getMatricula());
        assertEquals("Motocicleta", vehiculo.getTipo());
    }

    @Test
    public void creaUnVehiculoIndustrialAPartirDeJson() {
        Vehiculo vehiculo = FactoriaVehiculos.getVehiculo(
                "{\"tipo\":\"VehiculoIndustrial\",\"matricula\":\"9012GHI\","
                        + "\"categoria\":\"NORMAL\",\"annio\":2019,\"disponible\":true,"
                        + "\"capacidadCarga\":2000,\"numeroEjes\":4}");

        assertTrue(vehiculo instanceof VehiculoIndustrial);
        assertEquals("9012GHI", vehiculo.getMatricula());
        assertEquals("VehiculoIndustrial", vehiculo.getTipo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void rechazaUnTipoDeVehiculoDesconocido() {
        FactoriaVehiculos.getVehiculo(
                "{\"tipo\":\"Barco\",\"matricula\":\"0000XXX\","
                        + "\"categoria\":\"NORMAL\",\"annio\":2020,\"disponible\":true}");
    }
}