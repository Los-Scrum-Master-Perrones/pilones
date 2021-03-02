package DBUtilities;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.chart.fx.ChartViewer;

public class Pruba extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public static JFreeChart createChart() {

        double[] values = { 88,87
        };

        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", values, 1);

        JFreeChart histogram = ChartFactory.createHistogram("JFreeChart Histogram",
                "Data", "Frequency", dataset);

        return histogram;
    }

    @Override
    public void start(Stage stage) throws Exception {

        ChartViewer viewer = new ChartViewer(createChart());
        stage.setScene(new Scene(viewer));
        stage.setTitle("JFreeChart: Histogram");
        stage.setWidth(600);
        stage.setHeight(400);
        stage.show();
    }

}
