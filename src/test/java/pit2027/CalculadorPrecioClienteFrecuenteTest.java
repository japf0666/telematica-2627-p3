package pit2027;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class CalculadorPrecioClienteFrecuenteTest {

    @Test
    public void aplicaElCalculoParaAlquileresDeHastaTresDias() {
        Vehiculo vehiculo = new Turismo("1234ABC", Categoria.NORMAL, 2020, 5, 450.0);
        LocalDate fechaInicio = LocalDate.of(2026, 9, 20);
        LocalDate fechaFin = LocalDate.of(2026, 9, 22);

        int precio = new CalculadorPrecioClienteFrecuente().calcularPrecioAlquiler(
                fechaInicio, fechaFin, vehiculo);

        assertEquals(120, precio);
    }

    @Test
    public void aplicaElCalculoParaAlquileresDeMasDeTresDias() {
        Vehiculo vehiculo = new Turismo("1234ABC", Categoria.NORMAL, 2020, 5, 450.0);
        LocalDate fechaInicio = LocalDate.of(2026, 9, 20);
        LocalDate fechaFin = LocalDate.of(2026, 9, 25);

        int precio = new CalculadorPrecioClienteFrecuente().calcularPrecioAlquiler(
                fechaInicio, fechaFin, vehiculo);

        assertEquals(210, precio);
    }
}