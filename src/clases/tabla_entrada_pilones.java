package clases;

import clases.DBUtilities.DBType;
import clases.DBUtilities.DBUtilities;
import clases.Objetos_POJO.Clase_pilones_nombre;
import com.jfoenix.controls.JFXComboBox;
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
import java.sql.SQLException;
import java.util.Arrays;
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
    public ComboBox cbb_id_tabaco;
    public CheckBox cbx_id_tabaco;
    public ComboBox cbb_numero_pilon;
    public CheckBox cbx_numero_pilon;
    public Label lbl_d_t;
    public StackPane stack_entrada_pilon;

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


    public void Guardar(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Object[] campos = { cbb_id_tabaco,cbb_numero_pilon,date_fecha_entrada, txt_tiempo_adelato, date_fecha_estimada_salida,
               txt_cantidad_libras};

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
                prepareStatement("call insertar_tabla_pilon(?,?,?,?,?,?)");

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



}
