package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

public class AltaController {
    @FXML
    private TextField Textrfc, Textnombre, Textedad, Texttelefono, Textemail;
    @FXML
    private RadioButton Rm, Rf;
    @FXML
    private SplitMenuButton Splitpuestos;


    public void generarEmpleado() {
        String rfc, nombre, telefono, email, puesto;
        int edad;
        boolean sexo;

        rfc = Textrfc.getText();
        nombre = Textnombre.getText();
        if (Rm.isSelected()) {
            sexo = true;
        } else {
            sexo = false;
        }
        telefono = Texttelefono.getText();
        edad = Integer.parseInt(Textedad.getText());
        email = Textemail.getText();

    }
}
