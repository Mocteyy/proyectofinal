package controladores;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelos.DataHolderEmpleados;
import modelos.DataHolderPuesto;
import modelos.Empleado;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;

public class BuscarController implements Runnable{

    private boolean flag = false;
    private static boolean cambio = false;
    private Stage stage;
    private Thread thread;
    private ArrayList<Empleado> resultado =  DataHolderEmpleados.getEmpleados();


    @FXML
    private TableView<Empleado> tableEmpleado;
    @FXML
    private TableColumn<Empleado, String> colRfc,colNombre,colCorreo,colTelefono,colPuesto;
    @FXML
    private Button search;

    @FXML
    public void initialize() {
        /*En este método se inicializan el hilo para ver si extán vacíos los campos, la tabla, se cargan los
        * valores ya establecidos en DataHolderEmpleados.
        * También se inicializó el combobox*/




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
        searchPuesto.getItems().addAll(FXCollections.observableList(opciones));
        searchPuesto.setValue("Puesto");




        tableEmpleado.getItems().addAll(resultado);


        setOnClicListenerTable();

        thread = new Thread(this);
        thread.start();


    }

    private void inicializarEditarPane() throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("/vistas/editar_empleado.fxml"));
        stage = new Stage();
        stage.setTitle("Editar empleado");
        stage.setScene(new Scene(pane));
        stage.show();

    }

    public void setOnClicListenerTable(){
        tableEmpleado.setRowFactory(tv -> {
            TableRow<Empleado> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty())) {
                    DataHolderEmpleados.setEmpleadoEditarHolder(row.getItem());
                    try {
                        inicializarEditarPane();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });
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

        if (searchNombre.getText().isEmpty() && searchRFC.getText().isEmpty() && (searchPuesto.getValue().equals("Puesto"))){ //Verificación de textos nulos.
            System.out.println("Vacío");
        }else{
            if (rfcValdate()) { //Si el RFC está vacío, se procede a buscar o por nombre o por puesto
                busqueda();

            }else { //Se busca por RFC
                resultado = DataHolderEmpleados.read(searchRFC.getText(),DataHolderEmpleados.RFC);
            }
        }
    }



    @Override
    public void run() {
        //Hilo para reestablecer los valores de la tabla para mostrar todos los empleados.

            while (true) {
                if (searchNombre.getText().isEmpty() && searchRFC.getText().isEmpty() && (searchPuesto.getValue().equals("Puesto") || searchPuesto.getValue().equals("Puesto"))) {

                    tableEmpleado.refresh();
                    tableEmpleado.getItems().clear();

                    tableEmpleado.refresh();
                    System.out.println("Tabla: "+tableEmpleado.getItems());
                    tableEmpleado.getItems().addAll(DataHolderEmpleados.getEmpleados());

                    tableEmpleado.refresh();

                    break;
                }
                if (searchNombre.getText().isEmpty() && !searchPuesto.getValue().equals("Puesto")){
                    busqueda();
                    break;
                }
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
                if (searchPuesto.getValue().equals("Todos")){
                    resultado = DataHolderEmpleados.getEmpleados();
                }else {
                    resultado = DataHolderEmpleados.read(searchPuesto.getValue(), DataHolderEmpleados.PUESTO);
                    System.out.println(resultado);
                }
            }
            else{
                resultado = DataHolderEmpleados.read(searchNombre.getText(),searchPuesto.getValue());
            }
        }
        tableEmpleado.getItems().clear();
        tableEmpleado.refresh();

        tableEmpleado.getItems().addAll(resultado);
        //System.out.println("Tabla: "+tableEmpleado.getItems());
        tableEmpleado.refresh();
    }

    private boolean rfcValdate(){
        return searchRFC.getText().isEmpty();
    }


    public void actualizar(){
        tableEmpleado.refresh();
    }
}
