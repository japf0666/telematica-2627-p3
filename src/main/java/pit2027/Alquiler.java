package pit2027;

import java.time.LocalDate;

import org.json.JSONObject;

/**
 * Modela un alquiler de un vehículo por parte de un cliente, con fechas de inicio y fin.
 * Almacena los alquileres realizados en un array estático y proporciona métodos para calcular el precio del alquiler.
 * Alquiler
 */
public class Alquiler {
    
    private Vehiculo vehiculo;
    private Cliente cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Alquiler(Vehiculo vehiculo, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin) {
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }


    public int calcularPrecioAlquiler(LocalDate fechaInicio, LocalDate fechaFin, PoliticasDePrecios politica, Vehiculo vehiculo) {
        switch (politica) {
            case Normal: 
                return new CalculadorPrecioNormal().calcularPrecioAlquiler(fechaInicio, fechaFin, vehiculo);
            case TemporadaAlta:
                return new CalculadorPrecioTemporadaAlta().calcularPrecioAlquiler(fechaInicio, fechaFin, vehiculo);
            case ClienteFrecuente:
                return new CalculadorPrecioClienteFrecuente().calcularPrecioAlquiler(fechaInicio, fechaFin, vehiculo);
            case Oferton:
                return new CalculadorPrecioOferton().calcularPrecioAlquiler(fechaInicio, fechaFin, vehiculo);
            default:
                throw new IllegalArgumentException("Política de precios desconocida: " + politica);
        }
    }


    @Override 
    public String toString() {
        return new JSONObject(this).toString();
    }

    public static void main(String[] args) {

        LocalDate hoy = LocalDate.now();

        // Crear instancias de Vehiculo y Cliente
        Vehiculo vehiculo1 = new Turismo("ABC123", Categoria.NORMAL, 2020, 5, 400.0);
        Cliente cliente1 = new Cliente("Juan Pérez", "123456789");
        Alquiler alquiler1 = new Alquiler(vehiculo1, cliente1, hoy, hoy.plusDays(3)); // Alquiler de 3 días
        
        System.out.println(alquiler1.toString());
        System.out.println("--------------------------------------------------------");

        Vehiculo vehiculo2 = new VehiculoIndustrial("ABC123", Categoria.NORMAL, 2020, 1000, 3);
        Alquiler alquiler2 = new Alquiler(vehiculo2, cliente1, hoy, hoy.plusDays(1)); // Alquiler de 1 días

        System.out.println(alquiler2.toString());
        System.out.println("--------------------------------------------------------");
    }
}
