package pit2027;

import org.json.JSONObject;

public class FactoriaVehiculos {

    public static Vehiculo getVehiculo(String jsonString) {
        JSONObject json = new org.json.JSONObject(jsonString);
        String clase = json.getString("tipo");
        switch (clase) {
            case "Turismo":
                return new Turismo(jsonString);
            case "Motocicleta":
                return new Motocicleta(jsonString);
            case "VehiculoIndustrial":
                return new VehiculoIndustrial(jsonString);
            default:
                throw new IllegalArgumentException("Tipo de vehículo desconocido: " + clase);
        }
    }

    public static void main(String[] args) {

        Vehiculo v1 = new Turismo("1234ABC", Categoria.NORMAL, 2020, 5, 450.0);
        System.out.println(v1.toString());
        System.out.println("--------------------");
        Vehiculo v2 = FactoriaVehiculos.getVehiculo(v1.toString());
        System.out.println(v2.toString());
        System.out.println("___________________________________________");

        Vehiculo motocicleta = new Motocicleta("5678DEF", Categoria.NORMAL, 2021, 600);
        System.out.println(motocicleta.toString());
        System.out.println("--------------------");
        Vehiculo motocicleta2 = FactoriaVehiculos.getVehiculo(motocicleta.toString());
        System.out.println(motocicleta2.toString());
        System.out.println("___________________________________________");

        Vehiculo vehiculoIndustrial = new VehiculoIndustrial("9012GHI", Categoria.NORMAL, 2019, 2000, 4);
        System.out.println(vehiculoIndustrial.toString());
        System.out.println("--------------------");
        Vehiculo vehiculoIndustrial2 = FactoriaVehiculos.getVehiculo(vehiculoIndustrial.toString());
        System.out.println(vehiculoIndustrial2.toString());
    }

}
