package pit2027;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.time.LocalDate;

import org.junit.Test;

public class GestorAlquileresAppTest {

    @Test
    public void obtieneVehiculosPorTipo() {
        GestorAlquileresApp gestor = new GestorAlquileresApp();

        Vehiculo[] vehiculos = gestor.obtenerVehiculos("Turismo");

        assertEquals(3, vehiculos.length);
        for (Vehiculo vehiculo : vehiculos) {
            assertEquals("Turismo", vehiculo.getTipo());
        }
    }

    @Test
    public void obtieneVehiculosPorTipoYCategoria() {
        GestorAlquileresApp gestor = new GestorAlquileresApp();

        Vehiculo[] vehiculos = gestor.obtenerVehiculos("Motocicleta", Categoria.SUPERIOR);

        assertEquals(1, vehiculos.length);
        assertEquals("MSA2021", vehiculos[0].getMatricula());
        assertEquals(Categoria.SUPERIOR, vehiculos[0].getCategoria());
    }

    @Test
    public void agregaYObtieneTodosLosAlquileres() {
        GestorAlquileresApp gestor = new GestorAlquileresApp();
        Cliente cliente = new Cliente("Cliente de prueba", "999");
        Vehiculo vehiculo = gestor.obtenerVehiculos("Turismo", Categoria.NORMAL)[0];
        Alquiler alquiler = crearAlquiler(vehiculo, cliente);

        gestor.agregarAlquiler(alquiler);

        assertSame(alquiler, gestor.obtenerAlquileres()[0]);
    }

    @Test
    public void obtieneAlquileresPorCliente() {
        GestorAlquileresApp gestor = new GestorAlquileresApp();
        Cliente cliente = new Cliente("Cliente de prueba", "999");
        Vehiculo vehiculo = gestor.obtenerVehiculos("Turismo", Categoria.NORMAL)[0];
        Alquiler alquiler = crearAlquiler(vehiculo, cliente);
        gestor.agregarAlquiler(alquiler);

        Alquiler[] alquileres = gestor.obtenerAlquileres(cliente);

        assertEquals(1, alquileres.length);
        assertSame(alquiler, alquileres[0]);
    }

    @Test
    public void obtieneAlquileresPorVehiculo() {
        GestorAlquileresApp gestor = new GestorAlquileresApp();
        Cliente cliente = new Cliente("Cliente de prueba", "999");
        Vehiculo vehiculo = gestor.obtenerVehiculos("Turismo", Categoria.NORMAL)[0];
        Alquiler alquiler = crearAlquiler(vehiculo, cliente);
        gestor.agregarAlquiler(alquiler);

        Alquiler[] alquileres = gestor.obtenerAlquileres(vehiculo);

        assertEquals(1, alquileres.length);
        assertSame(alquiler, alquileres[0]);
    }

    private Alquiler crearAlquiler(Vehiculo vehiculo, Cliente cliente) {
        return new Alquiler(vehiculo, cliente,
                LocalDate.of(2026, 9, 20), LocalDate.of(2026, 9, 22));
    }
}