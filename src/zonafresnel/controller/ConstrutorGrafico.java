package zonafresnel.controller;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.*;
import java.awt.Color;
import java.awt.Dimension;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class ConstrutorGrafico {
    
    CalculoZonaFresnel calculoZonaFresnel = new CalculoZonaFresnel();
    
    public XYDataset createDataset(double torre1, double torre2, double raio, double frequencia, double distancia) {
        XYSeries P1 = new XYSeries("P1");
        XYSeries P2 = new XYSeries("P2");
        
        double x, y, d1, d2 = 0.0;
        double altura = 0.0;
        
        if(torre1 == torre2)
        {
            for(int i = 1; i < 100; i++)
            {
                x = (distancia / 100) * i;
                
                d1 = x;
                d2 = distancia - x;
                
                y = 550 * Math.sqrt((d1 * d2)/(distancia * frequencia)) + torre1; 
                
                P1.add(x, y);
                
                y = (y * -1) + (torre1 * 2);
                
                P2.add(x, y);
            }
        }
        else
        {
            for(int i = 1; i < 100; i++)
            {
                x = (distancia / 100) * i;
                
                d1 = x;
                d2 = distancia - x;
                
                altura = ((((torre1-torre2) * d2) / distancia) + torre2);

                y = 550 * Math.sqrt((d1 * d2)/(distancia * frequencia)) + altura; 
                
                P1.add(x, y);
                
                y = (y * -1) + (altura * 2);
                
                P2.add(x, y);
            }
        }
        
        XYSeriesCollection dataSet = new XYSeriesCollection();
        dataSet.addSeries(P1);
        dataSet.addSeries(P2);
            
        return dataSet;
    }

    public ChartPanel criarGrafico(double torre1, double torre2, double raio, double frequencia, double distancia) {

        NumberAxis xax = new NumberAxis("Distância [km]");
        NumberAxis yax = new NumberAxis("Altura [m]");
        XYSplineRenderer a = new XYSplineRenderer();
        a.setPrecision(10);
        
        XYPlot xyplot = new XYPlot(this.createDataset(torre1, torre2, raio, frequencia, distancia), xax, yax, a);
        
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
        
        renderer.setSeriesPaint(1, Color.red);
        renderer.setSeriesShapesVisible(0, Boolean.FALSE);
        renderer.setSeriesShapesVisible(1, Boolean.FALSE);
        renderer.setSeriesVisibleInLegend(0, Boolean.FALSE);
        renderer.setSeriesVisibleInLegend(1, Boolean.FALSE);
        
        JFreeChart chart = new JFreeChart(xyplot);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        
        chartPanel.setPreferredSize(new Dimension(400, 400));
        
        return chartPanel;
    }
    
}
