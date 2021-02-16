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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

import static javafx.fxml.FXMLLoader.load;

public class Proceso_pilon extends Aplicacion_principal implements Initializable {
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
    public Label lbl_id_remision;
    public TextField txt_entradas_salidas;
    public Label cbb_nombre_tabaco;
    public Label cbb_numero_pilon;
    public TextField txt_subtotal;
    public TextField txt_total_libras;
    public Button btn_guardar_proceso_pilon;
    public Button btn_actualizar_proceso_pilon;
    public StackPane stack_proceso_pilon;

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/resources/proceso_pilon.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setHeight(420);
        stage.setWidth(600);
        stage.setTitle("");
        stage.show();




    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardar(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Object[] campos = {lbl_id_proceso_pilon,date_fecha_proceso,lbl_id_remision,txt_entradas_salidas,txt_subtotal,txt_total_libras};

        String[] datos = new String[campos.length];
        int contador = 0;

        for (Object o: campos){
            if (o instanceof TextField){
                datos[contador] = ((TextField)o).getText();
            }else if(o instanceof JFXDatePicker){
                datos[contador] = String.valueOf(((JFXDatePicker)o).getValue());
            }else if(o instanceof Label){
                datos[contador] = ((Label)o).getText();

            } contador ++;
        }
        System.out.println(Arrays.toString(datos));
        PreparedStatement consulta = DBUtilities.getConnection(DBType.MARIADB).
                prepareStatement("call insertar_tabla_procesos(?,?,?,?,?,?)");

        for(int i= 0;i<datos.length;i++){
            consulta.setString(i+1,datos[i]);
        }

        ResultSet respuesta = consulta.executeQuery();


        String mensaje[]= new String[2];
        while (respuesta.next()){
            mensaje[0]= respuesta.getString(1);
            mensaje[1]= respuesta.getString(2);

        }

        btn_mensaje.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialogo.close();
            }
        });

        mensaje("Mensaje",mensaje[0],stack_proceso_pilon );



    }
}
