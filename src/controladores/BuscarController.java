package controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import modelos.DataHolderEmpleados;
import modelos.DataHolderPuesto;
import modelos.Empleado;

import java.util.ArrayList;

public class BuscarController implements Runnable{

    private ArrayList<Empleado> resultado = DataHolderEmpleados.getEmpleados();

    @FXML
    private TableView<Empleado> tableEmpleado;
    @FXML
    private TableColumn<Empleado, String> colRfc,colNombre,colCorreo,colTelefono,colPuesto;

    private boolean flag = false;

    private Thread thread;



    @FXML
    public void initialize(){
        /*En este método se inicializan el hilo para ver si extán vacíos los campos, la tabla, se cargan los
        * valores ya establecidos en DataHolderEmpleados.
        * También se inicializó el combobox*/


        thread = new Thread(this);
        thread.start();
        enterSearch(searchNombre,searchRFC, searchPuesto);

        colRfc.setCellValueFactory(new PropertyValueFactory<>("RFC"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colPuesto.setCellValueFactory(new PropertyValueFactory<>("nombrePuesto"));

        if (!flag){
            for (int i = 0; i < DataHolderEmpleados.getMatrizPrueba().length; i++) {
                DataHolderEmpleados.create(DataHolderEmpleados.getMatrizPrueba()[i]);
            }
            flag = true;
        }

        ArrayList<String> puestos =  DataHolderPuesto.getNombrePuestos();
        puestos.add("Todos");

        ObservableList<String> opciones = FXCollections.observableArrayList(
                puestos
        );
        //tipoAlumnos.add("Universidad");tipoAlumnos.add("Preparatoria");tipoAlumnos.add("Secundaria");
        searchPuesto.getItems().addAll(FXCollections.observableList(opciones));

        tableEmpleado.getItems().addAll(resultado);


    }


    @FXML
    private TextField searchNombre, searchRFC;
    @FXML
    private ComboBox<String> searchPuesto;

    public void buscar(){
        if (!thread.isAlive()){ //Si el hilo está activo, se destruye para volver a inicializarse.
            thread = new Thread(this);
            thread.start();
        }

        if (searchNombre.getText().isEmpty() && searchRFC.getText().isEmpty() && (searchPuesto.getValue().equals("Todos") || searchPuesto.getValue().equals("Puesto"))){ //Verificación de textos nulos.
            System.out.println("Vacío");
        }else{
            if (rfcValdate()) { //Si el RFC está vacío, se procede a buscar o por nombre o por puesto
                busqueda();
            }else { //Se busca por RFC
                resultado = DataHolderEmpleados.read(searchRFC.getText(),DataHolderEmpleados.RFC);
            }
        }
    }


    int i = 1;
    @Override
    public void run() {
        //Hilo para reestablecer los valores de la tabla para mostrar todos los empleados.
        try {
            while (true) {
                if (searchNombre.getText().isEmpty() && searchRFC.getText().isEmpty() && (searchPuesto.getValue().equals("Todos") || searchPuesto.getValue().equals("Puesto"))) {
                    System.out.println(i);
                    i++;
                    tableEmpleado.getItems().clear();
                    tableEmpleado.refresh();
                    tableEmpleado.getItems().addAll(DataHolderEmpleados.getEmpleados());

                    tableEmpleado.refresh();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void enterSearch(TextField textField1, TextField textField2, ComboBox<String> puestos){ //Al presionar enter en cualquier campo de texto, se ejecuta la búsqueda.
        textField1.setOnKeyPressed(event -> {
            if (event.getCode()== KeyCode.ENTER){
                buscar();
            }
        });
        textField2.setOnKeyPressed(event -> {
            if (event.getCode()== KeyCode.ENTER){
                buscar();
            }
        });
        puestos.setOnAction(event -> {
                buscar();
        });
    }

    private void busqueda(){
        if (searchRFC.getText().isEmpty()){
            if (searchPuesto.getValue().equals("Todos") || searchPuesto.getValue().equals("Puesto")){
                resultado = DataHolderEmpleados.read(searchNombre.getText(), DataHolderEmpleados.NOMBRE);
            }
            else if(searchNombre.getText().isEmpty()){
                resultado = DataHolderEmpleados.read(searchPuesto.getValue(), DataHolderEmpleados.PUESTO);
            }
            else{
                resultado = DataHolderEmpleados.read(searchNombre.getText(),searchPuesto.getValue());
            }
        }


        tableEmpleado.getItems().clear();
        tableEmpleado.refresh();
        tableEmpleado.getItems().addAll(resultado);
        tableEmpleado.refresh();
    }

    private boolean rfcValdate(){
        return searchRFC.getText().isEmpty();
    }
}
