import DBUtilities.DBType;
import DBUtilities.DBUtilities;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
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
import java.util.ResourceBundle;

public class nombre_pilon extends Aplicacion_principal implements Initializable {

    @FXML
    public TextField txt_nombre_pilon;
    @FXML
    public Button btn_guardar_pilon;
    @FXML
    public Button btn_actualizar_pilon;
    @FXML
    public StackPane stackpane;
    @FXML
    public Label lbl_id_pilon_mostra;
    @FXML
    public CheckMenuItem chck_menu_no_cerrar;

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/nombre_pilon.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Registro Pilon");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardar(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Object[] campos = {txt_nombre_pilon};

        String[] datos = new String[campos.length];
        int contador = 0;

        for (Object o: campos){
            if (o instanceof JFXTextField){
                datos[contador] = ((TextField)o).getText();
            }//else if(o instanceof Integer){
            // datos[contador]= String.valueOf(((int)o));
            //}
            contador++;
        }

        PreparedStatement consulta = DBUtilities.getConnection(DBType.MARIADB).
                prepareStatement("call insertar_pilones(?)");

        for(int i= 0;i<datos.length;i++){
            consulta.setString(i+1,datos[i]);
        }

        ResultSet respuesta = consulta.executeQuery();

        String mensaje[]= new String[2];
        while (respuesta.next()){
            mensaje[0]= respuesta.getString(1);
            mensaje[1]= respuesta.getString(2);
        }

        btn_mensaje.setOnAction(event -> {
            dialogo.close();
            try {
                SidePanelController.datos_tabla_registro();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            if (chck_menu_no_cerrar.isSelected()){

            }else {
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });
        txt_nombre_pilon.setText("");

        mensaje("Mensaje",mensaje[0],stackpane );
    }

    public void actualizar(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Object[] campos = {lbl_id_pilon_mostra,txt_nombre_pilon};

        String[] datos = new String[campos.length];
        int contador = 0;

        for (Object o: campos){
            if (o instanceof JFXTextField){
                datos[contador] = ((TextField)o).getText();
            }else if(o instanceof Label){
               datos[contador]= ((Label)o).getText();
            }
            contador++;
        }

        PreparedStatement consulta = DBUtilities.getConnection(DBType.MARIADB).
                prepareStatement("call actualizar_pilon(?,?)");

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
                try {
                    SidePanelController.datos_tabla_registro();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

            }
        });
        mensaje("Mensaje",mensaje[0],stackpane );
    }
}
