package DBUtilitie;


import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.data.category.DefaultCategoryDataset;


public class Main2 extends Application {

    static String[] DiasPorMes;

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
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

    @Override
    public void start(Stage stage) throws Exception {


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


            JFreeChart chart = BarChart.generateChart(createDataset(listadoValores), DiasPorMes,"Euros");

        ChartViewer viewer = new ChartViewer(chart);
        stage.setScene(new Scene(viewer));
        stage.setTitle("JFreeChart: Histogram");
        stage.setWidth(600);
        stage.setHeight(400);
        stage.show();

    }

    public static int numeroDeDiasMes(int mes){

        int numeroDias=-1;

        switch(mes){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                numeroDias=31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                numeroDias=30;
                break;
            case 2:

                Date anioActual=new Date();
                if(esBisiesto(1900 + anioActual.getYear())){
                    numeroDias=29;
                }else{
                    numeroDias=28;
                }
                break;

        }

        return numeroDias;
    }

    public static boolean esBisiesto(int anio) {

        GregorianCalendar calendar = new GregorianCalendar();
        boolean esBisiesto = false;
        if (calendar.isLeapYear(anio)) {
            esBisiesto = true;
        }
        return esBisiesto;

    }
}