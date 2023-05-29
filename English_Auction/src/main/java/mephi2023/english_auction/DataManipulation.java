/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi2023.english_auction;

import mephi2023.english_auction.auction.HistoryManipulation;
import mephi2023.english_auction.work_with_files.FileNamesStorage;
import mephi2023.english_auction.work_with_files.DataReader;
import mephi2023.english_auction.person.Person;
import mephi2023.english_auction.auction.Auction;
import mephi2023.english_auction.counting.CountingForPerson;
import mephi2023.english_auction.counting.CountingForLotWithPerson;
import mephi2023.english_auction.graph.LineChart;
import mephi2023.english_auction.graph.Dataset;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import mephi2023.english_auction.lot.Lot;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Kseny
 */
public class DataManipulation {
    HistoryManipulation hm;
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
    
    private void startAuction(boolean auto, int lot_id){
        MainDataOperations.setAuto(auto);
        MainDataOperations.setLot_id(lot_id);
        MainDataOperations.setCount_pass(0);
        Auction.init(MainDataOperations.getLots().get(lot_id).getNow_price()); 
    }
    
    private void priceAcceptabilityCalculation(){
        //РАСЧЁТ ПРИЕМЛИМОСТИ ЦЕНЫ
        ArrayList<Person> persons = MainDataOperations.getPersons();
        Lot lot = MainDataOperations.getLots().get(MainDataOperations.getLot_id());
        for (Person p : persons){
            CountingForLotWithPerson.countAcceptabilityPrice(FileNamesStorage.getAcceptability_fileName(), lot, p);
            System.out.println("=====");
        }
    }
    
    private void auctionEffectsCalculation(){
        //БЛОК РАСЧЁТА ЭФФЕКТОВ АУКЦИОНА
        //активность игроков, текущая-стартовая цены, как быстро растёт цена
        System.out.println(Auction.getActivity());
        System.out.println(Auction.getAuction_growth());
        System.out.println(MainDataOperations.getLots().get(MainDataOperations.getLot_id()).getNow_price()-Auction.getZero_price());
    }
    
    private void emotionsCalculation(){
        //БЛОК РАСЧЁТА ЭМОЦИЙ
        System.out.println(":) :| :(");
        ArrayList<Person> persons = MainDataOperations.getPersons();
        Lot lot = MainDataOperations.getLots().get(MainDataOperations.getLot_id());
        for (Person p : persons){
            //System.out.println(lots.get(lot_id).getPrev_price());
            System.out.print("Страх бедности : ");
            CountingForPerson.countFear_of_poverty(FileNamesStorage.getFear_of_poverty_fileName(), p);
            System.out.print("Неуверенность в товаре : ");
            CountingForLotWithPerson.countLackOfConfidence( FileNamesStorage.getLack_of_confidence_fileName(), lot, p);
            System.out.print("Уверенность в себе : ");
            CountingForLotWithPerson.countAssurance( FileNamesStorage.getAssurance_fileName(), lot, p);
            // ...<3            
            System.out.print("Азарт : ");
            CountingForPerson.countExcitement( FileNamesStorage.getExcitement_fileName(),  p);
            System.out.print("Необходимость в товаре : ");
            CountingForLotWithPerson.countNeedLot(lot, p);
            System.out.println(p.getNeed_one_lot());
            System.out.print("Приемлимость цены : ");
            System.out.println(p.getAcceptability());
            System.out.print("Страх потери : ");
            CountingForPerson.countFear_of_loss( FileNamesStorage.getFear_of_loss_fileName(),  p);
            System.out.println("=====");
        }    
    }
    
    private void activeParicipationDecision(){
        //РЕШЕНИЕ ОБ АКТИВНОМ УЧАСТИИ
        double F=0, U=0, S=0, E=0, N=0, A=0, L=0;
        double M = 0, Value_of_Activity = 23;
        ArrayList<Person> persons = MainDataOperations.getPersons();
        ArrayList<Person> participants = new ArrayList<>();
        for (Person p : persons){
            F = p.getFear_of_poverty();
            U = p.getLack_of_confidence();
            S = p.getAssurance();
            E = p.getExcitement();
            N = p.getNeed_one_lot();
            A = p.getNeed_one_lot();
            L = p.getFear_of_loss();
            M = F + U + S - E - N + A - L;
            if ((Value_of_Activity - M)<0 || p.isFlag_leader()){
                p.setFlag_leader(false);
                // не участвует
            } else {                
                // участвует
                participants.add(p);
            }
        }
        MainDataOperations.setParticipants(participants);
        
        //БЛОК РАСЧЁТА ЭФФЕКТОВ АУКЦИОНА
        //кол-во игроков участвующих
        Auction.setCount_participants(participants.size());
        System.out.println(participants.size());
    }
    
    private void foundLeader(double max_rate_of_price_increase){
        //ОПРЕДЕЛЕНИЕ ПОБЕДИТЕЛЯ
        ArrayList<Person> participants = MainDataOperations.getParticipants();
        Lot lot = MainDataOperations.getLots().get(MainDataOperations.getLot_id());
        for (Person p : participants){
            if (p.getRate_of_price_increase() == max_rate_of_price_increase){
                System.out.println(p.getName() +" : "+ p.getRate_of_price_increase());
                double now_price = lot.getNow_price();
                System.out.println(now_price);
                p.setFlag_leader(true);                
                Auction.setLeader(p);
                Auction.setAvg_grow_prev2_price(Auction.getAvg_grow_prev_price());
                Auction.setAvg_grow_prev_price(p.getRate_of_price_increase());
                Auction.setPrev_price(now_price);
                lot.setPrev_price(now_price);
                lot.setNow_price(now_price*max_rate_of_price_increase);
                break;
            }
        }        
    }
    
