import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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


    public void soloNumerosyunPunto(final JFXTextField campo, int limite, int conPunto) {

        campo.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (campo.getText().length() == 0 && event.getCharacter().equals(" ")) {
                    event.consume();
                }
                if (campo.getText().length() == 0 && event.getCharacter().equals(".")) {
                    event.consume();
                }
                if (campo.getText().length() <= limite) {
                    if (!Character.isDigit(event.getCharacter().charAt(0)) && !(event.getCharacter().equals("."))) {
                        event.consume();
                    }
                    if (event.getCharacter().equals(".") && campo.getText().contains(".")) {
                        event.consume();
                    }

                } else {

                    if (event.getCharacter().equals(".") || campo.getText().contains(".")) {

                        if (campo.getText().length() <= conPunto) {
                            if ((!Character.isDigit(event.getCharacter().charAt(0)) && !(event.getCharacter().equals(".")))
                                    || (event.getCharacter().equals(".") && campo.getText().contains("."))) {
                                event.consume();
                            } else {

                            }
                        }else{
                            event.consume();
                        }


                    } else {
                        event.consume();
                    }
                }
            }
        });
    }








}
