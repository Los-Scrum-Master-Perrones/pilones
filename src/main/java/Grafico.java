import DBUtilitie.BarChart;
import DBUtilitie.DBType;
import DBUtilitie.DBUtilities;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Grafico extends Aplicacion_principal implements Initializable {
    public ChartViewer gbc_temperatura_pilon;
    static String[] DiasPorMes;
    public StackPane stackpane;
    public Label lbl_numero_pilon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Date hoy = new Date();
        try {

            PreparedStatement statement = Objects.requireNonNull(DBUtilities.getConnection(DBType.MARIADB)).prepareStatement("CALL traer_datos_grafico_temperatura(?,?)");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            statement.setString(1,lbl_numero_pilon.getText());
            statement.setString(2,format.format(hoy));

            int numero_registros_mes=0;
            int dias= numeroDeDiasMes(hoy.getMonth()+1);
            DiasPorMes = new String[dias];
            ArrayList listadoValores = new ArrayList();

            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                listadoValores.add(String.valueOf(resultSet.getInt(3)));
                numero_registros_mes++;

            }
            int dias22 =dias-numero_registros_mes;
            for(int i = 1;i<=dias22 ;i++){
                listadoValores.add(String.valueOf(79));
            }
            for(int i = 1;i<=dias ;i++){
                DiasPorMes[i-1] = "2021-03-"+i;
            }

            System.out.println(Arrays.toString(DiasPorMes));

            JFreeChart chart = BarChart.generateChart(createDataset(listadoValores), DiasPorMes,"Euros");
            gbc_temperatura_pilon.setChart(chart);
        }catch (SQLException | ClassNotFoundException e){
            mensaje("Excepcion", e.getMessage(), stackpane);
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);

        StackPane pane = FXMLLoader.load(getClass().getResource("/grafico.fxml"));

        Scene  scene = new Scene(pane);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Grafico");
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();
    }

    private static DefaultCategoryDataset createDataset(List<String> list)
    {
        DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();

        int mes = 0;

        for(String element : list) {
            defaultCategoryDataset.addValue(Double.parseDouble(element), "0",
                    (new StringBuilder(DiasPorMes[mes])).toString());
            mes++;
        }

        return defaultCategoryDataset;
    }

}
