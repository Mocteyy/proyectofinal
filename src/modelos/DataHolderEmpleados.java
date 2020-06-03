package modelos;

import controladores.BuscarController;

import java.util.ArrayList;

public class DataHolderEmpleados {

    private static ArrayList<Empleado> empleados = new ArrayList<>();

    public static int NOMBRE = 1, PUESTO = 2, RFC = 3;

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

    public static ArrayList<Empleado> read(String variable, int condicionante){ //Método para sólo buscar si se ingresa solo un campo.
        ArrayList<Empleado> resultado = new ArrayList<>();

        if (condicionante == NOMBRE) {//Aquí se busca por nombre.
            for (Empleado empleado :
                    empleados) {
                if (empleado.getNombre().contains(variable)) {
                    resultado.add(empleado);
                }
            }
        }
        else if (condicionante == PUESTO) { //Se busca por puesto
            for (Empleado empleado :
                    empleados) {
                if (empleado.getNombrePuesto().equals(variable)) {
                    resultado.add(empleado);
                }
            }
        }
        else{ //Por último se busca por RFC
            for (Empleado empleado :
                    empleados) {
                if (empleado.getRFC().equals(variable)) {
                    resultado.add(empleado);
                }
            }
        }
        return resultado;
    }

    public static ArrayList<Empleado> read(String nombre, String puesto){ //Buscar por nombre y puesto
        ArrayList<Empleado> resultado = new ArrayList<>();

            for (Empleado empleado :
                    empleados) {
                if (empleado.getNombre().contains(nombre) && empleado.getNombrePuesto().equals(puesto)) {
                    resultado.add(empleado);
                }
            }

        return resultado;
    }


    public static boolean delete(String RFC){ //Borrar un empleado a partir de su RFC
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

    private static Empleado [] matrizPrueba = { //Carga de matriz de prueba
            new Empleado("ASD124", "Daniel Gámez", true,"2469201385",19,"lelemc@outlook.es","Programador"),
            new Empleado("ASD125", "David Yanez", true,"5860193856",19,"ddyymoctey@gmail.com","Programador"),
            new Empleado("ASD126", "Luis Yanez", true,"19857918",19,"luiselpro@hotmail.com","Tester"),
            new Empleado("ASD127", "Judas Fimbres", true,"4758392057",19,"tiobertix@gmail.com","Programador"),
            new Empleado("ASD128", "Oskar Gómez", true,"6759403756",19,"oskarin@hotmail.com","Administrador"),
            new Empleado("ASD129", "Francisco Yanez", true,"175495069",19,"pacopancho@outlook.es","Programador"),
            new Empleado("ASD130", "David Ochoa", true,"5676683349",19,"betoelloco@hotmail.com","Tester"),
            new Empleado("ASD131", "Alma Negrete", true,"6341243759",19,"almavida@gmail.com","Analista"),
            new Empleado("ASD132", "David Aguilar", true,"6341743759",19,"davida@gmail.com","Programador"),

    };

    public static Empleado[] getMatrizPrueba() {
        return matrizPrueba;
    }


    private static Empleado empleadoEditarHolder;

    public static Empleado getEmpleadoEditarHolder() {
        return empleadoEditarHolder;
    }

    public static void setEmpleadoEditarHolder(Empleado empleadoEditarHolder) {
        DataHolderEmpleados.empleadoEditarHolder = empleadoEditarHolder;
    }
}
