package clases;

import clases.DBUtilities.ActualizarTablas;
import clases.DBUtilities.modificaciones;
import clases.Objetos_POJO.Clase_pilones;
import clases.Objetos_POJO.Clase_pilones_nombre;
import clases.Objetos_POJO.Clase_tabacos;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class pantalla_principal extends Aplicacion_principal implements Initializable, modificaciones, ActualizarTablas {


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
                if(jt_clase_tabaco.getSelectionModel().getSelectedIndex() > 1){
                    btn_editar_pilon_tabla.setVisible(false);
                    btn_editar_tabaco_tabla.setVisible(true);
                }
            }
        });
    }
    private void tabla_pilones() {

        JFXTreeTableColumn<Clase_pilones_nombre,String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_pilones_nombre,String> _2 = new JFXTreeTableColumn<>("Numero de Pilon");


        _1.setPrefWidth(50);
        _2.setPrefWidth(229);

        jt_pilones.getColumns().addAll(_1,_2);

        _1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_pilones_nombre,String>("id_pilon")
        );

        _2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_pilones_nombre,String>("nombre_pilon")
        );

        jt_pilones.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(jt_pilones.getSelectionModel().getSelectedIndex() > 1){
                    btn_editar_pilon_tabla.setVisible(true);
                    btn_editar_tabaco_tabla.setVisible(false);
                }
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

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @Override
    public void cambiar_titulo(String titulo_main, int id_tabla) {

    }

    @Override
    public JFXTreeTableView<Clase_pilones_nombre> traer_jt_pilones() {
        btn_nuevo_pilon_tabla.setVisible(true);
        btn_nuevo_tabaco_tabla.setVisible(true);
        return jt_pilones;
    }

    @Override
    public JFXTreeTableView<Clase_tabacos> traer_jt_clase_tabaco() {
        btn_nuevo_pilon_tabla.setVisible(true);
        btn_nuevo_tabaco_tabla.setVisible(true);
        return jt_clase_tabaco;
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
        Parent root = FXMLLoader.load(getClass().getResource("/resources/pantalla_principal.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Nuevo Pilon");
        stage.show();
    }

    public void editar_pilon(ActionEvent actionEvent) {



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
}
