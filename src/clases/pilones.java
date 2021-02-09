package clases;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class pilones extends Aplicacion_principal implements Initializable {

    public StackPane stackpane;
    public Label lbl_idpilones;
    public Label lbl_id_pilones;
    public Label lbl_numero_pilon;
    public TextField txt_numero_pilon;
    public Label lbl_idtabaco;
    public Label lbl_id_tabaco;
    public Button btn_guardar_pilones;
    public Button btn_actualizar_pilones;


    @Override
    public void start(Stage primaryStage) throws Exception{
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/resources/pilones.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setHeight(250);
        stage.setWidth(450);
        stage.setTitle("Pilones");
        stage.show();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
