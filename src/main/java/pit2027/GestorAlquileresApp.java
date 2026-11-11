package pit2027;

import java.time.LocalDate;

public class GestorAlquileresApp {

    public static final int PRECIO_BASE = 50;
    private int contadorAlquileres = 0;
    private Alquiler[] alquileres = new Alquiler[1000];

    // Para simplificar, vamos a definir un array de clientes y 
    // otro de vehículos dentro del gestor.
    // En una aplicación real estarían en una base de datos o en otra clase.
    private Cliente[] clientes = new Cliente[10];
    private Vehiculo[] vehiculos = new Vehiculo[100];


    public GestorAlquileresApp() {
        inicializarClientes();
        inicializarVehiculos();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // INICIALIZACIÓN DE CLIENTES Y VEHÍCULOS DE EJEMPLO

    // Definimos métodos privados para inicializar clientes y vehículos de ejemplo
    private void inicializarClientes() {
        clientes[0] = new Cliente("Clara Videncia", "001");
        clientes[1] = new Cliente("Aitor Menta", "002");
        clientes[2] = new Cliente("Lola Mento", "003");
        clientes[3] = new Cliente("Armando Guerra", "004");
        clientes[4] = new Cliente("Ana Lítica", "005");
        clientes[5] = new Cliente("Carmelo Cotón", "006");
    }

    private void inicializarVehiculos() {
        vehiculos[0] = new Turismo("TNA2020", Categoria.NORMAL, 2020, 5, 450.0);
        vehiculos[1] = new Motocicleta("MNA2021", Categoria.NORMAL, 2021, 200);
        vehiculos[2] = new VehiculoIndustrial("CNA2019", Categoria.NORMAL, 2019, 1400, 2);
        vehiculos[3] = new Turismo("TSA2024", Categoria.SUPERIOR, 2024, 5, 550.0);
        vehiculos[4] = new Motocicleta("MSA2021", Categoria.SUPERIOR, 2021, 500);
        vehiculos[5] = new VehiculoIndustrial("CSA2025", Categoria.SUPERIOR, 2025, 2400, 3);
        vehiculos[6] = new Turismo("TEA2026", Categoria.ESPECIAL, 2026, 7, 650.0);
        vehiculos[7] = new Motocicleta("MEA2026", Categoria.ESPECIAL, 2026, 1000);
        vehiculos[8] = new VehiculoIndustrial("CEA2025", Categoria.ESPECIAL, 2025, 4400, 5);

    }

    /////////////////////////////////////////////////////////////////////////////////////
    // MÉTODOS PARA GESTIONAR VEHÍCULOS

     public Vehiculo[] obtenerVehiculos(String tipo) {
        
        Vehiculo[] vehiculosFiltrados = new Vehiculo[vehiculos.length];
        int contador = 0;

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo != null) {
                if (vehiculo.getTipo().equals(tipo)) {
                    vehiculosFiltrados[contador++] = vehiculo;
                }
            }
        }

