package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import modelos.DataHolderEmpleados;
import modelos.Empleado;

public class AltaController {
    @FXML
    private TextField Textrfc, Textnombre, Textedad, Texttelefono, Textemail;
    @FXML
    private RadioButton Rm, Rf;
    @FXML
    private ComboBox Combopuestos;


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
        puesto = Combopuestos.getValue().toString();

        Empleado a = new Empleado(rfc,nombre,sexo,telefono,edad,email,puesto);

        DataHolderEmpleados queso = new DataHolderEmpleados();
        queso.create(a);
    }
}
