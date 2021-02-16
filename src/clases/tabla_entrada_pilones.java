package clases;

import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class tabla_entrada_pilones extends Aplicacion_principal implements Initializable {

    public Label lbl_id_entrada_pilon;
    public TextField txt_id_tabaco;
    public TextField txt_id_pilon;
    public TextField txt_tiempo_adelato;
    public TextField txt_cantidad_libras;
    public JFXDatePicker date_fecha_entrada;
    public JFXDatePicker date_fecha_estimada_salida;
    public Button btn_guardar_tabla_entrada;
    public Button btn_actualizar_tabla_entrada;

    public void start(Stage primaryStage) throws Exception{
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/resources/tabla_entrada_pilones.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Entrada de Pilones");
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
