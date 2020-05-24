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

    public static ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    private static Empleado [] matrizPrueba = {
            new Empleado("ASD124", "Daniel Gámez", true,"2469201385",19,"lelemc@outlook.es","Programador"),
            new Empleado("ASD125", "David Yanez", true,"5860193856",19,"ddyymoctey@gmail.com","Programador"),
            new Empleado("ASD126", "Luis Yanez", true,"19857918",19,"luiselpro@hotmail.com","Tester"),
            new Empleado("ASD127", "Judas Fimbres", true,"4758392057",19,"tiobertix@gmail.com","Programador"),
            new Empleado("ASD128", "Oskar Gómez", true,"6759403756",19,"oskarin@hotmail.com","Administrador"),
            new Empleado("ASD129", "Francisco Yanez", true,"175495069",19,"pacopancho@outlook.es","Programador"),
            new Empleado("ASD130", "Alberto Ochoa", true,"5676683349",19,"betoelloco@hotmail.com","Tester"),
            new Empleado("ASD131", "Alma Negrete", true,"6341243759",19,"almavida@gmail.com","Analista"),

    };

    public static Empleado[] getMatrizPrueba() {
        return matrizPrueba;
    }
}
