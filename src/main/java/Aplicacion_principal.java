import DBUtilitie.DBType;
import DBUtilitie.DBUtilities;
import Objetos_POJO.*;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

public class Aplicacion_principal extends Application {


    JFXDialog dialogo = new JFXDialog();
    JFXButton btn_mensaje = new JFXButton("OK");
    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void carga_lista_de_faltaltes_de_revision(JFXListView lis_pilones_pendientes) {


    }

    public static int numeroDeDiasMes(int mes){

        int numeroDias=-1;

        switch(mes){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                numeroDias=31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                numeroDias=30;
                break;
            case 2:

                Date anioActual=new Date();
                if(esBisiesto(1900 + anioActual.getYear())){
                    numeroDias=29;
                }else{
                    numeroDias=28;
                }
                break;

        }

        return numeroDias;
    }

    public static boolean esBisiesto(int anio) {

        GregorianCalendar calendar = new GregorianCalendar();
        boolean esBisiesto = false;
        if (calendar.isLeapYear(anio)) {
            esBisiesto = true;
        }
        return esBisiesto;

    }


    public void tabla_remisiones(JFXTreeTableView<Clase_remisiones> jt_remisiones, JFXButton btn_editar_remision) {

        JFXTreeTableColumn<Clase_remisiones,String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_remisiones,String> _2 = new JFXTreeTableColumn<>("C\u00f3digo");
        JFXTreeTableColumn<Clase_remisiones,String> _4 = new JFXTreeTableColumn<>("Fecha");
        JFXTreeTableColumn<Clase_remisiones,String> _5 = new JFXTreeTableColumn<>("Destino");
        JFXTreeTableColumn<Clase_remisiones,String> _6 = new JFXTreeTableColumn<>("Origen");
        JFXTreeTableColumn<Clase_remisiones,String> _7 = new JFXTreeTableColumn<>("Descripci\u00f3n");
        JFXTreeTableColumn<Clase_remisiones,String> _8 = new JFXTreeTableColumn<>("Total (Lbs.)");

        _1.setPrefWidth(60);
        _2.setPrefWidth(80);
        _4.setPrefWidth(100);
        _5.setPrefWidth(200);
        _6.setPrefWidth(200);
        _7.setPrefWidth(431);
        _8.setPrefWidth(100);

        jt_remisiones.getColumns().addAll(_1,_2,_4,_5,_6,_7,_8);

        _1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_remisiones,String>("id_remision")
        );

