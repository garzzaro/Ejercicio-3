public class Consulta {
    private int fechaDeConsulta;
    private String tratamiento; 
    private String veterinario; 
    private Mascota mascota;
    private String motivoVisita; 

    //Constructores
    public Consulta(int fechaDeConsulta, String tratamiento, String veterinario, Mascota mascota, String motivoVisita) {
        this.fechaDeConsulta = fechaDeConsulta; 
        this.tratamiento = tratamiento;
        this.veterinario = veterinario;
        this.mascota = mascota;
        this.motivoVisita = motivoVisita;
    }

    //Metodos
    public int getFechaDeConsulta () {return fechaDeConsulta;}
    public String getTratamiento () {return tratamiento;}
    public String getVeterinario () {return veterinario;}
    public String getMotivoVisita () {return motivoVisita;}
    public Mascota getMascota () {return mascota;}
    

}
