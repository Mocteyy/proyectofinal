package controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Login {

    @FXML
    StackPane rootPane;
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
                Stage stage = (Stage) password.getScene().getWindow();
                stage.close();
                initSecond();
            }
            else{
                alerta();
            }
        }
    }

    //Método para que al hacer enter se ejecute la segunda pantalla
    private void enter(){
        password.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER){
                if (user.getText().equalsIgnoreCase("prueba") && password.getText().equalsIgnoreCase("1234")) {
                    Stage stage = (Stage) password.getScene().getWindow();
                    stage.close();

                    try {
                        initSecond();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    alerta();
                }
            }
        });
    }

    private void initSecond() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/inicio.fxml"));
        primaryStage.setTitle("Sistema gestor de empleados");

        primaryStage.setScene(new Scene(root, 900, 460));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/assets/images/LOGO3.png"));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });
    }


    @FXML
    public void initialize(){
       enter();
    }

    private void alerta(){
        JFXButton button = new JFXButton();
        Image image = new Image("/assets/icons/baseline_check_white_48dp.png", 25.0,25.0,true,true);
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);


        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(rootPane,dialogLayout,JFXDialog.DialogTransition.LEFT);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            dialog.close();

        });

        Label label = new Label("Usuario o contraseña incorrecto/a");


        label.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold;");
        button.setStyle("-fx-background-radius: 50; -fx-background-color:  #ED9300; -fx-font-size: 10pt; -fx-text-fill: white; -fx-font-weight: bold");
        dialogLayout.setBody(label);
        dialogLayout.setActions(button);
        dialog.setStyle("-fx-background-radius: 20");


        dialog.show();
    }
}
