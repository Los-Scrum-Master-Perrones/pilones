package clases;

import clases.DBUtilities.DBType;
import clases.DBUtilities.DBUtilities;
import clases.Objetos_POJO.Clase_tabacos;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class tabla_clase_tabaco extends Aplicacion_principal implements Initializable {

    public JFXTreeTableView<Clase_tabacos> jt_clase_tabaco_pilon;
    public JFXButton btn_guardar_claseTab_pilones;
    public JFXButton btn_actualizar_claseTab_pilones;
    public TextField txt_buscar_clase_tabaco;

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
        stage.setTitle("Clase de Tabaco");
        stage.show();



    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // para crear la tabla
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


        //se inicializa el metodo Cargar
        try {
            Cargar_tabla_tabaco_pilon();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

     // Cargar los datos de la tabla
    public void Cargar_tabla_tabaco_pilon() throws SQLException, ClassNotFoundException {
        PreparedStatement consulta_tabaco = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                "SELECT * FROM clase_tabaco");
        ResultSet resultSet_tabaco = consulta_tabaco.executeQuery();

        ObservableList<Clase_tabacos> data_tabaco = FXCollections.observableArrayList();
        while (resultSet_tabaco.next()){
            data_tabaco.add(new Clase_tabacos(resultSet_tabaco.getString(1),
                    resultSet_tabaco.getString(2)
            ));
        }
        TreeItem<Clase_tabacos> root = new RecursiveTreeItem<>(data_tabaco, RecursiveTreeObject::getChildren);

        jt_clase_tabaco_pilon.setRoot(root);
        jt_clase_tabaco_pilon.setShowRoot(false);

    }

}
