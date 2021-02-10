package clases;

import clases.DBUtilities.ActualizarTablas;
import clases.DBUtilities.modificaciones;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class pantalla_principal extends Aplicacion_principal implements Initializable, modificaciones, ActualizarTablas {



    public StackPane stackpane;
    public JFXHamburger boton_menu;
    public JFXDrawer drawer;


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
