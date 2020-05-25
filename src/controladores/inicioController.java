package controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

import java.io.IOException;

public class inicioController {

    @FXML
    private AnchorPane changePane;
    @FXML
    private Button altaButton, buscarButton;

    @FXML
    private void buscar() throws IOException {
        buscarButton.setStyle("-fx-background-color: #183B97;");
        altaButton.setStyle("-fx-background-color:  #2E64FE");

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/vistas/buscar.fxml"));
        changePane.getChildren().setAll(pane);
    }

    @FXML
    private void alta() throws IOException {
        altaButton.setStyle("-fx-background-color: #183B97;");
        buscarButton.setStyle("-fx-background-color:  #2E64FE");

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/vistas/alta_empleado.fxml"));
        changePane.getChildren().setAll(pane);
    }

    public void salir(){
        System.exit(0);
    }

}
