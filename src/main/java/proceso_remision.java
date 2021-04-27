import DBUtilitie.DBType;
import DBUtilitie.DBUtilities;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.SQLException;
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
    public JFXButton btn_guardar;
    public JFXButton btn_actualizar;
    public DBUtilities db = new DBUtilities(DBType.MARIADB);

    public Label lbl_descripcion;
    public Label lbl_cantidad_libras;
    public JFXTextField txt_descripcion11;
    public JFXTextField txt_descripcion22;
    public JFXTextField txt_descripcion33;
    public JFXTextField txt_descripcion44;
    public JFXTextField txt_descripcion55;
    public JFXTextField txt_description1;


    @Override
    public void start(Stage primaryStage) throws Exception {
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
        stage.setTitle("Proceso de Remisi\u00f3n");
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    solo_numeros(txt_numero_remision,4);
    solo_letras(txt_destino_remision,30);
    solo_letras(txt_origen_remision,30);
    soloNumerosyunPunto(txt_descripcion11,3,6);
    soloNumerosyunPunto(txt_descripcion22,3,6);
    soloNumerosyunPunto(txt_descripcion33,3,6);
    soloNumerosyunPunto(txt_descripcion44,3,6);
    soloNumerosyunPunto(txt_descripcion55,3,6);
   soloNumerosyunPunto(txt_total_libras,3,6);


    }

    public void guardar(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        Object[] campos = {txt_numero_remision, date_fecha_remision, txt_origen_remision, txt_destino_remision, txt_description1, txt_descripcion11,
                txt_descripcion2.getText().equals("") ? "" : txt_descripcion2, txt_descripcion22.getText().equals("") ? "" : txt_descripcion22,
                txt_descripcion3.getText().equals("") ? "" : txt_descripcion3, txt_descripcion33.getText().equals("") ? "" : txt_descripcion33,
                txt_descripcion4.getText().equals("") ? "" : txt_descripcion4, txt_descripcion44.getText().equals("") ? "" : txt_descripcion44,
                txt_descripcion5.getText().equals("") ? "" : txt_descripcion5, txt_descripcion55.getText().equals("") ? "" : txt_descripcion55,
                txt_total_libras};

        String[] mensaje = db.insert("insertar_remision_proceso", campos);
        if (mensaje[1].equals("1")) {
            mensaje("Confirmaci\u00f3n", mensaje[0]
                    , stackpane);
            btn_mensaje.setOnAction(event -> {
                dialogo.close();
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
                try {
                    SidePanelController.datos_tabla_remisones();
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }
            });
        }else{
        mensaje("Error", mensaje[0]
                ,stackpane);
        btn_mensaje.setOnAction(event -> {
            dialogo.close();
            try {
                SidePanelController.datos_tabla_entradas_salidas();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }

        });
    }
    }




    private void Calcular_Remision(){

        double total_remision = 0.00;
        double cant_LBS1 = 0.00;
        double cant_LBS2 = 0.00;
        double cant_LBS3 = 0.00;
        double cant_LBS4 = 0.00;
        double cant_LBS5 = 0.00;

        cant_LBS1 = Double.parseDouble(txt_descripcion11.getText());
        cant_LBS2 = Double.parseDouble(txt_descripcion22.getText());
        cant_LBS3 = Double.parseDouble(txt_descripcion33.getText());
        cant_LBS4 = Double.parseDouble(txt_descripcion44.getText());
        cant_LBS5 = Double.parseDouble(txt_descripcion55.getText());

                total_remision = ((cant_LBS1) + (cant_LBS2) + (cant_LBS3) + (cant_LBS4) + (cant_LBS5));
                String total = String.valueOf(total_remision);
                txt_total_libras.setText(total);

    }


    public void Cant_Lbs1(KeyEvent keyEvent) {

            Calcular_Remision();
    }

    public void Cant_Lbs2(KeyEvent keyEvent) {

            Calcular_Remision();


    }

    public void Cant_Lbs3(KeyEvent keyEvent) {
        Calcular_Remision();
    }

    public void Cant_Lbs4(KeyEvent keyEvent) {
        Calcular_Remision();
    }

    public void Cant_Lbs5(KeyEvent keyEvent) {
        Calcular_Remision();
    }

    public void Borra_Lbs1(MouseEvent mouseEvent) {
        txt_descripcion11.setText("");
    }

    public void Borra_Lbs2(MouseEvent mouseEvent) {
        txt_descripcion22.setText("");
    }

    public void Borra_Lbs3(MouseEvent mouseEvent) {
        txt_descripcion33.setText("");
    }

    public void Borra_Lbs4(MouseEvent mouseEvent) {
        txt_descripcion44.setText("");
    }

    public void Borra_Lbs5(MouseEvent mouseEvent) {
        txt_descripcion55.setText("");
    }

    public void Abandona_lbs1(MouseEvent mouseEvent) {
        if(txt_descripcion11.getText().equals("")){
            txt_descripcion11.setText("0.00");

        }
    }

    public void Abandona_lbs2(MouseEvent mouseEvent) {
        if (txt_descripcion22.getText().equals("")){
            txt_descripcion22.setText("0.00");
        }
    }

    public void Abandona_lbs3(MouseEvent mouseEvent) {
        if (txt_descripcion33.getText().equals("")){
            txt_descripcion33.setText("0.00");
        }
    }

    public void Abandona_lbs4(MouseEvent mouseEvent) {
        if (txt_descripcion44.getText().equals("")){
            txt_descripcion44.setText("0.00");
        }
    }

    public void Abandona_lbs5(MouseEvent mouseEvent) {
        if (txt_descripcion55.getText().equals("")){
            txt_descripcion55.setText("0.00");
        }
    }
}
