package clases;

import clases.DBUtilities.DBType;
import clases.DBUtilities.DBUtilities;
import clases.Objetos_POJO.Clase_pilones;
import clases.Objetos_POJO.Clase_pilones_nombre;
import clases.Objetos_POJO.Clase_tabacos;
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
import javafx.scene.control.Button;
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

public class tabla_registros_pilones extends Aplicacion_principal implements Initializable {

    public JFXTreeTableView<Clase_pilones_nombre> jt_registro_pilones;
    public Button btn_guardar_registro_pilones;
    public Button btn_actualizar_registro_pilones;
    public TextField txt_buscar_registro_pilones;

    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/resources/tabla_registros_pilones.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Registros de Pilones");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // para crear la tabla
        JFXTreeTableColumn<Clase_pilones_nombre,String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_pilones_nombre,String> _2 = new JFXTreeTableColumn<>("Número de Pilón");


        _1.setPrefWidth(50);
        _2.setPrefWidth(480);

        jt_registro_pilones.getColumns().addAll(_1,_2);

        _1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_pilones_nombre,String>("id_pilon")
        );

        _2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_pilones_nombre,String>("nombre_pilon")
        );


        //se inicializa el metodo Cargar
        try {
            Cargar_tabla_pilon();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Cargar los datos de la tabla
    public void Cargar_tabla_pilon() throws SQLException, ClassNotFoundException {
        PreparedStatement consulta_tabaco = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                "SELECT * FROM pilones");
        ResultSet resultSet_pilones = consulta_tabaco.executeQuery();

        ObservableList<Clase_pilones_nombre> data_pilones = FXCollections.observableArrayList();
        while (resultSet_pilones.next()){
            data_pilones.add(new Clase_pilones_nombre(resultSet_pilones.getString(1),
                    resultSet_pilones.getString(2)
            ));
        }
        TreeItem<Clase_pilones_nombre> root = new RecursiveTreeItem<>(data_pilones, RecursiveTreeObject::getChildren);

        jt_registro_pilones.setRoot(root);
        jt_registro_pilones.setShowRoot(false);

    }

    }

