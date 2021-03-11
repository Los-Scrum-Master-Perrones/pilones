import DBUtilitie.DBType;
import DBUtilitie.DBUtilities;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.ResourceBundle;

public class control_temperatura extends Aplicacion_principal implements Initializable {
    public Label lbl_temperatura;
    public Label lbl_fecha_revision;
    public Label lbl_mantenimiento;
    public JFXDatePicker date_fecha_revision;
    public JFXTextField txt_mantenimiento;
    public JFXTextField txt_temperatura;
    public Label lbl_id_pilones;
    public Label lbl_id_pilon;
    public JFXButton btn_guardar;
    public JFXButton btn_actualizar;
    public StackPane stack_control_temp;

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

    }

    public void guardar(ActionEvent actionEvent) throws Exception, ClassNotFoundException {
        FXMLLoader ventana = new FXMLLoader(getClass().getResource("/sidepanel.fxml"));

        Object[] campos = {lbl_id_pilon,txt_temperatura,date_fecha_revision,txt_mantenimiento};

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
                prepareStatement("call insertar_control_temp(?,?,?,?)");

        for(int i= 0;i<datos.length;i++){
            consulta.setString(i+1,datos[i]);
        }

        ResultSet respuesta = consulta.executeQuery();


        String mensaje[]= new String[2];
        while (respuesta.next()){
            mensaje[0]= respuesta.getString(1);
            mensaje[1]= respuesta.getString(2);

        }
         SidePanelController metodo = ventana.getController();
        metodo.datos_tabla_registro_temperatura();
        btn_mensaje.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialogo.close();

                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

            }
        });
        txt_temperatura.setText("");
        txt_mantenimiento.setText("");


        mensaje("Mensaje",mensaje[0],stack_control_temp );

    }
}
