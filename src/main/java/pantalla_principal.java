import DBUtilitie.ActualizarTablas;
import DBUtilitie.DBType;
import DBUtilitie.DBUtilities;
import DBUtilitie.modificaciones;
import Objetos_POJO.*;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


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

    //TODO procesos

    @FXML
    public JFXTreeTableView <Clase_en_sa_proceso_pilon>jt_proceso_entrada_pilon;
    @FXML
    public JFXButton btn_nuevo_entrada_pilon;
    @FXML
    public JFXButton btn_editar_entrada_pilon;
    @FXML
    public JFXTreeTableView <Clase_en_sa_proceso_pilon> jt_proceso_salidas_pilon;
    @FXML
    public JFXButton btn_nuevo_salidas_pilon;
    @FXML
    public JFXButton btn_editar_salidas_pilon;

    //TODO Variables de tabla de control temperatura

    @FXML
    public JFXTreeTableView<Clase_control_temperatura> jt_control_temp;
    @FXML
    public JFXTreeTableView<Clase_pilones_nombre> jt_pilon_control_temp;
    @FXML
    public JFXButton btn_nuevo_control_temp;
    @FXML
    public JFXButton btn_eliminar_control_temp;
    @FXML
    public JFXButton btn_grafica_actual_temperatura;
    @FXML
    public AnchorPane anchor_resumen_temperatura;
    @FXML
    public JFXListView lis_pilones_pendientes;
    @FXML
    public AnchorPane anchor_botones_meses;
    @FXML
    public JFXComboBox cbx_anio_fecha_temperatura;
    @FXML
    public JFXButton btn_grafico_enero;
    @FXML
    public JFXButton btn_grafico_febrero;
    @FXML
    public JFXButton btn_grafico_marzo;
    @FXML
    public JFXButton btn_grafico_abril;
    @FXML
    public JFXButton btn_grafico_mayo;
    @FXML
    public JFXButton btn_grafico_junio;
    @FXML
    public JFXButton btn_grafico_julio;
    @FXML
    public JFXButton btn_grafico_agosto;
    @FXML
    public JFXButton btn_grafico_septiembre;
    @FXML
    public JFXButton btn_grafico_octubre;
    @FXML
    public JFXButton btn_grafico_noviembre;
    @FXML
    public JFXButton btn_grafico_diciembre;


    //TODO otras variables

    HamburgerBackArrowBasicTransition transition;

    private Scene scene;


    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/pantalla_principal.fxml"));

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
        tabla_clase_tabaco(jt_clase_tabaco,btn_editar_pilon_tabla,btn_editar_tabaco_tabla);
        tabla_pilones(jt_pilones,btn_editar_pilon_tabla,btn_editar_tabaco_tabla);
        tabla_remisiones(jt_remisiones,btn_editar_remision);
        tabla_Control_temp(jt_control_temp,jt_pilon_control_temp,btn_nuevo_control_temp,btn_eliminar_control_temp,anchor_botones_meses);
        tabla_en_y_sa_proceso(jt_proceso_entrada_pilon,btn_editar_entrada_pilon,btn_editar_salidas_pilon);
        tabla_en_sa_pilon(jt_proceso_salidas_pilon,btn_editar_entrada_pilon,btn_editar_salidas_pilon);

        if (!Main.ventana_splash) {
            loadSplashScreen();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sidepanel.fxml"));
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
                drawer.setVisible(false);
            } else {
                drawer.setVisible(true);
                drawer.open();
            }

        });
    }


    private void loadSplashScreen() {

                try {

                    Main.ventana_splash = true;

                    AnchorPane pane = FXMLLoader.load(getClass().getResource(("/pantalla.fxml")));
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
                            StackPane parentContent = FXMLLoader.load(getClass().getResource(("/pantalla_principal.fxml")));
                            stackpane.getChildren().setAll(parentContent);
                        } catch (IOException ex) {
                            Logger.getLogger(pantalla_principal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                } catch (IOException ex) {
                    Logger.getLogger(pantalla_principal.class.getName()).log(Level.SEVERE, null, ex);
                }

    }


    @Override
    public void cambiar_titulo(String titulo_main, int id_tabla) {

    }

    @Override
    public JFXTreeTableView<Clase_pilones_nombre> traer_jt_pilones() {

        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(true);
        btn_editar_pilon_tabla.setVisible(false);
        btn_nuevo_tabaco_tabla.setVisible(true);
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
        anchor_botones_meses.setVisible(false);
        anchor_resumen_temperatura.setVisible(false);
        btn_grafica_actual_temperatura.setVisible(false);

        //TODO botones entradas y salidas proceso
        btn_nuevo_entrada_pilon.setVisible(false);
        btn_editar_entrada_pilon.setVisible(false);
        btn_nuevo_salidas_pilon.setVisible(false);
        btn_editar_salidas_pilon.setVisible(false);

        return jt_pilones;
    }

    @Override
    public JFXTreeTableView<Clase_tabacos> traer_jt_clase_tabaco() {

        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(true);
        btn_editar_pilon_tabla.setVisible(false);
        btn_nuevo_tabaco_tabla.setVisible(true);
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
        anchor_botones_meses.setVisible(false);
        anchor_resumen_temperatura.setVisible(false);
        btn_grafica_actual_temperatura.setVisible(false);

        //TODO botones entradas y salidas proceso
        btn_nuevo_entrada_pilon.setVisible(false);
        btn_editar_entrada_pilon.setVisible(false);
        btn_nuevo_salidas_pilon.setVisible(false);
        btn_editar_salidas_pilon.setVisible(false);

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
        anchor_botones_meses.setVisible(false);
        anchor_resumen_temperatura.setVisible(false);
        btn_grafica_actual_temperatura.setVisible(false);

        //TODO botones entradas y salidas proceso
        btn_nuevo_entrada_pilon.setVisible(false);
        btn_editar_entrada_pilon.setVisible(false);
        btn_nuevo_salidas_pilon.setVisible(false);
        btn_editar_salidas_pilon.setVisible(false);

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
        anchor_botones_meses.setVisible(false);
        anchor_resumen_temperatura.setVisible(false);
        btn_grafica_actual_temperatura.setVisible(false);

        //TODO botones entradas y salidas proceso
        btn_nuevo_entrada_pilon.setVisible(false);
        btn_editar_entrada_pilon.setVisible(false);
        btn_nuevo_salidas_pilon.setVisible(false);
        btn_editar_salidas_pilon.setVisible(false);
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
        btn_eliminar_control_temp.setVisible(false);
        btn_nuevo_control_temp.setVisible(true);
        anchor_botones_meses.setVisible(false);
        anchor_resumen_temperatura.setVisible(true);
        btn_grafica_actual_temperatura.setVisible(false);


        //TODO botones entradas y salidas proceso
        btn_nuevo_entrada_pilon.setVisible(false);
        btn_editar_entrada_pilon.setVisible(false);
        btn_nuevo_salidas_pilon.setVisible(false);
        btn_editar_salidas_pilon.setVisible(false);
        return jt_pilon_control_temp;
    }

    @Override
    public JFXTreeTableView<Clase_en_sa_proceso_pilon> traer_jt_en_sa_proceso_pilon() {
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
        btn_eliminar_control_temp.setVisible(false);
        btn_nuevo_control_temp.setVisible(false);

        //TODO botones entradas y salidas proceso
        btn_nuevo_entrada_pilon.setVisible(true);
        btn_editar_entrada_pilon.setVisible(false);
        btn_nuevo_salidas_pilon.setVisible(true);
        btn_editar_salidas_pilon.setVisible(false);
        return jt_proceso_entrada_pilon;
    }


    @Override
    public JFXTreeTableView<Clase_en_sa_proceso_pilon> traer_jt_en_sa_pilon() {
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
        btn_eliminar_control_temp.setVisible(false);
        btn_nuevo_control_temp.setVisible(false);

        //TODO botones entradas y salidas proceso
        btn_nuevo_entrada_pilon.setVisible(true);
        btn_editar_entrada_pilon.setVisible(false);
        btn_nuevo_salidas_pilon.setVisible(true);
        btn_editar_salidas_pilon.setVisible(false);

        return jt_proceso_salidas_pilon;
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
        FXMLLoader ventana = new FXMLLoader(getClass().getResource("/nombre_pilon.fxml"));
        StackPane root;
        root = ventana.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Nuevo Pilon");
        stage.show();
        nombre_pilon pilon = ventana.getController();
        pilon.btn_actualizar_pilon.setVisible(false);
        DBUtilities.CargarId(pilon.lbl_id_pilon_mostra,"SELECT * FROM pilones ORDER BY id_pilon DESC" );
    }

    public void editar_pilon(ActionEvent actionEvent) throws IOException {


        int seleccion = jt_pilones.getSelectionModel().getSelectedIndex();
        StackPane root1;
        FXMLLoader ventana;
        ventana = new FXMLLoader(getClass().getResource(
                "/nombre_pilon.fxml"));
        root1 = ventana.load();
        scene.setRoot(root1);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Actualizar Pilon");
        stage.setScene(scene);

        nombre_pilon clase = ventana.getController();

        clase.lbl_id_pilon_mostra.setText(String.valueOf(jt_pilones.getTreeItem(seleccion).getValue().getId_pilon()));

        clase.txt_nombre_pilon.setText(String.valueOf(jt_pilones.getTreeItem(seleccion).getValue().getNombre_pilon()));
        clase.btn_guardar_pilon.setVisible(false);

        stage.showAndWait();



    }

    public void nuevo_tabaco(ActionEvent actionEvent) throws IOException {


        FXMLLoader ventana = new FXMLLoader(getClass().getResource("/clase_tabaco.fxml"));
        StackPane root;
        root = ventana.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Nuevo Tabaco");

        stage.show();

        clase_tabaco tabaco = ventana.getController();
        tabaco.btn_actualizar_clase_tabaco.setVisible(false);
        DBUtilities.CargarId(tabaco.lbl_id_tabaco,"SELECT * FROM clase_tabaco ORDER BY id_tabaco DESC");

    }

    public void editar_tabaco(ActionEvent actionEvent) throws IOException {

        int seleccion = jt_clase_tabaco.getSelectionModel().getSelectedIndex();
        StackPane root1;
        FXMLLoader ventana;
        ventana = new FXMLLoader(getClass().getResource(
                "/clase_tabaco.fxml"));
        root1 = ventana.load();
        scene.setRoot(root1);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Actualizar tabaco");
        stage.setScene(scene);

        clase_tabaco clase = ventana.getController();

        clase.lbl_id_tabaco.setText(String.valueOf(jt_clase_tabaco.getTreeItem(seleccion).getValue().getId_tabaco()));
        clase.txt_nombre_tabaco.setText(jt_clase_tabaco.getTreeItem(seleccion).getValue().getNombre_tbc());
        clase.btn_guardar_clase_tabaco.setVisible(false);
        stage.showAndWait();


    }

    // Boton de agregar control de la temperatura

    public void agregar_control_temp() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/control_temperatura.fxml"));

        int seleccion = jt_pilon_control_temp.getSelectionModel().getSelectedIndex();
        StackPane root1;
        FXMLLoader ventana;
        ventana = new FXMLLoader(getClass().getResource(
                "/control_temperatura.fxml"));
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
                    PreparedStatement consulta = Objects.requireNonNull(DBUtilities.getConnection(DBType.MARIADB))
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

        /************************************ TODO eventos de botones de remision *****************************************************/

        public void nuevo_remision(ActionEvent actionEvent) throws IOException {
                StackPane root;
               // Parent root = FXMLLoader.load(getClass().getResource("/proceso_remision.fxml"));
                FXMLLoader ventana = new FXMLLoader(getClass().getResource("/proceso_remision.fxml"));
                root = ventana.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.initStyle(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("Nueva Remisión");
                stage.show();
                proceso_remision remision = ventana.getController();
                DBUtilities.CargarId(remision.lbl_id_remision,"SELECT * FROM remision_proceso ORDER BY id_remision_proceso DESC");
        }

        public void editar_remision(ActionEvent actionEvent) throws IOException {

            int seleccion = jt_remisiones.getSelectionModel().getSelectedIndex();
            StackPane root1;
            FXMLLoader ventana;
            ventana = new FXMLLoader(getClass().getResource(
                    "/proceso_remision.fxml"));
            root1 = ventana.load();
            scene.setRoot(root1);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Actualizar Remisión");
            stage.setScene(scene);

            proceso_remision clase = ventana.getController();

            //clase.lbl_id_tabaco.setText(String.valueOf(jt_clase_tabaco.getTreeItem(seleccion).getValue().getId_tabaco()));
            //clase.txt_nombre_tabaco.setText(jt_clase_tabaco.getTreeItem(seleccion).getValue().getNombre_tbc());

            stage.showAndWait();

        }

        public void imprimir_remision(ActionEvent actionEvent) throws IOException {

        }

        public void imprimir_remisions(ActionEvent actionEvent) throws IOException {

        }




    public void Agregar_entradas_proceso(ActionEvent actionEvent) throws IOException {

        StackPane root1;
        FXMLLoader ventana;
        ventana = new FXMLLoader(getClass().getResource("/proceso_pilon.fxml"));

        root1 = ventana.load();
        scene.setRoot(root1);
        Stage stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.setTitle("Registro de entradas y salidas de la tabla Proceso");
        stage1.setScene(scene);

        Proceso_pilon proceso = ventana.getController();
        proceso.cbx_tabla_proceso.setDisable(true);
        proceso.cbx_tabla_proceso.setSelected(true);
        proceso.cbx_tabla_pilon.setSelected(false);
        proceso.cbx_tabla_pilon.setDisable(true);
        proceso.btn_actualizar_proceso_pilon.setVisible(false);
        stage1.show();
        DBUtilities.CargarId( proceso.lbl_id_proceso_pilon,"SELECT * FROM tabla_procesos ORDER BY id_tabla_proceso DESC");


    }




    public void Editar_entradas_proceso(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/proceso_pilon.fxml"));

        int seleccion = jt_proceso_entrada_pilon.getSelectionModel().getSelectedIndex();
        StackPane root1;
        FXMLLoader ventana;
        ventana = new FXMLLoader(getClass().getResource(
                "/proceso_pilon.fxml"));
        root1 = ventana.load();
        scene.setRoot(root1);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Actualizar Registro de entradas y salidas de la tabla Proceso");
        stage.setScene(scene);

        Proceso_pilon proceso = ventana.getController();

        //proceso.txt_id_remision_pilon(String.valueOf(jt_clase_tabaco.getTreeItem(seleccion).getValue().getId_tabaco()));
        //proceso.cbx_tabla_proceso.get
        proceso.lbl_id_proceso_pilon.setText(jt_proceso_entrada_pilon.getTreeItem(seleccion).getValue().getId_en_sa_proceso_pilon());
        proceso.date_fecha_proceso.getValue();
        proceso.txt_id_remision_pilon.setText(jt_proceso_entrada_pilon.getTreeItem(seleccion).getValue().getRemision_en_sa_proceso_pilon());
        proceso.txt_entradas_salidas.setText(jt_proceso_entrada_pilon.getTreeItem(seleccion).getValue().getEn_sa_proceso_pilon());
        //proceso.cbb_nombre_tabaco.getItems().add(jt_proceso_entrada_pilon.getTreeItem(seleccion).getValue());

        proceso.btn_guardar_proceso_pilon.setVisible(false);
        stage.showAndWait();
    }

    public void Agregr_salidas_pilon(ActionEvent actionEvent) throws IOException {

        StackPane root1;
        FXMLLoader ventana;
        ventana = new FXMLLoader(getClass().getResource("/proceso_pilon.fxml"));

        root1 = ventana.load();
        scene.setRoot(root1);
        Stage stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.setTitle("Registro de entradas y salidas de la tabla Proceso");
        stage1.setScene(scene);

        Proceso_pilon proceso = ventana.getController();
        proceso.cbx_tabla_proceso.setDisable(true);
        proceso.cbx_tabla_proceso.setSelected(false);
        proceso.cbx_tabla_pilon.setSelected(true);
        proceso.cbx_tabla_pilon.setDisable(true);
        proceso.btn_actualizar_proceso_pilon.setVisible(false);

        stage1.show();
        DBUtilities.CargarId(proceso.lbl_id_proceso_pilon,"SELECT * FROM tabla_pilon ORDER BY id_tabla_pilon DESC");

    }

    public void Editar_salidas_pilon(ActionEvent actionEvent) {
    }
}

