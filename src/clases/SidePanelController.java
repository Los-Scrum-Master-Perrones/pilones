package clases;

import clases.DBUtilities.ActualizarTablas;
import clases.DBUtilities.DBType;
import clases.DBUtilities.DBUtilities;
import clases.Objetos_POJO.Clase_control_temperatura;
import clases.Objetos_POJO.Clase_pilones;
import clases.Objetos_POJO.Clase_pilones_nombre;
import clases.Objetos_POJO.Clase_remisiones;
import clases.Objetos_POJO.Clase_tabacos;
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
    public JFXButton boton_registro_pilones;
    public JFXButton boton_temperatura;
    public JFXButton boton_entradas_salidas;
    public JFXButton boton_grafico_temperatura;


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
                break;
            case "Registro Temperatura":
                datos_tabla_registro_temperatura();
                break;
            case "Gráfico de Temperatura":
                datos_grafico();
                break;
            case "Entradas y Salidas":
                datos_tabla_entradas_salidas();
                break;
            case "Remisiones":
                datos_tabla_remisones();
                break;
        }

        ventana_nueva.traer_menu_lateral().close();
        ventana_nueva.traer_transiscion().setRate( ventana_nueva.traer_transiscion().getRate() * -1);
        ventana_nueva.traer_transiscion().play();

    }

    private void datos_tabla_remisones() throws SQLException, ClassNotFoundException {

        ventana_nueva.traer_jt_clase_tabaco().setVisible(false);
        ventana_nueva.traer_jt_pilones().setVisible(false);
        ventana_nueva.traer_jt_remisiones().setVisible(true);

        //TODO Tabaco Query
        PreparedStatement consulta = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                "SELECT * FROM remision_proceso");
        ResultSet resultSe = consulta.executeQuery();

        ObservableList<Clase_remisiones> data_remisiones = FXCollections.observableArrayList();

        while (resultSe.next()){
            String[] desStrings = new String[5];
            String[] canStrings = new String[5];

            Clase_remisiones remision = new Clase_remisiones(resultSe.getString(1),
                    resultSe.getString(2),
                    resultSe.getString(3),
                    resultSe.getString(4),
                    resultSe.getString(5),
                    resultSe.getString(16));

            desStrings[0] =  resultSe.getString(6);
            canStrings[0] =  resultSe.getString(7);

            if(resultSe.getString(8)!=null) {
                desStrings[1] = resultSe.getString(8);
                canStrings[1] = resultSe.getString(9);
            }else{
                desStrings[1] = "";
                canStrings[1] = "";
            }

            if(resultSe.getString(10)!=null) {
                desStrings[2] =  resultSe.getString(10);
                canStrings[2] =  resultSe.getString(11);
            }else{
                desStrings[2] = "";
                canStrings[2] = "";
            }

            if(resultSe.getString(12)!=null) {
                desStrings[3] =  resultSe.getString(12);
                canStrings[3] =  resultSe.getString(13);
            }else{
                desStrings[3] = "";
                canStrings[3] = "";
            }

            if(resultSe.getString(14)!=null) {
                desStrings[4] =  resultSe.getString(14);
                canStrings[4] =  resultSe.getString(15);
            }else{
                desStrings[4] = "";
                canStrings[4] = "";
            }

            System.out.println(Arrays.toString(desStrings)
        +" "+Arrays.toString(canStrings));

            remision.setTabacos_descrip_remision(desStrings);
            remision.setTotal_descrip_remision(canStrings);

            remision.descripcion();

            data_remisiones.add(remision);
        }
        TreeItem<Clase_remisiones> root = new RecursiveTreeItem<>(data_remisiones, RecursiveTreeObject::getChildren);

        ventana_nueva.traer_jt_remisiones().setRoot(root);
        ventana_nueva.traer_jt_remisiones().setShowRoot(false);

    }

    private void datos_tabla_entradas_salidas() {

    }

    private void datos_grafico() {
    }

    static void datos_tabla_registro_temperatura() throws Exception, ClassNotFoundException {

        ventana_nueva.traer_jt_control_temp().setVisible(true);
        ventana_nueva.traer_jt_pilon_control_temp().setVisible(true);
        ventana_nueva.traer_jt_pilones().setVisible(false);
        ventana_nueva.traer_jt_clase_tabaco().setVisible(false);

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


    }

    public static void datos_tabla_registro() throws SQLException, ClassNotFoundException {

        ventana_nueva.traer_jt_clase_tabaco().setVisible(true);
        ventana_nueva.traer_jt_pilones().setVisible(true);
        ventana_nueva.traer_jt_pilon_control_temp().setVisible(false);
        ventana_nueva.traer_jt_control_temp().setVisible(false);



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