    private void checkParticipants(){
        ArrayList<Person> participants = MainDataOperations.getParticipants();
        if (participants.isEmpty()){
            MainDataOperations.addCount_pass();
        } else {
            MainDataOperations.setCount_pass(0);
        }
        System.out.println("=====");
    }
    
    private void rateOfPriceIncreaseDecision(){
        //РЕШЕНИЕ ЗАДАЧИ РАСЧЁТА ПОВЫШЕНИЯ ЦЕН
        ArrayList<Person> participants = MainDataOperations.getParticipants();
        double max_rate_of_price_increase = -1;
        for (Person p : participants){
            System.out.print("Степень повышения цены : ");
            CountingForPerson.countRate_of_price_increase( FileNamesStorage.getRate_of_price_increase_fileName(),  p);
            if (p.getRate_of_price_increase() > max_rate_of_price_increase){
                max_rate_of_price_increase = p.getRate_of_price_increase();
            }
        }
        System.out.println("=====");
        foundLeader(max_rate_of_price_increase);
        System.out.println(Auction.getLeader().getName());
        checkParticipants();
    }
    
    private void initializeDataset(){        
        Dataset dataset = new Dataset("График для лота " + 
                (MainDataOperations.getLot_id()+1));
        XYDataset dataset_lot = dataset.createDataset(0,0);
        hm.addDatasets(dataset);
        hm.addDatasets_lots(dataset_lot);
    }
    LineChart demo;
    public void vizualizeDataset(){        
        demo = new LineChart("График для лота " + 
                (MainDataOperations.getLot_id()+1), hm.getTemp_dataset_lot());
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
    public void unvizualizeDataset(){
        demo.dispose();
    }
    
    public Map<Integer, String> getNamesParticipants(){
        Map<Integer,String> namesPersons = new HashMap<Integer,String>();
        ArrayList<Person> persons = MainDataOperations.getPersons();
        for (int i = 0; i < persons.size(); ++i){
            namesPersons.put(i, persons.get(i).getName());
        }
        return namesPersons;
    }
    public DefaultTableModel drawModel(int person_id){
        ArrayList<Person> persons = MainDataOperations.getPersons();
        Person person = persons.get(person_id);

        ArrayList<String> names = new ArrayList<>();
        names.add("Благосостояние");
        names.add("Необходимость в лоте");
        names.add("Скупость");
        names.add("Тип участника");
        names.add("Самоуверенность");
        names.add("Желание рисковать");
        names.add("Приемлимость цены");
        names.add("Страх бедности");
        names.add("Неуверенности в товаре");
        names.add("Азарт");
        names.add("Уверенность в себе");
        names.add("Страх потери");
        
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(person.getWelfare());
        parameters.add(person.getNeed_one_lot());
        parameters.add(person.getStinginess());
        parameters.add(person.getPlayer_type());
        parameters.add(person.getSelf_confidence());
        parameters.add(person.getRisk_appetite());
        parameters.add(person.getAcceptability());
        parameters.add(person.getFear_of_poverty());
        parameters.add(person.getLack_of_confidence());
        parameters.add(person.getExcitement());
        parameters.add(person.getAssurance());
        parameters.add(person.getFear_of_loss());
        
        return getModel(parameters, names);
    }
    private DefaultTableModel getModel(ArrayList<Object> parameters, 
            ArrayList<String> names){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Параметр");
        model.addColumn("Значение");
        
        
        for (int i = 0; i < names.size(); i++) {
            Object[] values = new Object[names.size()+1];
            values[0] = names.get(i);
            values[1] = parameters.get(i);
            model.addRow(values);
        }
        return model;
    }
    
    public void initModelingAuction(boolean auto, int lot_id){        
        loadData();
        startAuction(auto, lot_id);
        initializeDataset();
        hm.disappearTempRadioButton();
    }
    
    public int oneTourAuction(int numb_step){
        priceAcceptabilityCalculation();
        auctionEffectsCalculation();
        emotionsCalculation();
        activeParicipationDecision();
        rateOfPriceIncreaseDecision();
        hm.setTemp_dataset_lot(hm.getTemp_dataset().createDataset(numb_step, Auction.getPrev_price()));
        return (numb_step+1);
    }
    
    public void modelingAuction(boolean auto, int lot_id){
        int n = MainDataOperations.getPersons().size(), numb_step = 1;
        
        //НАЧАЛО ЦИКЛА
        while(MainDataOperations.getCount_pass() != n){
            numb_step = oneTourAuction(numb_step);
        //КОНЕЦ ЦИКЛА
        }
        System.out.println(Auction.getLeader().getName());
        System.out.println(Auction.getPrev_price());
        //vizualizeDataset();
    }
    public String getLeaderName(){
        return Auction.getLeader().getName();
    }
    public double getLeaderPrice(){
        double count_after_dot = 1000;
        System.out.println((Math.round(Auction.getPrev_price()*count_after_dot)));
        return (Math.round(Auction.getPrev_price()*count_after_dot))/count_after_dot;
    }
}
