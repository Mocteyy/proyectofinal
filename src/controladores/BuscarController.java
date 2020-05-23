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


    @FXML
    public void initialize(){

        for (int i = 0; i < DataHolderEmpleados.getMatrizPrueba().length; i++) {
            DataHolderEmpleados.getEmpleados().add(DataHolderEmpleados.getMatrizPrueba()[i]);
        }

        colRfc.setCellValueFactory(new PropertyValueFactory<>("RFC"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colPuesto.setCellValueFactory(new PropertyValueFactory<>("puesto"));

        tableEmpleado.getItems().addAll(DataHolderEmpleados.getEmpleados());
    }

    @FXML
    private TextField searchNombre, searchRFC, searchPuesto;


}
