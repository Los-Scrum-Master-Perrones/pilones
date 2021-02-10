package clases;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class SidePanelController extends Aplicacion_principal implements Initializable {


    public JFXButton boton_clase_tabaco;
    public JFXButton boton_registro_pilones;
    public JFXButton boton_temperatura;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
