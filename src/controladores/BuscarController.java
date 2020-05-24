package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelos.DataHolderEmpleados;
import modelos.Empleado;

public class BuscarController {

    @FXML
    private TableView<Empleado> tableEmpleado;
    @FXML
    private TableColumn<Empleado, String> colRfc,colNombre,colCorreo,colTelefono,colPuesto;

    private boolean flag = false;


    @FXML
    public void initialize(){



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

        tableEmpleado.getItems().addAll(DataHolderEmpleados.getEmpleados());

    }

    @FXML
    private TextField searchNombre, searchRFC, searchPuesto;

    public void buscar(){
        if (searchNombre.getText().isEmpty() && searchRFC.getText().isEmpty() && searchPuesto.getText().isEmpty()){
            System.out.println("Vacío");
        }else{

        }
    }

}
