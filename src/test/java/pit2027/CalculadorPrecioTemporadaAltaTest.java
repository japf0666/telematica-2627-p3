package pit2027;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class CalculadorPrecioTemporadaAltaTest {

    @Test
    public void duplicaElPrecioNormal() {
        Vehiculo vehiculo = new Turismo("1234ABC", Categoria.NORMAL, 2020, 5, 450.0);
        LocalDate fechaInicio = LocalDate.of(2026, 9, 20);
        LocalDate fechaFin = LocalDate.of(2026, 9, 22);

        int precio = new CalculadorPrecioTemporadaAlta().calcularPrecioAlquiler(
                fechaInicio, fechaFin, vehiculo);

        assertEquals(2 * 3 * vehiculo.getPrecioBase(), precio);
    }
}