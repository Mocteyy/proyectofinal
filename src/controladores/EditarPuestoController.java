package controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import modelos.DataHolderPuesto;
import modelos.Puesto;
import modelos.Validator;

public class EditarPuestoController {
    @FXML
    private TextArea description_puesto;

    @FXML
    private TextField nombre_puesto;

    @FXML
    private TextField salario_puesto;

    @FXML
    private Button guardar_button, salir_button;
    @FXML
    private StackPane rootPane;

    private Puesto puesto = DataHolderPuesto.getPuestoHolder();

    @FXML
    public void initialize(){


        description_puesto.setText(puesto.getDescription());
        nombre_puesto.setText(puesto.getNombrePuesto());
        nombre_puesto.setDisable(true);
        salario_puesto.setText(""+puesto.getSalario());
    }

    public void actualizar(){

        if (description_puesto.getText().isEmpty() && salario_puesto.getText().isEmpty()){
            alertas("Hay campos vacíos");
        }
        else if (!Validator.validateSalary(salario_puesto.getText()) || Double.parseDouble(salario_puesto.getText()) < 1){
            alertas("Salario inválido");
        }
        else{
            alertas("Se ha modificado el puesto");
            puesto.setDescription(description_puesto.getText());
            puesto.setSalario(Double.parseDouble(salario_puesto.getText()));

            Stage stage = (Stage) nombre_puesto.getParent().getScene().getWindow();
            stage.close();
        }
    }

    private void alertas(String aviso){
        JFXButton button = new JFXButton();
        Image image = new Image("/assets/icons/baseline_check_white_48dp.png", 25.0,25.0,true,true);
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);


        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(rootPane,dialogLayout,JFXDialog.DialogTransition.LEFT);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            dialog.close();

        });

        Label label = new Label(aviso);


        label.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold;");
        button.setStyle("-fx-background-radius: 50; -fx-background-color:  #ED9300; -fx-font-size: 10pt; -fx-text-fill: white; -fx-font-weight: bold");
        dialogLayout.setBody(label);
        dialogLayout.setActions(button);
        dialog.setStyle("-fx-background-radius: 20");


        dialog.show();
    }

    public void salir(){
        System.exit(0);
    }
}
