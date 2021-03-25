package ventanas;

import DBUtilitie.ActualizarTablas;
import DBUtilitie.DBType;
import DBUtilitie.DBUtilities;
import DBUtilitie.modificaciones;
import Objetos_POJO.*;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
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

    //TODO Varibles de la tabla remision

    @FXML
    public JFXTreeTableView<Clase_remisiones> jt_remisiones;
    @FXML
    public JFXButton btn_nueva_remision;
    @FXML
    public JFXButton btn_editar_remision;
    @FXML
    public JFXButton btn_imprimir_remision;
    @FXML
    public JFXCheckBox chck_busqueda_mes;
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

    public String[] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};

    //TODO procesos

    @FXML
    public JFXTreeTableView<Clase_en_sa_proceso_pilon> jt_proceso_entrada_pilon;
    @FXML
    public JFXButton btn_nuevo_entrada_pilon;
    @FXML
    public JFXButton btn_editar_entrada_pilon;
    @FXML
    public JFXTreeTableView<Clase_en_sa_proceso_pilon> jt_proceso_salidas_pilon;
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
    @FXML
    public JFXTreeTableView<Clase_entradas_pilones> jt_entradas_pilones;
    @FXML
    public JFXButton btn_editar_entrada_pilones;
    @FXML
    public JFXButton btn_nueva_entrada_pilones;
    @FXML
    public JFXTreeTableView<Clase_control_pilones> jt_control_pilones;
    @FXML
    public JFXButton btn_editar_control_pilones;
    @FXML
    public JFXButton btn_nueva_control_pilones;
    public DBUtilities db = new DBUtilities(DBType.MARIADB);
    public TextField jtxt_buscar_entradas_pilon;
    public TextField jfx_buscar_proceso_entrad_pilon;
    public TextField jtxt_buscar_salidas_pilon;
    public TextField jtx_buscar_control_pilon;
    public TextField jtx_buscar_tab_princ;
    public TextField jtx_buscar_pilon;


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
        carga_lista_de_faltaltes_de_revision(lis_pilones_pendientes);
        tabla_clase_tabaco(jt_clase_tabaco, btn_editar_pilon_tabla, btn_editar_tabaco_tabla);
        tabla_pilones(jt_pilones, btn_editar_pilon_tabla, btn_editar_tabaco_tabla);
        tabla_remisiones(jt_remisiones, btn_editar_remision);
        tabla_Control_temp(jt_control_temp, jt_pilon_control_temp, btn_nuevo_control_temp, btn_eliminar_control_temp, anchor_botones_meses, btn_grafica_actual_temperatura);
        tabla_en_y_sa_proceso(jt_proceso_entrada_pilon, btn_editar_entrada_pilon, btn_editar_salidas_pilon);
        tabla_en_sa_pilon(jt_proceso_salidas_pilon, btn_editar_entrada_pilon, btn_editar_salidas_pilon);
        tabla_entradas_pilones(jt_entradas_pilones, btn_editar_entrada_pilones, btn_nueva_entrada_pilones);
        tabla_control_pilones(jt_control_pilones, btn_editar_control_pilones, btn_nueva_control_pilones);

        chck_busqueda_anio.setOnAction(event -> {
            cbx_anio.setDisable(chck_busqueda_anio.isSelected());
            cbx_mes.setDisable(chck_busqueda_anio.isSelected());
            chck_busqueda_mes.setSelected(chck_busqueda_anio.isSelected());
        });

        chck_busqueda_mes.setOnAction(event -> { cbx_mes.setDisable(chck_busqueda_mes.isSelected());});

        for (String e : meses){ cbx_mes.getItems().add(e); }
        for (int i = 0; i < 4; i++) {
            cbx_anio_fecha_temperatura.getItems().add(new Date().getYear() + 1900 - i);
            cbx_anio.getItems().add(new Date().getYear()+1900-i);
        }

        cbx_anio_fecha_temperatura.getSelectionModel().select(0);
        cbx_anio.getSelectionModel().select(0);
        cbx_mes.getSelectionModel().select(new Date().getMonth());


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
    public JFXListView lista_temperatura() {
        return lis_pilones_pendientes;
    }

    @Override
    public JFXTreeTableView<Clase_pilones_nombre> traer_jt_pilones() {

        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(true);
        btn_editar_pilon_tabla.setVisible(false);
        btn_nuevo_tabaco_tabla.setVisible(true);
        btn_editar_tabaco_tabla.setVisible(false);
        jtx_buscar_tab_princ.setVisible(true);
        jtx_buscar_pilon.setVisible(true);

        //TODO botones de registro Remisones
        btn_editar_remision.setVisible(false);
        btn_nueva_remision.setVisible(false);
        btn_imprimir_remision.setVisible(false);
        btn_imprimir_remisiones.setVisible(false);
        cbx_anio.setVisible(false);
        cbx_mes.setVisible(false);
        chck_busqueda_anio.setVisible(false);
        chck_busqueda_mes.setVisible(false);
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
        jfx_buscar_proceso_entrad_pilon.setVisible(false);
        jtxt_buscar_salidas_pilon.setVisible(false);

        //TODO botones entradas de pilones
        btn_editar_entrada_pilones.setVisible(false);
        btn_nueva_entrada_pilones.setVisible(false);
        jtxt_buscar_entradas_pilon.setVisible(false);

        //TODO botones Control de pilones
        btn_editar_control_pilones.setVisible(false);
        btn_nueva_control_pilones.setVisible(false);
        jtx_buscar_control_pilon.setVisible(false);

        return jt_pilones;
    }

    @Override
    public JFXTreeTableView<Clase_tabacos> traer_jt_clase_tabaco() {

        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(true);
        btn_editar_pilon_tabla.setVisible(false);
        btn_nuevo_tabaco_tabla.setVisible(true);
        btn_editar_tabaco_tabla.setVisible(false);
        jtx_buscar_tab_princ.setVisible(true);
        jtx_buscar_pilon.setVisible(true);

        //TODO botones de registro Remisones
        btn_editar_remision.setVisible(false);
        btn_nueva_remision.setVisible(false);
        btn_imprimir_remision.setVisible(false);
        btn_imprimir_remisiones.setVisible(false);
        cbx_anio.setVisible(false);
        cbx_mes.setVisible(false);
        chck_busqueda_anio.setVisible(false);
        chck_busqueda_mes.setVisible(false);
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
        jfx_buscar_proceso_entrad_pilon.setVisible(false);
        jtxt_buscar_salidas_pilon.setVisible(false);

        //TODO botones entradas de pilones
        btn_editar_entrada_pilones.setVisible(false);
        btn_nueva_entrada_pilones.setVisible(false);
        jtxt_buscar_entradas_pilon.setVisible(false);

        // TODO botones Control de pilones
        btn_editar_control_pilones.setVisible(false);
        btn_nueva_control_pilones.setVisible(false);
        jtx_buscar_control_pilon.setVisible(false);

        return jt_clase_tabaco;
    }

    @Override
    public JFXTreeTableView<Clase_remisiones> traer_jt_remisiones() {
        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(false);
        btn_editar_pilon_tabla.setVisible(false);
        btn_nuevo_tabaco_tabla.setVisible(false);
        btn_editar_tabaco_tabla.setVisible(false);
        jtx_buscar_tab_princ.setVisible(false);
        jtx_buscar_pilon.setVisible(false);

        //TODO botones de registro Remisones
        btn_editar_remision.setVisible(false);
        btn_nueva_remision.setVisible(true);
        btn_imprimir_remision.setVisible(false);
        btn_imprimir_remisiones.setVisible(true);
        cbx_anio.setVisible(true);
        cbx_mes.setVisible(true);
        chck_busqueda_anio.setVisible(true);
        chck_busqueda_mes.setVisible(true);
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
        jfx_buscar_proceso_entrad_pilon.setVisible(false);
        jtxt_buscar_salidas_pilon.setVisible(false);

        //TODO botones entradas de pilones
        btn_editar_entrada_pilones.setVisible(false);
        btn_nueva_entrada_pilones.setVisible(false);
        jtxt_buscar_entradas_pilon.setVisible(false);

        //TODO botones Control de pilones
        btn_editar_control_pilones.setVisible(false);
        btn_nueva_control_pilones.setVisible(false);
        jtx_buscar_control_pilon.setVisible(false);

        return jt_remisiones;
    }

    @Override
    public JFXTreeTableView<Clase_control_temperatura> traer_jt_control_temp() {
        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(false);
        btn_editar_pilon_tabla.setVisible(false);
        btn_nuevo_tabaco_tabla.setVisible(false);
        btn_editar_tabaco_tabla.setVisible(false);
        jtx_buscar_tab_princ.setVisible(false);
        jtx_buscar_pilon.setVisible(false);

        //TODO botones de registro Remisones
        btn_editar_remision.setVisible(false);
        btn_nueva_remision.setVisible(false);
        btn_imprimir_remision.setVisible(false);
        btn_imprimir_remisiones.setVisible(false);
        cbx_anio.setVisible(false);
        cbx_mes.setVisible(false);
        chck_busqueda_anio.setVisible(false);
        chck_busqueda_mes.setVisible(false);
        txt_busqueda_remision.setVisible(false);

        //TODO botones control de temperatura
        btn_nuevo_control_temp.setVisible(true);
        btn_eliminar_control_temp.setVisible(true);
        anchor_botones_meses.setVisible(false);
        anchor_resumen_temperatura.setVisible(false);
        btn_grafica_actual_temperatura.setVisible(true);

        //TODO botones entradas de pilones
        btn_editar_entrada_pilones.setVisible(false);
        btn_nueva_entrada_pilones.setVisible(false);
        jtxt_buscar_entradas_pilon.setVisible(false);

        //TODO botones entradas y salidas proceso
        btn_nuevo_entrada_pilon.setVisible(false);
        btn_editar_entrada_pilon.setVisible(false);
        btn_nuevo_salidas_pilon.setVisible(false);
        btn_editar_salidas_pilon.setVisible(false);
        jfx_buscar_proceso_entrad_pilon.setVisible(false);
        jtxt_buscar_salidas_pilon.setVisible(false);


        //TODO botones Control de pilones
        btn_editar_control_pilones.setVisible(false);
        btn_nueva_control_pilones.setVisible(false);
        jtx_buscar_control_pilon.setVisible(false);

        return jt_control_temp;
    }

    @Override
    public JFXTreeTableView<Clase_pilones_nombre> traer_jt_pilon_control_temp() {

        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(false);
        btn_editar_pilon_tabla.setVisible(false);
        btn_nuevo_tabaco_tabla.setVisible(false);
        btn_editar_tabaco_tabla.setVisible(false);
        jtx_buscar_tab_princ.setVisible(false);
        jtx_buscar_pilon.setVisible(false);

        //TODO botones de registro Remisones
        btn_editar_remision.setVisible(false);
        btn_nueva_remision.setVisible(false);
        btn_imprimir_remision.setVisible(false);
        btn_imprimir_remisiones.setVisible(false);
        cbx_anio.setVisible(false);
        cbx_mes.setVisible(false);
        chck_busqueda_anio.setVisible(false);
        chck_busqueda_mes.setVisible(false);
        txt_busqueda_remision.setVisible(false);

        //TODO botones control de temperatura
        btn_eliminar_control_temp.setVisible(false);
        btn_nuevo_control_temp.setVisible(true);
        anchor_botones_meses.setVisible(false);
        anchor_resumen_temperatura.setVisible(true);
        btn_grafica_actual_temperatura.setVisible(false);

        //TODO botones entradas de pilones
        btn_editar_entrada_pilones.setVisible(false);
        btn_nueva_entrada_pilones.setVisible(false);
        jtxt_buscar_entradas_pilon.setVisible(false);

        //TODO botones entradas y salidas proceso
        btn_nuevo_entrada_pilon.setVisible(false);
        btn_editar_entrada_pilon.setVisible(false);
        btn_nuevo_salidas_pilon.setVisible(false);
        btn_editar_salidas_pilon.setVisible(false);
        jfx_buscar_proceso_entrad_pilon.setVisible(false);
        jtxt_buscar_salidas_pilon.setVisible(false);


        //TODO botones Control de pilones
        btn_editar_control_pilones.setVisible(false);
        btn_nueva_control_pilones.setVisible(false);
        jtx_buscar_control_pilon.setVisible(false);

        return jt_pilon_control_temp;
    }

    @Override
    public JFXTreeTableView<Clase_en_sa_proceso_pilon> traer_jt_en_sa_proceso_pilon() {
        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(false);
        btn_editar_pilon_tabla.setVisible(false);
        btn_nuevo_tabaco_tabla.setVisible(false);
        btn_editar_tabaco_tabla.setVisible(false);
        jtx_buscar_tab_princ.setVisible(false);
        jtx_buscar_pilon.setVisible(false);

        //TODO botones de registro Remisones
        btn_editar_remision.setVisible(false);
        btn_nueva_remision.setVisible(false);
        btn_imprimir_remision.setVisible(false);
        btn_imprimir_remisiones.setVisible(false);
        cbx_anio.setVisible(false);
        cbx_mes.setVisible(false);
        chck_busqueda_anio.setVisible(false);
        chck_busqueda_mes.setVisible(false);
        txt_busqueda_remision.setVisible(false);

        //TODO botones control de temperatura
        btn_eliminar_control_temp.setVisible(false);
        btn_nuevo_control_temp.setVisible(false);

        //TODO botones entradas de pilones
        btn_editar_entrada_pilones.setVisible(false);
        btn_nueva_entrada_pilones.setVisible(false);
        jtxt_buscar_entradas_pilon.setVisible(false);

        //TODO botones entradas y salidas proceso
        btn_nuevo_entrada_pilon.setVisible(true);
        btn_editar_entrada_pilon.setVisible(false);
        btn_nuevo_salidas_pilon.setVisible(true);
        btn_editar_salidas_pilon.setVisible(false);
        jfx_buscar_proceso_entrad_pilon.setVisible(true);
        jtxt_buscar_salidas_pilon.setVisible(true);


        //TODO botones Control de pilones
        btn_editar_control_pilones.setVisible(false);
        btn_nueva_control_pilones.setVisible(false);
        jtx_buscar_control_pilon.setVisible(false);

        return jt_proceso_entrada_pilon;
    }


    @Override
    public JFXTreeTableView<Clase_en_sa_proceso_pilon> traer_jt_en_sa_pilon() {
        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(false);
        btn_editar_pilon_tabla.setVisible(false);
        btn_nuevo_tabaco_tabla.setVisible(false);
        btn_editar_tabaco_tabla.setVisible(false);
        jtx_buscar_tab_princ.setVisible(false);
        jtx_buscar_pilon.setVisible(false);

        //TODO botones de registro Remisones
        btn_editar_remision.setVisible(false);
        btn_nueva_remision.setVisible(false);
        btn_imprimir_remision.setVisible(false);
        btn_imprimir_remisiones.setVisible(false);
        cbx_anio.setVisible(false);
        cbx_mes.setVisible(false);
        chck_busqueda_anio.setVisible(false);
        chck_busqueda_mes.setVisible(false);
        txt_busqueda_remision.setVisible(false);

        //TODO botones control de temperatura
        btn_eliminar_control_temp.setVisible(false);
        btn_nuevo_control_temp.setVisible(false);

        //TODO botones entradas de pilones
        btn_editar_entrada_pilones.setVisible(false);
        btn_nueva_entrada_pilones.setVisible(false);
        jtxt_buscar_entradas_pilon.setVisible(false);

        //TODO botones entradas y salidas proceso
        btn_nuevo_entrada_pilon.setVisible(true);
        btn_editar_entrada_pilon.setVisible(false);
        btn_nuevo_salidas_pilon.setVisible(true);
        btn_editar_salidas_pilon.setVisible(false);
        jfx_buscar_proceso_entrad_pilon.setVisible(true);
        jtxt_buscar_salidas_pilon.setVisible(true);

        //TODO botones Control de pilones
        btn_editar_control_pilones.setVisible(false);
        btn_nueva_control_pilones.setVisible(false);
        jtx_buscar_control_pilon.setVisible(false);


        return jt_proceso_salidas_pilon;
    }

    @Override
    public JFXTreeTableView<Clase_entradas_pilones> traer_jt_entra_pilones() {
        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(false);
        btn_editar_pilon_tabla.setVisible(false);
        btn_nuevo_tabaco_tabla.setVisible(false);
        btn_editar_tabaco_tabla.setVisible(false);
        jtx_buscar_tab_princ.setVisible(false);
        jtx_buscar_pilon.setVisible(false);

        //TODO botones de registro Remisones
        btn_editar_remision.setVisible(false);
        btn_nueva_remision.setVisible(false);
        btn_imprimir_remision.setVisible(false);
        btn_imprimir_remisiones.setVisible(false);
        cbx_anio.setVisible(false);
        cbx_mes.setVisible(false);
        chck_busqueda_anio.setVisible(false);
        chck_busqueda_mes.setVisible(false);
        txt_busqueda_remision.setVisible(false);

        //TODO botones control de temperatura
        btn_eliminar_control_temp.setVisible(false);
        btn_nuevo_control_temp.setVisible(false);

        //TODO botones entradas y salidas proceso
        btn_nuevo_entrada_pilon.setVisible(false);
        btn_editar_entrada_pilon.setVisible(false);
        btn_nuevo_salidas_pilon.setVisible(true);
        btn_editar_salidas_pilon.setVisible(false);
        jfx_buscar_proceso_entrad_pilon.setVisible(false);
        jtxt_buscar_salidas_pilon.setVisible(false);

        //TODO botones entradas de pilones
        btn_editar_entrada_pilones.setVisible(false);
        btn_nueva_entrada_pilones.setVisible(true);
        jtxt_buscar_entradas_pilon.setVisible(true);

        //TODO botones Control de pilones
        btn_editar_control_pilones.setVisible(false);
        btn_nueva_control_pilones.setVisible(false);
        jtx_buscar_control_pilon.setVisible(false);


        return jt_entradas_pilones;
    }

    @Override
    public JFXTreeTableView<Clase_control_pilones> traer_jt_control_pilones() {
        //TODO botones de registro tabaco y pilones
        btn_nuevo_pilon_tabla.setVisible(false);
        btn_editar_pilon_tabla.setVisible(false);
        btn_nuevo_tabaco_tabla.setVisible(false);
        btn_editar_tabaco_tabla.setVisible(false);
        jtx_buscar_tab_princ.setVisible(false);
        jtx_buscar_pilon.setVisible(false);

        //TODO botones de registro Remisones
        btn_editar_remision.setVisible(false);
        btn_nueva_remision.setVisible(false);
        btn_imprimir_remision.setVisible(false);
        btn_imprimir_remisiones.setVisible(false);
        cbx_anio.setVisible(false);
        cbx_mes.setVisible(false);
        chck_busqueda_anio.setVisible(false);
        chck_busqueda_mes.setVisible(false);
        txt_busqueda_remision.setVisible(false);

        //TODO botones control de temperatura
        btn_eliminar_control_temp.setVisible(false);
        btn_nuevo_control_temp.setVisible(false);

        //TODO botones entradas y salidas proceso
        btn_nuevo_entrada_pilon.setVisible(false);
        btn_editar_entrada_pilon.setVisible(false);
        btn_nuevo_salidas_pilon.setVisible(true);
        btn_editar_salidas_pilon.setVisible(false);
        jfx_buscar_proceso_entrad_pilon.setVisible(false);
        jtxt_buscar_salidas_pilon.setVisible(false);

        //TODO botones entradas de pilones
        btn_editar_entrada_pilones.setVisible(false);
        btn_nueva_entrada_pilones.setVisible(false);
        jtxt_buscar_entradas_pilon.setVisible(false);

        //TODO botones Control de pilones
        btn_editar_control_pilones.setVisible(false);
        btn_nueva_control_pilones.setVisible(true);
        jtx_buscar_control_pilon.setVisible(true);



        return jt_control_pilones;
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
        DBUtilities.CargarId(pilon.lbl_id_pilon_mostra, "SELECT * FROM pilones ORDER BY id_pilon DESC");
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
        if (seleccion >= 0) {
            clase.lbl_id_pilon_mostra.setText(String.valueOf(jt_pilones.getTreeItem(seleccion).getValue().getId_pilon()));

            clase.txt_nombre_pilon.setText(String.valueOf(jt_pilones.getTreeItem(seleccion).getValue().getNombre_pilon()));
            clase.btn_guardar_pilon.setVisible(false);

            stage.showAndWait();
        } else {
            mensaje("Alerta", "Fila no seleccionada"
                    , stackpane);
            btn_mensaje.setOnAction(event -> {
                dialogo.close();
                try {

                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }

            });
        }


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
        DBUtilities.CargarId(tabaco.lbl_id_tabaco, "SELECT * FROM clase_tabaco ORDER BY id_tabaco DESC");

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
        if (seleccion >= 0) {
            clase.lbl_id_tabaco.setText(String.valueOf(jt_clase_tabaco.getTreeItem(seleccion).getValue().getId_tabaco()));
            clase.txt_nombre_tabaco.setText(jt_clase_tabaco.getTreeItem(seleccion).getValue().getNombre_tbc());
            clase.btn_guardar_clase_tabaco.setVisible(false);
            stage.showAndWait();
        } else {
            mensaje("Alerta", "Fila no seleccionada"
                    , stackpane);
            btn_mensaje.setOnAction(event -> {
                dialogo.close();
                try {

                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }

            });
        }


    }

    // Boton de agregar control de la temperatura

    public void agregar_control_temp() throws Exception {
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
            consulta.setString(1, dato.getId_control_temp());
            ResultSet respuesta = consulta.executeQuery();

            //Cargar la tabla
            SidePanelController.datos_tabla_registro_temperatura();

            String mensaje[] = new String[2];
            while (respuesta.next()) {
                mensaje[0] = respuesta.getString(1);
                mensaje[1] = respuesta.getString(2);

            }
            btn_mensaje.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialogo.close();
                }
            });
            mensaje("Mensaje", mensaje[0], stackpane);
        } catch (Exception e) {

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
        DBUtilities.CargarId(remision.lbl_id_remision, "SELECT * FROM remision_proceso ORDER BY id_remision_proceso DESC");
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
        if (seleccion >= 0) {
            //clase.lbl_id_tabaco.setText(String.valueOf(jt_clase_tabaco.getTreeItem(seleccion).getValue().getId_tabaco()));
            //clase.txt_nombre_tabaco.setText(jt_clase_tabaco.getTreeItem(seleccion).getValue().getNombre_tbc());

            stage.showAndWait();
        } else {
            mensaje("Alerta", "Fila no seleccionada"
                    , stackpane);
            btn_mensaje.setOnAction(event -> {
                dialogo.close();
                try {

                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }

            });
        }

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
        DBUtilities.CargarId(proceso.lbl_id_proceso_pilon, "SELECT * FROM tabla_procesos ORDER BY id_tabla_proceso DESC");


    }


    public void Editar_entradas_proceso(ActionEvent actionEvent) throws IOException, ParseException {
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

        if (seleccion >= 0) {
            proceso.lbl_id_proceso_pilon.setText(jt_proceso_entrada_pilon.getTreeItem(seleccion).getValue().getId_en_sa_proceso_pilon());
            proceso.cbx_tabla_proceso.setSelected(true);
            proceso.date_fecha_proceso.setValue(new SimpleDateFormat("yyyy-MM-dd").parse(jt_proceso_entrada_pilon.getTreeItem(seleccion).getValue().
                    getFecha_en_sa_proceso_pilon()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            proceso.txt_id_remision_pilon.setText(jt_proceso_entrada_pilon.getTreeItem(seleccion).getValue().getRemision_en_sa_proceso_pilon());
            proceso.txt_entradas_salidas.setText(jt_proceso_entrada_pilon.getTreeItem(seleccion).getValue().getEn_sa_proceso_pilon());
            proceso.txt_nombre_tabaco.setText(String.valueOf(jt_proceso_entrada_pilon.getTreeItem(seleccion).getValue().getNombre_tab_en_sa_proceso_pilon()));
            proceso.txt_numero_pilon.setText(String.valueOf(jt_proceso_entrada_pilon.getTreeItem(seleccion).getValue().getNum_en_sa_proceso_pilon()));
            proceso.txt_subtotal.setText(String.valueOf(jt_proceso_entrada_pilon.getTreeItem(seleccion).getValue().getSubtotal_en_sa_proceso_pilon()));
            proceso.txt_total_libras.setText(String.valueOf(jt_proceso_entrada_pilon.getTreeItem(seleccion).getValue().getTotal_lbs_en_sa_proceso_pilon()));
            proceso.txt_total_remision.setText(String.valueOf(jt_proceso_entrada_pilon.getTreeItem(seleccion).getValue().getTotal_remision_en_sa_proceso_pilon()));

            proceso.btn_guardar_proceso_pilon.setVisible(false);
            proceso.cbx_tabla_proceso.setDisable(true);
            proceso.cbx_tabla_pilon.setSelected(false);
            proceso.cbx_tabla_pilon.setDisable(true);
            stage.showAndWait();

        } else {
            mensaje("Alerta", "Fila no seleccionada"
                    , stackpane);
            btn_mensaje.setOnAction(event -> {
                dialogo.close();
                try {

                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }

            });
        }
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
        DBUtilities.CargarId(proceso.lbl_id_proceso_pilon, "SELECT * FROM tabla_pilon ORDER BY id_tabla_pilon DESC");

    }

    public void Editar_salidas_pilon(ActionEvent actionEvent) throws IOException, ParseException {
        Parent root = FXMLLoader.load(getClass().getResource("/proceso_pilon.fxml"));

        int seleccion = jt_proceso_salidas_pilon.getSelectionModel().getSelectedIndex();
        StackPane root1;
        FXMLLoader ventana;
        ventana = new FXMLLoader(getClass().getResource(
                "/proceso_pilon.fxml"));
        root1 = ventana.load();
        scene.setRoot(root1);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Actualizar Registro de entradas y salidas de la tabla Pil\u00f3n");
        stage.setScene(scene);

        Proceso_pilon proceso = ventana.getController();

        if (seleccion >= 0) {
            proceso.lbl_id_proceso_pilon.setText(jt_proceso_salidas_pilon.getTreeItem(seleccion).getValue().getId_en_sa_proceso_pilon());
            proceso.cbx_tabla_proceso.setSelected(false);
            proceso.date_fecha_proceso.setValue(new SimpleDateFormat("yyyy-MM-dd").parse(jt_proceso_salidas_pilon.getTreeItem(seleccion).getValue().
                    getFecha_en_sa_proceso_pilon()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            proceso.txt_id_remision_pilon.setText(jt_proceso_salidas_pilon.getTreeItem(seleccion).getValue().getRemision_en_sa_proceso_pilon());
            proceso.txt_entradas_salidas.setText(jt_proceso_salidas_pilon.getTreeItem(seleccion).getValue().getEn_sa_proceso_pilon());
            proceso.txt_nombre_tabaco.setText(String.valueOf(jt_proceso_salidas_pilon.getTreeItem(seleccion).getValue().getNombre_tab_en_sa_proceso_pilon()));
            proceso.txt_numero_pilon.setText(String.valueOf(jt_proceso_salidas_pilon.getTreeItem(seleccion).getValue().getNum_en_sa_proceso_pilon()));
            proceso.txt_subtotal.setText(String.valueOf(jt_proceso_salidas_pilon.getTreeItem(seleccion).getValue().getSubtotal_en_sa_proceso_pilon()));
            proceso.txt_total_libras.setText(String.valueOf(jt_proceso_salidas_pilon.getTreeItem(seleccion).getValue().getTotal_lbs_en_sa_proceso_pilon()));
            proceso.txt_total_remision.setText(String.valueOf(jt_proceso_salidas_pilon.getTreeItem(seleccion).getValue().getTotal_remision_en_sa_proceso_pilon()));

            proceso.btn_guardar_proceso_pilon.setVisible(false);
            proceso.cbx_tabla_proceso.setDisable(true);
            proceso.cbx_tabla_pilon.setSelected(true);
            proceso.cbx_tabla_pilon.setDisable(true);
            stage.showAndWait();

        } else {
            mensaje("Alerta", "Fila no seleccionada"
                    , stackpane);
            btn_mensaje.setOnAction(event -> {
                dialogo.close();
                try {

                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }

            });
        }
    }

    public void grafica_actual(ActionEvent actionEvent) throws IOException {
        StackPane root;
        FXMLLoader ventana = new FXMLLoader(getClass().getResource("/grafico.fxml"));
        root = ventana.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Grafico");

        Grafico grafico = ventana.getController();
        grafico.lbl_numero_pilon.setText(jt_pilon_control_temp.getSelectionModel().getSelectedItem().getValue().getNombre_pilon());

        grafico.datos_grafica(new Date());

        stage.show();

    }

    public void registros_anteriores(ActionEvent actionEvent) throws IOException, ParseException {
        int mes = 0;
        switch (((JFXButton) actionEvent.getSource()).getText()) {
            case "Enero":
                mes = 1;
                break;
            case "Febrero":
                mes = 2;
                break;
            case "Marzo":
                mes = 3;
                break;
            case "Abril":
                mes = 4;
                break;
            case "Mayo":
                mes = 5;
                break;
            case "Junio":
                mes = 6;
                break;
            case "Julio":
                mes = 7;
                break;
            case "Agosto":
                mes = 8;
                break;
            case "Septiembre":
                mes = 9;
                break;
            case "Octubre":
                mes = 10;
                break;
            case "Noviembre":
                mes = 11;
                break;
            case "Diciembre":
                mes = 12;
                break;
        }


        StackPane root;
        FXMLLoader ventana = new FXMLLoader(getClass().getResource("/grafico.fxml"));
        root = ventana.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Grafico");

        Grafico grafico = ventana.getController();
        grafico.lbl_numero_pilon.setText(jt_pilon_control_temp.getSelectionModel().getSelectedItem().getValue().getNombre_pilon());

        grafico.datos_grafica(new SimpleDateFormat("yyyy-MM-dd").parse(cbx_anio_fecha_temperatura.getSelectionModel().getSelectedItem().toString() + "-" + mes + "-1"));
        System.out.println(cbx_anio_fecha_temperatura.getSelectionModel().getSelectedItem().toString() + "-" + mes + "-1");
        stage.show();
    }

    public void nueva_entrada_pilon(ActionEvent actionEvent) throws IOException {
        StackPane root;
        // Parent root = FXMLLoader.load(getClass().getResource("/roceso_remision.fxml"));
        FXMLLoader ventana = new FXMLLoader(getClass().getResource("/tabla_entrada_pilones.fxml"));
        root = ventana.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Nueva Entrada de pil\u00f3n");
        stage.show();
        tabla_entrada_pilones entra_pilones = ventana.getController();
        DBUtilities.CargarId(entra_pilones.lbl_id_entrada_pilon, "SELECT * FROM entrada_pilones ORDER BY id_entrada_pilones DESC");
        entra_pilones.btn_actualizar_tabla_entrada.setVisible(false);
    }

    public void editar_entrada_pilon(ActionEvent actionEvent) throws IOException, ParseException {
        int seleccion = jt_entradas_pilones.getSelectionModel().getSelectedIndex();
        StackPane root1;
        FXMLLoader ventana;
        ventana = new FXMLLoader(getClass().getResource(
                "/tabla_entrada_pilones.fxml"));
        root1 = ventana.load();
        scene.setRoot(root1);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Actualizar entrada de Pil\u00f3n");
        stage.setScene(scene);

        tabla_entrada_pilones tabla_entra_pilones = ventana.getController();
        if (seleccion >= 0) {
            tabla_entra_pilones.lbl_id_entrada_pilon.setText(String.valueOf(jt_entradas_pilones.getTreeItem(seleccion).getValue().getId_entrada_pilones()));
            tabla_entra_pilones.txt_id_tabaco.setText(String.valueOf(jt_entradas_pilones.getTreeItem(seleccion).getValue().getNombre_tabaco_entradas_pilones()));
            tabla_entra_pilones.txt_numero_pilon_entrada.setText(String.valueOf(jt_entradas_pilones.getTreeItem(seleccion).getValue().getNumero_pilon_entradas_pilones()));
            tabla_entra_pilones.date_fecha_entrada.setValue(new SimpleDateFormat("yyyy-MM-dd").parse(jt_entradas_pilones.getTreeItem(seleccion).getValue().
                    getFecha_entradas_pilones()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            tabla_entra_pilones.txt_tiempo_adelato.setText(String.valueOf(jt_entradas_pilones.getTreeItem(seleccion).getValue().getTiempo_adelanto_entradas_pilones()));
            tabla_entra_pilones.date_fecha_estimada_salida.setValue(new SimpleDateFormat("yyyy-MM-dd").parse(jt_entradas_pilones.getTreeItem(seleccion).getValue().
                    getFecha_entradas_pilones()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            tabla_entra_pilones.txt_cantidad_libras.setText(String.valueOf(jt_entradas_pilones.getTreeItem(seleccion).getValue().getCantidad_libras_entradas_pilones()));
            tabla_entra_pilones.btn_guardar_tabla_entrada.setVisible(false);

            stage.showAndWait();
        } else {
            mensaje("Alerta", "Fila no seleccionada"
                    , stackpane);
            btn_mensaje.setOnAction(event -> {
                dialogo.close();
                try {

                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }

            });
        }

    }

    public void nueva_control_pilones(ActionEvent actionEvent) throws IOException {
        StackPane root;
        // Parent root = FXMLLoader.load(getClass().getResource("/proceso_remision.fxml"));
        FXMLLoader ventana = new FXMLLoader(getClass().getResource("/tabla_control_pilones.fxml"));
        root = ventana.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Nuevo Registro de Control de pil\u00f3n");
        stage.show();
        control_pilones control_pilones = ventana.getController();
        DBUtilities.CargarId(control_pilones.lbl_id_control_pilon, "SELECT * FROM control_pilones ORDER BY id_control_pilones DESC");
        control_pilones.btn_actualizar_control_pilones.setVisible(false);

    }


    public void editar_control_pilones(ActionEvent actionEvent) throws IOException, ParseException {

        int seleccion = jt_control_pilones.getSelectionModel().getSelectedIndex();
        StackPane root1;
        FXMLLoader ventana;
        ventana = new FXMLLoader(getClass().getResource(
                "/tabla_control_pilones.fxml"));
        root1 = ventana.load();
        scene.setRoot(root1);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Actualizar control de Pilones");
        stage.setScene(scene);

        control_pilones control_pilon = ventana.getController();
        if (seleccion >= 0) {
            control_pilon.lbl_id_control_pilon.setText(String.valueOf(jt_control_pilones.getTreeItem(seleccion).getValue().getId_control_pilon()));
            control_pilon.txt_clase_tabaco_control.setText(String.valueOf(jt_control_pilones.getTreeItem(seleccion).getValue().getClase_tabaco_control()));
            control_pilon.jtxt_variedad_tabaco.setText(String.valueOf(jt_control_pilones.getTreeItem(seleccion).getValue().getVariedad_tabaco()));
            control_pilon.jtxt_finca_pilon.setText(String.valueOf(jt_control_pilones.getTreeItem(seleccion).getValue().getFinca_pilon()));
            control_pilon.jdate_fecha_control.setValue(new SimpleDateFormat("yyyy-MM-dd").parse(jt_control_pilones.getTreeItem(seleccion).getValue().
                    getFecha_control()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            control_pilon.txt_numero_pilon_control.setText(String.valueOf(jt_control_pilones.getTreeItem(seleccion).getValue().getNumero_pilon_control()));
            control_pilon.jtxt_entrada_tabaco_pilon.setText(String.valueOf(jt_control_pilones.getTreeItem(seleccion).getValue().getEntrada_tabaco_pilon()));
            control_pilon.jtxt_salida_tabaco_pilon.setText(String.valueOf(jt_control_pilones.getTreeItem(seleccion).getValue().getSalida_tabaco_pilon()));
            control_pilon.jtxt_total_actual.setText(String.valueOf(jt_control_pilones.getTreeItem(seleccion).getValue().getTotal_actual()));
            control_pilon.jtxt_existencia_total.setText(String.valueOf(jt_control_pilones.getTreeItem(seleccion).getValue().getExistencia_total()));
            control_pilon.btn_guardar_control_pilones.setVisible(false);

            stage.showAndWait();
        } else {
            mensaje("Alerta", "Fila no seleccionada"
                    , stackpane);
            btn_mensaje.setOnAction(event -> {
                dialogo.close();
                try {

                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }

            });
        }

    }

    public void buscarTabPrinc(String valor) throws SQLException, ClassNotFoundException {


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

        jt_clase_tabaco.setRoot(root);
        jt_clase_tabaco.setShowRoot(false);




    }

    public void buscar_tab_princ(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        buscarTabPrinc(jtx_buscar_tab_princ.getText());
    }

    public void buscarPilonPrinc(String valor) throws SQLException, ClassNotFoundException {


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

        jt_pilones.setRoot(root);
        jt_pilones.setShowRoot(false);



    }

    public void buscar_pilon_princ(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        buscarPilonPrinc(jtx_buscar_pilon.getText());
    }



    void buscarEntrada(String valor) throws SQLException, ClassNotFoundException {


        PreparedStatement consulta_tabaco = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                " Call buscar_entradas_pilon(?)");

        consulta_tabaco.setString(1, valor);
        ResultSet resultSet_tabaco = consulta_tabaco.executeQuery();

        ObservableList<Clase_entradas_pilones> data_tabaco = FXCollections.observableArrayList();
        while (resultSet_tabaco.next()) {

            data_tabaco.add(new Clase_entradas_pilones(resultSet_tabaco.getString(1),
                    resultSet_tabaco.getString(2), resultSet_tabaco.getString(3),
                    resultSet_tabaco.getString(4), resultSet_tabaco.getString(5),
                    resultSet_tabaco.getString(6), resultSet_tabaco.getString(7)
            ));
        }
        TreeItem<Clase_entradas_pilones> root = new RecursiveTreeItem<>(data_tabaco, RecursiveTreeObject::getChildren);

        jt_entradas_pilones.setRoot(root);
        jt_entradas_pilones.setShowRoot(false);


    }

    public void buscar_entrada(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        buscarEntrada(jtxt_buscar_entradas_pilon.getText());
    }

     private void buscarEntradaProceso(String valor) throws SQLException, ClassNotFoundException {


        PreparedStatement consulta_entrada_proceso = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                " Call buscar_tabla_proceso(?)");

        consulta_entrada_proceso.setString(1, valor);
        ResultSet resultSet_entrada_proceso = consulta_entrada_proceso.executeQuery();

        ObservableList<Clase_en_sa_proceso_pilon> data_entrada_proceso = FXCollections.observableArrayList();
        while (resultSet_entrada_proceso.next()) {

            data_entrada_proceso.add(new Clase_en_sa_proceso_pilon(resultSet_entrada_proceso.getString(1),
                    resultSet_entrada_proceso.getString(2), resultSet_entrada_proceso.getString(3),
                    resultSet_entrada_proceso.getString(4), resultSet_entrada_proceso.getString(5),
                    resultSet_entrada_proceso.getString(6), resultSet_entrada_proceso.getString(7),
                    resultSet_entrada_proceso.getString(8), resultSet_entrada_proceso.getString(9)


            ));
        }
        TreeItem<Clase_en_sa_proceso_pilon> root = new RecursiveTreeItem<>(data_entrada_proceso, RecursiveTreeObject::getChildren);

        jt_proceso_entrada_pilon.setRoot(root);
        jt_proceso_entrada_pilon.setShowRoot(false);
    }


    public void buscar_entrada_proceso(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
       buscarEntradaProceso(jfx_buscar_proceso_entrad_pilon.getText());
    }

    void buscarSalidasPilon(String valor) throws SQLException, ClassNotFoundException {
        PreparedStatement consulta_salidas_proceso = DBUtilities.getConnection(DBType.MARIADB).prepareStatement(
                " Call buscar_tabla_pilon(?)");

        consulta_salidas_proceso.setString(1, valor);
        ResultSet resultSet_salidas_proceso = consulta_salidas_proceso.executeQuery();

        ObservableList<Clase_en_sa_proceso_pilon> data_salidas_proceso = FXCollections.observableArrayList();
        while (resultSet_salidas_proceso.next()) {

            data_salidas_proceso.add(new Clase_en_sa_proceso_pilon(resultSet_salidas_proceso.getString(1),
                    resultSet_salidas_proceso.getString(2), resultSet_salidas_proceso.getString(3),
                    resultSet_salidas_proceso.getString(4), resultSet_salidas_proceso.getString(5),
                    resultSet_salidas_proceso.getString(6), resultSet_salidas_proceso.getString(7),
                    resultSet_salidas_proceso.getString(8), resultSet_salidas_proceso.getString(9)


            ));
        }
        TreeItem<Clase_en_sa_proceso_pilon> root = new RecursiveTreeItem<>(data_salidas_proceso, RecursiveTreeObject::getChildren);

        jt_proceso_salidas_pilon.setRoot(root);
        jt_proceso_salidas_pilon.setShowRoot(false);
    }

    public void buscar_salidas_pilon(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        buscarSalidasPilon(jtxt_buscar_salidas_pilon.getText());

    }

    public void buscar_control_pilon(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        db.datos_tabla_control_pilones(jt_control_pilones,
                "Call buscar_control_pilones(?)",new Clase_control_pilones(),new String[]{});
    }


    public void busqueda_remisiones() throws SQLException, ClassNotFoundException {
        String fecha = "";
        if(chck_busqueda_anio.isSelected() && chck_busqueda_mes.isSelected()){
            fecha = "";
        }else if (!chck_busqueda_anio.isSelected()){
            fecha = cbx_anio.getSelectionModel().getSelectedItem().toString()+"-"+(cbx_mes.getSelectionModel().getSelectedIndex()+1)+"-01";
        }
        System.out.println(fecha);

        db.datos_tabla_control_pilones(jt_remisiones,
                "Call buscar_remisiones(?,?)",new Clase_remisiones(),new String[]{txt_busqueda_remision.getText(),fecha});
    }
}

