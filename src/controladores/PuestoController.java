package controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import modelos.DataHolderEmpleados;
import modelos.DataHolderPuesto;
import modelos.Empleado;
import modelos.Puesto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PuestoController implements {
    @FXML
    private Button editarID,regresarID;
    @FXML
    private TableView <Puesto> tablePuesto;
    @FXML
    private TableColumn <Puesto, String> colNomPuesto,colDescripcion;
    @FXML
    private TableColumn <Puesto, Double> colSalario;

    private Stage stage;



    public void Initialize ()
    {

        colNomPuesto.setCellValueFactory(new PropertyValueFactory<>("nombrePuesto"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        tablePuesto.getItems().addAll(DataHolderPuesto.getPuestoHolder());

    }

    public void regresar()
    {


    }


    public void goToEdit () throws IOException {

        StackPane pane = FXMLLoader.load(getClass().getResource("/vistas/editar_puesto.fxml"));
        stage = new Stage();
        stage.setTitle("Editar puesto");
        stage.setScene(new Scene(pane));
        stage.getIcons().add(new Image("/assets/images/LOGO3.png"));
        stage.show();

    }

    public void setOnClicListenerTable(){
        tablePuesto.setRowFactory(tv -> {
            TableRow<Puesto> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty())) {
                    DataHolderPuesto.setPuestoHolder(row.getItem());
                    try {
                        goToEdit();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });
    }

}
