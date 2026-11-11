package pit2027;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MotocicletaTest {

    @Test
    public void constructorInicializaLosDatosEspecificos() {
        Motocicleta motocicleta = new Motocicleta("5678DEF", Categoria.SUPERIOR, 2021, 600);

        assertEquals("5678DEF", motocicleta.getMatricula());
        assertEquals(Categoria.SUPERIOR, motocicleta.getCategoria());
        assertEquals(2021, motocicleta.getAnnio());
        assertEquals(600, motocicleta.getCilindrada());
        assertEquals("Motocicleta", motocicleta.getTipo());
    }

    @Test
    public void constructorJsonReconstruyeLaMotocicleta() {
        Motocicleta motocicleta = new Motocicleta(
                "{\"matricula\":\"5678DEF\",\"categoria\":\"NORMAL\","
                        + "\"annio\":2021,\"disponible\":true,\"cilindrada\":600}");

        assertEquals("5678DEF", motocicleta.getMatricula());
        assertEquals(Categoria.NORMAL, motocicleta.getCategoria());
        assertEquals(2021, motocicleta.getAnnio());
        assertEquals(600, motocicleta.getCilindrada());
        assertEquals("Motocicleta", motocicleta.getTipo());
        assertEquals(true, motocicleta.isDisponible());
    }
}