package clases;

import clases.DBUtilities.DBType;
import clases.DBUtilities.DBUtilities;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.ResourceBundle;

public class proceso_remision extends Aplicacion_principal implements Initializable {

    public StackPane stackpane;
    
    public Label id_remision;
    public Label lbl_id_remision;
    public Label numero_remision;
    public TextField txt_numero_remision;
    public Label fecha_remision;
    public DatePicker date_fecha_remision;
    public Label origen_remision;
    public TextField txt_origen_remision;
    public Label destino_remision;
    public TextField txt_destino_remision;
    public Label total_libras;
    public TextField txt_total_libras;
    public Label total_remision;
    public TextField txt_total_remision;
    public Button btn_guardar;
    public Button btn_actualizar;
    public TableView tbl_descripcion;
    public TextField txt_buscar_descripcion;
    public Label buscar_descripcion;


    @Override
    public void start(Stage primaryStage) throws Exception{
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/resources/proceso_remision.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setHeight(400);
        stage.setWidth(537);
        stage.setTitle("Proceso de Remisión");
        stage.show();
}


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardar(ActionEvent actionEvent) {





    }
}
