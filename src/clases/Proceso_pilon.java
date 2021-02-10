package clases;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class Proceso_pilon extends Aplicacion_principal implements Initializable {
    public Label lbl_id_procesopilon;
    public Label lbl_id_proceso_pilon;
    public Label lbl_fecha_proceso;
    public Label lbl_id_remi;
    public Label lbl_entradas_salidas;
    public Label lbl_id_tab;
    public Label lbl_id_pil;
    public Label lbl_subtotal;
    public Label lbl_total_libras;
    public DatePicker date_fecha_proceso;
    public Label lbl_id_remision;
    public TextField txt_entradas_salidas;
    public Label lbl_id_tabaco;
    public Label lbl_id_pilon;
    public TextField txt_subtotal;
    public TextField txt_total_libras;
    public Button btn_guardar_proceso_pilon;
    public Button btn_actualizar_proceso_pilon;

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/resources/proceso_pilon" +
                ".fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setHeight(420);
        stage.setWidth(600);
        stage.setTitle("");
        stage.show();




    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
