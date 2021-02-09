package clases;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class control_temperatura extends Aplicacion_principal{
    public Label lbl_temperatura;
    public Label lbl_fecha_revision;
    public Label lbl_mantenimiento;
    public DatePicker date_fecha_revision;
    public TextField txt_mantenimiento;
    public TextField txt_temperatura;
    public Label lbl_id_pilones;
    public Label lbl_id_pilon;
    public Button btn_guardar;
    public Button btn_actualizar;

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/resources/control_temperatura.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setHeight(300);
        stage.setWidth(400);
        stage.setTitle("");
        stage.show();




    }
}
