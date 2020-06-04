package controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import modelos.DataHolderEmpleados;
import modelos.DataHolderPuesto;
import modelos.Empleado;
import com.jfoenix.controls.events.JFXDialogEvent;
import modelos.Validator;

import java.awt.*;
import java.beans.EventHandler;


public class AltaController {
    @FXML
    private TextField textRfc, textNombre, textEdad, textTelefono, textEmail;
    @FXML
    private RadioButton rM, rF;
    @FXML
    private ComboBox<String> comboPuestos;
    @FXML
    private StackPane rootPane;

    private String rfc, nombre, telefono, email, puesto;

    private boolean sexo = false;

    private int edad = 0;

    @FXML
    public void initialize(){
        ObservableList<String> puestos = FXCollections.observableArrayList(DataHolderPuesto.getNombrePuestos());
        comboPuestos.getItems().addAll(puestos);
        comboPuestos.setValue("PUESTO");
    }

    public void generarEmpleado() {




        try {
            if (!textTelefono.getText().isEmpty() && !textEdad.getText().isEmpty() && !textEmail.getText().isEmpty() &&
                    !textNombre.getText().isEmpty() && (rF.isSelected() || rM.isSelected()) &&
                    !comboPuestos.getValue().equals("PUESTO")) {

                if (validaciones(1, textTelefono.getText()) && validaciones(2, textNombre.getText()) &&
                        validaciones(3,textEmail.getText()) && validaciones(4, textRfc.getText())) {



                    rfc = textRfc.getText();
                    nombre = textNombre.getText();


                    telefono = textTelefono.getText();

                    edad = Integer.parseInt(textEdad.getText());

                    if (edad>0) {

                        email = textEmail.getText();
                        puesto = comboPuestos.getValue();

                        Empleado empleado = new Empleado(rfc.toUpperCase(), nombre, sexo, telefono, edad, email, puesto);

                        if (DataHolderEmpleados.create(empleado)) {
                            crearAlerta("Empleado contratado!");
                        } else {
                            crearAlerta("RFC ya existente");
                        }
                    }
                    else{
                        crearAlerta("Edad inválida");
                    }
                }
            } else {
                crearAlerta("Faltan uno o más campos!");
            }
        }catch (Exception e){
            crearAlerta("Se ha producido un error, campo(s) erróneo(s)");
        }
    }

    public void seleccionSexoM(){

        if (rM.isSelected()){
            rF.setSelected(false);
            sexo = true;
        }

    }

    public void seleccionSexoF(){
        if (rF.isSelected()){
            rM.setSelected(false);
            sexo = false;
        }
    }

    public void crearAlerta(String aviso){



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

    private boolean validaciones(int condi, String campoValidar){
        if (condi == 1){
            if (!Validator.validateTelephone(campoValidar)){
                crearAlerta("Número telefónico inválido");
                return false;
            }
        }
        else if (condi == 2){
            if (!Validator.validateName(campoValidar)){
                crearAlerta("En el nombre solo pueden ir letras");
                return false;
            }
        }
        else if(condi == 3){
            if (!Validator.valdateEmail(campoValidar)) {
                crearAlerta("Correo electrónico inválido");
                return false;
            }
        }
        else{
            if (!Validator.validateRFC(campoValidar)){
                crearAlerta("RFC inválido");
                return false;
            }
        }
        return true;
    }


}

