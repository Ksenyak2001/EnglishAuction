/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction.manipulation;

import mephi2023.english_auction.graph.Dataset;
import java.util.ArrayList;
import javax.swing.JRadioButton;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Kseny
 */
public class HistoryManipulation {
    private ArrayList<String> prevLots_id;
    private ArrayList<String> prevLeaders;
    private ArrayList<Double> prevPrices;
    private ArrayList<Dataset> datasets;
    private ArrayList<XYDataset> datasets_lots;
    private Dataset temp_dataset;
    private XYDataset temp_dataset_lot;
    private JRadioButton tempRadioButton;
    
    public HistoryManipulation(){
        prevLots_id = new ArrayList<>();
        prevLeaders = new ArrayList<>();
        prevPrices = new ArrayList<>();
        datasets = new ArrayList<>();
        datasets_lots = new ArrayList<>();
    }
    
    public void disappearTempRadioButton(){
        tempRadioButton.setSelected(false);
        tempRadioButton.setVisible(false);
    }
    
    public JRadioButton getTempRadioButton() {
        return tempRadioButton;
    }

    public void setTempRadioButton(JRadioButton tempRadioButton) {
        this.tempRadioButton = tempRadioButton;
    }
    
    

    public ArrayList<Double> getPrevPrices() {
        return prevPrices;
    }

    public void setPrevPrices(ArrayList<Double> prevPrices) {
        this.prevPrices = prevPrices;
    }

    public void addPrevPrices(double price) {
        this.prevPrices.add(price);
    }
    
    public ArrayList<String> getPrevLeaders() {
        return prevLeaders;
    }

    public void setPrevLeaders(ArrayList<String> prevLeaders) {
        this.prevLeaders = prevLeaders;
    }

    public void addPrevLeaders(String leader) {
        this.prevLeaders.add(leader);
    }
    
    
    public ArrayList<String> getPrevLots_id() {
        return prevLots_id;
    }

    public void setPrevLots_id(ArrayList<String> datasets_lots) {
        this.prevLots_id = datasets_lots;
    }

    public void addPrevLots_id(String lots_id) {
        this.prevLots_id.add(lots_id);
    }
    
    
    public ArrayList<Dataset> getDatasets() {
        return datasets;
    }

    public void setDatasets(ArrayList<Dataset> datasets) {
        this.datasets = datasets;
    }

    public void addDatasets(Dataset dataset) {
        this.setTemp_dataset(dataset);
        this.datasets.add(dataset);
    }

    public ArrayList<XYDataset> getDatasets_lots() {
        return datasets_lots;
    }

    public void setDatasets_lots(ArrayList<XYDataset> datasets_lots) {
        this.datasets_lots = datasets_lots;
    }

    public void addDatasets_lots(XYDataset dataset_lot) {
        this.setTemp_dataset_lot(dataset_lot);
        this.datasets_lots.add(dataset_lot);
    }
    
    public Dataset getTemp_dataset() {
        return temp_dataset;
    }

    public void setTemp_dataset(Dataset temp_dataset) {
        this.temp_dataset = temp_dataset;
    }

    public XYDataset getTemp_dataset_lot() {
        return temp_dataset_lot;
    }

    public void setTemp_dataset_lot(XYDataset temp_dataset_lot) {
        this.temp_dataset_lot = temp_dataset_lot;
    }
    
}
