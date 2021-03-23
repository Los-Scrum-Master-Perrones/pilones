package ventanas;

import DBUtilitie.BarChart;
import DBUtilitie.DBType;
import DBUtilitie.DBUtilities;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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
    static String[] DiasPorMes,fecha_clones;;
    public StackPane stackpane;
    public Label lbl_numero_pilon;
    public String lbl_anio = "2021";
    public String lbl_mes = "03";
    public Label lbl_mes_grafico;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
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

    public void datos_grafica(Date hoy){

        try {

            PreparedStatement statement = Objects.requireNonNull(DBUtilities.getConnection(DBType.MARIADB)).prepareStatement("CALL traer_datos_grafico_temperatura(?,?)");

            SimpleDateFormat format_anio = new SimpleDateFormat("yyyy");
            SimpleDateFormat format_mes= new SimpleDateFormat("MM");
            SimpleDateFormat format_mes_palabra= new SimpleDateFormat("MMMM");

            lbl_mes_grafico.setText(format_mes_palabra.format(hoy).toUpperCase()+" "+format_anio.format(hoy));

            statement.setString(1,lbl_numero_pilon.getText());
            statement.setString(2,format_anio.format(hoy)+"-"+format_mes.format(hoy)+"-"+1);

            System.out.println(format_anio.format(hoy)+"-"+format_mes.format(hoy)+"-"+1);
            int numero_registros_mes=0;
            int dias= numeroDeDiasMes(hoy.getMonth()+1);

            DiasPorMes = new String[dias];
            fecha_clones = new String[dias];
            ArrayList listadoValores = new ArrayList();

            ResultSet resultSet =statement.executeQuery();

            while (resultSet.next()){
                listadoValores.add(String.valueOf(resultSet.getInt(3)));

                System.out.println(resultSet.getInt(5)+"hola");
                numero_registros_mes++;
            }

            int dias22 =dias-numero_registros_mes;
            for(int i = 1;i<=dias22 ;i++){
                listadoValores.add(String.valueOf(79));
            }
            for(int i = 1;i<=dias ;i++){
                DiasPorMes[i-1] = i+"";
                fecha_clones[i-1] = lbl_anio+"-"+lbl_mes+"-"+i;
            }

            System.out.println(Arrays.toString(DiasPorMes));



            JFreeChart chart = BarChart.generateChart(createDataset(listadoValores), DiasPorMes,fecha_clones,"Euros");
            gbc_temperatura_pilon.setChart(chart);
        }catch (SQLException | ClassNotFoundException e){
            mensaje("Excepcion", e.getMessage(), stackpane);
        }

    }


}
