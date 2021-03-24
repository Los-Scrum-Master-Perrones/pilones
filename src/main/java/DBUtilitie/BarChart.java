package DBUtilitie;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.text.DecimalFormat;
import java.util.ArrayList;

import Objetos_POJO.Clase_control_temperatura;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.DefaultCategoryDataset;



public class BarChart {

    public static JFreeChart generateChart(DefaultCategoryDataset defaultCategoryDataset, String[] diasPorMes, String[] diasPorMes2, ArrayList<Clase_control_temperatura> lista, String ejeY)
    {
        boolean legend = false;
        boolean tooltips = false;
        boolean urls = false;

        JFreeChart chart = ChartFactory.createBarChart("", "", "", defaultCategoryDataset, PlotOrientation.VERTICAL, legend, tooltips, urls);

        System.out.println(chart.getCategoryPlot().getPlotType());
        chart.setBackgroundPaint(Color.WHITE);

        CategoryPlot plot = chart.getCategoryPlot();

        // color del fondo del histograma
        plot.setBackgroundPaint(Color.WHITE);
        // color de las líneas horizontales a partir del eje Y
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        // se oculta el recuadro del histograma
        plot.setOutlineVisible(false);
        // se elimina el margen entre los ejes y el chart
        plot.setAxisOffset(new RectangleInsets(0D, 0D, 0D, 0D));
        // se crea un bar renderer especial para mostrar cada "category" en una
        // sola barra con capas superpuestas
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

//      renderer.setBarPainter(new StandardBarPainter());
        renderer.setBarPainter(new CustomBarPainter());
        renderer.setShadowVisible(false);

        // color de las barras de facturas anteriores
        GradientPaint gradientGray = new GradientPaint(0.0F, 0.0F, Color.GRAY, 0.0F, 0.0F, new Color(208, 208, 208));
        renderer.setSeriesPaint(0, gradientGray);
        // color de la barra de última factura
        GradientPaint gradientRed = new GradientPaint(0.0F, 0.0F, Color.BLUE, 0.0F, 0.0F, new Color(26, 30, 114));
        renderer.setSeriesPaint(1, gradientRed);

        // se ajusta el ancho de las barras de última factura para que coincidan
        // en tamaño con la de última factura ya que al ser el renderer por
        // capas según se va incrementando la "category" la anchura de la barra
        // aumenta
        renderer.setItemMargin(-0.6D);

        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new CustomStandardCategoryItemLabelGenerator("{2}", new DecimalFormat("0"),diasPorMes2,lista));
        renderer.setDefaultItemLabelFont(new Font("Arial", Font.BOLD, 16));

        plot.setRenderer(renderer);

        CategoryAxis cAxis = plot.getDomainAxis();
        // se reducen los márgenes laterales entre el eje Y y las barras
        cAxis.setLowerMargin(0.01D);
        cAxis.setUpperMargin(0.01D);
        // se eliminan las marcas por categoría en el eje X
        cAxis.setTickMarksVisible(false);
        // color de la línea del eje X
        cAxis.setAxisLinePaint(Color.LIGHT_GRAY);
        // se rotan 45º las etiquetas del eje X para que no haya problema de
        // espacio
        cAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 9));
        cAxis.setLabelFont(new Font("Arial", Font.BOLD, 9));

        final NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        configurarRangeAxis(rangeAxis);

        return chart;
    }

    private static void configurarRangeAxis(NumberAxis rangeAxis) {
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setTickUnit(new NumberTickUnit(2));
        rangeAxis.setRange(79, 95);
    }
}