        _2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_remisiones,String>("codigo_remision")
        );

        _4.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_remisiones,String>("fecha_remision")
        );
        _5.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_remisiones,String>("destino_remision")
        );

        _6.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_remisiones,String>("origen_remision")
        );
        _7.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_remisiones,String>("descripcion_remision")
        );

        _8.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_remisiones,String>("total_remision")
        );

        jt_remisiones.setOnMouseClicked(event -> btn_editar_remision.setVisible(true));
    }

    public void tabla_clase_tabaco(JFXTreeTableView jt_clase_tabaco, JFXButton btn_editar_pilon_tabla, JFXButton btn_editar_tabaco_tabla,
                                   TextField jtx_buscar_tab_princ) {

        JFXTreeTableColumn<Clase_tabacos,String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_tabacos,String> _2 = new JFXTreeTableColumn<>("Clase Tabaco");

        _1.setPrefWidth(100);
        _2.setPrefWidth(753);

        jt_clase_tabaco.getColumns().addAll(_1,_2);

        _1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_tabacos,String>("id_tabaco")
        );

        _2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_tabacos,String>("nombre_tbc")
        );

        jt_clase_tabaco.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                btn_editar_pilon_tabla.setVisible(false);
                btn_editar_tabaco_tabla.setVisible(true);
                jtx_buscar_tab_princ.setText("");


            }
        });
    }

    public void tabla_pilones(JFXTreeTableView jt_pilones,JFXButton btn_editar_pilon_tabla,
                              JFXButton btn_editar_tabaco_tabla,TextField jtx_buscar_pilon) {

        JFXTreeTableColumn<Clase_pilones_nombre, String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_pilones_nombre, String> _2 = new JFXTreeTableColumn<>("N\u00famero de Pil\u00f3n");


        _1.setPrefWidth(50);
        _2.setPrefWidth(229);

        jt_pilones.getColumns().addAll(_1, _2);

        _1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_pilones_nombre, String>("id_pilon")
        );

        _2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_pilones_nombre, String>("nombre_pilon")
        );


        jt_pilones.setOnMouseClicked(event -> {
            btn_editar_pilon_tabla.setVisible(true);
            btn_editar_tabaco_tabla.setVisible(false);
            jtx_buscar_pilon.setText("");


        });

    }


    public  void tabla_Control_temp(JFXTreeTableView<Clase_control_temperatura> jt_control_temp,JFXTreeTableView<Clase_pilones_nombre> jt_pilon_control_temp,
                                    JFXButton btn_nuevo_control_temp,  JFXButton btn_eliminar_control_temp,
                                    AnchorPane anchor_botones_meses, JFXButton btn_grafica_actual_temperatura){
        JFXTreeTableColumn<Clase_control_temperatura, String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_control_temperatura, String> _2 = new JFXTreeTableColumn<>("N\u00famero de Pil\u00f3n");
        JFXTreeTableColumn<Clase_control_temperatura, String> _3 = new JFXTreeTableColumn<>("Temperatura");
        JFXTreeTableColumn<Clase_control_temperatura, String> _4 = new JFXTreeTableColumn<>("Fecha de Revisi\u00f3n");
        JFXTreeTableColumn<Clase_control_temperatura, String> _5 = new JFXTreeTableColumn<>("Mantenimiento");


        _1.setPrefWidth(53);
        _2.setPrefWidth(150);
        _3.setPrefWidth(106);
        _4.setPrefWidth(150);
        _5.setPrefWidth(150);

        jt_control_temp.getColumns().addAll(_1, _2, _3, _4, _5);

        _1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_control_temperatura, String>("id_control_temp")
        );

        _2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_control_temperatura, String>("id_pilon_temp")
        );
        _3.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_control_temperatura, String>("temperatura")
        );
        _4.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_control_temperatura, String>("fecha_revision_temp")
        );
        _5.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_control_temperatura, String>("mantenimiento_temp")
        );


        ////// tabla pilones
        JFXTreeTableColumn<Clase_pilones_nombre, String> _1_1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_pilones_nombre, String> _2_2 = new JFXTreeTableColumn<>("N\u00famero de Pil\u00f3n");


        _1_1.setPrefWidth(50);
        _2_2.setPrefWidth(229);

        jt_pilon_control_temp.getColumns().addAll(_1_1, _2_2);

        _1_1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_pilones_nombre, String>("id_pilon")
        );

        _2_2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_pilones_nombre, String>("nombre_pilon")
        );


        jt_pilon_control_temp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_nuevo_control_temp.setVisible(true);
                btn_eliminar_control_temp.setVisible(false);
                anchor_botones_meses.setVisible(true);
                btn_grafica_actual_temperatura.setVisible(true);

                //TODO Temperatura Query
                PreparedStatement consulta_control_temp = null;
                try {
                    consulta_control_temp = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                            "SELECT * FROM control_temperatura where id_pilones=?");
                    consulta_control_temp.setString(1,jt_pilon_control_temp.getSelectionModel().getSelectedItem().getValue().getNombre_pilon());
                    ResultSet resultSet_control_temp = consulta_control_temp.executeQuery();

                    ObservableList<Clase_control_temperatura> data_temperatura = FXCollections.observableArrayList();
                    while (resultSet_control_temp.next()){
                        data_temperatura.add(new Clase_control_temperatura(resultSet_control_temp.getString(1),
                                resultSet_control_temp.getString(2),resultSet_control_temp.getString(3),
                                resultSet_control_temp.getString(4),resultSet_control_temp.getString(5)
                        ));
                    }
                    TreeItem<Clase_control_temperatura> root3 = new RecursiveTreeItem<>(data_temperatura, RecursiveTreeObject::getChildren);

                    jt_control_temp.setRoot(root3);
                    jt_control_temp.setShowRoot(false);
                } catch (SQLException throwables) {

                    throwables.printStackTrace();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });

        jt_control_temp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_nuevo_control_temp.setVisible(false);
                btn_eliminar_control_temp.setVisible(true);
                anchor_botones_meses.setVisible(false);
                btn_grafica_actual_temperatura.setVisible(false);
            }
        });

    }



    public void tabla_en_y_sa_proceso(JFXTreeTableView<Clase_en_sa_proceso_pilon> jt_proceso_entrada_pilon,
                                      JFXButton btn_editar_entrada_pilon, JFXButton btn_editar_salidas_pilon,
                                      TextField jfx_buscar_proceso_entrad_pilon){

        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _2 = new JFXTreeTableColumn<>("Fecha de Proceso ");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _3 = new JFXTreeTableColumn<>("N\u00Famero de Remisi\u00f3n");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _4 = new JFXTreeTableColumn<>("Entradas y Salidas");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _5 = new JFXTreeTableColumn<>("Nombre de Tabaco");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _7 = new JFXTreeTableColumn<>("Sub Total ");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _8 = new JFXTreeTableColumn<>("Total Libras");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _9 = new JFXTreeTableColumn<>("Total General");

        _1.setPrefWidth(40);
        _2.setPrefWidth(130);
        _3.setPrefWidth(160);
        _4.setPrefWidth(230);
        _5.setPrefWidth(330);
        _7.setPrefWidth(110);
        _8.setPrefWidth(110);
        _9.setPrefWidth(105);

        jt_proceso_entrada_pilon.getColumns().addAll(_1, _2, _3, _4, _5, _7, _8, _9);

        _1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("id_en_sa_proceso_pilon")
        );

        _2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("fecha_en_sa_proceso_pilon")
        );
        _3.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("remision_en_sa_proceso_pilon")
        );
        _4.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("en_sa_proceso_pilon")
        );
        _5.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("nombre_tab_en_sa_proceso_pilon")
        );

        _7.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("subtotal_en_sa_proceso_pilon")
        );
        _8.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("total_lbs_en_sa_proceso_pilon")
        );
        _9.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("total_remision_en_sa_proceso_pilon")
        );
        jt_proceso_entrada_pilon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_editar_entrada_pilon.setVisible(true);
                btn_editar_salidas_pilon.setVisible(false);
                jfx_buscar_proceso_entrad_pilon.setText("");
            }
        });

    }


    public void tabla_en_sa_pilon(JFXTreeTableView jt_proceso_salidas_pilon, JFXButton btn_editar_entrada_pilon,
                                   JFXButton btn_editar_salidas_pilon, TextField jtxt_buscar_salidas_pilon){
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _2 = new JFXTreeTableColumn<>("Fecha de Proceso ");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _3 = new JFXTreeTableColumn<>("N\u00Famero de Remisi\u00f3n");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _4 = new JFXTreeTableColumn<>("Entradas y Salidas");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _5 = new JFXTreeTableColumn<>("Nombre de Tabaco");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _7 = new JFXTreeTableColumn<>("Sub Total ");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _8 = new JFXTreeTableColumn<>("Total Libras");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _9 = new JFXTreeTableColumn<>("Total General");

        _1.setPrefWidth(40);
        _2.setPrefWidth(130);
        _3.setPrefWidth(160);
        _4.setPrefWidth(230);
        _5.setPrefWidth(330);
        _7.setPrefWidth(110);
        _8.setPrefWidth(110);
        _9.setPrefWidth(105);


        jt_proceso_salidas_pilon.getColumns().addAll(_1, _2, _3, _4, _5, _7, _8, _9);

        _1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("id_en_sa_proceso_pilon")
        );

        _2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("fecha_en_sa_proceso_pilon")
        );
        _3.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("remision_en_sa_proceso_pilon")
        );
        _4.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("en_sa_proceso_pilon")
        );
        _5.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("nombre_tab_en_sa_proceso_pilon")
        );

        _7.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("subtotal_en_sa_proceso_pilon")
        );
        _8.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("total_lbs_en_sa_proceso_pilon")
        );
        _9.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("total_remision_en_sa_proceso_pilon")
        );
        jt_proceso_salidas_pilon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               btn_editar_entrada_pilon.setVisible(false);
               btn_editar_salidas_pilon.setVisible(true);
                jtxt_buscar_salidas_pilon.setText("");
            }
        });
    }

    public void tabla_entradas_pilones(JFXTreeTableView jt_entradas_pilones, JFXButton btn_editar_entrada_pilones,
                                  TextField jtxt_buscar_entradas_pilon){
        JFXTreeTableColumn<Clase_entradas_pilones, String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_entradas_pilones, String> _2 = new JFXTreeTableColumn<>("Nombre de tabaco");
        JFXTreeTableColumn<Clase_entradas_pilones, String> _3 = new JFXTreeTableColumn<>("Variedad");
        JFXTreeTableColumn<Clase_entradas_pilones, String> _4 = new JFXTreeTableColumn<>("Finca");
        JFXTreeTableColumn<Clase_entradas_pilones, String> _5 = new JFXTreeTableColumn<>("Pil\u00f3n");
        JFXTreeTableColumn<Clase_entradas_pilones, String> _6 = new JFXTreeTableColumn<>("Fecha de entrada");
        JFXTreeTableColumn<Clase_entradas_pilones, String> _7 = new JFXTreeTableColumn<>("Tiempo de adelanto");
        JFXTreeTableColumn<Clase_entradas_pilones, String> _8 = new JFXTreeTableColumn<>("Fecha estimada de salida");
        JFXTreeTableColumn<Clase_entradas_pilones, String> _9 = new JFXTreeTableColumn<>("Cantidad en libras");

        _1.setPrefWidth(40);
        _2.setPrefWidth(300);
        _3.setPrefWidth(130);
        _4.setPrefWidth(90);
        _5.setPrefWidth(70);
        _6.setPrefWidth(180);
        _7.setPrefWidth(150);
        _8.setPrefWidth(150);
        _9.setPrefWidth(120);



        jt_entradas_pilones.getColumns().addAll(_1, _2, _3, _4, _5,_6, _7,_8,_9);

        _1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_entradas_pilones, String>("id_entrada_pilones")
        );

        _2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_entradas_pilones, String>("nombre_tabaco_entradas_pilones")
        );

        _3.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_entradas_pilones, String>("variedad")
        );

        _4.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_entradas_pilones, String>("finca")
        );

        _5.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_entradas_pilones, String>("numero_pilon_entradas_pilones")
        );
        _6.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_entradas_pilones, String>("fecha_entradas_pilones")
        );
        _7.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_entradas_pilones, String>("tiempo_adelanto_entradas_pilones")
        );
        _8.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_entradas_pilones, String>("fecha_estima_salida_entradas_pilones")
        );

        _9.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_entradas_pilones, String>("cantidad_libras_entradas_pilones")
        );
        jt_entradas_pilones.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                btn_editar_entrada_pilones.setVisible(true);
                jtxt_buscar_entradas_pilon.setText("");

            }
        });
}

