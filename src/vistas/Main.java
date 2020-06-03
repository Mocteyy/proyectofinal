package vistas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelos.DataHolderEmpleados;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Sistema gestor de empleados");
        primaryStage.setScene(new Scene(root, 690, 350));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/assets/images/LOGO3.png"));
        primaryStage.show();


        for (int i = 0; i < DataHolderEmpleados.getMatrizPrueba().length; i++) {
            DataHolderEmpleados.create(DataHolderEmpleados.getMatrizPrueba()[i]);
        }



        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });

    }




    public static void main(String[] args) {
        launch(args);
    }
}
