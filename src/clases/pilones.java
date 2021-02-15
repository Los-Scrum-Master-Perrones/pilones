package clases;

import clases.DBUtilities.DBType;
import clases.DBUtilities.DBUtilities;
import clases.Objetos_POJO.Clase_tabacos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class pilones extends Aplicacion_principal implements Initializable {

    public StackPane stackpane;
    public Label lbl_idpilones;
    public Label lbl_id_pilones;
    public Label lbl_numero_pilon;
    public TextField txt_numero_pilon;
    public Label lbl_idtabaco;
    public Label lbl_id_tabaco;
    public Button btn_guardar_pilones;
    public Button btn_actualizar_pilones;
    public Label lbl_clase_tabaco;
    public ComboBox<Clase_tabacos> cbb_clase_tabaco;
    public StackPane stack_pilones;


    @Override
    public void start(Stage primaryStage) throws Exception{
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/resources/pilones.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setHeight(250);
        stage.setWidth(450);
        stage.setTitle("Pilones");
        stage.show();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardar(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Object[] campos = {txt_numero_pilon};

        String[] datos = new String[campos.length];
        int contador = 0;

        for (Object o: campos){
             if (o instanceof TextField){
                datos[contador] = ((TextField)o).getText();
           }//else if(o instanceof Integer){
            //datos[contador]= String.valueOf(((int)o));
            // }
           }
        PreparedStatement consulta = Objects.requireNonNull(DBUtilities.getConnection(DBType.MARIADB)).
                prepareStatement("call insertar_pilones(?)");

        for(int i= 0;i<datos.length;i++){
            consulta.setString(i+1,datos[i]);
        }

        ResultSet respuesta = consulta.executeQuery();

        String mensaje[]= new String[2];
        while (respuesta.next()){
            mensaje[0]= respuesta.getString(1);
            mensaje[1]= respuesta.getString(2);
        }

        btn_mensaje.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { dialogo.close(); }
        });

        mensaje("Mensaje",mensaje[0],stack_pilones );


    }

}
