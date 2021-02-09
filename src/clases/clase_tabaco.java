package clases;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class clase_tabaco extends Aplicacion_principal{

    public Label lbl_nombre_tabaco;
    public TextField txt_nombre_tabaco;
    public Button btn_guardar_clase_tabaco;
    public Button btn_actualizar_clase_tabaco;
    public Label lbl_id_clase_tabaco;
    public Label lbl_id_tabaco;

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/resources/clase_tabaco.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setHeight(180);
        stage.setWidth(332);
        stage.setTitle("");
        stage.show();




    }
}
