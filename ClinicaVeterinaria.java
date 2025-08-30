import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClinicaVeterinaria {
    private String nombreClinica;
    private ArrayList<Dueno> duenos;
    private ArrayList<Consulta> consultas;
    private ArrayList<Mascota> mascotas;

    public ClinicaVeterinaria(String nombreClinica, ArrayList<Dueno> duenos, ArrayList<Mascota> mascotas, ArrayList<Consulta> consultas) {
        this.nombreClinica = nombreClinica;
        this.duenos = duenos;
        this.mascotas = mascotas;
        this.consultas = consultas;
    }

    public String getNombreClinica() {
        return nombreClinica;
    }

    public ArrayList<Dueno> getDuenos() {
        return duenos;
    }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }

    public void registrarMascota(Mascota mascota) {
        this.mascotas.add(mascota);
    }

    public void registrarDueno(Dueno dueno) {
        this.duenos.add(dueno);
    }

    public void registrarConsulta(Consulta consulta) {
        this.consultas.add(consulta);
    }

    public Mascota buscarMascota(String nombre) {
        for (Mascota mascota : mascotas) {
            if (mascota.getNombre().equalsIgnoreCase(nombre)) {
                return mascota;
            }
        }
        return null;
    }

    public Dueno buscarDueno(String nombre) {
        for (Dueno dueno : duenos) {
            if (dueno.getNombre().equalsIgnoreCase(nombre)) {
                return dueno;
            }
        }
        return null;
    }

    public String especieMasAtendida() {
        HashMap<String, Integer> especieCount = new HashMap<>();
        for (Consulta consulta : consultas) {
            String especie = consulta.getMascota().getEspecie();
            especieCount.put(especie, especieCount.getOrDefault(especie, 0) + 1);
        }
        
        String especieMasAtendida = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : especieCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                especieMasAtendida = entry.getKey();
            }
        }
        return especieMasAtendida;
    }

    public Mascota mascotaConMasConsultas() {
        HashMap<Mascota, Integer> mascotaCount = new HashMap<>();
        for (Consulta consulta : consultas) {
            Mascota mascota = consulta.getMascota();
            mascotaCount.put(mascota, mascotaCount.getOrDefault(mascota, 0) + 1);
        }
        
        Mascota mascotaConMasConsultas = null;
        int maxCount = 0;
        for (Map.Entry<Mascota, Integer> entry : mascotaCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mascotaConMasConsultas = entry.getKey();
            }
        }
        return mascotaConMasConsultas;
    }

    public Double promedioConsultaMes() {
        if (consultas.isEmpty()) {
            return 0.0;
        }
        int totalConsultas = consultas.size();
        int meses = 12;
        return (double) totalConsultas / meses;
    }

    public void mostrarReportes() {
        System.out.println("Especie mÃ¡s atendida: " + especieMasAtendida());
        Mascota mascotaMasTratada = mascotaConMasConsultas();
        if (mascotaMasTratada != null) {
            System.out.println("Mascota con mÃ¡s consultas: " + mascotaMasTratada.getNombre());
        } else {
            System.out.println("No hay mascotas registradas.");
        }
        System.out.println("Promedio de consultas por mes: " + promedioConsultaMes());
    }
}
