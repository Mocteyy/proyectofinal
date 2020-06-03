package modelos;

public class Puesto {

    private String nombrePuesto;
    private double salario;
    private String description;

    public Puesto(String nombrePuesto, double salario, String description) {
        this.nombrePuesto = nombrePuesto;
        this.salario = salario;
        this.description = description;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public double getSalario() {
        return salario;
    }

    public String getDescription() {
        return description;
    }

    public static String programador = "Responsable del desarrollo de los sistemas requeridos por la empresa";
    public static String administrador = "Resposable de las finanzas de la empresa";
    public static String tester = "Responsable de probar y validar el sistema del programador";
    public static String analista = "Responsable de realizar la documentación del producto de software y validar si cumple con los requisitos del cliente";

}
