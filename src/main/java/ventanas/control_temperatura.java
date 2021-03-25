package ventanas;

import DBUtilitie.DBType;
import DBUtilitie.DBUtilities;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class control_temperatura extends Aplicacion_principal implements Initializable {
    public Label lbl_fecha_revision;
    public JFXDatePicker date_fecha_revision;
    public JFXTextField txt_mantenimiento;
    public JFXTextField txt_temperatura;
    public Label lbl_id_pilones;
    public Label lbl_id_pilon;
    public JFXButton btn_guardar;
    public JFXButton btn_actualizar;
    public StackPane stack_control_temp;
    public DBUtilities db = new DBUtilities(DBType.MARIADB);


    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/control_temperatura.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("");
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date_fecha_revision.setValue(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        solo_numeros(txt_temperatura,3);
        solo_letras(txt_mantenimiento,25);

    }

    public void guardar(ActionEvent actionEvent) throws Exception {
        boton_guardar();
        Object[] campos = {lbl_id_pilon,txt_temperatura,date_fecha_revision,txt_mantenimiento};

        String[] mensaje = db.insert("insertar_control_temp",campos) ;
        if (mensaje[1].equals("1")){
            mensaje("Confirmaci\u00f3n", mensaje[0]
                    ,stack_control_temp);
        }else{
            mensaje("Error", mensaje[1]
                    ,stack_control_temp);
        }
        txt_temperatura.setText("");
        txt_mantenimiento.setText("");
    }

    private void boton_guardar() {
        btn_mensaje.setOnAction(event -> {
            dialogo.close();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            try {
                SidePanelController.datos_tabla_registro_temperatura();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        });
    }



}

