package pit2027;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculadorPrecioTemporadaAlta implements ICalculadorPrecio {

    @Override
    public int calcularPrecioAlquiler(LocalDate fechaInicio, LocalDate fechaFin, Vehiculo vehiculo) {
        
        int dias = (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1; // +1 to include the start day
        return 2 * (int) (dias * vehiculo.getPrecioBase());
    }
}
