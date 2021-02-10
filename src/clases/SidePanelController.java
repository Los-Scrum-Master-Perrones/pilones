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
    public JFXButton boton_entradas_salidas;
    public JFXButton boton_grafico_temperatura;
    private pantalla_principal ventana_nueva;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setActualizaMain(pantalla_principal actualizaMain){
        this.ventana_nueva = actualizaMain;

    }

    public void abrir_ventanas(ActionEvent actionEvent) {
        switch (((JFXButton)actionEvent.getSource()).getText()){
            case "Clase tabaco":

                break;
            case "Registro Pilones":

                break;
            case "Registro Temperatura":

                break;
            case "Entradas y Salidas":

                break;
            case "Gráfico de Temperatura":

                break;
        }

    }


}
