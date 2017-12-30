package rlai;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;
import org.nd4j.linalg.api.ndarray.INDArray;

public class Rlai2DChart extends Application {

    private String title;
    private String xName;
    private String yName;

    private JFreeChart chart;
    private DefaultXYDataset dataset;
    private Map<String, INDArray> lines = new HashMap<>();

    public Rlai2DChart(String title, String xName, String yName) {
        this.title = title;
        this.xName = xName;
        this.yName = yName;
    }

    @Override
    public void start(Stage primaryStage) {
        chart = ChartFactory.createXYLineChart(title, xName, yName, dataset);
    }

    public void addLine(String lineName, INDArray array) {
        //dataset.addSeries(lineName,);
        lines.put(lineName, array);
    }
}
