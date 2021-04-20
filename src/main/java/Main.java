import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;

public class Main extends Application {

    public static boolean ventana_splash = false;

    @Override
    public void start(Stage stage) throws Exception{


        try {

            StackPane root = FXMLLoader.load(getClass().getResource("/pantalla_principal.fxml"));


            stage.setTitle("Plasencia");
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);

            double width = 1260;
            double height = 670;

            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getCause().getMessage()
            );
        }
    }


    public static void main(String[] args) {
    try{

         launch(args);
    }catch (Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage()
        );
    }
    }
}


