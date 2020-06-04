package controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
import javafx.stage.Stage;
import modelos.DataHolderEmpleados;
import modelos.DataHolderPuesto;
import modelos.Empleado;
import modelos.Validator;

public class EditarEmpleadoController {

    private Empleado empleado;

    @FXML
    private TextField rfcEditar, nombreEditar, emailEditar, edadEditar, telefonoEditar;
    @FXML
    private RadioButton sexoM, sexoF;
    @FXML
    private ComboBox<String> comboPuestoEditar;
    @FXML
    private Label salarioView;
    @FXML
    private StackPane rootPane;

    private boolean sexo;


    @FXML
    public void initialize(){



        empleado = DataHolderEmpleados.getEmpleadoEditarHolder();

        rfcEditar.setText(empleado.getRFC());
        nombreEditar.setText(empleado.getNombre());
        emailEditar.setText(empleado.getEmail());
        edadEditar.setText(String.valueOf(empleado.getEdad()));
        telefonoEditar.setText(empleado.getTelefono());
        if (empleado.isSexo()){
            sexoM.setSelected(true);
        }
        else{
            sexoF.setSelected(true);
        }

        ObservableList<String> opciones = FXCollections.observableArrayList(
                DataHolderPuesto.getNombrePuestos()
        );
        comboPuestoEditar.getItems().addAll(FXCollections.observableList(opciones));

        comboPuestoEditar.setValue(empleado.getNombrePuesto());

        rfcEditar.setDisable(true);

        salarioView.setText(salarioView.getText() + " " + empleado.getPuesto().getSalario());

        comboPuestoEditar.setOnAction(event -> {
            salarioView.setText( "Nuevo salario: " + DataHolderPuesto.leer(comboPuestoEditar.getValue()).getSalario());
        });
    }

    public void salir(){
        Stage stage = (Stage) rfcEditar.getParent().getScene().getWindow();
        stage.close();
    }


    public void controlDesicionM(){
        if (sexoM.isSelected()){
            sexoF.setSelected(false);
            sexo = true;
        }
    }

    public void controlDesicionF(){
        if (sexoF.isSelected()){
            sexoM.setSelected(false);
            sexo = false;
        }
    }

    public void guardar(){

        if (!nombreEditar.getText().isEmpty() && (sexoM.isSelected() || sexoF.isSelected()) && !telefonoEditar.getText().isEmpty() && !edadEditar.getText().isEmpty()
        && !emailEditar.getText().isEmpty() && !comboPuestoEditar.getValue().equals("PUESTO")) {

            if (Integer.parseInt(edadEditar.getText()) > 0) {
                if (validaciones(1, telefonoEditar.getText()) && validaciones(2, nombreEditar.getText())
                        && validaciones(3, emailEditar.getText())) {

                    DataHolderEmpleados.update(empleado, nombreEditar.getText(), sexo, telefonoEditar.getText(), Integer.parseInt(edadEditar.getText()), emailEditar.getText(), comboPuestoEditar.getValue());

                    Stage stage = (Stage) rfcEditar.getParent().getScene().getWindow();
                    stage.close();
                }
            }
            else{
                crearAlerta("Edad inválida", false);
            }

        }
        else{
            crearAlerta("Campo o campos vacíos", false);
        }


    }

    private boolean validaciones(int condi, String campoValidar){
        if (condi == 1){
            if (!Validator.validateTelephone(campoValidar)){
                crearAlerta("Número telefónico inválido", false);
                return false;
            }
        }
        else if (condi == 2){
            if (!Validator.validateName(campoValidar)){
                crearAlerta("En el nombre solo pueden ir letras", false);
                return false;
            }
        }
        else if(condi == 3){
            if (!Validator.valdateEmail(campoValidar)) {
                crearAlerta("Correo electrónico inválido", false);
                return false;
            }
        }
        return true;
    }

    private void crearAlerta(String aviso, boolean despedir){


        JFXButton button = new JFXButton();
        Image image = new Image("/assets/icons/baseline_check_white_48dp.png", 25.0,25.0,true,true);
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);


        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(rootPane,dialogLayout,JFXDialog.DialogTransition.LEFT);



        if (despedir){
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                Stage stage = (Stage) rfcEditar.getScene().getWindow();
                stage.close();
            });
        }else{
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                dialog.close();
            });
        }


        Label label = new Label(aviso);


        label.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold;");
        button.setStyle("-fx-background-radius: 50; -fx-background-color:  #ED9300; -fx-font-size: 10pt; -fx-text-fill: white; -fx-font-weight: bold");
        dialogLayout.setBody(label);
        dialogLayout.setActions(button);
        dialog.setStyle("-fx-background-radius: 20");

        dialog.show();
    }

    public void despedir(){

        JFXButton button = new JFXButton("Despedir");
        JFXButton button1 = new JFXButton("Cancelar");





        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(rootPane,dialogLayout,JFXDialog.DialogTransition.LEFT);

        Label label = new Label("Desea despedir a este empleado?");

        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            DataHolderEmpleados.delete(rfcEditar.getText());
            dialog.close();
            crearAlerta("Empleado despedido", true);
        });

        button1.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            dialog.close();
        });

        label.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold;");
        button.setStyle("-fx-background-radius: 50; -fx-background-color:  #ED9300; -fx-font-size: 10pt; -fx-text-fill: white; -fx-font-weight: bold");
        button1.setStyle("-fx-background-radius: 50; -fx-background-color:  #ED9300; -fx-font-size: 10pt; -fx-text-fill: white; -fx-font-weight: bold");
        dialogLayout.setBody(label);
        dialogLayout.setActions(button, button1);
        dialog.setStyle("-fx-background-radius: 20");

        dialog.show();



    }


}
