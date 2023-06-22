/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction.manipulation;

import mephi2023.english_auction.work_with_files.FileNamesStorage;
import mephi2023.english_auction.work_with_files.DataReader;
import mephi2023.english_auction.person.Person;
import mephi2023.english_auction.auction.Auction;
import mephi2023.english_auction.graph.LineChart;
import mephi2023.english_auction.graph.Dataset;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import mephi2023.english_auction.English_Auction;
import mephi2023.english_auction.MainDataOperations;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Kseny
 */
public class DataManipulation {
    static HistoryManipulation hm;
    public static int flag_button = 0;    
    LineChart demo;

    public double getMax_rate_of_price_increase() {
        return ModelingAuctionManipulation.getMax_rate_of_price_increase();
    }
    
    public DataManipulation(){        
        hm = new HistoryManipulation();
    }
    
    public void decreaseLot_id(){
        MainDataOperations.decreaseLot_id();
    }
    public int getLot_id(){
        return MainDataOperations.getLot_id();
    }
    public void setLot_id(int lot_id){
        MainDataOperations.setLot_id(lot_id);
    }
    
    public void setTempRadioButton(JRadioButton tempButton){
        hm.setTempRadioButton(tempButton);
    }
    
    private void loadData(){
        String fileName = FileNamesStorage.getVar6_fileName();
        
        //ЗАГРУЗКА ПАРАМЕТРОВ
        ArrayList<ArrayList<Object>> ar1 = null, ar2= null, ar3= null;
        try {            
            ar1 = DataReader.ReadXLSX(fileName, 0, 1);
            ar2 = DataReader.ReadXLSX(fileName, 1, 1);
            ar3 = DataReader.ReadXLSX(fileName,2, 2);
        } catch (IOException | InvalidFormatException ex) {
            Logger.getLogger(English_Auction.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 1 : имя/спекулянт/деньги/скупость/риск/самоуверенность
        // 2 : лот/чел - необх. в товаре
        // 3 : лот/древность/состояние/редкость : 0/2/4/6
        ar3.remove(5);
        ar3.remove(3);
        ar3.remove(1);
        
        MainDataOperations.initPersons(ar1, ar2);
        //РАСЧЁТ ПРЕДПОЛАГАЕМОЙ ЦЕНЫ
        MainDataOperations.initLots(ar3, FileNamesStorage.getPrice_fileName());
    }
    
    
    public boolean isPrevLots(){
        return hm.getPrevLots_id().size()>1;
    }
    
    
    public void initModelingAuction(boolean auto, int lot_id){        
        loadData();
        ModelingAuctionManipulation.startAuction(auto, lot_id);
        this.initializeDataset();
        hm.disappearTempRadioButton();
    }
    
    public int oneTourAuction(int numb_step){
        ModelingAuctionManipulation.priceAcceptabilityCalculation();
        ModelingAuctionManipulation.auctionEffectsCalculation();
        ModelingAuctionManipulation.emotionsCalculation();
        ModelingAuctionManipulation.activeParicipationDecision();
        ModelingAuctionManipulation.rateOfPriceIncreaseDecision();
        hm.setTemp_dataset_lot(hm.getTemp_dataset().createDataset(numb_step, Auction.getPrev_price()));
        return (numb_step+1);
    }
    
    public void initializeDataset(){        
        Dataset dataset = new Dataset("График для лота " + 
                (MainDataOperations.getLot_id()+1));
        XYDataset dataset_lot = dataset.createDataset(0,Auction.getZero_price());
        hm.addDatasets(dataset);
        hm.addDatasets_lots(dataset_lot);
        hm.addPrevLots_id(String.valueOf(this.getLot_id() + 1));
    }
    public void vizualizeDataset(){        
        demo = new LineChart("График для лота " + 
                (MainDataOperations.getLot_id()+1), hm.getTemp_dataset_lot());
        demo.pack();
        demo.setBounds(650, 100, 500, 400);
        //RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
    
    public void vizualizeDataset(int prevLot_id){        
        demo = new LineChart("График для лота " + 
                (hm.getPrevLots_id().get(prevLot_id)), hm.getDatasets_lots().get(prevLot_id));
        demo.pack();
        demo.setBounds(650, 100, 500, 400);
        //RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
    public void unvizualizeDataset(){
        if (demo != null) {
            demo.dispose();
        }
    }
    
    public Map<Integer, String> getNamesParticipants(){
        Map<Integer,String> namesPersons = new HashMap<Integer,String>();
        ArrayList<Person> persons = MainDataOperations.getPersons();
        for (int i = 0; i < persons.size(); ++i){
            namesPersons.put(i, persons.get(i).getName());
        }
        return namesPersons;
    }
    public Map<Integer, String> getNamesTourParticipants(){
        Map<Integer,String> namesPersons = new HashMap<Integer,String>();
        ArrayList<Person> persons = MainDataOperations.getParticipants();
        for (int i = 0; i < persons.size(); ++i){
            namesPersons.put(i, persons.get(i).getName());
        }
        return namesPersons;
    }
    
    public Map<Integer, String> getNamesPrevLots(){
        Map<Integer,String> namesPrevLots = new HashMap<Integer,String>();
        ArrayList<String> prevLots_is = hm.getPrevLots_id();
        for (int i = 0; i < prevLots_is.size(); ++i){
            namesPrevLots.put(i, prevLots_is.get(i));
        }
        return namesPrevLots;
    }
        
    public void modelingAuction(int lot_id){
        //int n = MainDataOperations.getPersons().size(); 
        int n = 3; 
        int numb_step = 1;
        
        //НАЧАЛО ЦИКЛА
        while(MainDataOperations.getCount_pass() != n){
            numb_step = oneTourAuction(numb_step);
        //КОНЕЦ ЦИКЛА
        }
        
        CalculateParamsManipulation.doNewRightScaleParameters();
        
        hm.addPrevLeaders(Auction.getLeader().getName());
        hm.addPrevPrices(Auction.getPrev_price());
        System.out.println(Auction.getLeader().getName());
        System.out.println(Auction.getPrev_price());
        //vizualizeDataset();
        System.out.println(hm.getDatasets().size());
        System.out.println(hm.getDatasets_lots().size());
    }
    
    public boolean checkEndAuction(int n){
        return MainDataOperations.getCount_pass() == n;
    }
    
    public void modelingManualAuction(int n, JLabel countStepInfoLabel){
        MainDataOperations.setManual_numb_step(oneTourAuction(MainDataOperations.getManual_numb_step()));
        if (MainDataOperations.getCount_pass() == (n-2)){
            countStepInfoLabel.setText("И раз...");
        }  else if (MainDataOperations.getCount_pass() == (n-1)){
            countStepInfoLabel.setText("И два...");            
        }  else if (MainDataOperations.getCount_pass() == n){
            countStepInfoLabel.setText("Продано!");
            CalculateParamsManipulation.doNewRightScaleParameters();
        }  else {
            countStepInfoLabel.setText("..."); 
        }
    }
    /*public void modelingManualAuction(int n, JLabel countStepInfoLabel){
        int lot_id = this.getLot_id();
        MainDataOperations.setManual_numb_step(oneTourAuction(MainDataOperations.getManual_numb_step()));
        if (MainDataOperations.getCount_pass() == (n-2)){
            countStepInfoLabel.setText("И раз...");
        }  else if (MainDataOperations.getCount_pass() == (n-1)){
            countStepInfoLabel.setText("И два...");            
        }  else if (MainDataOperations.getCount_pass() == n){
            countStepInfoLabel.setText("Продано!"); 
            changeCount_take_part();
            doInRightScaleParameters();
        }  else {
            countStepInfoLabel.setText("..."); 
        }
    }*/
    public String getLeaderName(){
        return Auction.getLeader().getName();
    }
    public double getLeaderPrice(){
        double count_after_dot = 1000;
        System.out.println((Math.round(Auction.getPrev_price()*count_after_dot)));
        return (Math.round(Auction.getPrev_price()*count_after_dot))/count_after_dot;
    }
    public String getPrevLeaderName(int id){
        return hm.getPrevLeaders().get(id);
    }
    public static double returnRightView(double value){
        double count_after_dot = 1000;
        return (Math.round(value*count_after_dot))/count_after_dot;
    }
    public double getPrevLeaderPrice(int id){
        return DataManipulation.returnRightView(hm.getPrevPrices().get(id));
    }
    public double getNowPrice(){
        
        return DataManipulation.returnRightView(Auction.getPrev_price());
    }
    
    
    public DefaultTableModel drawTableLotsModel(){
        return DrawManipulation.drawTableLotsModel(hm.getPrevPrices().size(), hm.getPrevLots_id(), hm.getPrevPrices(), hm.getPrevLeaders());
    }
    
    
    public static DefaultTableModel drawModel(int person_id){
        return DrawManipulation.drawModel(person_id);
    }
    
    
}
