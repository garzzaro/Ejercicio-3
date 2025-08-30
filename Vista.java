import java.util.Scanner;

public class Vista {
    private static final Scanner sc = new Scanner(System.in);
    private ClinicaVeterinaria clinica;
    
    public Vista(ClinicaVeterinaria clinica) {
        this.clinica = clinica;
    }
    
    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");
            switch (opcion) {
                case 1:
                    registrarDueno();
                    break;
                case 2:
                    registrarMascota();
                    break;
                case 3:
                    registrarConsulta();
                    break;
                case 4:
                    mostrarReportes();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
            System.out.println();
        } while (opcion != 0);
    }
    
    private void mostrarMenu() {
        System.out.println("Veterinaria " + clinica.getNombreClinica());
        System.out.println("1. Registrar dueño");
        System.out.println("2. Registrar mascota");
        System.out.println("3. Registrar consulta");
        System.out.println("4. Reportes (todos)");
        System.out.println("0. Salir");
    }
    
    private void registrarDueno() {
        String nombre = leerTexto("Nombre: ");
        int telefono = leerEntero("Teléfono: ");
        String direccion = leerTexto("Dirección: ");
        
        java.util.ArrayList<Mascota> mascotas = new java.util.ArrayList<>();
        Dueno nuevoDueno = new Dueno(nombre, telefono, mascotas, direccion);
        clinica.registrarDueno(nuevoDueno);
        System.out.println("Dueño registrado.");
    }
    
    private void registrarMascota() {
        String nombreDueno = leerTexto("Nombre del dueño: ");
        Dueno d = clinica.buscarDueno(nombreDueno);
        if (d == null) {
            System.out.println("No existe ese dueño.");
            return;
        }
        String nombre = leerTexto("Nombre mascota: ");
        String especie = leerTexto("Especie: ");
        String raza = leerTexto("Raza: ");
        int edad = leerEntero("Edad: ");
        String personaResponsable = leerTexto("Persona responsable: ");
        
        Mascota nuevaMascota = new Mascota(especie, raza, edad, personaResponsable, nombre, nombreDueno);
        clinica.registrarMascota(nuevaMascota);
        System.out.println("Mascota registrada.");
    }
    
    private void registrarConsulta() {
        String dueno = leerTexto("Nombre dueño: ");
        String mascota = leerTexto("Nombre mascota: ");
        Mascota m = buscarMascotaPorNombreYDueno(mascota, dueno);
        if (m == null) {
            System.out.println("No existe esa mascota con ese dueño.");
            return;
        }
        int fecha = leerEntero("Fecha (yyyyMMdd): ");
        String motivo = leerTexto("Motivo: ");
        String tratamiento = leerTexto("Tratamiento: ");
        String veterinario = leerTexto("Veterinario: ");
        
        Consulta nuevaConsulta = new Consulta(fecha, tratamiento, veterinario, m, motivo);
        clinica.registrarConsulta(nuevaConsulta);
        System.out.println("Consulta registrada.");
    }
    
    private void mostrarReportes() {
        System.out.println(" REPORTES ");
        
        String especie = clinica.especieMasAtendida();
        System.out.println(especie == null ? "Sin datos de especie." : "Especie más atendida: " + especie);
        
        Mascota m = clinica.mascotaConMasConsultas();
        System.out.println(m == null ? "Sin datos de consultas." :
                "Mascota con más consultas: " + m.getNombre() + " (Dueño: " + m.getDueno() + ")");
        
        double prom = clinica.promedioConsultaMes();
        System.out.printf("Promedio consultas/mes: %.2f%n", prom);
        
        System.out.println(" Lista de dueños ");
        if (clinica.getDuenos().isEmpty()) {
            System.out.println("No hay dueños registrados.");
        } else {
            for (Dueno d : clinica.getDuenos()) {
                System.out.println("Dueño: " + d.getNombre() + " | Tel: " + d.getTelefono() + " | Dirección: " + d.getDireccion());
            }
        }
        
        System.out.println("Lista de mascotas");
        if (clinica.getMascotas().isEmpty()) {
            System.out.println("No hay mascotas registradas.");
        } else {
            for (Mascota ma : clinica.getMascotas()) {
                System.out.println("Mascota: " + ma.getNombre() + " | Especie: " + ma.getEspecie() + 
                                 " | Raza: " + ma.getRaza() + " | Edad: " + ma.geEdad() + 
                                 " | Dueño: " + ma.getDueno());
            }
        }
        
        System.out.println("Lista de consultas");
        if (clinica.getConsultas().isEmpty()) {
            System.out.println("No hay consultas registradas.");
        } else {
            for (Consulta c : clinica.getConsultas()) {
                System.out.println("Fecha: " + c.getFechaDeConsulta() +
                        " | Mascota: " + c.getMascota().getNombre() +
                        " | Dueño: " + c.getMascota().getDueno() +
                        " | Motivo: " + c.getMotivoVisita() +
                        " | Tratamiento: " + c.getTratamiento());
            }
        }
    }
    
    private Mascota buscarMascotaPorNombreYDueno(String nombreMascota, String nombreDueno) {
        for (Mascota mascota : clinica.getMascotas()) {
            if (mascota.getNombre().equalsIgnoreCase(nombreMascota) && 
                mascota.getDueno().equalsIgnoreCase(nombreDueno)) {
                return mascota;
            }
        }
        return null;
    }
    
    private static String leerTexto(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }
    
    private static int leerEntero(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido.");
            }
        }
    }
}