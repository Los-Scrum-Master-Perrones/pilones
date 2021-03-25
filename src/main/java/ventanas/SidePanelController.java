package ventanas;

import DBUtilitie.ActualizarTablas;
import DBUtilitie.DBType;
import DBUtilitie.DBUtilities;
import Objetos_POJO.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SidePanelController extends Aplicacion_principal implements Initializable {


    private static ActualizarTablas ventana_nueva;

    public JFXButton boton_clase_tabaco;
    public JFXButton boton_temperatura;
    public JFXButton boton_entradas_salidas;
    public JFXButton boton_entrada_pilon;
    public JFXButton boton_control_pilones;
    public JFXButton boton_remision;
    public JFXButton boton_otro;
    public static DBUtilities db = new DBUtilities(DBType.MARIADB);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void setActualizaMain(pantalla_principal actualizaMain){
        ventana_nueva = actualizaMain;
    }

    public void abrir_ventanas(ActionEvent actionEvent) throws Exception {

        switch (((JFXButton)actionEvent.getSource()).getText()){
            case "Registro de tabaco y pilones":
                datos_tabla_registro();
                //System.out.println("1");
                break;
            case "Entrada a pil\u00f3n":
                datos_tabla_entradas_pilon();
                //System.out.println("1");
                break;
            case "Proceso de Pil\u00f3n":
                datos_tabla_entradas_salidas();
                //System.out.println("1");
                break;
            case "Control de pilones":
                datos_tabla_control_pilones();
                //System.out.println("1");
                break;
            case "Registro Temperatura":
                datos_tabla_registro_temperatura();
                //System.out.println("1");
                break;
            case "Remisiones":
                datos_tabla_remisones();
                //System.out.println("1");
                break;
            case "Otro":
                datos_grafico();
                //System.out.println("1");
                break;
        }

        ventana_nueva.traer_menu_lateral().close();
        ventana_nueva.traer_transiscion().setRate( ventana_nueva.traer_transiscion().getRate() * -1);
        ventana_nueva.traer_transiscion().play();
        ventana_nueva.traer_menu_lateral().setVisible(false);


    }

    public static void datos_tabla_control_pilones() throws SQLException, ClassNotFoundException {

        ventana_nueva.traer_jt_control_temp().setVisible(false);
        ventana_nueva.traer_jt_pilon_control_temp().setVisible(false);
        ventana_nueva.traer_jt_pilones().setVisible(false);
        ventana_nueva.traer_jt_clase_tabaco().setVisible(false);
        ventana_nueva.traer_jt_remisiones().setVisible(false);
        ventana_nueva.traer_jt_en_sa_proceso_pilon().setVisible(false);
        ventana_nueva.traer_jt_en_sa_pilon().setVisible(false);
        ventana_nueva.traer_jt_entra_pilones().setVisible(false);
        ventana_nueva.traer_jt_control_pilones().setVisible(true);

        db.datos_tabla_control_pilones(ventana_nueva.traer_jt_control_pilones(),
                "SELECT * FROM control_pilones",new Clase_control_pilones(),new String[]{});
    }

    public static void datos_tabla_entradas_pilon() throws SQLException, ClassNotFoundException {


        ventana_nueva.traer_jt_control_temp().setVisible(false);
        ventana_nueva.traer_jt_pilon_control_temp().setVisible(false);
        ventana_nueva.traer_jt_pilones().setVisible(false);
        ventana_nueva.traer_jt_clase_tabaco().setVisible(false);
        ventana_nueva.traer_jt_remisiones().setVisible(false);
        ventana_nueva.traer_jt_en_sa_proceso_pilon().setVisible(false);
        ventana_nueva.traer_jt_en_sa_pilon().setVisible(false);
        ventana_nueva.traer_jt_entra_pilones().setVisible(true);
        ventana_nueva.traer_jt_control_pilones().setVisible(false);



        //TODO Entradas de pilones Query
        PreparedStatement consulta_entra_pilones = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                "SELECT * FROM entrada_pilones");
        ResultSet resultSet_entra_pilones = consulta_entra_pilones.executeQuery();

        ObservableList<Clase_entradas_pilones> data_entra_pilones = FXCollections.observableArrayList();
        while (resultSet_entra_pilones.next()){
            data_entra_pilones.add(new Clase_entradas_pilones(resultSet_entra_pilones.getString(1),
                    resultSet_entra_pilones.getString(2),resultSet_entra_pilones.getString(3),
                    resultSet_entra_pilones.getString(4),resultSet_entra_pilones.getString(5),
                    resultSet_entra_pilones.getString(6),resultSet_entra_pilones.getString(7)
            ));
        }
        TreeItem<Clase_entradas_pilones> root3 = new RecursiveTreeItem<>(data_entra_pilones, RecursiveTreeObject::getChildren);

        ventana_nueva.traer_jt_entra_pilones().setRoot(root3);
        ventana_nueva.traer_jt_entra_pilones().setShowRoot(false);
    }

    

    public static void datos_tabla_remisones() throws SQLException, ClassNotFoundException {

        ventana_nueva.traer_jt_control_temp().setVisible(false);
        ventana_nueva.traer_jt_pilon_control_temp().setVisible(false);
        ventana_nueva.traer_jt_pilones().setVisible(false);
        ventana_nueva.traer_jt_clase_tabaco().setVisible(false);
        ventana_nueva.traer_jt_remisiones().setVisible(true);
        ventana_nueva.traer_jt_en_sa_proceso_pilon().setVisible(false);
        ventana_nueva.traer_jt_en_sa_pilon().setVisible(false);
        ventana_nueva.traer_jt_entra_pilones().setVisible(false);
        ventana_nueva.traer_jt_control_pilones().setVisible(false);


        db.datos_tabla_control_pilones(ventana_nueva.traer_jt_remisiones(),
                "SELECT * FROM remision_proceso",new Clase_remisiones(),new String[]{});


    }

    public static void datos_tabla_entradas_salidas() throws SQLException, ClassNotFoundException {

        ventana_nueva.traer_jt_control_temp().setVisible(false);
        ventana_nueva.traer_jt_pilon_control_temp().setVisible(false);
        ventana_nueva.traer_jt_pilones().setVisible(false);
        ventana_nueva.traer_jt_clase_tabaco().setVisible(false);
        ventana_nueva.traer_jt_remisiones().setVisible(false);
        ventana_nueva.traer_jt_en_sa_proceso_pilon().setVisible(true);
        ventana_nueva.traer_jt_en_sa_pilon().setVisible(true);
        ventana_nueva.traer_jt_entra_pilones().setVisible(false);
        ventana_nueva.traer_jt_control_pilones().setVisible(false);

        //TODO Tabla Proceso Query
        PreparedStatement consulta_en_sa_proceso = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                "SELECT * FROM tabla_procesos");
        ResultSet resultSet_en_sa_proceso = consulta_en_sa_proceso.executeQuery();

        ObservableList<Clase_en_sa_proceso_pilon> data_proceso = FXCollections.observableArrayList();
        while (resultSet_en_sa_proceso.next()){
            data_proceso.add(new Clase_en_sa_proceso_pilon(resultSet_en_sa_proceso.getString(1),
                    resultSet_en_sa_proceso.getString(2),resultSet_en_sa_proceso.getString(3),
                    resultSet_en_sa_proceso.getString(4),resultSet_en_sa_proceso.getString(5),
                    resultSet_en_sa_proceso.getString(6),resultSet_en_sa_proceso.getString(7),
                    resultSet_en_sa_proceso.getString(8),resultSet_en_sa_proceso.getString(9)
            ));
        }
        TreeItem<Clase_en_sa_proceso_pilon> proceso = new RecursiveTreeItem<>(data_proceso, RecursiveTreeObject::getChildren);

        ventana_nueva.traer_jt_en_sa_proceso_pilon().setRoot(proceso);
        ventana_nueva.traer_jt_en_sa_proceso_pilon().setShowRoot(false);


        //TODO Tabla Pilon Query
        PreparedStatement consulta_en_sa_pilon = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                "SELECT * FROM tabla_pilon");
        ResultSet resultSet_en_sa_pilon = consulta_en_sa_pilon.executeQuery();

        ObservableList<Clase_en_sa_proceso_pilon> data_pilon = FXCollections.observableArrayList();
        while (resultSet_en_sa_pilon.next()){
            data_pilon.add(new Clase_en_sa_proceso_pilon(resultSet_en_sa_pilon.getString(1),
                    resultSet_en_sa_pilon.getString(2),resultSet_en_sa_pilon.getString(3),
                    resultSet_en_sa_pilon.getString(4),resultSet_en_sa_pilon.getString(5),
                    resultSet_en_sa_pilon.getString(6),resultSet_en_sa_pilon.getString(7),
                    resultSet_en_sa_pilon.getString(8),resultSet_en_sa_pilon.getString(9)
            ));
        }
        TreeItem<Clase_en_sa_proceso_pilon> pilon = new RecursiveTreeItem<>(data_pilon, RecursiveTreeObject::getChildren);

        ventana_nueva.traer_jt_en_sa_pilon().setRoot(pilon);
        ventana_nueva.traer_jt_en_sa_pilon().setShowRoot(false);

    }

    private void datos_grafico() {

    }


    public static void datos_tabla_registro_temperatura() throws Exception{

        ventana_nueva.traer_jt_control_temp().setVisible(true);
        ventana_nueva.traer_jt_pilon_control_temp().setVisible(true);
        ventana_nueva.traer_jt_pilones().setVisible(false);
        ventana_nueva.traer_jt_clase_tabaco().setVisible(false);
        ventana_nueva.traer_jt_remisiones().setVisible(false);
        ventana_nueva.traer_jt_en_sa_proceso_pilon().setVisible(false);
        ventana_nueva.traer_jt_en_sa_pilon().setVisible(false);
        ventana_nueva.traer_jt_entra_pilones().setVisible(false);
        ventana_nueva.traer_jt_control_pilones().setVisible(false);



        //TODO Temperatura Query
        PreparedStatement consulta_control_temp = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                "SELECT * FROM control_temperatura");
        ResultSet resultSet_control_temp = consulta_control_temp.executeQuery();

        ObservableList<Clase_control_temperatura> data_temperatura = FXCollections.observableArrayList();
        while (resultSet_control_temp.next()){
            data_temperatura.add(new Clase_control_temperatura(resultSet_control_temp.getString(1),
                    resultSet_control_temp.getString(2),resultSet_control_temp.getString(3),
                    resultSet_control_temp.getString(4),resultSet_control_temp.getString(5)
            ));
        }
        TreeItem<Clase_control_temperatura> root3 = new RecursiveTreeItem<>(data_temperatura, RecursiveTreeObject::getChildren);

        ventana_nueva.traer_jt_control_temp().setRoot(root3);
        ventana_nueva.traer_jt_control_temp().setShowRoot(false);

        //TODO Pilones Temp Query
        PreparedStatement consulta_pilones = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                "SELECT * FROM pilones");
        ResultSet resultSet_pilones1 = consulta_pilones.executeQuery();
        ObservableList<Clase_pilones_nombre> data_pilones1 = FXCollections.observableArrayList();

        while (resultSet_pilones1.next()){
            data_pilones1.add(new Clase_pilones_nombre(resultSet_pilones1.getString(1),
                    resultSet_pilones1.getString(2)
            ));
        }

        TreeItem<Clase_pilones_nombre> root_4 = new RecursiveTreeItem<>(data_pilones1, RecursiveTreeObject::getChildren);

        ventana_nueva.traer_jt_pilon_control_temp().setRoot(root_4);
        ventana_nueva.traer_jt_pilon_control_temp().setShowRoot(false);
        //ventana_nueva.traer_jt_clase_tabaco().setVisible(false);
        //ventana_nueva.traer_jt_pilones().setVisible(false);

        ventana_nueva.lista_temperatura().getItems().clear();

        PreparedStatement consulta_temp_actual = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                "CALL revision_pendiente_pilon()");
        ResultSet resultSet_temp_actual= consulta_temp_actual.executeQuery();

        PreparedStatement consulta_temp_cnat = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                "CALL revision_pendiente_pilon()");
        ResultSet tamanio = consulta_temp_cnat.executeQuery();

        int[] id_pilon = new int[data_pilones1.size()];

        for ( int ii = 0;resultSet_temp_actual.next();ii++){
            id_pilon[ii] = resultSet_temp_actual.getInt(2);
        }
        System.out.println(Arrays.toString(id_pilon));
        System.out.println(data_pilones1);
        for(Clase_pilones_nombre c:data_pilones1){
            int can = 0;
            for (int ii = 0;ii<id_pilon.length;ii++){
                if(id_pilon[ii] == Integer.parseInt(c.getNombre_pilon())){
                    can++;
                }
            }
            if (can == 0){
                ventana_nueva.lista_temperatura().getItems().add(c);
            }
        }



    }

    public static void datos_tabla_registro() throws SQLException, ClassNotFoundException {

        ventana_nueva.traer_jt_clase_tabaco().setVisible(true);
        ventana_nueva.traer_jt_pilones().setVisible(true);
        ventana_nueva.traer_jt_pilon_control_temp().setVisible(false);
        ventana_nueva.traer_jt_control_temp().setVisible(false);
        ventana_nueva.traer_jt_remisiones().setVisible(false);
        ventana_nueva.traer_jt_en_sa_proceso_pilon().setVisible(false);
        ventana_nueva.traer_jt_en_sa_pilon().setVisible(false);
        ventana_nueva.traer_jt_entra_pilones().setVisible(false);
        ventana_nueva.traer_jt_control_pilones().setVisible(false);


        //TODO Tabaco Query
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

        ventana_nueva.traer_jt_clase_tabaco().setRoot(root);
        ventana_nueva.traer_jt_clase_tabaco().setShowRoot(false);


        //TODO Pilones Query
        PreparedStatement consulta_pilones = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                "SELECT * FROM pilones");
        ResultSet resultSet_pilones = consulta_pilones.executeQuery();
        ObservableList<Clase_pilones_nombre> data_pilones = FXCollections.observableArrayList();

        while (resultSet_pilones.next()){
            data_pilones.add(new Clase_pilones_nombre(resultSet_pilones.getString(1),
                    resultSet_pilones.getString(2)
            ));
        }

        TreeItem<Clase_pilones_nombre> root_2 = new RecursiveTreeItem<>(data_pilones, RecursiveTreeObject::getChildren);

        ventana_nueva.traer_jt_pilones().setRoot(root_2);
        ventana_nueva.traer_jt_pilones().setShowRoot(false);


    }

}
