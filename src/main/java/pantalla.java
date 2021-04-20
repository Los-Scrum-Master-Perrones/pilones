import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class pantalla extends Aplicacion_principal{
    public AnchorPane anchor;
    public AnchorPane anchor_panatalla;

    @Override
    public void init() throws Exception {
        super.init();
        anchor_panatalla.setStyle(" -fx-background-image: url(/presentacion_TAOPAR.jpg);");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);

    }
}
