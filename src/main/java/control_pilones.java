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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public static JFXTextField txt_clase_tabaco_control2;
    public static JFXTextField txt_numero_pilon_control2;
    public static JFXTextField jtxt_total_por_tabaco2;
    public JFXTextField txt_clase_tabaco_control;
    public  JFXTextField txt_numero_pilon_control;
    public  JFXTextField jtxt_total_por_tabaco;


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
        control_pilones.txt_numero_pilon_control2 = txt_numero_pilon_control;
        control_pilones.txt_clase_tabaco_control2 = txt_clase_tabaco_control;
        control_pilones.jtxt_total_por_tabaco2 = jtxt_total_por_tabaco;


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
        controlador1.setCon( this);

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
        controlador.setCon( this);

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

    @Override
    public JFXTextField cargar_total_actual() {
        return txt_numero_pilon_control;
    }

    private void Calcular_Existentcia_pilon(){
        double total = 0.00;
        double salidas = 0.00;
        double entradas = 0.00;
        double total_por_tab = 0.00;
        double total_actual = 0.00;

        entradas = Double.parseDouble(jtxt_entrada_tabaco_pilon.getText());
        salidas = Double.parseDouble(jtxt_salida_tabaco_pilon.getText());
        total_actual = Double.parseDouble(jtxt_total_actual.getText());

        if((entradas>=total_actual || salidas<=total_actual)  ) {
            total = ((total_actual) + (entradas) - (salidas));
            String total_existencia = String.valueOf(total);
            jtxt_existencia_total.setText(total_existencia);

        }/*else if(salidas<=total_por_tab){
            total = ((total_actual) + (entradas) - (salidas));
            String total_existencia = String.valueOf(total);
            jtxt_existencia_total.setText(total_existencia);
        }*/
        else{
            mensaje("Alerta","Las salidas no pueden exceder de la existencia total",stackpane_control_pilones);
            btn_mensaje.setOnAction(event -> {
                dialogo.close();
                jtxt_existencia_total.setText("0.00");
        });
        }
    }
    public static void Cargar_Total_Por_pilon(){
        try {
            String nume = null;

            //String num1 = control.txt_numero_pilon_control.getText();
            String num1 = control_pilones.txt_numero_pilon_control2.getText();
            String num2 = control_pilones.txt_clase_tabaco_control2.getText();
            //System.out.println("pilon '"+num1+"'");
            //System.out.println("tabaco '"+num2+"'");


            PreparedStatement st = DBUtilities.getConnection(DBType.MARIADB).prepareStatement("SELECT `1.1-detalles_tabaco_en_pilon`.libras FROM `1.1-detalles_tabaco_en_pilon`, `1-pilon_actividad`, clase_tabaco,pilones " +
                    "WHERE `1-pilon_actividad`.id_pilon = pilones.numero_pilon AND `1.1-detalles_tabaco_en_pilon`.id_pilon_activo = `1-pilon_actividad`.id and `1.1-detalles_tabaco_en_pilon`.id_tabaco = clase_tabaco.id_tabaco " +
                    "AND clase_tabaco.nombre_tabaco = '"+num2+"' AND pilones.numero_pilon = '"+num1+"'");
            ResultSet consulta = st.executeQuery();

            if (consulta != null && consulta.next()) {
                nume = consulta.getString(1);
                System.out.println(nume);
                String subtotal = String.valueOf(nume);
                jtxt_total_por_tabaco2.setText(subtotal);
            } else {
                jtxt_total_por_tabaco2.setText("0.00");
            }
            st.close();
            consulta.close();
            DBUtilities.getConnection(DBType.MARIADB).close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Entrada_pilon(KeyEvent keyEvent) {

        Calcular_Existentcia_pilon();
    }

    public void salida_pilon(KeyEvent keyEvent) {
        double salida = 0.00;
        salida = Double.parseDouble(jtxt_salida_tabaco_pilon.getText());
        double total_tab = 0.00;
        total_tab = Double.parseDouble(jtxt_total_por_tabaco.getText());
        if (salida > total_tab){
            mensaje("Alerta","Las salidas no pueden exceder de la existencia total que tiene el tabaco",stackpane_control_pilones);
            btn_mensaje.setOnAction(event -> {
                        dialogo.close();
                        jtxt_existencia_total.setText("0.00");
        });
        }else {
            Calcular_Existentcia_pilon();
        }
    }

    public void borra_entrada(MouseEvent mouseEvent) {
        //jtxt_entrada_tabaco_pilon.setEditable(true);
        jtxt_entrada_tabaco_pilon.setText("");
    }

    public void abandona_entra(MouseEvent mouseEvent) {
        if (jtxt_entrada_tabaco_pilon.getText().equals("")){
            jtxt_entrada_tabaco_pilon.setText("0.00");
        }
    }

    public void borra_salidas(MouseEvent mouseEvent) {
        //jtxt_salida_tabaco_pilon.setEditable(true);
        jtxt_salida_tabaco_pilon.setText("");
    }

    public void abandona_sali(MouseEvent mouseEvent) {
        if (jtxt_salida_tabaco_pilon.getText().equals("")){
            jtxt_salida_tabaco_pilon.setText("0.00");
        }
    }
}
