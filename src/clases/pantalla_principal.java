package clases;

import clases.DBUtilities.ActualizarTablas;
import clases.DBUtilities.DBType;
import clases.DBUtilities.DBUtilities;
import clases.DBUtilities.modificaciones;
import clases.Objetos_POJO.Clase_control_temperatura;
import clases.Objetos_POJO.Clase_pilones_nombre;
import clases.Objetos_POJO.Clase_remisiones;
import clases.Objetos_POJO.Clase_tabacos;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import io.datafx.controller.ViewController;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import jdk.nashorn.internal.codegen.CompilerConstants;

import javax.swing.*;
import javax.swing.tree.DefaultTreeSelectionModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@ViewController(value = "/resources/pantalla_principal.fxml", title = "Plasencia")
public final class pantalla_principal extends Aplicacion_principal implements Initializable, modificaciones, ActualizarTablas {


    @FXML
    public StackPane stackpane;
    @FXML
    public JFXHamburger boton_menu;
    @FXML
    public JFXDrawer drawer;
    @FXML
    public JFXTreeTableView<Clase_pilones_nombre> jt_pilones;
    @FXML
    public JFXTreeTableView<Clase_tabacos> jt_clase_tabaco;
    @FXML
    public JFXButton btn_nuevo_pilon_tabla;
    @FXML
    public JFXButton btn_editar_pilon_tabla;
    @FXML
    public JFXButton btn_nuevo_tabaco_tabla;
    @FXML
    public JFXButton btn_editar_tabaco_tabla;

    //TODO Varibles de la tabla revison
    @FXML
    public JFXTreeTableView<Clase_remisiones> jt_remisiones;
    @FXML
    public JFXButton btn_nueva_remision;
    @FXML
    public JFXButton btn_editar_remision;
    @FXML
    public JFXButton btn_imprimir_remision;
    @FXML
    public JFXCheckBox cbx_busqueda_mes;
    @FXML
    public JFXComboBox cbx_mes;
    @FXML
    public JFXCheckBox chck_busqueda_anio;
    @FXML
    public JFXComboBox cbx_anio;
    @FXML
    public JFXButton btn_imprimir_remisiones;
    @FXML
    public JFXTextField txt_busqueda_remision;

    //Variables de tabla de control temp
    @FXML
    public JFXTreeTableView<Clase_control_temperatura> jt_control_temp;
    @FXML
    public JFXTreeTableView<Clase_pilones_nombre> jt_pilon_control_temp;
    @FXML
    public JFXButton btn_nuevo_control_temp;
    @FXML
    public JFXButton btn_eliminar_control_temp;
    public JFXTreeTableView jt_proceso_entrada_pilon;
    public JFXButton btn_nuevo_entrada_pilon;
    public JFXButton btn_editar_entrada_pilon;
    public JFXCheckBox cbx_entrada_pilon;
    public JFXCheckBox cbx_salida_pilon;


    //TODO otras variables

    HamburgerBackArrowBasicTransition transition;

    private Scene scene;


    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/resources/pantalla_principal.fxml"));

        scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setHeight(720);
        stage.setWidth(1200);
        stage.setTitle("");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scene = new Scene(new AnchorPane());
        tabla_clase_tabaco();
        tabla_pilones();
        tabla_remisiones();
        tabla_Control_temp();

