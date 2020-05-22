package modelos;

import java.util.ArrayList;

public class DataHolderEmpleados {

    private static ArrayList<Empleado> empleados = new ArrayList<>();

    public static boolean create(Empleado empleado) {
        for (Empleado e :
                empleados) {
            if (e.getRFC().equals(empleado.getRFC())) {
                return false;
            }

        }

        empleados.add(empleado);

        return true;
    }

    public static Empleado read(String rfc){
        for (Empleado e :
                empleados) {
            if (e.getRFC().equals(rfc)){
                return e;
            }
        }
        return null;
    }


    public static boolean delete(String RFC){
        for (Empleado e :
                empleados) {
            if (e.getRFC().equals(RFC)){
                empleados.remove(e);
                return true;
            }
        }
        return false;
    }

    public static void update(Empleado e, String nombre, boolean sexo, String telefono, int edad, String email, String puesto){
        e.setNombre(nombre);
        e.setEdad(edad);
        e.setEmail(email);
        e.setSexo(sexo);
        e.setPuesto(puesto);
        e.setTelefono(telefono);
    }

}
