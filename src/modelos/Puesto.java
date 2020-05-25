package modelos;

public class Puesto {

    private String nombrePuesto;
    private double salario;

    public Puesto(String nombrePuesto, double salario) {
        this.nombrePuesto = nombrePuesto;
        this.salario = salario;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public double getSalario() {
        return salario;
    }


}
