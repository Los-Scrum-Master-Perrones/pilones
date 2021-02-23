package clases;

import clases.DBUtilities.DBType;
import clases.DBUtilities.DBUtilities;
import clases.DBUtilities.RegistroCombobox;
import clases.Objetos_POJO.Clase_pilones_nombre;
import clases.Objetos_POJO.Clase_tabacos;
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

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class Proceso_pilon extends Aplicacion_principal implements Initializable, RegistroCombobox {
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
    public TextField txt_id_remision_pilon;
    public TextField txt_entradas_salidas;
    public TextField txt_subtotal;
    public TextField txt_total_libras;
    public Button btn_guardar_proceso_pilon;
    public Button btn_actualizar_proceso_pilon;
    public StackPane stack_proceso_pilon;
    public JFXComboBox<Clase_tabacos> cbb_nombre_tabaco;
    public JFXComboBox<Clase_pilones_nombre> cbb_numero_pilon;
    public CheckBox cbx_nombre_tabaco;
    public CheckBox cbx_numero_pilon;
    public Label lbl_nombre_tab;
    public Label lbl_numero_pilon;

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/resources/proceso_pilon.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Registro de proceso");
        stage.show();




    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public String Valores_tabaco(){
        String tabaco = new String();
        for (int i = 0; i<cbb_nombre_tabaco.getItems().size();i++){
            tabaco += cbb_nombre_tabaco.getItems().get(i).toString()+", ";
        }
        return tabaco.toString();
    }

    public void guardar(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Object[] campos = { date_fecha_proceso, txt_id_remision_pilon, txt_entradas_salidas,
                Valores_tabaco(),cbb_numero_pilon, txt_subtotal, txt_total_libras};

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
                prepareStatement("call insertar_tabla_pilon(?,?,?,?,?,?,?)");
        PreparedStatement consulta1 = DBUtilities.getConnection(DBType.MARIADB).
                prepareStatement("call insertar_tabla_procesos(?,?,?,?,?,?,?)");

        for (int i = 0; i < datos.length; i++) {
            consulta.setString(i + 1, datos[i]);
            consulta1.setString(i + 1, datos[i]);
        }

        ResultSet respuesta = consulta.executeQuery();
        ResultSet respuesta1 = consulta1.executeQuery();


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

        mensaje("Mensaje", mensaje[0], stack_proceso_pilon);

    }

    public void abrir_tabla_pilon(ActionEvent actionEvent) throws IOException {
        FXMLLoader vista_tabla_pilon = new FXMLLoader(getClass().getResource("/resources/tabla_registros_pilones.fxml"));

        StackPane root = vista_tabla_pilon.load();


        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Agregar Pilon");
        tabla_registros_pilones controlador = vista_tabla_pilon.getController();
        controlador.registrocontroller(this);
        controlador.Ocultar_botones();
        stage.show();


    }

    public void abrir_tabla_tabaco(ActionEvent actionEvent) throws IOException {
        FXMLLoader vista_tabla_tabaco = new FXMLLoader(getClass().getResource("/resources/tabla_clase_tabaco.fxml"));

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
        controlador.btn_guardar_claseTab_entradas_pilones.setVisible(false);
        controlador.btn_actualizar_claseTab_entradas_pilones.setVisible(false);
        stage.show();

    }




    @Override
    public JFXComboBox<Clase_tabacos>cargar_datos_tabaco() {
        return cbb_nombre_tabaco;
    }

    @Override
    public JFXComboBox<Clase_pilones_nombre> cargar_datos_pilon() {
        return cbb_numero_pilon;
    }

    @Override
    public JFXComboBox cargar_datos_entrada_tabaco() {
        return null;
    }

    @Override
    public JFXComboBox cargar_datos_entrada_pilon() {
        return null;
    }

}

