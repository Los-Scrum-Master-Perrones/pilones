package ventanas;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyph;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static boolean ventana_splash = false;

    @Override
    public void start(Stage stage) throws Exception{

            StackPane root = FXMLLoader.load(getClass().getResource("/pantalla_principal.fxml"));

            JFXDecorator decorator = new JFXDecorator(stage, root,false,false,true);
           
            decorator.setGraphic(new SVGGlyph(""));

            stage.setTitle("Plasencia");
            stage.setMaximized(false);
            stage.setResizable(false);

            double width = 1200;
            double height = 780;

            Scene scene = new Scene(decorator, width, height);
            stage.setScene(scene);
            stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}


