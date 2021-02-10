package clases;

import clases.DBUtilities.ActualizarTablas;
import clases.DBUtilities.modificaciones;
import clases.Objetos_POJO.Clase_pilones;
import clases.Objetos_POJO.Clase_tabacos;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class pantalla_principal extends Aplicacion_principal implements Initializable, modificaciones, ActualizarTablas {



    public StackPane stackpane;
    public JFXHamburger boton_menu;
    public JFXDrawer drawer;
    public JFXTreeTableView<Clase_pilones>  jt_pilones;
    public JFXTreeTableView<Clase_tabacos> jt_clase_tabaco;


    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/resources/pantalla_principal.fxml"));

        Scene scene = new Scene(root);
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
        tabla_clase_tabaco();
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


        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(boton_menu);
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
        JFXTreeTableColumn<Clase_tabacos,String> _3 = new JFXTreeTableColumn<>("Operación");


        _1.setPrefWidth(50);
        _2.setPrefWidth(120);
        _3.setPrefWidth(100);

        jt_clase_tabaco.getColumns().addAll(_1,_2,_3);

        _1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_tabacos,String>("id_tabaco")
        );

        _2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_tabacos,String>("nombre_tbc")
        );

        Callback<TreeTableColumn<Clase_tabacos, String>, TreeTableCell<Clase_tabacos, String>> cellFactory
                = //
                new Callback<TreeTableColumn<Clase_tabacos, String>, TreeTableCell<Clase_tabacos, String>>() {
                    @Override
                    public TreeTableCell call(final TreeTableColumn<Clase_tabacos, String> param) {
                        final TreeTableCell<Clase_tabacos, String> cell = new TreeTableCell<Clase_tabacos, String>() {

                            final JFXButton btn = new JFXButton("Editar");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setButtonType(JFXButton.ButtonType.RAISED);
                                    btn.setOnAction(event -> {

                                            }
                                    );
                                    setAlignment(Pos.CENTER);
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        _3.setCellFactory(cellFactory);
    }
    private void tabla_pilones() {

        JFXTreeTableColumn<Clase_pilones,String> _1 = new JFXTreeTableColumn<>("ID");
        JFXTreeTableColumn<Clase_pilones,String> _2 = new JFXTreeTableColumn<>("Numero de Pilon");
        JFXTreeTableColumn<Clase_pilones,String> _3 = new JFXTreeTableColumn<>("Clase Tabaco");
        JFXTreeTableColumn<Clase_pilones,String> _4 = new JFXTreeTableColumn<>("Operación");


        _1.setPrefWidth(50);
        _2.setPrefWidth(50);
        _3.setPrefWidth(120);
        _4.setPrefWidth(100);

        jt_pilones.getColumns().addAll(_1,_2,_3,_4);

        _1.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_pilones,String>("id_pilon")
        );

        _2.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_pilones,String>("nombre_pilon")
        );

        _3.setCellValueFactory(
                new TreeItemPropertyValueFactory<Clase_pilones,String>("nombre_tbc")
        );

        Callback<TreeTableColumn<Clase_tabacos, String>, TreeTableCell<Clase_tabacos, String>> cellFactory
                = //
                new Callback<TreeTableColumn<Clase_tabacos, String>, TreeTableCell<Clase_tabacos, String>>() {
                    @Override
                    public TreeTableCell call(final TreeTableColumn<Clase_tabacos, String> param) {
                        final TreeTableCell<Clase_tabacos, String> cell = new TreeTableCell<Clase_tabacos, String>() {

                            final JFXButton btn = new JFXButton("Editar");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setButtonType(JFXButton.ButtonType.RAISED);
                                    btn.setOnAction(event -> {

                                            }
                                    );
                                    setAlignment(Pos.CENTER);
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        _3.setCellFactory(cellFactory);
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
}
