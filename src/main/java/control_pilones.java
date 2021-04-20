import DBUtilitie.DBUtilities;
import DBUtilitie.DBType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DBUtilitie.RegistroCombobox;

public class control_pilones extends Aplicacion_principal implements Initializable, RegistroCombobox {

    public Label lbl_id_control_pilones;
    public Label lbl_id_control_pilon;
    public CheckBox cbx_clase_tabaco;
    public DatePicker jdate_fecha_control;
    public CheckBox cbx_numero_pilon;
    public JFXTextField jtxt_entrada_tabaco_pilon;
    public JFXTextField jtxt_salida_tabaco_pilon;
    public JFXTextField jtxt_total_actual;
    public JFXTextField jtxt_existencia_total;
    public JFXButton btn_guardar_control_pilones;
    public JFXButton btn_actualizar_control_pilones;
    public StackPane stackpane_control_pilones;
    public DBUtilities db = new DBUtilities(DBType.MARIADB);
    public JFXTextField txt_clase_tabaco_control;
    public JFXTextField txt_numero_pilon_control;



    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/tabla_control_pilones.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Control pilones");
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        soloNumerosyunPunto(jtxt_entrada_tabaco_pilon,3,6);
        soloNumerosyunPunto(jtxt_salida_tabaco_pilon,3,6);
        soloNumerosyunPunto(jtxt_total_actual,3,6);
        soloNumerosyunPunto(jtxt_existencia_total,3,6);


    }
    public void Guardar(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {

        boton_guardar();
        Object[] campos = { txt_clase_tabaco_control, jdate_fecha_control,
                txt_numero_pilon_control,jtxt_entrada_tabaco_pilon,jtxt_salida_tabaco_pilon,
                jtxt_total_actual,jtxt_existencia_total};

        String[] mensaje = db.insert("insertar_control_pilones",campos) ;
        if (mensaje[1].equals("1")){
            mensaje("Confirmaci\u00f3n", mensaje[0]
                    ,stackpane_control_pilones);
        }else{
            mensaje("Error", mensaje[1]
                    ,stackpane_control_pilones);
        }

    }

    private void boton_guardar() {
        btn_mensaje.setOnAction(event -> {
            dialogo.close();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            try {
                SidePanelController.datos_tabla_control_pilones();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }

            });
        }

    public void actualizar_ctrl_pilon(ActionEvent actionEvent) {
        boton_guardar();
        Object[] campos = {lbl_id_control_pilon, txt_clase_tabaco_control,
                 jdate_fecha_control,
                txt_numero_pilon_control,jtxt_entrada_tabaco_pilon,jtxt_salida_tabaco_pilon,
                jtxt_total_actual,jtxt_existencia_total};

        String[] mensaje = db.insert("actualizar_control_pilones",campos) ;
        if (mensaje[1].equals("1")){
            mensaje("Confirmaci\u00f3n", mensaje[0]
                    ,stackpane_control_pilones);
        }else{
            mensaje("Error", mensaje[1]
                    ,stackpane_control_pilones);
        }

    }





    public void Abrir_clase_tab_ctrl_pilones(ActionEvent actionEvent) throws IOException {
        FXMLLoader vista_tabla_tabaco = new FXMLLoader(getClass().getResource("/tabla_clase_tabaco.fxml"));

        StackPane root = vista_tabla_tabaco.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Agregar tabaco");
        tabla_clase_tabaco controlador1 = vista_tabla_tabaco.getController();
        controlador1.registrocontroller(this);
        controlador1.btn_guardar_claseTab_pilones.setVisible(false);
        controlador1.btn_actualizar_claseTab_pilones.setVisible(false);
        controlador1.btn_guardar_claseTab_entradas_pilones.setVisible(false);
        controlador1.btn_actualizar_claseTab_entradas_pilones.setVisible(false);
        controlador1.Ocultar_botones2();
        stage.show();
    }

    public void Abrir_clase_pilon_ctrl_pilones(ActionEvent actionEvent) throws IOException {
        FXMLLoader vista_tabla_pilon_entra = new FXMLLoader(getClass().getResource("/tabla_registros_pilones.fxml"));

        StackPane root = vista_tabla_pilon_entra.load();


        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Agregar Pilon");
        tabla_registros_pilones controlador = vista_tabla_pilon_entra.getController();
        controlador.registrocontroller( this);
        controlador.btn_guardar_registro_pilones.setVisible(false);
        controlador.btn_actualizar_registro_pilones.setVisible(false);
        controlador.btn_guardar_registro_entrada_pilones.setVisible(false);
        controlador.btn_actualizar_registro_entrada_pilones.setVisible(false);
        controlador.Ocultar_botones2();
        stage.show();
    }


    @Override
    public JFXTextField cargar_datos_tabaco() {
        return null;
    }

    @Override
    public JFXTextField cargar_datos_pilon() {
        return null;
    }

    @Override
    public JFXTextField cargar_datos_entrada_tabaco() {
        return null;
    }

    @Override
    public JFXTextField cargar_datos_entrada_pilon() {
        return null;
    }

    @Override
    public JFXTextField cargar_datos_tab_control_pilones() {
        return txt_clase_tabaco_control;
    }

    @Override
    public JFXTextField cargar_datos_pilones_control_pilones() {
        return txt_numero_pilon_control;
    }



}
