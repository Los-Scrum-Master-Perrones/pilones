package ventanas;

import DBUtilitie.DBType;
import DBUtilitie.DBUtilities;
import DBUtilitie.RegistroCombobox;
import Objetos_POJO.Clase_pilones_nombre;
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
import javafx.scene.control.Button;
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

public class tabla_registros_pilones extends Aplicacion_principal implements Initializable {

    public JFXTreeTableView<Clase_pilones_nombre> jt_registro_pilones;
    public Button btn_guardar_registro_pilones;
    public Button btn_actualizar_registro_pilones;
    public TextField txt_buscar_registro_pilones;
    public JFXButton btn_guardar_registro_entrada_pilones;
    public JFXButton btn_actualizar_registro_entrada_pilones;
    public JFXButton btn_guardar_pilon_control_pilones;
    public JFXButton btn_actualizar_pilon_control_pilones;
    RegistroCombobox vista;

    public void registrocontroller(RegistroCombobox vista){
        this.vista= vista;
    }

    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/tabla_registros_pilones.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Registros de Pilones");
        stage.show();
    }
    public void Ocultar_botones(){
        if (vista.cargar_datos_pilon().getText().length()==0){
            btn_guardar_registro_pilones.setVisible(true);
            btn_actualizar_registro_pilones.setVisible(false);
        }else{
            btn_guardar_registro_pilones.setVisible(false);
            btn_actualizar_registro_pilones.setVisible(true);
        }
    }
    public void Ocultar_botones1(){
        if (vista.cargar_datos_entrada_pilon().getText().length()==0){
            btn_guardar_registro_entrada_pilones.setVisible(true);
            btn_actualizar_registro_entrada_pilones.setVisible(false);
        }else{
            btn_guardar_registro_entrada_pilones.setVisible(false);
            btn_actualizar_registro_entrada_pilones.setVisible(true);
        }
    }
    public void Ocultar_botones2(){
        if (vista.cargar_datos_pilones_control_pilones().getText().length()==0){
            btn_guardar_pilon_control_pilones.setVisible(true);
            btn_actualizar_pilon_control_pilones.setVisible(false);
        }else{
            btn_guardar_pilon_control_pilones.setVisible(false);
            btn_actualizar_pilon_control_pilones.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    // Ocultar botones



        // para crear la tabla
        JFXTreeTableColumn<Clase_pilones_nombre,String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_pilones_nombre,String> _2 = new JFXTreeTableColumn<>("N\u00famero de Pil\u00f3n");


        _1.setPrefWidth(50);
        _2.setPrefWidth(105);

        jt_registro_pilones.getColumns().addAll(_1,_2);

        _1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_pilones_nombre,String>("id_pilon")
        );

        _2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_pilones_nombre,String>("ventanas.nombre_pilon")
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
        PreparedStatement consulta_pilones = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                "SELECT * FROM pilones");
        ResultSet resultSet_pilones = consulta_pilones.executeQuery();

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

    public void Agregar(ActionEvent actionEvent) {

        vista.cargar_datos_pilon().setText(jt_registro_pilones.getSelectionModel().
                getSelectedItem().getValue().getNombre_pilon());
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();


    }

    public void Actualizar(ActionEvent actionEvent) {
        vista.cargar_datos_pilon().setText(jt_registro_pilones.getSelectionModel().
                getSelectedItem().getValue().getNombre_pilon());
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


    public void Agregar_entrada(ActionEvent actionEvent) {
        vista.cargar_datos_entrada_pilon().setText(jt_registro_pilones.getSelectionModel().
                getSelectedItem().getValue().getNombre_pilon());
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void Actualizar_entrada(ActionEvent actionEvent) {
        vista.cargar_datos_entrada_pilon().setText(jt_registro_pilones.getSelectionModel().
                getSelectedItem().getValue().getNombre_pilon());
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void Agregar_control_pilones(ActionEvent actionEvent) {
       /*int selection = jt_registro_pilones.getSelectionModel().getSelectedIndex();
        if (vista.cargar_datos_pilones_control_pilones().getSelectionModel().isEmpty()){
            vista.cargar_datos_pilones_control_pilones().getItems().add(jt_registro_pilones.getTreeItem(selection).getValue());
            vista.cargar_datos_pilones_control_pilones().getSelectionModel().select(0);

            // vista.cargar_datos_tabaco().getItems().add(new Clase_tabacos("1","habano"));
            //vista.cargar_datos_tabaco().getSelectionModel().select(0);
        }
        else {
            vista.cargar_datos_pilones_control_pilones().getItems().add(jt_registro_pilones.getTreeItem(selection).getValue());
        }*/
        vista.cargar_datos_pilones_control_pilones().setText(jt_registro_pilones.getSelectionModel().
                getSelectedItem().getValue().getNombre_pilon());
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void Actualizar_control_pilones(ActionEvent actionEvent) {
        /*int selection = jt_registro_pilones.getSelectionModel().getSelectedIndex();
        int seleccion_2 = vista.cargar_datos_pilones_control_pilones().getSelectionModel().getSelectedIndex();

        vista.cargar_datos_pilones_control_pilones().getItems().remove(seleccion_2);
        vista.cargar_datos_pilones_control_pilones().getItems().add(seleccion_2, jt_registro_pilones.getTreeItem(selection).getValue());
        vista.cargar_datos_pilones_control_pilones().getSelectionModel().select(seleccion_2);*/
        vista.cargar_datos_pilones_control_pilones().setText(jt_registro_pilones.getSelectionModel().
                getSelectedItem().getValue().getNombre_pilon());
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void buscarPilon(String valor) throws SQLException, ClassNotFoundException {


        PreparedStatement consulta_pilones = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                "CALL Buscar_pilones(?)");
        consulta_pilones.setString(1, valor);
        ResultSet resultSet_pilones = consulta_pilones.executeQuery();

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

    public void buscar_pilon(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        buscarPilon(txt_buscar_registro_pilones.getText());
    }
}

