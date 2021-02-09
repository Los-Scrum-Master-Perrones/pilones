package clases;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class pantalla_principal extends Aplicacion_principal implements Initializable {


    public StackPane stackpane;



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

    }
    private void loadSplashScreen() {
            {
                try {

                    Main.ventana_splash = true;

                    StackPane pane = FXMLLoader.load(getClass().getResource(("/resources/pantalla.fxml")));
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

}
