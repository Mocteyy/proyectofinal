package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import modelos.DataHolderEmpleados;
import modelos.Empleado;

import java.util.ArrayList;

public class BuscarController implements Runnable{

    private ArrayList<Empleado> resultado = DataHolderEmpleados.getEmpleados();

    @FXML
    private TableView<Empleado> tableEmpleado;
    @FXML
    private TableColumn<Empleado, String> colRfc,colNombre,colCorreo,colTelefono,colPuesto;

    private boolean flag = false, pausar = false, suspender = false;

    private Thread thread;
    private Runnable runnable;


    @FXML
    public void initialize(){

        runnable = this;
        thread = new Thread(runnable);
        thread.start();
        enterSearch(searchNombre,searchPuesto,searchRFC);

        colRfc.setCellValueFactory(new PropertyValueFactory<>("RFC"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colPuesto.setCellValueFactory(new PropertyValueFactory<>("puesto"));

        if (!flag){
            for (int i = 0; i < DataHolderEmpleados.getMatrizPrueba().length; i++) {
                DataHolderEmpleados.create(DataHolderEmpleados.getMatrizPrueba()[i]);
            }
            flag = true;
        }

        tableEmpleado.getItems().addAll(resultado);


    }


    @FXML
    private TextField searchNombre, searchRFC, searchPuesto;

    public void buscar(){
        if (!thread.isAlive()){
            thread = new Thread(this);
            thread.start();
        }

        if (searchNombre.getText().isEmpty() && searchRFC.getText().isEmpty() && searchPuesto.getText().isEmpty()){
            System.out.println("Vacío");
        }else{
            if (searchRFC.getText().isEmpty()){
                if (searchPuesto.getText().isEmpty()){
                    resultado = DataHolderEmpleados.read(searchNombre.getText(), DataHolderEmpleados.NOMBRE);
                }
                else if(searchNombre.getText().isEmpty()){
                    resultado = DataHolderEmpleados.read(searchPuesto.getText(), DataHolderEmpleados.PUESTO);
                }
                else{
                    resultado = DataHolderEmpleados.read(searchNombre.getText(),searchPuesto.getText());
                }
            }
            else{
                resultado = DataHolderEmpleados.read(searchRFC.getText(),DataHolderEmpleados.RFC);
            }

            tableEmpleado.getItems().clear();
            tableEmpleado.refresh();
            tableEmpleado.getItems().addAll(resultado);
            tableEmpleado.refresh();



        }
    }


    int i = 1;
    @Override
    public void run() {
        try {
            while (true) {
                if (searchNombre.getText().isEmpty() && searchRFC.getText().isEmpty() && searchPuesto.getText().isEmpty()) {
                    System.out.println(i);
                    i++;
                    tableEmpleado.getItems().clear();
                    //System.out.println(tableEmpleado.getItems().size());
                    tableEmpleado.refresh();
                    tableEmpleado.getItems().addAll(DataHolderEmpleados.getEmpleados());
                    //System.out.println(tableEmpleado.getItems().size());
                    tableEmpleado.refresh();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void enterSearch(TextField textField1, TextField textField2, TextField textField3){
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
        textField3.setOnKeyPressed(event -> {
            if (event.getCode()== KeyCode.ENTER){
                buscar();
            }
        });
    }


}
