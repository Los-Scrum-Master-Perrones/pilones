package clases;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Aplicacion_principal extends Application {


    JFXDialog dialogo = new JFXDialog();
    JFXButton btn_mensaje = new JFXButton("OK");
    @Override
    public void start(Stage primaryStage) throws Exception {

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
}
