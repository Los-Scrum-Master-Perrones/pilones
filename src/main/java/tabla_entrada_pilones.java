import DBUtilities.DBType;
import DBUtilities.DBUtilities;
import DBUtilities.RegistroCombobox;
import Objetos_POJO.Clase_pilones_nombre;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class tabla_entrada_pilones extends Aplicacion_principal implements Initializable, RegistroCombobox {

    public Label lbl_id_entrada_pilon;
    public TextField txt_id_tabaco;
    public TextField txt_id_pilon;
    public JFXTextField txt_tiempo_adelato;
    public JFXTextField txt_cantidad_libras;
    public JFXDatePicker date_fecha_entrada;
    public JFXDatePicker date_fecha_estimada_salida;
    public JFXButton btn_guardar_tabla_entrada;
    public JFXButton btn_actualizar_tabla_entrada;
    public JFXComboBox cbb_id_tabaco;
    public CheckBox cbx_id_tabaco;
    public JFXComboBox cbb_numero_pilon_entrada;
    public CheckBox cbx_numero_pilon_entrada;
    public Label lbl_d_t;
    public StackPane stack_entrada_pilon;


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
        Object[] campos = { cbb_id_tabaco.getSelectionModel().getSelectedItem().toString(),cbb_numero_pilon_entrada,date_fecha_entrada, txt_tiempo_adelato,
                date_fecha_estimada_salida, txt_cantidad_libras};

        String[] datos = new String[campos.length];
        int contador = 0;

        for (Object o : campos) {
            if (o instanceof TextField) {
                datos[contador] = ((TextField) o).getText();
            } else if (o instanceof JFXDatePicker) {
                datos[contador] = String.valueOf(((JFXDatePicker) o).getValue());
            } else if (o instanceof Label) {
                datos[contador] = ((Label) o).getText();
            } else if (o instanceof JFXComboBox) {
                datos[contador] = ((Clase_pilones_nombre)((JFXComboBox<Clase_pilones_nombre>) o)
                        .getSelectionModel().getSelectedItem()).getNombre_pilon();
            }else if (o instanceof String) {
                datos[contador] =(String)  o;
            }
            contador++;
        }
        System.out.println(Arrays.toString(datos));
        PreparedStatement consulta = DBUtilities.getConnection(DBType.MARIADB).
                prepareStatement("call insertar_entrada_pilon(?,?,?,?,?,?)");

        for (int i = 0; i < datos.length; i++) {
            consulta.setString(i + 1, datos[i]);

        }

        ResultSet respuesta = consulta.executeQuery();



        String mensaje[] = new String[2];
        while (respuesta.next()) {
            mensaje[0] = respuesta.getString(1);
            mensaje[1] = respuesta.getString(2);

        }

        btn_mensaje.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialogo.close();

            }

        });

        mensaje("Mensaje", mensaje[0], stack_entrada_pilon);

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
    public JFXComboBox cargar_datos_tabaco() {
        return null;
    }

    @Override
    public JFXComboBox cargar_datos_pilon() {
        return null;
    }

    @Override
    public JFXComboBox cargar_datos_entrada_tabaco() {
        return cbb_id_tabaco;
    }

    @Override
    public JFXComboBox cargar_datos_entrada_pilon() {
        return cbb_numero_pilon_entrada;
    }

    @Override
    public JFXComboBox cargar_datos_tab_control_pilones() {
        return null;
    }

    @Override
    public JFXComboBox cargar_datos_pilones_control_pilones() {
        return null;
    }


}
