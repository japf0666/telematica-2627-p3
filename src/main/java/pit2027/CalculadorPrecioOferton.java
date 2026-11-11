package pit2027;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculadorPrecioOferton implements ICalculadorPrecio {

    @Override
    public int calcularPrecioAlquiler(LocalDate fechaInicio, LocalDate fechaFin, Vehiculo vehiculo) {
        
        int dias = (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1; // +1 to include the start day
        int precioDia = vehiculo.getPrecioBase();

        // Se aplica un descuento del 30% en el precio diario
        double total = precioDia * dias * 0.7;

        // Si el alquiler es de más de 7 días, se aplica un descuento 
        // adicional del 10% en el total
        if (dias > 7) {
            total *= 0.9;
        }
        return (int) Math.round(total);     
    }
}
