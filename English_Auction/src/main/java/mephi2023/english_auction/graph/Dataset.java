/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction.graph;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Kseny
 */
public class Dataset {
    XYSeries series_lot; 
    public Dataset() {
        series_lot = new XYSeries("Series for lot");
    }
    public Dataset(String name) {
        series_lot = new XYSeries(name);
    }
    public XYDataset createDataset(int numb_step, double price) {
        series_lot.add( numb_step, price);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series_lot);                     
        return dataset;
    }
}