public void tabla_control_pilones(JFXTreeTableView jt_control_pilones, JFXButton btn_editar_control_pilones,
                                 TextField jtx_buscar_control_pilon){

    JFXTreeTableColumn<Clase_control_pilones, String> _1 = new JFXTreeTableColumn<>("ID");
    JFXTreeTableColumn<Clase_control_pilones, String> _2 = new JFXTreeTableColumn<>("Nombre de tabaco");
    JFXTreeTableColumn<Clase_control_pilones, String> _5 = new JFXTreeTableColumn<>("Fecha");
    JFXTreeTableColumn<Clase_control_pilones, String> _6 = new JFXTreeTableColumn<>("N\u00Famero de Pil\u00f3n");
    JFXTreeTableColumn<Clase_control_pilones, String> _7 = new JFXTreeTableColumn<>("Entrada de tabaco");
    JFXTreeTableColumn<Clase_control_pilones, String> _8 = new JFXTreeTableColumn<>("Salida de tabaco");
    JFXTreeTableColumn<Clase_control_pilones, String> _9 = new JFXTreeTableColumn<>("Total actual");
    JFXTreeTableColumn<Clase_control_pilones, String> _10 = new JFXTreeTableColumn<>("Existencia total");

    _1.setPrefWidth(60);
    _2.setPrefWidth(320);
    _5.setPrefWidth(130);
    _6.setPrefWidth(140);
    _7.setPrefWidth(140);
    _8.setPrefWidth(130);
    _9.setPrefWidth(150);
    _10.setPrefWidth(150);



    jt_control_pilones.getColumns().addAll(_1, _2, _5,_6, _7,_8,_9,_10);

    _1.setCellValueFactory(
            new TreeItemPropertyValueFactory<Clase_control_pilones, String>("id_control_pilon")
    );

    _2.setCellValueFactory(
            new TreeItemPropertyValueFactory<Clase_control_pilones, String>("clase_tabaco_control")
    );

    _5.setCellValueFactory(
            new TreeItemPropertyValueFactory<Clase_control_pilones, String>("fecha_control")
    );
    _6.setCellValueFactory(
            new TreeItemPropertyValueFactory<Clase_control_pilones, String>("numero_pilon_control")
    );

    _7.setCellValueFactory(
            new TreeItemPropertyValueFactory<Clase_control_pilones, String>("entrada_tabaco_pilon")
    );
    _8.setCellValueFactory(
            new TreeItemPropertyValueFactory<Clase_control_pilones, String>("salida_tabaco_pilon")
    );
    _9.setCellValueFactory(
            new TreeItemPropertyValueFactory<Clase_control_pilones, String>("total_actual")
    );

    _10.setCellValueFactory(
            new TreeItemPropertyValueFactory<Clase_control_pilones, String>("existencia_total")
    );
    jt_control_pilones.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            btn_editar_control_pilones.setVisible(true);
            jtx_buscar_control_pilon.setText("");

        }
    });

}



    public void mensaje(String titulo, String mensaje, StackPane root){
        JFXDialogLayout ventana_mensaje= new JFXDialogLayout();
        ventana_mensaje.setHeading(new Text(titulo));
        ventana_mensaje.setBody(new Text(mensaje));
        dialogo = new JFXDialog(root,ventana_mensaje,JFXDialog.DialogTransition.CENTER);

        dialogo.setMinHeight(root.getPrefHeight());
        dialogo.setMinWidth(root.getPrefWidth());

        ventana_mensaje.setActions(btn_mensaje);
        dialogo.show();
    }


    public void soloNumerosyunPunto(final JFXTextField campo, int limite, int conPunto) {

        campo.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (campo.getText().length() == 0 && event.getCharacter().equals(" ")) {
                    event.consume();
                }
                if (campo.getText().length() == 0 && event.getCharacter().equals(".")) {
                    event.consume();
                }
                if (campo.getText().length() <= limite) {
                    if (!Character.isDigit(event.getCharacter().charAt(0)) && !(event.getCharacter().equals("."))) {
                        event.consume();
                    }
                    if (event.getCharacter().equals(".") && campo.getText().contains(".")) {
                        event.consume();
                    }

                } else {

                    if (event.getCharacter().equals(".") || campo.getText().contains(".")) {

                        if (campo.getText().length() <= conPunto) {
                            if ((!Character.isDigit(event.getCharacter().charAt(0)) && !(event.getCharacter().equals(".")))
                                    || (event.getCharacter().equals(".") && campo.getText().contains("."))) {
                                event.consume();
                            } else {

                            }
                        }else{
                            event.consume();
                        }
                    } else {
                        event.consume();
                    }
                }
            }
        });
    }

    public void solo_numeros(JFXTextField caja, int longitud) {
        caja.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(javafx.scene.input.KeyEvent evt) {
                if (caja.getText().length() == 0 && evt.getCharacter().equals("!") ||
                        evt.getCharacter().equals(".")
                        ) {
                    evt.consume();
                }
                if (caja.getText().length() <= longitud) {
                    if (!Character.isDigit(evt.getCharacter().charAt(0)) && !(evt.getCharacter().equals("."))) {
                        evt.consume();
                    }
                    if (evt.getCharacter().equals(".") && caja.getText().contains(".")) {
                        evt.consume();
                    }

                } else {

                    if (evt.getCharacter().equals(".") || caja.getText().contains(".")) {

                        if (caja.getText().length() <= longitud) {
                            if ((!Character.isDigit(evt.getCharacter().charAt(0))  )) {
                                evt.consume();
                            } else {

                            }
                        }else{
                            evt.consume();
                        }
                    } else {
                        evt.consume();
                    }
                }


                if (caja.getText().length() >= longitud) {
                    evt.consume();
                }
            }
        });

    }

    public void solo_letras(JFXTextField caja, int longitud) {
        caja.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent evt) {

                if (caja.getText().length() <= longitud) {
                    if (!Character.isLetter(evt.getCharacter().charAt(0)) && !(evt.getCharacter().equals(" "))) {
                        evt.consume();
                    }


                } else {

                    if (evt.getCharacter().equals(" ") || caja.getText().contains(" ")) {

                        if (caja.getText().length() <= longitud) {
                            if ((!Character.isLetter(evt.getCharacter().charAt(0)) && !(evt.getCharacter().equals(" ") ))
                                    || (evt.getCharacter().equals(" ") || caja.getText().contains(" "))) {
                                evt.consume();
                            } else {

                            }
                        }else{
                            evt.consume();
                        }
                    } else {
                        evt.consume();
                    }
                }



                if (caja.getText().length() >= longitud) {
                    evt.consume();
                }
            }
        });
    }







}
