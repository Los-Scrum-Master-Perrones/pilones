package ventanas;

import DBUtilitie.DBType;
import DBUtilitie.DBUtilities;
import DBUtilitie.RegistroCombobox;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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

public class tabla_entrada_pilones extends Aplicacion_principal implements Initializable, RegistroCombobox {

    public Label lbl_id_entrada_pilon;
    public JFXTextField txt_id_tabaco;
    public JFXTextField txt_tiempo_adelato;
    public JFXTextField txt_cantidad_libras;
    public JFXDatePicker date_fecha_entrada;
    public JFXDatePicker date_fecha_estimada_salida;
    public JFXButton btn_guardar_tabla_entrada;
    public JFXButton btn_actualizar_tabla_entrada;
    public CheckBox cbx_id_tabaco;
    public CheckBox cbx_numero_pilon_entrada;
    public Label lbl_d_t;
    public StackPane stack_entrada_pilon;
    public JFXTextField txt_numero_pilon_entrada;

    public DBUtilities db = new DBUtilities(DBType.MARIADB);


    public void start(Stage primaryStage) throws Exception{
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/tabla_entrada_pilones.fxml"));

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


    public void Guardar(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Object[] campos = { txt_id_tabaco,txt_numero_pilon_entrada,date_fecha_entrada, txt_tiempo_adelato,
                date_fecha_estimada_salida, txt_cantidad_libras};

        String[] mensaje = db.insert("insertar_entrada_pilon", campos);
        if (mensaje[1].equals("1")) {
            mensaje("Confirmaci\u00f3n", mensaje[0]
                    , stack_entrada_pilon);
            btn_mensaje.setOnAction(event -> {
                dialogo.close();
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
                try {
                    SidePanelController.datos_tabla_entradas_pilon();
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }
            });
        }else{
            mensaje("Error", mensaje[0]
                    ,stack_entrada_pilon);
            btn_mensaje.setOnAction(event -> {
                dialogo.close();
                try {
                    SidePanelController.datos_tabla_entradas_pilon();
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }

            });
        }
    }

    public void Actualizar_entrada(ActionEvent actionEvent) {
        btn_mensaje.setOnAction(event -> {
            dialogo.close();
            try {
                SidePanelController.datos_tabla_entradas_pilon();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        });

        Object[] campos = {lbl_id_entrada_pilon,txt_id_tabaco,txt_numero_pilon_entrada,
                date_fecha_entrada,txt_tiempo_adelato,date_fecha_estimada_salida,txt_cantidad_libras};

        String[] mensaje = db.insert("actualizar_entrada_pilones",campos) ;

        if (mensaje[1].equals("1")){
            mensaje("Confirmaci\u00f3n", mensaje[0]
                    ,stack_entrada_pilon);
        }else{
            mensaje("Error", mensaje[1]
                    ,stack_entrada_pilon);
        }

    }

    public void abrir_tabla_tabaco(ActionEvent actionEvent) throws IOException {
        FXMLLoader vista_tabla_tabaco = new FXMLLoader(getClass().getResource("/tabla_clase_tabaco.fxml"));

        StackPane root = vista_tabla_tabaco.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Agregar tabaco");
        tabla_clase_tabaco controlador = vista_tabla_tabaco.getController();
        controlador.registrocontroller(this);
        controlador.btn_guardar_claseTab_pilones.setVisible(false);
        controlador.btn_actualizar_claseTab_pilones.setVisible(false);
        controlador.btn_guardar_Tab_control_pilones.setVisible(false);
        controlador.btn_actualizar_Tab_control_pilones.setVisible(false);
        controlador.Ocultar_botones1();
        stage.show();
    }

    public void abrir_tabla_pilon(ActionEvent actionEvent) throws IOException {
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
        controlador.btn_guardar_pilon_control_pilones.setVisible(false);
        controlador.btn_actualizar_pilon_control_pilones.setVisible(false);
        controlador.Ocultar_botones1();
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
        return txt_id_tabaco;
    }

    @Override
    public JFXTextField cargar_datos_entrada_pilon() {
        return txt_numero_pilon_entrada;
    }

    @Override
    public JFXTextField cargar_datos_tab_control_pilones() {
        return null;
    }

    @Override
    public JFXTextField cargar_datos_pilones_control_pilones() {
        return null;
    }



}