        // Ajustar el tamaño del array para que solo contenga los vehículos encontrados
        Vehiculo[] resultado = new Vehiculo[contador];
        System.arraycopy(vehiculosFiltrados, 0, resultado, 0, contador);
        return resultado;
    }   


    public Vehiculo[] obtenerVehiculos(String tipo, Categoria categoria) {
        
        Vehiculo[] vehiculosFiltrados = new Vehiculo[vehiculos.length];
        int contador = 0;

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo != null) {
                if (vehiculo.getTipo().equals(tipo) && vehiculo.getCategoria() == categoria) {
                    vehiculosFiltrados[contador++] = vehiculo;
                }
            }
        }

        // Ajustar el tamaño del array para que solo contenga los vehículos encontrados
        Vehiculo[] resultado = new Vehiculo[contador];
        System.arraycopy(vehiculosFiltrados, 0, resultado, 0, contador);
        return resultado;
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // MÉTODOS PARA GESTIONAR ALQUILERES

    /**
     * Agrega un nuevo alquiler al array de alquileres.
     * @param alquiler
     */
    public void agregarAlquiler(Alquiler alquiler) {
        if (contadorAlquileres < alquileres.length) {
            alquileres[contadorAlquileres] = alquiler;
            contadorAlquileres++;
        } else {
            System.out.println("No se pueden agregar más alquileres. Capacidad máxima alcanzada.");
        }
    }

    /**
     * Devuelve un array con todos los alquileres registrados en el gestor.
     * @return
     */
    public Alquiler[] obtenerAlquileres() {
        return alquileres; 
    }

    /**
     * Devuelve un array con todos los alquileres de un cliente específico.
     * @param cliente
     * @return
     */
    public Alquiler[] obtenerAlquileres(Cliente cliente) {
        Alquiler[] alquileresCliente = new Alquiler[alquileres.length];
        int contador = 0;
        for (Alquiler alquiler : alquileres) {
            if (alquiler != null && alquiler.getCliente().equals(cliente)) {
                alquileresCliente[contador++] = alquiler;
            }
        }

        // Ajustar el tamaño del array para que solo contenga los alquileres encontrados
        Alquiler[] resultado = new Alquiler[contador];
        System.arraycopy(alquileresCliente, 0, resultado, 0, contador);
        return resultado;
    }

    /**
     * Devuelve un array con todos los alquileres de un vehículo específico.
     * @param vehiculo
     * @return
     */
    public Alquiler[] obtenerAlquileres(Vehiculo vehiculo) {
        Alquiler[] alquileresVehiculo = new Alquiler[alquileres.length];
        int contador = 0;
        for (Alquiler alquiler : alquileres) {
            if (alquiler != null && alquiler.getVehiculo().equals(vehiculo)) {
                alquileresVehiculo[contador++] = alquiler;
            }
        }

        // Ajustar el tamaño del array para que solo contenga los alquileres encontrados
        Alquiler[] resultado = new Alquiler[contador];
        System.arraycopy(alquileresVehiculo, 0, resultado, 0, contador);
        return resultado;
    }

    private boolean esClienteFrecuente(Cliente cliente, int umbral) {
        umbral = umbral > 0 ? umbral : 3; // Valor por defecto si el umbral es menor o igual a 0
        int contadorAlquileresCliente = 0;
        for (Alquiler alquiler : alquileres) {
            if (alquiler != null && alquiler.getCliente().equals(cliente)) {
                contadorAlquileresCliente++;
            }
        }
        return contadorAlquileresCliente >= umbral; 
    }





    /////////////////////////////////////////////////////////////////////////////////////
    // INTERFAZ DE USUARIO (CONSOLA) Y LÓGICA DE NEGOCIO

    // La interfaz de usuario y la lógica de negocio suelen implementarse por separado.
    // Aquí se integran ambas por simplicidad, pero en una aplicación real se recomienda separarlas.


    public void menuPrincipal(){
        
        boolean salir = false;
        
        while(!salir){

            // Se da oportunidad de salir inmediatamente de la app.
            System.out.println("Gestíon de Alquileres");
            System.out.println("¿Desea salir de la app (S/N)?");

            char s = KeyBoard.readChar();
            if (s == 's' || s == 'S'){
                System.out.println("Bye ...");
                return;
            }

            // Primero se identiifca al cliente:
            Cliente cliente = identificarCliente();

            // Ahora el cliente puede elegir una opción o salir de la aplicación.
            if (cliente != null) {
                System.out.println("Sr " + cliente.getNombre() + " Introduzca una opción");
                System.out.println("(1) seleccionar coche para alquilar");
                System.out.println("(2) entregar un coche alquilado");
                System.out.println("(3) listar su histórico de alquileres");

                int opcion = KeyBoard.readInt();
                System.out.println("opción escogida: " + opcion);

                switch (opcion) {
                    case 1:
                        Vehiculo v = seleccionarVehiculo(cliente);
                        if (v != null && v.isDisponible()){
                            // para simplificar la IU se supone que el alquiler comienza "ahora"
                            // y el usuario especifica duración en días.
                            System.out.println("¿cuántos días desea alquilar el vehículo?");

                            int dias = KeyBoard.readInt();

                            if (dias <= 0) {
                                System.out.println("La duración debe ser mayor que cero.");
                                break;
                            }

                            LocalDate inicio = LocalDate.now();
                            LocalDate fin = inicio.plusDays(dias - 1);

                            // Creamos y registramos el alquiler
                            Alquiler q = new Alquiler(v, cliente, inicio, fin);
                            this.agregarAlquiler(q);

                            // Calculamos precio.
                            // Comprobamos si el cliente es usuario frecuente para aplicarle
                            // la tarifa que le corresponde.
                            boolean usuarioFrecuente = this.esClienteFrecuente(cliente, 3);

                            if (usuarioFrecuente){

                                System.out.println("Alquiler realizado. Tarifa Usuario frecuente.");
                                System.out.println("coste = " + 
                                                      q.calcularPrecioAlquiler(inicio, fin, 
                                                        PoliticasDePrecios.ClienteFrecuente, 
                                                        v));
                            }
                            else{
                                // aplicamos tarifa normal.
                                // Dejamos Temporada Alta y Ofertón para versiones posteriores.
                                System.out.println("Alquiler realizado. Tarifa normal.");
                                System.out.println("coste = " + 
                                                      q.calcularPrecioAlquiler(inicio, fin, 
                                                        PoliticasDePrecios.Normal, 
                                                        v));
                            }
                            
                        }
                        else{
                            System.out.println("Debe seleccionar un vehículo disponible");
                        }
                        break;
                    case 2: 
                        System.out.println("opción no implementada");
                        break;              
                    case 3:
                        Alquiler alqs [] = obtenerAlquileres(cliente);
                        for (Alquiler q: alqs){
                            System.out.print(q);
                        }
                    default:
                        break;
                }
            }
            else{
                System.out.println("Debe identificarse correctamente o salir de la app");
            }
        }
    }

    public Cliente identificarCliente(){

        boolean seleccionado = false;
        Cliente cliente = null;

        while (!seleccionado) {
        
            System.out.println("Bienvenido al Gestor de Alquileres");
            for (int i = 0; i < clientes.length; i++) {
                if (clientes[i] != null) {
                    System.out.println(i + " ---> " + clientes[i].getNombre() + " - " + clientes[i].getDni());
                }
            }
            System.out.println("Identífiquese como cliente con su número de cliente (-1 para salir):");
            int numeroCliente = KeyBoard.readInt();
            if (numeroCliente == -1) {
                return null;
            }
            else if (numeroCliente < 0 || numeroCliente >= clientes.length || clientes[numeroCliente] == null) {
                System.out.println("identificarCliente:Número de cliente inválido.");
                return null;
            }
            else{
                seleccionado = true;
                cliente = clientes[numeroCliente];
            }
        }
        return cliente;
    }

    public Vehiculo seleccionarVehiculo(Cliente cliente){
        boolean seleccionado = false;
        Vehiculo vehiculo = null;

        while (!seleccionado) {
        
            System.out.println("Estimado Sr./Sra. " + cliente.getNombre());
            System.out.println("Introduzca el número correspondiente al tipo de vehículo que desea alquilar:");
            System.out.println("1. Turismo, 2. Motocicleta, 3. Vehículo Industrial: ");

            int tipoVehiculo = KeyBoard.readInt();
            String tipo = "";
            Vehiculo [] elegibles = new Vehiculo[100];

            switch (tipoVehiculo) {
                case 1:
                    System.out.println("Ha seleccionado Turismo.");
                    tipo = "Turismo";
                    break;
                case 2:
                    System.out.println("Ha seleccionado Motocicleta.");
                    tipo = "Motocicleta";
                    break;
                case 3:
                    System.out.println("Ha seleccionado Vehículo Industrial.");
                    tipo = "VehiculoIndustrial";
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione un tipo de vehículo válido.");
                    continue; // Volver a pedir la selección
            }

            System.out.println("Vehiculos disponibles");

            System.out.println("Estimado Sr./Sra. " + cliente.getNombre() + "\n seleccione categoría.");
            System.out.println("Introduzca el número correspondiente a la categoría que desea alquilar:");
            System.out.println("1. Normal, 2. Superior, 3. Especial, 4.Cualquiera: ");

            int categoriaVehiculo = KeyBoard.readInt();

            switch (categoriaVehiculo) {
                case 1:
                    System.out.println("Ha seleccionado Categoría Normal.");
                    elegibles = obtenerVehiculos(tipo, Categoria.NORMAL);
                    break;
                case 2:
                    System.out.println("Ha seleccionado Categoría Superior.");
                    elegibles = obtenerVehiculos(tipo, Categoria.SUPERIOR);
                    break;
                case 3:
                    System.out.println("Ha seleccionado Categoría Especial.");
                    elegibles = obtenerVehiculos(tipo, Categoria.ESPECIAL);
                    break;
                case 4:
                    System.out.println("Ha seleccionado cualquier categoría.");
                    elegibles = obtenerVehiculos(tipo);
                    break;
                default:
                    System.out.println("Categoría no válida.");
                    continue;
            }

            if (elegibles.length == 0) {
                System.out.println("No hay vehículos del tipo y categoría seleccionados.");
                continue;
            }

            System.out.println("Coches elegibles");

            for(int i = 0; i < elegibles.length; i++){
                System.out.println(i + " ---> " + elegibles[i]);
            }


            System.out.println("Introduzca número coche (sólo si está disponible)");
            int seleccion = KeyBoard.readInt();

            if (seleccion < 0 || seleccion >= elegibles.length) {
                System.out.println("Número de vehículo no válido.");
                continue;
            }

            if (!elegibles[seleccion].isDisponible()) {         
                System.out.println("El vehículo seleccionado no está disponible.");
                continue;
            }

            vehiculo = elegibles[seleccion];
            seleccionado = true;
        }
        return vehiculo;
    }

    public void listarAlquileres (){
        System.out.println("Listado de alquileres");
        for (Alquiler alq: alquileres){
            if (alq != null) {
                System.out.println(alq);
            }
        }
        System.out.println();
    }

    public void listarVehiculos(){
        System.out.println("Listado de vehículos");
        for (Vehiculo veh: vehiculos){
            if (veh != null) {
                System.out.println(veh);
            }       
        }
        System.out.println();
    }


    public static void main(String [] args){
        GestorAlquileresApp app = new GestorAlquileresApp();
        app.menuPrincipal();
    }

}
