package modelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataHolderPuesto {

    private static Puesto puestoHolder;

    private static List<Puesto> puestos = new ArrayList<>(Arrays.asList(
            new Puesto("Programador",15000.0, Puesto.programador),
            new Puesto("Tester",17000.0, Puesto.tester),
            new Puesto("Administrador",16000.0, Puesto.administrador),
            new Puesto("Analista",20000.0, Puesto.analista)));

    public static ArrayList<String> getNombrePuestos() { //Este método regresa los nombres de todos los empleos
        ArrayList<String> resultado = new ArrayList<>();

        for (Puesto puesto:
             puestos) {
            resultado.add(puesto.getNombrePuesto());
        }

        return resultado;
    }

    public static Puesto leer(String nombre){
        for (Puesto p :
                puestos) {
            if (p.getNombrePuesto().equals(nombre)){
                return p;
            }
        }
        return null;
    }

    public static Puesto getPuestoHolder() {
        return puestoHolder;
    }

    public static void setPuestoHolder(Puesto puestoHolder) {
        DataHolderPuesto.puestoHolder = puestoHolder;
    }
}
