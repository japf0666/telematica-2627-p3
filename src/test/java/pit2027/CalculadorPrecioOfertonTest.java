package pit2027;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class CalculadorPrecioOfertonTest {

    @Test
    public void aplicaElCalculoParaAlquileresDeHastaSieteDias() {
        Vehiculo vehiculo = new Turismo("1234ABC", Categoria.NORMAL, 2020, 5, 450.0);
        LocalDate fechaInicio = LocalDate.of(2026, 9, 20);
        LocalDate fechaFin = LocalDate.of(2026, 9, 26);

        int precio = new CalculadorPrecioOferton().calcularPrecioAlquiler(
                fechaInicio, fechaFin, vehiculo);

        assertEquals(245, precio);
    }

    @Test
    public void aplicaElCalculoParaAlquileresDeMasDeSieteDias() {
        Vehiculo vehiculo = new Turismo("1234ABC", Categoria.NORMAL, 2020, 5, 450.0);
        LocalDate fechaInicio = LocalDate.of(2026, 9, 20);
        LocalDate fechaFin = LocalDate.of(2026, 9, 27);

        int precio = new CalculadorPrecioOferton().calcularPrecioAlquiler(
                fechaInicio, fechaFin, vehiculo);

        assertEquals(252, precio);
    }
}