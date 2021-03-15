import Objetos_POJO.*;
import com.jfoenix.controls.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    public void tabla_clase_tabaco(JFXTreeTableView jt_clase_tabaco,JFXButton btn_editar_pilon_tabla, JFXButton btn_editar_tabaco_tabla) {

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

            }
        });
    }

    public void tabla_pilones(JFXTreeTableView jt_pilones,JFXButton btn_editar_pilon_tabla, JFXButton btn_editar_tabaco_tabla) {

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
        });

    }


    public  void tabla_Control_temp(JFXTreeTableView jt_control_temp,JFXTreeTableView jt_pilon_control_temp,
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



    public void tabla_en_y_sa_proceso(JFXTreeTableView<Clase_en_sa_proceso_pilon> jt_proceso_entrada_pilon, JFXButton btn_editar_entrada_pilon, JFXButton btn_editar_salidas_pilon){

        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _2 = new JFXTreeTableColumn<>("Fecha de Proceso ");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _3 = new JFXTreeTableColumn<>("N\u00Famero de Remisión");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _4 = new JFXTreeTableColumn<>("Entradas y Salidas");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _5 = new JFXTreeTableColumn<>("Nombre de Tabaco");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _6 = new JFXTreeTableColumn<>("N\u00famero de Pil\u00f3n");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _7 = new JFXTreeTableColumn<>("Sub Total ");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _8 = new JFXTreeTableColumn<>("Total Libras");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _9 = new JFXTreeTableColumn<>("Total General");

        _1.setPrefWidth(40);
        _2.setPrefWidth(110);
        _3.setPrefWidth(150);
        _4.setPrefWidth(170);
        _5.setPrefWidth(280);
        _6.setPrefWidth(120);
        _7.setPrefWidth(100);
        _8.setPrefWidth(80);
        _9.setPrefWidth(100);

        jt_proceso_entrada_pilon.getColumns().addAll(_1, _2, _3, _4, _5,_6, _7, _8, _9);

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
        _6.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("num_en_sa_proceso_pilon")
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
            }
        });

    }


    public void tabla_en_sa_pilon(JFXTreeTableView jt_proceso_salidas_pilon, JFXButton btn_editar_entrada_pilon,
                                   JFXButton btn_editar_salidas_pilon){
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _2 = new JFXTreeTableColumn<>("Fecha de Proceso ");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _3 = new JFXTreeTableColumn<>("N\u00Famero de Remisión");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _4 = new JFXTreeTableColumn<>("Entradas y Salidas");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _5 = new JFXTreeTableColumn<>("Nombre de Tabaco");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _6 = new JFXTreeTableColumn<>("N\u00famero de Pil\u00f3n");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _7 = new JFXTreeTableColumn<>("Sub Total ");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _8 = new JFXTreeTableColumn<>("Total Libras");
        JFXTreeTableColumn<Clase_en_sa_proceso_pilon, String> _9 = new JFXTreeTableColumn<>("Total General");

        _1.setPrefWidth(40);
        _2.setPrefWidth(110);
        _3.setPrefWidth(150);
        _4.setPrefWidth(170);
        _5.setPrefWidth(280);
        _6.setPrefWidth(120);
        _7.setPrefWidth(100);
        _8.setPrefWidth(80);
        _9.setPrefWidth(100);


        jt_proceso_salidas_pilon.getColumns().addAll(_1, _2, _3, _4, _5,_6, _7, _8, _9);

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
        _6.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_en_sa_proceso_pilon, String>("num_en_sa_proceso_pilon")
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








}
