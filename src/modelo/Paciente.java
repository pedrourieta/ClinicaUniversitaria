package modelo;

public class Paciente {

    private final int numeroExpediente;
    private final String nombre;
    private final int edad;
    private final String especialidad;
    private final int numeroConsultas;

    public Paciente(int numeroExpediente, String nombre, int edad,
                    String especialidad, int numeroConsultas) {
        this.numeroExpediente = numeroExpediente;
        this.nombre = nombre;
        this.edad = edad;
        this.especialidad = especialidad;
        this.numeroConsultas = numeroConsultas;
    }

    public int getNumeroExpediente() {
        return numeroExpediente;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public int getNumeroConsultas() {
        return numeroConsultas;
    }
}
