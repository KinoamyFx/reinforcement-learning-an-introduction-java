package rlai;

import java.awt.*;

import javafx.scene.Scene;
import lombok.Getter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.data.xy.DefaultXYDataset;
import org.nd4j.linalg.api.ndarray.INDArray;

public class RLAIChart2D {

    @Getter
    private String title;
    private String xName;
    private String yName;

    private DefaultXYDataset dataset;

    public RLAIChart2D(String title, String xName, String yName) {
        this.title = title;
        this.xName = xName;
        this.yName = yName;
        this.dataset = new DefaultXYDataset();
    }

    public void addLine(String lineName, INDArray array) {
        double[][] data = new double[2][array.columns()];
        data[0] = array.getRow(0).data().asDouble();
        data[1] = array.getRow(1).data().asDouble();
        dataset.addSeries(lineName, data);
    }

    public void addLine(String lineName, double[][] data) {
        dataset.addSeries(lineName, data);
    }

    public Scene toScene() {
        JFreeChart chart = ChartFactory.createXYLineChart(title, xName, yName, dataset);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        plot.getDomainAxis().setLowerMargin(0.0);

        chart.getLegend().setFrame(BlockBorder.NONE);
        chart.getLegend().setHorizontalAlignment(HorizontalAlignment.CENTER);
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)r;
            renderer.setDefaultShapesVisible(false);
            renderer.setDrawSeriesLineAsPath(true);
            renderer.setAutoPopulateSeriesStroke(false);
            renderer.setDefaultStroke(new BasicStroke(2.0f));
        }
        ChartViewer viewer = new ChartViewer(chart);
        return new Scene(viewer);
    }
}
