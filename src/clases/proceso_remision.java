package clases;

import clases.DBUtilities.DBType;
import clases.DBUtilities.DBUtilities;
import clases.Objetos_POJO.Clase_tabacos;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class proceso_remision extends Aplicacion_principal implements Initializable {

    public StackPane stackpane;
    
    public Label id_remision;
    public Label lbl_id_remision;
    public Label numero_remision;
    public TextField txt_numero_remision;
    public Label fecha_remision;
    public DatePicker date_fecha_remision;
    public Label origen_remision;
    public TextField txt_origen_remision;
    public Label destino_remision;
    public TextField txt_destino_remision;
    public Label total_libras;
    public TextField txt_total_libras;
    public Label total_remision;
    public TextField txt_total_remision;
    public Button btn_guardar;
    public Button btn_actualizar;
    public TextField txt_buscar_descripcion;
    public Label buscar_descripcion;
    public JFXTreeTableView jt_descripcion;


    @Override
    public void start(Stage primaryStage) throws Exception{
        super.start(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/resources/proceso_remision.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setHeight(400);
        stage.setWidth(537);
        stage.setTitle("Proceso de Remisión");
        stage.show();
}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // para crear la tabla
        JFXTreeTableColumn _1 = new JFXTreeTableColumn("Descripción");
        _1.setPrefWidth(472);


    }

    public void guardar(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Object[] campos = {txt_numero_remision,date_fecha_remision,txt_origen_remision,txt_destino_remision,
                txt_buscar_descripcion,txt_total_libras,txt_total_remision};

        String[] datos = new String[campos.length];
        int contador = 0;

        for (Object o: campos){
            if (o instanceof TextField){
                datos[contador] = ((TextField)o).getText();
            }else if(o instanceof JFXDatePicker){
                datos[contador] = String.valueOf(((JFXDatePicker)o).getValue());
            //}else if(o instanceof Label) {
                //datos[contador] = ((Label) o).getText();
            //} else if(o instanceof ComboBox){
                //datos[contador] = String.valueOf(((ComboBox)o).getValue());

            } contador ++;
        }
        System.out.println(Arrays.toString(datos));
        PreparedStatement consulta = DBUtilities.getConnection(DBType.MARIADB).
                prepareStatement("call insertar_tabla_pilon(?,?,?,?,?,?,?)");


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
            public void handle(ActionEvent event) {
                dialogo.close();

            }
        });

        mensaje("Mensaje",mensaje[0],stackpane );







    }
}
