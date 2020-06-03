package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditarPuestoController {
    @FXML
    private TextArea description_puesto;

    @FXML
    private TextField nombre_puesto;

    @FXML
    private TextField salario_puesto;

    @FXML
    private Button buscar_button, salir_button;
}
