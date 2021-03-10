import DBUtilities.DBType;
import DBUtilities.DBUtilities;
import DBUtilities.RegistroCombobox;
import Objetos_POJO.Clase_pilones_nombre;
import Objetos_POJO.Clase_tabacos;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    public Label lbl_total_remision;
    public JFXTextField txt_total_remision;
    public JFXCheckBox cbx_tabla_proceso;
    public JFXCheckBox cbx_tabla_pilon;


    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/proceso_pilon.fxml"));

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
                Valores_tabaco(),cbb_numero_pilon, txt_subtotal, txt_total_libras,txt_total_remision};

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
                datos[contador] = ((JFXComboBox<Clase_pilones_nombre>) o)
                        .getSelectionModel().getSelectedItem().getNombre_pilon();
            }else if (o instanceof String) {
                datos[contador] =(String)  o;
            }
            contador++;
        }
        System.out.println(Arrays.toString(datos));

        if (cbx_tabla_proceso.isSelected()){
            PreparedStatement consulta1 = DBUtilities.getConnection(DBType.MARIADB).
                    prepareStatement("call insertar_tabla_procesos(?,?,?,?,?,?,?,?)");

            for (int i = 0; i < datos.length; i++) {
                consulta1.setString(i + 1, datos[i]);
                consulta1.setString(i + 1, datos[i]);
            }

            ResultSet respuesta = consulta1.executeQuery();
            ResultSet respuesta1 = consulta1.executeQuery();


            String mensaje[] = new String[2];
            while (respuesta.next()) {
                mensaje[0] = respuesta.getString(1);
                mensaje[1] = respuesta.getString(2);

            }
            SidePanelController.datos_tabla_entradas_salidas();

            btn_mensaje.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialogo.close();
                    Node source = (Node) actionEvent.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();

                }
            });
            mensaje("Mensaje", mensaje[0], stack_proceso_pilon);

        }else if ( cbx_tabla_pilon.isSelected()){
            PreparedStatement consulta = DBUtilities.getConnection(DBType.MARIADB).
                    prepareStatement("call insertar_tabla_pilon(?,?,?,?,?,?,?,?)");

            for (int i = 0; i < datos.length; i++) {
                consulta.setString(i + 1, datos[i]);
                consulta.setString(i + 1, datos[i]);
            }

            ResultSet respuesta = consulta.executeQuery();
            ResultSet respuesta1 = consulta.executeQuery();


            String mensaje[] = new String[2];
            while (respuesta.next()) {
                mensaje[0] = respuesta.getString(1);
                mensaje[1] = respuesta.getString(2);

            }

            SidePanelController.datos_tabla_entradas_salidas();

            btn_mensaje.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialogo.close();
                    Node source = (Node) actionEvent.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();

                }
            });
            mensaje("Mensaje", mensaje[0], stack_proceso_pilon);

        }
    }

    public void abrir_tabla_pilon(ActionEvent actionEvent) throws IOException {
        FXMLLoader vista_tabla_pilon = new FXMLLoader(getClass().getResource("/tabla_registros_pilones.fxml"));

        StackPane root1 = vista_tabla_pilon.load();


        Scene scene = new Scene(root1);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Agregar Pilon");
        tabla_registros_pilones controlador1 = vista_tabla_pilon.getController();
        controlador1.registrocontroller(this);
        controlador1.btn_guardar_registro_entrada_pilones.setVisible(false);
        controlador1.btn_actualizar_registro_entrada_pilones.setVisible(false);
        controlador1.btn_guardar_pilon_control_pilones.setVisible(false);
        controlador1.btn_actualizar_pilon_control_pilones.setVisible(false);
        controlador1.Ocultar_botones();
        stage.show();


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
        controlador.btn_guardar_claseTab_entradas_pilones.setVisible(false);
        controlador.btn_actualizar_claseTab_entradas_pilones.setVisible(false);
        controlador.btn_guardar_Tab_control_pilones.setVisible(false);
        controlador.btn_actualizar_Tab_control_pilones.setVisible(false);
        controlador.Ocultar_botones();
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

    @Override
    public JFXComboBox cargar_datos_tab_control_pilones() {
        return null;
    }

    @Override
    public JFXComboBox cargar_datos_pilones_control_pilones() {
        return null;
    }


}

