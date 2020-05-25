package modelos;

public class Empleado {

    private String RFC;
    private String nombre;
    private boolean sexo;
    private String telefono;
    private int edad;
    private String email;
    private Puesto puesto;

    public Empleado(String RFC, String nombre, boolean sexo, String telefono, int edad, String email, String puesto) {
        this.RFC = RFC;
        this.nombre = nombre;
        this.sexo = sexo;
        this.telefono = telefono;
        this.edad = edad;
        this.email = email;
        this.puesto = DataHolderPuesto.leer(puesto);
    }

    public String getRFC() {
        return RFC;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String  getNombrePuesto() {
        return puesto.getNombrePuesto();
    }

    public void setPuesto(String puesto) {
        this.puesto = DataHolderPuesto.leer(puesto);
    }

    public Puesto getPuesto() {
        return puesto;
    }
}
