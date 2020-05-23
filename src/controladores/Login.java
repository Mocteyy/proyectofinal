package controladores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Login {

    @FXML
    AnchorPane rootPane;
    @FXML
    PasswordField password;
    @FXML
    TextField user;
    @FXML
    private Button entrar;



    @FXML
    private void loadSecond() throws IOException{ //Cargar la segunda pantalla
        if (!user.getText().isEmpty() && !password.getText().isEmpty()) {
            if (user.getText().equalsIgnoreCase("prueba") && password.getText().equalsIgnoreCase("1234")) {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/vistas/inicio.fxml"));
                rootPane.getChildren().setAll(pane);
            }
        }
    }

    //Método para que al hacer enter se ejecute la segunda pantalla
    private void enter(){
        password.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER){
                if (user.getText().equalsIgnoreCase("prueba") && password.getText().equalsIgnoreCase("1234")) {
                    AnchorPane pane = null;
                    try {
                        pane = FXMLLoader.load(getClass().getResource("/vistas/inicio.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    rootPane.getChildren().setAll(pane);
                }
            }
        });
    }



    @FXML
    public void initialize(){
       enter();
    }
}
