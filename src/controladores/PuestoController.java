package controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import modelos.DataHolderPuesto;
import modelos.Puesto;

import java.io.IOException;

public class PuestoController{
    @FXML
    private Button editarID,regresarID;
    @FXML
    private TableView <Puesto> tablePuesto;
    @FXML
    private TableColumn <Puesto, String> nombrePuesto, description;
    @FXML
    private TableColumn <Puesto, Double> salario;

    private Stage stage;


    @FXML
    public void initialize ()
    {

        nombrePuesto.setCellValueFactory(new PropertyValueFactory<>("nombrePuesto"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        salario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        tablePuesto.getItems().addAll(DataHolderPuesto.getPuestos());
        setOnClicListenerTable();

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

    public void actualizar(){
        tablePuesto.getItems().clear();
        tablePuesto.refresh();
        tablePuesto.getItems().addAll(DataHolderPuesto.getPuestos());
    }

}
