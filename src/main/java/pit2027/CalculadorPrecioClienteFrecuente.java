package pit2027;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculadorPrecioClienteFrecuente implements ICalculadorPrecio {

    @Override
    public int calcularPrecioAlquiler(LocalDate fechaInicio, LocalDate fechaFin, Vehiculo vehiculo) {
        

        int dias = (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1; // +1 to include the start day

        int precioDia = vehiculo.getPrecioBase();
        if (dias <= 3) {
             return (int) Math.round(precioDia * dias * 0.8);
        } 
        else {
            double cuota1 = precioDia * 3 * 0.8;
            double cuota2 = precioDia * (dias - 3) * 0.6;
            return (int) Math.round(cuota1 + cuota2);
        }       
    }
}
