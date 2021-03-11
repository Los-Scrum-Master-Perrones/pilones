import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Grafico extends Aplicacion_principal implements Initializable {
    public ChartViewer gbc_temperatura_pilon;
    static String[] DiasPorMes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Date hoy = new Date();

        int dias= numeroDeDiasMes(hoy.getMonth()+1);
        DiasPorMes = new String[31];
        ArrayList listadoValores = new ArrayList();

        for(int i = 1;i<=dias ;i++){
            int val = 80 + ((int) (Math.random() * 11 + 1));

            if(i<5) listadoValores.add(String.valueOf(val));
            else listadoValores.add(String.valueOf(80));
        }
        for(int i = 1;i<=dias ;i++){
            DiasPorMes[i-1] = "2021-03-"+i;

        }


        JFreeChart chart = DBUtilities.BarChart.generateChart(createDataset(listadoValores), DiasPorMes,"Euros");
        gbc_temperatura_pilon.setChart(chart);
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
