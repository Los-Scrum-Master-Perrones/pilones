import DBUtilitie.DBType;
import DBUtilitie.DBUtilities;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class clase_tabaco extends Aplicacion_principal implements Initializable {

    public Label lbl_nombre_tabaco;
    public JFXTextField txt_nombre_tabaco;
    public JFXButton btn_guardar_clase_tabaco;
    public JFXButton btn_actualizar_clase_tabaco;
    public Label lbl_id_clase_tabaco;
    public Label lbl_id_tabaco;
    public StackPane stackpane;
    public CheckMenuItem chck_menu_no_cerrar;
    public DBUtilities db = new DBUtilities(DBType.MARIADB);


    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/clase_tabaco.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Registro clase tabaco");
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardar() throws SQLException, ClassNotFoundException {
        boton_guardar();

        Object[] campos = {txt_nombre_tabaco};

        String[] mensaje = db.insert("insertar_tabaco",campos) ;

        if (mensaje[1].equals("1")){
            mensaje("Confirmaci\u00f3n", mensaje[0]
                    ,stackpane);
            DBUtilities.CargarId(lbl_id_tabaco,"SELECT * FROM clase_tabaco ORDER BY clase_tabaco.id_tabaco DESC ");
        }else{
            mensaje("Error", mensaje[1]
                    ,stackpane);
        }
        txt_nombre_tabaco.setText("");
    }

    private void boton_guardar() {
        btn_mensaje.setOnAction(event -> {
            dialogo.close();
            try {
                SidePanelController.datos_tabla_registro();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            if (chck_menu_no_cerrar.isSelected()) {
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });
    }

    public void actualizar() throws SQLException, ClassNotFoundException {
        btn_mensaje.setOnAction(event -> {
            dialogo.close();
            try {
                SidePanelController.datos_tabla_registro();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

        });

        Object[] campos = {lbl_id_tabaco,txt_nombre_tabaco};

        String[] mensaje = db.insert("actualizar_tabaco",campos) ;

        if (mensaje[1].equals("1")){
            mensaje("Confirmaci\u00f3n", mensaje[0]
                    ,stackpane);
        }else{
            mensaje("Error", mensaje[1]
                    ,stackpane);
        }

    }


}
