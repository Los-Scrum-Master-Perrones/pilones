package clases;

import clases.Objetos_POJO.Clase_tabacos;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class tabla_clase_tabaco extends Aplicacion_principal implements Initializable {

    public JFXTreeTableView jt_clase_tabaco_pilon;

    @Override
    public void start(Stage primaryStage) throws Exception{
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/resources/tabla_clase_tabaco.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Pilones");
        stage.show();



    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        JFXTreeTableColumn<Clase_tabacos,String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_tabacos,String> _2 = new JFXTreeTableColumn<>("Clase Tabaco");


        _1.setPrefWidth(50);
        _2.setPrefWidth(480);

        jt_clase_tabaco_pilon.getColumns().addAll(_1,_2);

        _1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_tabacos,String>("id_tabaco")
        );

        _2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_tabacos,String>("nombre_tbc")
        );
    }
}
