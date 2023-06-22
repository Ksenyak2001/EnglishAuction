/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Kseny
 */
public class LineChart extends ApplicationFrame {
    //private static final long serialVersionUID = 1L;

    public LineChart(final String title, XYDataset dataset) {
        super(title);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 480));
        setContentPane(chartPanel);
    }

    private JFreeChart createChart(XYDataset dataset) {
        final JFreeChart chart = ChartFactory.createXYLineChart(
                        "График изменения цены", null, null, dataset,
                        PlotOrientation.VERTICAL, true, false, false);

        chart.setBackgroundPaint(Color.white);

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(new Color(232, 232, 232));

        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint (Color.gray);

        // Определение отступа меток делений
        plot.setAxisOffset(new RectangleInsets (1.0, 1.0, 1.0, 1.0));

        // Скрытие осевых линий
        plot.getDomainAxis().setAxisLineVisible (false);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        // Удаление меток
        renderer.setSeriesShapesVisible (0, false);

        // Настройка графика (цвет, ширина линии)
        renderer.setSeriesPaint  (0, Color.orange);
        renderer.setSeriesStroke (0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
        
        // Настройка NumberAxis
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        
        rangeAxis.setAxisLineVisible (false);
        rangeAxis.setStandardTickUnits(NumberAxis
                                      .createIntegerTickUnits());
        return chart;
    }
}
