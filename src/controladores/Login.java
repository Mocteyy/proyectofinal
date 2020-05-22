package controladores;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Login {

    public void iniciarSesion() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../vistas/inicio.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Inicio");
        primaryStage.setScene(new Scene(root, 740, 470));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
