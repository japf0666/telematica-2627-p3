package pit2027;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClienteTest {

    @Test
    public void constructorInicializaLosDatosDelCliente() {
        Cliente cliente = new Cliente("Ana García", "12345678A");

        assertEquals("Ana García", cliente.getNombre());
        assertEquals("12345678A", cliente.getDni());
    }
}