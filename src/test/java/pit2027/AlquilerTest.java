package pit2027;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.time.LocalDate;

import org.junit.Test;

public class AlquilerTest {

    @Test
    public void constructorInicializaTodosLosDatosDelAlquiler() {
        Vehiculo vehiculo = new Turismo("1234ABC", Categoria.NORMAL, 2020, 5, 450.0);
        Cliente cliente = new Cliente("Ana García", "12345678A");
        LocalDate fechaInicio = LocalDate.of(2026, 9, 20);
        LocalDate fechaFin = LocalDate.of(2026, 9, 22);

        Alquiler alquiler = new Alquiler(vehiculo, cliente, fechaInicio, fechaFin);

        assertSame(vehiculo, alquiler.getVehiculo());
        assertSame(cliente, alquiler.getCliente());
        assertSame(fechaInicio, alquiler.getFechaInicio());
        assertSame(fechaFin, alquiler.getFechaFin());
    }

    @Test
    public void calcularPrecioAlquilerCalculaElPrecioNormal() {
        LocalDate fechaInicio = LocalDate.of(2026, 9, 20);
        LocalDate fechaFin = LocalDate.of(2026, 9, 22);
        Vehiculo vehiculo = new Turismo("1234ABC", Categoria.NORMAL, 2020, 5, 450.0);
        Alquiler alquiler = new Alquiler(vehiculo, null, fechaInicio, fechaFin);

        assertEquals(3 * vehiculo.getPrecioBase(),
                alquiler.calcularPrecioAlquiler(fechaInicio, fechaFin,
                        PoliticasDePrecios.Normal, vehiculo));
    }

    @Test
    public void calcularPrecioAlquilerAplicaLaTemporadaAlta() {
        LocalDate fechaInicio = LocalDate.of(2026, 9, 20);
        LocalDate fechaFin = LocalDate.of(2026, 9, 22);
        Vehiculo vehiculo = new Turismo("1234ABC", Categoria.NORMAL, 2020, 5, 450.0);
        Alquiler alquiler = new Alquiler(vehiculo, null, fechaInicio, fechaFin);

        assertEquals(3 * vehiculo.getPrecioBase() * 2,
                alquiler.calcularPrecioAlquiler(fechaInicio, fechaFin,
                        PoliticasDePrecios.TemporadaAlta, vehiculo));
    }

    @Test
    public void calcularPrecioAlquilerAplicaElDescuentoDeClienteFrecuente() {
        LocalDate fechaInicio = LocalDate.of(2026, 9, 20);
        LocalDate fechaFin = LocalDate.of(2026, 9, 22);
        Vehiculo vehiculo = new Turismo("1234ABC", Categoria.NORMAL, 2020, 5, 450.0);
        Alquiler alquiler = new Alquiler(vehiculo, null, fechaInicio, fechaFin);

        assertEquals(120,
                alquiler.calcularPrecioAlquiler(fechaInicio, fechaFin,
                        PoliticasDePrecios.ClienteFrecuente, vehiculo));
    }

    @Test
    public void calcularPrecioAlquilerAplicaElDescuentoDeOferton() {
        LocalDate fechaInicio = LocalDate.of(2026, 9, 20);
        LocalDate fechaFin = LocalDate.of(2026, 9, 27);
        Vehiculo vehiculo = new Turismo("1234ABC", Categoria.NORMAL, 2020, 5, 450.0);
        Alquiler alquiler = new Alquiler(vehiculo, null, fechaInicio, fechaFin);

        assertEquals(252,
                alquiler.calcularPrecioAlquiler(fechaInicio, fechaFin,
                        PoliticasDePrecios.Oferton, vehiculo));
    }
}