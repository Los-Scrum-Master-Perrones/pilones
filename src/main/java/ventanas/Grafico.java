package ventanas;

import DBUtilitie.BarChart;
import DBUtilitie.DBType;
import DBUtilitie.DBUtilities;
import Objetos_POJO.Clase_control_temperatura;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.net.URL;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Grafico extends Aplicacion_principal implements Initializable {
    public ChartViewer gbc_temperatura_pilon;
    static String[] DiasPorMes,fecha_clones,mantenimiento, temperatura;
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

            lbl_anio = format_anio.format(hoy);
            lbl_mes = format_mes.format(hoy);

            int dias= numeroDeDiasMes(hoy.getMonth()+1);

            DiasPorMes = new String[dias];
            fecha_clones = new String[dias];
            mantenimiento = new String[dias];
            temperatura = new String[dias];

            for(int t = 0 ; t<temperatura.length ; t++ ){
                temperatura[t] = "79";
            }


            ArrayList<Clase_control_temperatura> lista = new ArrayList<>();
            ArrayList listadoValores = new ArrayList();

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                lista.add(new Clase_control_temperatura(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)));

                System.out.println( resultSet.getString(1)+" "+
                        resultSet.getString(2)+" "+
                        resultSet.getString(3)+" "+
                        resultSet.getString(4)+" "+
                        resultSet.getString(5));
            }


            for(int i = 1;i<=dias ;i++){
                DiasPorMes[i-1] = i+"";
                fecha_clones[i-1] = lbl_anio+"-"+lbl_mes+"-"+i;
            }

            for(int i = 0;i<lista.size() ;i++){
                for(int ii = 0;ii<fecha_clones.length ;ii++){
                    if(lista.get(i).getFecha_revision_temp().equals(fecha_clones[ii])){
                        temperatura[ii] = lista.get(i).getTemperatura();
                    }
                }
            }

            for(int i = 0;i<temperatura.length ;i++){
                listadoValores.add(temperatura[i]);
            }


            System.out.println(Arrays.toString(temperatura));
            System.out.println(Arrays.toString(fecha_clones));
            System.out.println(Arrays.toString(DiasPorMes));


           JFreeChart chart = BarChart.generateChart(createDataset(listadoValores), DiasPorMes,fecha_clones,lista,"Euros");
           gbc_temperatura_pilon.setChart(chart);
        }catch (SQLException | ClassNotFoundException e){
            mensaje("Excepcion", e.getMessage(), stackpane);
        }

    }


}
