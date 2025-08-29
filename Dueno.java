import java.util.ArrayList;

public class Dueno {
    private String nombre; 
    private int telefono; 
    private ArrayList<Mascota> mascotas;
    private String direccion; 

    //Constructores
    public Dueno(String nombre, int telefono, ArrayList<Mascota> mascotas, String direccion) {
        this.nombre = nombre; 
        this.telefono = telefono; 
        this.mascotas = mascotas;
        this.direccion = direccion; 
        
    }   

    //Metodos

    public String getNombre () {return nombre;}
    public int getTelefono () {return telefono;}
    public void setTelefono (int telefono) {this.telefono = telefono;}
    public ArrayList<Mascota> getMascotas () {return mascotas;}
    public void setMascotas (ArrayList<Mascota> mascotas) {this.mascotas = mascotas;}
    public String getDireccion () {return direccion;}
        public void setDireccion (String direccion) {this.direccion = direccion;}
    }

