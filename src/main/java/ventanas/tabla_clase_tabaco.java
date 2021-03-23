package ventanas;

import DBUtilitie.ActualizarTablas;
import DBUtilitie.DBType;
import DBUtilitie.DBUtilities;


import DBUtilitie.RegistroCombobox;
import Objetos_POJO.Clase_tabacos;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class tabla_clase_tabaco extends Aplicacion_principal implements Initializable {

    private static ActualizarTablas ventana_nueva;

    public JFXTreeTableView<Clase_tabacos> jt_clase_tabaco_pilon;
    public JFXButton btn_guardar_claseTab_pilones;
    public JFXButton btn_actualizar_claseTab_pilones;
    public TextField txt_buscar_clase_tabaco;
    public JFXButton btn_guardar_claseTab_entradas_pilones;
    public JFXButton btn_actualizar_claseTab_entradas_pilones;
    public JFXButton btn_guardar_Tab_control_pilones;
    public JFXButton btn_actualizar_Tab_control_pilones;

    RegistroCombobox vista1;
    // RegistroCombobox vista2;

    public void registrocontroller(RegistroCombobox vista1) {
        this.vista1 = vista1;
    }
    //public void registrocontroller1(RegistroCombobox1 vista2){ this.vista2= vista2; }

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/tabla_clase_tabaco.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Clase de Tabaco");
        stage.show();

    }

    public void Ocultar_botones() {
        if (vista1.cargar_datos_tabaco().getText().length() == 0) {
            btn_guardar_claseTab_pilones.setVisible(true);
            btn_actualizar_claseTab_pilones.setVisible(false);
        } else {
            btn_guardar_claseTab_pilones.setVisible(false);
            btn_actualizar_claseTab_pilones.setVisible(true);
        }
    }

    public void Ocultar_botones1() {
        if (vista1.cargar_datos_entrada_tabaco().getText().length() == 0) {
            btn_guardar_claseTab_entradas_pilones.setVisible(true);
            btn_actualizar_claseTab_entradas_pilones.setVisible(false);
        } else {
            btn_guardar_claseTab_entradas_pilones.setVisible(false);
            btn_actualizar_claseTab_entradas_pilones.setVisible(true);
        }
    }

    public void Ocultar_botones2() {
        if (vista1.cargar_datos_tab_control_pilones().getText().length() == 0) {
            btn_guardar_Tab_control_pilones.setVisible(true);
            btn_actualizar_Tab_control_pilones.setVisible(false);
        } else {
            btn_guardar_Tab_control_pilones.setVisible(false);
            btn_actualizar_Tab_control_pilones.setVisible(true);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // para crear la tabla
        JFXTreeTableColumn<Clase_tabacos, String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_tabacos, String> _2 = new JFXTreeTableColumn<>("Clase Tabaco");


        _1.setPrefWidth(50);
        _2.setPrefWidth(480);

        jt_clase_tabaco_pilon.getColumns().addAll(_1, _2);

        _1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_tabacos, String>("id_tabaco")
        );

        _2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_tabacos, String>("nombre_tbc")
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
        while (resultSet_tabaco.next()) {
            data_tabaco.add(new Clase_tabacos(resultSet_tabaco.getString(1),
                    resultSet_tabaco.getString(2)
            ));
        }
        TreeItem<Clase_tabacos> root = new RecursiveTreeItem<>(data_tabaco, RecursiveTreeObject::getChildren);

        jt_clase_tabaco_pilon.setRoot(root);
        jt_clase_tabaco_pilon.setShowRoot(false);

    }

    public void Agregar_tabaco(ActionEvent actionEvent) {

        vista1.cargar_datos_tabaco().setText(jt_clase_tabaco_pilon.getSelectionModel().
                getSelectedItem().getValue().getNombre_tbc());

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    public void Actualizar_tabaco(ActionEvent actionEvent) {


        vista1.cargar_datos_tabaco().setText(jt_clase_tabaco_pilon.getSelectionModel().
                getSelectedItem().getValue().getNombre_tbc());
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void agregar_entrada_tabaco(ActionEvent actionEvent) {
        vista1.cargar_datos_entrada_tabaco().setText(jt_clase_tabaco_pilon.getSelectionModel().
                getSelectedItem().getValue().getNombre_tbc());
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    public void actualizar_entrada_tabaco(ActionEvent actionEvent) {
        vista1.cargar_datos_entrada_tabaco().setText(jt_clase_tabaco_pilon.getSelectionModel().
                getSelectedItem().getValue().getNombre_tbc());
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void agregar_tab_control_pilones(ActionEvent actionEvent) {
        vista1.cargar_datos_tab_control_pilones().setText(jt_clase_tabaco_pilon.getSelectionModel().
                getSelectedItem().getValue().getNombre_tbc());
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }
        public void actualizar_tab_control_pilones (ActionEvent actionEvent){
            vista1.cargar_datos_tab_control_pilones().setText(jt_clase_tabaco_pilon.getSelectionModel().
                    getSelectedItem().getValue().getNombre_tbc());
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }

    public void buscarTab(String valor) throws SQLException, ClassNotFoundException {


        PreparedStatement consulta_tabaco = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                " Call buscar_tabaco(?)");

        consulta_tabaco.setString(1,valor);
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


    public void buscar(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        buscarTab(txt_buscar_clase_tabaco.getText());
        //Cargar_tabla_tabaco_pilon();
    }
}
