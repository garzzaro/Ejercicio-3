public class Mascota {
    private String especie;
    private String raza;
    private int edad;
    private String personaResponsable;
    private String nombre;
    private String dueno;


 //Constructores
    public Mascota(String especie, String raza, int edad, String personaResponsable, String nombre, String dueno) {
        this.especie = especie; 
        this.raza = raza;
        this.edad = edad; 
        this.personaResponsable = personaResponsable; 
        this.nombre = nombre; 
        this.dueno = dueno; 
    }

    // Metodos 
    public String getEspecie ( ) {return especie; }
    public String getRaza () {return raza;}
    public int geEdad () {return edad;}
    public String getPersonaResponsable () {return personaResponsable;}
    public String getNombre () {return nombre;}
    public String getDueno () {return dueno;}
    }