        if (!Main.ventana_splash) {
            loadSplashScreen();
        }


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/sidepanel.fxml"));
            VBox box = loader.load();
            SidePanelController controller = loader.getController();
            controller.setActualizaMain(this);
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(pantalla_principal.class.getName()).log(Level.SEVERE, null, ex);
        }


        transition = new HamburgerBackArrowBasicTransition(boton_menu);
        transition.setRate(-1);
        boton_menu.setOnMouseClicked((e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isOpened()) {
                drawer.close();
            } else {
                drawer.open();
            }

        });
    }

    private void tabla_remisiones() {

        JFXTreeTableColumn<Clase_remisiones,String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_remisiones,String> _2 = new JFXTreeTableColumn<>("Código");
        JFXTreeTableColumn<Clase_remisiones,String> _4 = new JFXTreeTableColumn<>("Fecha");
        JFXTreeTableColumn<Clase_remisiones,String> _5 = new JFXTreeTableColumn<>("Destino");
        JFXTreeTableColumn<Clase_remisiones,String> _6 = new JFXTreeTableColumn<>("Origen");
        JFXTreeTableColumn<Clase_remisiones,String> _7 = new JFXTreeTableColumn<>("Descripción");
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

        jt_remisiones.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                btn_editar_pilon_tabla.setVisible(false);
                btn_editar_tabaco_tabla.setVisible(true);

            }
        });
    }

    private void tabla_clase_tabaco() {

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
    private void tabla_pilones() {

        JFXTreeTableColumn<Clase_pilones_nombre, String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_pilones_nombre, String> _2 = new JFXTreeTableColumn<>("Numero de Pilon");


        _1.setPrefWidth(50);
        _2.setPrefWidth(229);

        jt_pilones.getColumns().addAll(_1, _2);

        _1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_pilones_nombre, String>("id_pilon")
        );

        _2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_pilones_nombre, String>("nombre_pilon")
        );


        jt_pilones.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                    btn_editar_pilon_tabla.setVisible(true);
                    btn_editar_tabaco_tabla.setVisible(false);
                            }
        });

    }

    private void tabla_Control_temp(){
        JFXTreeTableColumn<Clase_control_temperatura, String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_control_temperatura, String> _2 = new JFXTreeTableColumn<>("Número de Pilón");
        JFXTreeTableColumn<Clase_control_temperatura, String> _3 = new JFXTreeTableColumn<>("Temperatura");
        JFXTreeTableColumn<Clase_control_temperatura, String> _4 = new JFXTreeTableColumn<>("Fecha de Revisión");
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
        JFXTreeTableColumn<Clase_pilones_nombre, String> _2_2 = new JFXTreeTableColumn<>("Número de Pilón");


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
            }
        });

        jt_control_temp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn_nuevo_control_temp.setVisible(false);
                btn_eliminar_control_temp.setVisible(true);
            }
        });

    }



    private void loadSplashScreen() {
            {
                try {

                    Main.ventana_splash = true;

                    AnchorPane pane = FXMLLoader.load(getClass().getResource(("/resources/pantalla.fxml")));
                    stackpane.getChildren().setAll(pane);

                    FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
                    fadeIn.setFromValue(0);
                    fadeIn.setToValue(1);
                    fadeIn.setCycleCount(1);

                    FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
                    fadeOut.setFromValue(1);
                    fadeOut.setToValue(0);
                    fadeOut.setCycleCount(1);

                    fadeIn.play();

                    fadeIn.setOnFinished((e) -> {
                        fadeOut.play();
                    });

                    fadeOut.setOnFinished((e) -> {
                        try {
                            StackPane parentContent = FXMLLoader.load(getClass().getResource(("/resources/pantalla_principal.fxml")));
                            stackpane.getChildren().setAll(parentContent);
                        } catch (IOException ex) {
                            Logger.getLogger(pantalla_principal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                } catch (IOException ex) {
                    Logger.getLogger(pantalla_principal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }


    @Override
    public void cambiar_titulo(String titulo_main, int id_tabla) {

    }

    @Override
    public JFXTreeTableView<Clase_pilones_nombre> traer_jt_pilones() {

        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(true);
        btn_editar_pilon_tabla.setVisible(true);
        btn_nuevo_tabaco_tabla.setVisible(false);
        btn_editar_tabaco_tabla.setVisible(false);

        //TODO botones de registro Remisones
        btn_editar_remision.setVisible(false);
        btn_nueva_remision.setVisible(false);
        btn_imprimir_remision.setVisible(false);
        btn_imprimir_remisiones.setVisible(false);
        cbx_anio.setVisible(false);
        cbx_mes.setVisible(false);
        chck_busqueda_anio.setVisible(false);
        cbx_busqueda_mes.setVisible(false);
        txt_busqueda_remision.setVisible(false);

        //TODO botones control de temperatura
        btn_eliminar_control_temp.setVisible(false);
        btn_nuevo_control_temp.setVisible(false);

        return jt_pilones;
    }

    @Override
    public JFXTreeTableView<Clase_tabacos> traer_jt_clase_tabaco() {

        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(true);
        btn_editar_pilon_tabla.setVisible(true);
        btn_nuevo_tabaco_tabla.setVisible(false);
        btn_editar_tabaco_tabla.setVisible(false);

        //TODO botones de registro Remisones
        btn_editar_remision.setVisible(false);
        btn_nueva_remision.setVisible(false);
        btn_imprimir_remision.setVisible(false);
        btn_imprimir_remisiones.setVisible(false);
        cbx_anio.setVisible(false);
        cbx_mes.setVisible(false);
        chck_busqueda_anio.setVisible(false);
        cbx_busqueda_mes.setVisible(false);
        txt_busqueda_remision.setVisible(false);

        //TODO botones control de temperatura
        btn_eliminar_control_temp.setVisible(false);
        btn_nuevo_control_temp.setVisible(false);

        return jt_clase_tabaco;
    }

    @Override
    public JFXTreeTableView<Clase_remisiones> traer_jt_remisiones() {
        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(false);
        btn_editar_pilon_tabla.setVisible(false);
        btn_nuevo_tabaco_tabla.setVisible(false);
        btn_editar_tabaco_tabla.setVisible(false);

        //TODO botones de registro Remisones
        btn_editar_remision.setVisible(false);
        btn_nueva_remision.setVisible(true);
        btn_imprimir_remision.setVisible(false);
        btn_imprimir_remisiones.setVisible(true);
        cbx_anio.setVisible(true);
        cbx_mes.setVisible(true);
        chck_busqueda_anio.setVisible(true);
        cbx_busqueda_mes.setVisible(true);
        txt_busqueda_remision.setVisible(true);

        //TODO botones control de temperatura
        btn_nuevo_control_temp.setVisible(false);
        btn_eliminar_control_temp.setVisible(false);

        return jt_remisiones;
    }
    
    @Override
    public JFXTreeTableView<Clase_control_temperatura> traer_jt_control_temp() {
        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(false);
        btn_editar_pilon_tabla.setVisible(false);
        btn_nuevo_tabaco_tabla.setVisible(false);
        btn_editar_tabaco_tabla.setVisible(false);

        //TODO botones de registro Remisones
        btn_editar_remision.setVisible(false);
        btn_nueva_remision.setVisible(false);
        btn_imprimir_remision.setVisible(false);
        btn_imprimir_remisiones.setVisible(false);
        cbx_anio.setVisible(false);
        cbx_mes.setVisible(false);
        chck_busqueda_anio.setVisible(false);
        cbx_busqueda_mes.setVisible(false);
        txt_busqueda_remision.setVisible(false);

        //TODO botones control de temperatura
        btn_nuevo_control_temp.setVisible(true);
        btn_eliminar_control_temp.setVisible(true);
        return jt_control_temp;
    }

    @Override
    public JFXTreeTableView<Clase_pilones_nombre> traer_jt_pilon_control_temp() {

        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(false);
        btn_editar_pilon_tabla.setVisible(false);
        btn_nuevo_tabaco_tabla.setVisible(false);
        btn_editar_tabaco_tabla.setVisible(false);

        //TODO botones de registro Remisones
        btn_editar_remision.setVisible(false);
        btn_nueva_remision.setVisible(false);
        btn_imprimir_remision.setVisible(false);
        btn_imprimir_remisiones.setVisible(false);
        cbx_anio.setVisible(false);
        cbx_mes.setVisible(false);
        chck_busqueda_anio.setVisible(false);
        cbx_busqueda_mes.setVisible(false);
        txt_busqueda_remision.setVisible(false);

        //TODO botones control de temperatura
        btn_eliminar_control_temp.setVisible(true);
        btn_nuevo_control_temp.setVisible(true);
        return jt_pilon_control_temp;
    }

    @Override
    public JFXDrawer traer_menu_lateral() {
        return drawer;
    }

    @Override
    public HamburgerBackArrowBasicTransition traer_transiscion() {
        return transition;
    }

    public void nuevo_pilon(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/nombre_pilon.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Nuevo Pilon");
        stage.show();
    }

    public void editar_pilon(ActionEvent actionEvent) throws IOException {


        int seleccion = jt_pilones.getSelectionModel().getSelectedIndex();
        StackPane root1;
        FXMLLoader ventana;
        ventana = new FXMLLoader(getClass().getResource(
                "/resources/nombre_pilon.fxml"));
        root1 = ventana.load();
        scene.setRoot(root1);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Actualizar Pilon");
        stage.setScene(scene);

        nombre_pilon clase = ventana.getController();

        clase.lbl_id_pilon_mostra.setText(String.valueOf(jt_pilones.getTreeItem(seleccion).getValue().getId_pilon()));

        clase.txt_nombre_pilon.setText(String.valueOf(jt_pilones.getTreeItem(seleccion).getValue().getNombre_pilon()));


        stage.showAndWait();



    }

    public void nuevo_tabaco(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/clase_tabaco.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Nuevo Tabaco");
        stage.show();

    }

    public void editar_tabaco(ActionEvent actionEvent) throws IOException {

        int seleccion = jt_clase_tabaco.getSelectionModel().getSelectedIndex();
        StackPane root1;
        FXMLLoader ventana;
        ventana = new FXMLLoader(getClass().getResource(
                "/resources/clase_tabaco.fxml"));
        root1 = ventana.load();
        scene.setRoot(root1);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Actualizar tabaco");
        stage.setScene(scene);

        clase_tabaco clase = ventana.getController();

        clase.lbl_id_tabaco.setText(String.valueOf(jt_clase_tabaco.getTreeItem(seleccion).getValue().getId_tabaco()));
        clase.txt_nombre_tabaco.setText(jt_clase_tabaco.getTreeItem(seleccion).getValue().getNombre_tbc());

        stage.showAndWait();


    }

    // Boton de agregar control de la temperatura

    public void agregar_control_temp() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/resources/control_temperatura.fxml"));

        int seleccion = jt_pilon_control_temp.getSelectionModel().getSelectedIndex();
        StackPane root1;
        FXMLLoader ventana;
        ventana = new FXMLLoader(getClass().getResource(
                "/resources/control_temperatura.fxml"));
        root1 = ventana.load();
        scene.setRoot(root1);
        Stage stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.setTitle("Calcular Control de Temperatura");
        stage1.setScene(scene);

        control_temperatura clase = ventana.getController();

        clase.lbl_id_pilon.setText(String.valueOf(jt_pilon_control_temp.getTreeItem(seleccion).getValue().getNombre_pilon()));
        clase.btn_actualizar.setVisible(false);
        stage1.showAndWait();

    }

    public void eliminar_control_temp(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        //FXMLLoader ventana = new FXMLLoader(getClass().getResource("/resources/sidepanel.fxml.fxml"));
        int fila = jt_control_temp.getSelectionModel().getSelectedIndex();
        Clase_control_temperatura dato = jt_control_temp.getTreeItem(fila).getValue();
                try {
                    //Consulta
                    PreparedStatement consulta = DBUtilities.getConnection(DBType.MARIADB)
                            .prepareStatement("CALL eliminar_control_temp(?)");
                    consulta.setString(1,dato.getId_control_temp());
                    ResultSet respuesta = consulta.executeQuery();

                    //Cargar la tabla
                    SidePanelController.datos_tabla_registro_temperatura();

                    String mensaje[]= new String[2];
                    while (respuesta.next()){
                        mensaje[0]= respuesta.getString(1);
                        mensaje[1]= respuesta.getString(2);

                    }
                    btn_mensaje.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                    dialogo.close();
                                                }
                                            });
                    mensaje("Mensaje",mensaje[0],stackpane );
                }
                catch (Exception e) {

                }


    }

    }

