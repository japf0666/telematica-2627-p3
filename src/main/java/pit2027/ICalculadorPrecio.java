package pit2027;

import java.time.LocalDate;

/**
 * Interface para calcular el precio de un alquiler de vehículo.
 * Implementaciones de esta interfaz pueden definir diferentes políticas de cálculo de precios.
 * ICalculadorPrecio
 */
public interface ICalculadorPrecio {
    public int calcularPrecioAlquiler(LocalDate fechaInicio, LocalDate fechaFin, Vehiculo vehiculo);
}
