import DBUtilitie.DBType;
import DBUtilitie.DBUtilities;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
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

public class proceso_remision extends Aplicacion_principal implements Initializable {

    public StackPane stackpane;
    
    public Label id_remision;
    public Label lbl_id_remision;
    public Label numero_remision;
    public JFXTextField txt_numero_remision;
    public Label fecha_remision;
    public DatePicker date_fecha_remision;
    public Label origen_remision;
    public JFXTextField txt_origen_remision;
    public Label destino_remision;
    public JFXTextField txt_destino_remision;
    public JFXTextField txt_descripcion2;
    public JFXTextField txt_descripcion3;
    public JFXTextField txt_descripcion4;
    public JFXTextField txt_descripcion5;
    public Label total_libras;
    public JFXTextField txt_total_libras;
    public JFXTextField txt_total_remision;
    public JFXButton btn_guardar;
    public JFXButton btn_actualizar;


    public Label lbl_descripcion;
    public Label lbl_cantidad_libras;
    public JFXTextField txt_descripcion11;
    public JFXTextField txt_descripcion22;
    public JFXTextField txt_descripcion33;
    public JFXTextField txt_descripcion44;
    public JFXTextField txt_descripcion55;
    public JFXTextField txt_description1;





    @Override
    public void start(Stage primaryStage) throws Exception{
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/proceso_remision.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setHeight(400);
        stage.setWidth(537);
        stage.setTitle("Proceso de Remisión");
        stage.show();
}


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txt_total_libras.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(txt_total_libras.getText().length() >= 7){
                    event.consume();

                }


                if(Character.isLetter(event.getCharacter().charAt(0))){
                    event.consume();


                }
            }
        });

    }

    public void guardar(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Object[] campos = {txt_numero_remision,date_fecha_remision,txt_origen_remision,txt_destino_remision,txt_description1,txt_descripcion11,
                txt_descripcion2.getText().equals("")?null:txt_descripcion2,txt_descripcion22.getText().equals("")?null:txt_descripcion22,
                txt_descripcion3.getText().equals("")?null:txt_descripcion3,txt_descripcion33.getText().equals("")?null:txt_descripcion33,
                txt_descripcion4.getText().equals("")?null:txt_descripcion4,txt_descripcion44.getText().equals("")?null:txt_descripcion44,
                txt_descripcion5.getText().equals("")?null:txt_descripcion5,txt_descripcion55.getText().equals("")?null:txt_descripcion55,
                txt_total_libras};

        String[] datos = new String[campos.length];
        int contador = 0;

        for (Object o: campos){
            if (o instanceof JFXTextField){
                datos[contador] = ((JFXTextField)o).getText();
            }else if(o instanceof JFXDatePicker){
                datos[contador] = String.valueOf(((JFXDatePicker)o).getValue());
            //}else if(o instanceof Label) {
                //datos[contador] = ((Label) o).getText();
            } else if(o instanceof JFXTextField){
                datos[contador] = String.valueOf(((JFXTextField)o).getText());

            } contador ++;
        }
        System.out.println(Arrays.toString(datos));
        PreparedStatement consulta = DBUtilities.getConnection(DBType.MARIADB).
                prepareStatement("call insertar_remision_proceso(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");


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

        mensaje("Mensaje",mensaje[0],stackpane );


    }
}
